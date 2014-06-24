import java.util.Random;
import java.sql.*;

public class createDumpDataCode {
	public static String getRandomStringForNameAndNickname(int length) {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		 
		  String chars[] = 
		    "가,나,다,라,마,바,사,아,자,차,카,타,파,하,야,오,우,거,고,교,감,강,앙,돈,서,국,안,돈,거,고,갸,야,마,사,커,구,국,용,돈,안,사,정,희,수,안,녕,하,세,요,구,글,코,드,가,져,다,가,썼,어,도,데,체,이,게,무,슨,일,이,야,도,데,체,어,떻,게,하,란,말,이,야,오,피,스,삼,육,오,구,독,자,한,테,스,카,이,드,라,이,브,일,테,라,를,준,다,네,".split(",");
		 
		  for (int i=0 ; i<length; i++)
		  {
		    buffer.append(chars[random.nextInt(chars.length)]);
		  }
		
		  return buffer.toString();
	}

	public static String getSex() {
		Random random = new Random();
		int checkNum = random.nextInt(2);
		if(checkNum == 0) return "남자";
		else return "여자";
	}
	
	public static String getLocation() {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		String firstLocation[] = "서울특별시, 부산광역시, 대구광역시, 인천광역시, 광주광역시, 대전광역시, 울산광역시, 강원도, 경기도, 경상남도, 경상북도, 전라남도, 전라북도, 제주도, 충청남도, 충청북도".split(",");
		buffer.append(firstLocation[random.nextInt(firstLocation.length)]);
		String secondLocation[] = "강남구, 강동구, 강북구, 강서구, 관악구, 광진구, 구로구, 금천구, 노원구, 도봉구, 동대문구, 동작구, 마포구, 서대문구, 서초구, 성동구, 성북구, 송파구, 양천구, 영등포구, 용산구, 은평구, 종로구, 중구, 중랑구, 강서구, 금정구, 남구, 동구, 동래구, 부산진구, 북구, 사상구, 서하구, 서구, 수영구, 연제구, 영도구, 중구, 해운대구, 기장군, 남구, 달서구, 동구, 서구, 북구, 수성구, 중구, 달성군".split(",");
		buffer.append(secondLocation[random.nextInt(secondLocation.length)]);
		buffer.append(" ");
		String thridLocation[] = "개포1동,개포2동,개포4동,개포동,논현1동,논현2동,논현동,대치1동,대치2동,대치4동,대치동,도곡1동,도곡2동,도곡동,삼성1동,삼성2동,삼성동,세곡동,수서동,신사동,압구정동,역삼1동,역삼2동,역삼동,율현동,일원1동,일원2동,일원본동,일원동,자곡동,청담동,강일동,고덕1동,고덕2동,고덕동,길동,둔촌1동,둔촌2동,둔촌동,명일1동,명일2동,명일동,상일동,성내1동,성내2동,성내3동,성내동,암사1동,암사2동,암사3동,암사동,천호1동,천호2동,천호3동,천호동".split(",");
		buffer.append(thridLocation[random.nextInt(thridLocation.length)]);
		
		return buffer.toString();
	}
	
	public static String getIdORPwd(int length) {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		 
		  String chars[] = 
		    "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
		 
		  for (int i=0 ; i<length ; i++)
		  {
		    buffer.append(chars[random.nextInt(chars.length)]);
		  }
		
		  return buffer.toString();
		
	}
	
	public static String getPhoneNum() {
		 StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		  buffer.append("010-");
		  buffer.append(random.nextInt(9)+1);
		  for(int i=0; i<3; i++) {
			  int tempNum = random.nextInt(10);
			  buffer.append(tempNum);
		  }
		  int firstRnadomNum = random.nextInt(9) + 1;
		  buffer.append("-"+ firstRnadomNum);
		  for(int i=0; i<3; i++) {
			  int tempNum = random.nextInt(10);
			  buffer.append(tempNum);
		  }
		  return buffer.toString();
	}
	
	
	public static void main(String[] args) throws SQLException {
		createDumpDataCode Dump = new createDumpDataCode();
		
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
				pstmt = conn.prepareStatement("insert into client values (null, ?, ?, ?, ?, md5(?), ?, ?)" );
				pstmt.setString(1, Dump.getRandomStringForNameAndNickname(3));
				pstmt.setString(2, Dump.getSex());
				pstmt.setString(3, Dump.getLocation());
				pstmt.setString(4, Dump.getIdORPwd(7));
				pstmt.setString(5, Dump.getIdORPwd(9));
				pstmt.setString(6, Dump.getPhoneNum());
				pstmt.setString(7, Dump.getRandomStringForNameAndNickname(5));
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

