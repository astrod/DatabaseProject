import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class CreateDumpDataForBook extends createDumpDataCode {

	public static String getISBN() {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		  buffer.append("9");
		  for(int i=0; i<12; i++) {
			  int tempNum = random.nextInt(10);
			  buffer.append(tempNum);
		  }
		  return buffer.toString();
	}
	
	public static String getWeightAndPage() {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		  int check = random.nextInt(3);
		  if(check == 0 || check == 1) {
			  buffer.append(random.nextInt(9)+1);
			  for(int i =0; i<2; i++) {
				  buffer.append(random.nextInt(10));
			  }
		  }
		  else {
			  buffer.append(random.nextInt(2)+1);
			  for(int j =0; j<3; j++) {
				  buffer.append(random.nextInt(10));
			  }
		  }
		  
		  return buffer.toString();
		  
	}
	
	public static String getyanggang() {
		  Random random = new Random();
		  int checkNum = random.nextInt(2); 
		  if(checkNum == 0)
		  {
			  return "양장본";
		  }
		  else {
			  return "반양장본";
		  }
	}
	
	public static int getOriginPrice() {
		Random random = new Random();
		int returnVal = random.nextInt(10000) + 10000;
		return returnVal;
	}
	
	public static String getPublishDate() {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		  int month = random.nextInt(12)+1;
		  if(month < 10) {
			  buffer.append("199" + random.nextInt(10) + "-" + 0 + month + "-");
		  }
		  else {
			  buffer.append("199" + random.nextInt(10) + "-" + month + "-");
		  }
		  int day = random.nextInt(27)+1;
		  buffer.append(day);
		  
		  return buffer.toString();
	}
	
	public static String getField() {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		 
		  String chars[] = 
		    "가정,건강,경제경영,고전,과학,대학교재,만화,사전,사회과학,소설,수험서,어린이,에세이,여행,역사,예술,외국어,유아,인문학,컴퓨터,종교".split(",");
		 
		  buffer.append(chars[random.nextInt(chars.length)]);
		
		  return buffer.toString();
	}
	
	public static void main(String[] args) throws SQLException {
		CreateDumpDataForBook Dump = new CreateDumpDataForBook();
		  Random random = new Random();
		  int boundrayRandom = random.nextInt(6) + 3;
		
		Connection conn=null;
	    Statement stmt=null;
	    ResultSet rs=null;
		String sql;
		PreparedStatement pstmt;

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
			for(int i=0; i<1000; i++) {
				pstmt = conn.prepareStatement("insert into Book values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
				pstmt.setString(1, Dump.getISBN());
				pstmt.setString(2, Dump.getWeightAndPage());
				pstmt.setString(3, Dump.getWeightAndPage());
				pstmt.setString(4, Dump.getyanggang());
				pstmt.setInt(5, Dump.getOriginPrice());
				pstmt.setString(6, Dump.getRandomStringForNameAndNickname(3));
				pstmt.setString(7, Dump.getRandomStringForNameAndNickname(3));
				pstmt.setString(8, Dump.getRandomStringForNameAndNickname(4));
				pstmt.setString(9, Dump.getPublishDate());
				pstmt.setString(10, Dump.getField());
				pstmt.setString(11, Dump.getRandomStringForNameAndNickname(boundrayRandom));
				pstmt.execute();
				pstmt.close();
			}
			
			

			
		}  catch (Exception e) {
				System.err.println("Driver Error" + e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(stmt != null)stmt.close();
			if(conn != null) conn.close();
		}
	}

}
