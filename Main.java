import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		createTable();
		insertIntoTable();
		select();
	}
	public static ArrayList<String> select() throws Exception{
		try {
			Connection c = getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM testtable");
			ResultSet rs = ps.executeQuery();
			ArrayList<String> array = new ArrayList<String>();
			
			while(rs.next()) {
				System.out.print(rs.getInt("id"));
				System.out.print(": ");
				System.out.print(rs.getString("firstname"));
				System.out.print(" ");
				System.out.println(rs.getString("lastname"));
				
				//ArrayList<String> array = new ArrayList<String>();
				array.add(rs.getString("firstname"));
				array.add(rs.getString("lastname"));
			}
			System.out.println("table printed");
			return array;
		}
		catch(Exception e) {
			System.out.println(e);

		}
		return null;
	}
	public static void insertIntoTable() throws Exception{
		try {
			String v1 = "Mustafa";
			String v2 = "Rizvi";
			Connection c = getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO testtable (firstname, lastname) VALUES ('"+v1+"', '"+v2+"')");
			ps.executeUpdate();
			
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("info inserted");
		}
	}
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/testdb";
			String usern = "root";
			String password = "Spaceshuttle";
			Class.forName(driver);
			
			Connection c = DriverManager.getConnection(url, usern, password);
			System.out.println("connected");
			return c;
		}
		catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public static void createTable() throws Exception{
		try {
			Connection c = getConnection();
			PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS testtable(id int NOT NULL AUTO_INCREMENT, firstname varchar(40), lastname varchar(40), PRIMARY KEY(id))");
			ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally{
			System.out.println("table created");
		}
	}

}
