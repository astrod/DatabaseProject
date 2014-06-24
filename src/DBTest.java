import java.sql.*; //java.sql 커넥션을 사용하겠다.

public class DBTest {
	public static void main(String[] args) throws SQLException {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String sql;
		PreparedStatement pstmt;
		

		
		
		String addr = "jdbc:mysql://10.73.44.20/phouse";
		String user = "popi";
		String pw = "db1004";
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //라이브러리에 있는 이걸 사용하겠다는 의미임. 런타임때 뭘 쓸지를 결정하는 것임.
			
		} catch(ClassNotFoundException e) {
			System.err.println("Driver Error" + e.getMessage());
			return;
		}
		System.out.println("Driver Loading Success");
		
		try {
			conn = DriverManager.getConnection(addr, user, pw);
			System.out.println("Connect Success");
			stmt = conn.createStatement();
			sql = "select * from test1"; //select 안에 string 쿼리를 집어넣음 
			rs = stmt.executeQuery(sql); //쿼리를 excuteQuery로 실행함. 리턴은 resultset을 리턴함. 이건 첫 번째 레코드를 가리키는 게 아니라 한번 next를 사용해야 다음 걸로 가게 됨.
			
			
			
			while(rs.next()) {
				int x = rs.getInt(1); //컬럼의 위치로 가져오는 것 
				String str = rs.getString("name"); //컬럼의 이름으로 가져오는 것  
				String date = rs.getString(3);
				System.out.printf("%d : %s : %s \n", x, str, date);
			}
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			System.err.println("DB Error : " + e.getMessage());
			return;
		}
		//stmt.execute("insert into heesu ~"); 이걸 가지고 insert를 할 수 있음.
		//stmt.executeUpdate("update heesu set name = 'heesu'); 이게 업데이트하는 쿼리임.
		
		String[] a = {"정희수", "남세현", "서동유", "장택순"};
		String temp = "insert into heesu (id, name) values (?, ?)";
		pstmt = conn.prepareStatement(temp);
		
		int i =0;
		for(String str:a) {
			pstmt.setInt(1, i);
			pstmt.setString(2, str);
			pstmt.execute();
			i++;
		}
		
		
	}
}


