
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/synergy";
	
	static final String USER = "root";
	static final String PASS = "";
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	public void connect()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void add_business(String id,String city)
	{		
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO city(city_id, city)"
                    +"VALUES"
                    +"(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, city);
			pstmt.executeUpdate();
			pstmt.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void add_restaurant(String id,String name,String address,String city)
	{
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO restaurants(rest_id, rest_name,full_address,city_id)"
                    +"VALUES"
                    +"(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, city);
			pstmt.executeUpdate();
			pstmt.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add_review(String id,String rest_id,String review)
	{
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO reviews(review_id, rest_id, review)"
                    +"VALUES"
                    +"(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, rest_id);
			pstmt.setString(3, review);
			pstmt.executeUpdate();
			pstmt.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
