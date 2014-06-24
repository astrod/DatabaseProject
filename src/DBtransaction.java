import java.sql.*;

public class DBtransaction {
	public boolean sendMoney(String user1, String user2, int money) throws Exception {
		if(money < 0) {
			System.err.println("money is under zero");
			return false;
		}
		
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String sql;
		PreparedStatement pstmt;
		
		
		String addr = "jdbc:mysql://10.73.45.60/nextdb";
		String user = "jongun";
		String pw = "next123!@#";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");			
		} catch(ClassNotFoundException e) {
			System.err.println("Driver Error" + e.getMessage());
			return false;
		}
		System.out.println("Driver Loading Success");
		
		try {
			conn = DriverManager.getConnection(addr, user, pw);
			System.out.println("Connect Success");
			conn.setAutoCommit(false);
			//start transaction;
			pstmt = conn.prepareStatement("BEGIN;");
			pstmt.execute();
			pstmt.close();
			
			pstmt = conn.prepareStatement("update sendMoney set money = money - ? WHERE userid = ?");
			pstmt.setInt(1, money);
			pstmt.setString(2, user1);
			pstmt.execute();
			pstmt.close();
			
			pstmt = conn.prepareStatement("select money from sendMoney where userid = ?");
			pstmt.setString(1, user1);
			pstmt.execute();
			
			rs = pstmt.getResultSet();
			rs.next();
			
			int resultMoney = rs.getInt("money");
			pstmt.close();
			
			if(resultMoney < 0) {
				System.out.println("잔고미달 ");
				
				pstmt = conn.prepareStatement("ROLLBACK");
				pstmt.execute();
				pstmt.close();
			}
			else {
				pstmt = conn.prepareStatement("update sendMoney set money = money + ? WHERE userid = ?");
				pstmt.setInt(1, money);
				pstmt.setString(2, user2);
				pstmt.execute();
				pstmt.close();
				
				pstmt = conn.prepareStatement("COMMIT;");
				pstmt.execute();
				pstmt.close();
				System.out.println("이체완료 ");
			}
			
		} catch(SQLException e) {
			System.err.println("DB Error : " + e.getMessage());
			return false;
		}
		
		
	
		return false;
	}

	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		DBtransaction t = new DBtransaction();
		t.sendMoney("a1", "a2", 100);
	}

}
