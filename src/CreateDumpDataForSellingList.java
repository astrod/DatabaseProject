import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class CreateDumpDataForSellingList {

	public static String getSellingNum() {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		  buffer.append("7");
		  for(int i=0; i<8; i++) {
			  int tempNum = random.nextInt(10);
			  buffer.append(tempNum);
		  }
		  return buffer.toString();
	}
	
	public static String getBuyDate() {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		  int month = random.nextInt(12)+1;
		  if(month < 10) {
			  buffer.append("200" + random.nextInt(10) + "-" + 0 + month + "-");
		  }
		  else {
			  buffer.append("200" + random.nextInt(10) + "-" + month + "-");
		  }
		  int day = random.nextInt(27)+1;
		  buffer.append(day);
		  
		  return buffer.toString();
	}
	
	public static String getOrderMethod() {
	  Random random = new Random();
	  int checkNum = random.nextInt(4);
	  if(checkNum == 0) {
		  return "신용카드";
	  } else if(checkNum == 1) {
		  return "실시간 계좌이체";
	  } else if(checkNum == 2) {
		  return "무통장입금";
	  } else {
		  return "휴대폰 결제";
	  }
	}
	
	public static int getSellingCost() {
		Random random = new Random();
		int returnVal = random.nextInt(5000) + 5000;
		return returnVal;
	}
	
	
	public static void main(String[] args) throws SQLException {
		CreateDumpDataForSellingList Dump = new CreateDumpDataForSellingList();
		StringBuffer buffer = new StringBuffer();
		
		Connection conn=null;
	    Statement stmt=null;
	    ResultSet rs=null;
		ResultSet rsBook = null;
		String sql;
		PreparedStatement pstmtBook;
		PreparedStatement pstmtQuery = null;
		
		
		

		String addr = "jdbc:mysql://10.73.45.60/nextdb";
		String user = "jongun";
		String pwd = "next123!@#";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver Error" + e.getMessage());
		}
		System.out.println("Driver Loading Success");
		
		try {
			conn = DriverManager.getConnection(addr, user, pwd);
			System.out.println("Connect Success");
		
			int firstUniNum = 1;
			int secondUniNum = 2;
			String ISBN;
			
			pstmtBook = conn.prepareStatement("select ISBN from Book;");
			pstmtBook.execute();
			rsBook = pstmtBook.getResultSet();
			
			for(int i=0; i<500; i++) {
				rsBook.next();	
				ISBN = rsBook.getString("ISBN");
				buffer.append(ISBN);
			}
			pstmtBook.close();

			int first = 0;
			int end = 13;
			

			for(int i=0; i<500; i++) {
				
				String Book_ISBN = buffer.substring(first, end);
				
				pstmtQuery = conn.prepareStatement("insert into SellingList values (?, ?, ?, ?, ?, ?, ?, ?);" );
				pstmtQuery.setInt(1, firstUniNum);
				pstmtQuery.setInt(2, secondUniNum);
				pstmtQuery.setString(3, Dump.getSellingNum());
				pstmtQuery.setString(4, Book_ISBN);
				pstmtQuery.setString(5, Dump.getBuyDate());
				pstmtQuery.setString(6, Dump.getOrderMethod());
				pstmtQuery.setInt(7, Dump.getSellingCost());
				pstmtQuery.setString(8, "selling");
				pstmtQuery.execute();
				firstUniNum++;
				secondUniNum++;
				first += 13;
				end += 13;
				pstmtQuery.close();
			}
			
			pstmtQuery.close();

			
			
		}  catch (Exception e) {
				System.err.println("Driver Error2" + e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(stmt != null)stmt.close();
			if(conn != null) conn.close();
		}
	}

}
