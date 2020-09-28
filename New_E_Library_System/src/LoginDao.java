import java.sql.*;

public class LoginDao {
	String sql = "select * from login where username=? and password=?";
	String url = "jdbc:mysql://localhost:3307/servlet";
	public boolean check(String uname, String pass){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root","root");
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,uname);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	} 
}
