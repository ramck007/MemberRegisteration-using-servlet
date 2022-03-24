import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDB {
	
	private String dbURL="jdbc:mysql://localhost:3306/sample";
	private String dbUname="root";
	private String dbPass="1234";
	private String dbDriver="com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection con= null;
		try {
			con = DriverManager.getConnection(dbURL,dbUname,dbPass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	public String insert(MemberData member) {
		loadDriver(dbDriver);
		Connection con= getConnection();
		String result="Data Entered Successfully";
		String sql="insert into member values(?,?,?,?);";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getUname());
			ps.setString(2, member.getEmail());
			ps.setString(3, member.getPassword());
			ps.setString(4, member.getPhone());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			result="OOps! Data not Entered";
			e.printStackTrace();
		}
		return result;
	}

}
