package Testing;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

	private Database db;
	private Connection con;
	private String Fehlermeldung;

	public Database getDB(){
		db= new Database();
		return db;
	}
	public void createConnection(){
		con = null;
		try{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_database","root","user");
			System.out.print("es funktioniert - Connection aufgebaut");
		}catch(SQLException e){
			Fehlermeldung= Fehlermeldung +e.getMessage()+" con ";
		}
	}
	public void createTable(){
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE user40 ("
									+"vorname VARCHAR(15),"
									+"nachname VARCHAR(15),"
									+"email	VARCHAR(30),"
									+"passw	VARCHAR(16),"
									+"PRIMARY KEY (email)"
									+ ");" 
			);
		} catch (SQLException e) {
			Fehlermeldung= Fehlermeldung +e.getMessage()+"  -createTable-  ";
		}
	}
	public boolean saveUser(String vorname,String nachname,String email,String passw) throws SQLException{
		String query="insert into user40 values(?,?,?,?)";
		try{
		PreparedStatement stmt1=con.prepareStatement(query);
				stmt1.setString(1, vorname);
				stmt1.setString(2, nachname);
				stmt1.setString(3, email);
				stmt1.setString(4, passw);
				System.out.println(" eingefügt ");
		stmt1.executeUpdate();
		return true;
			
		} catch (SQLException e) {
			Fehlermeldung= Fehlermeldung +e.getMessage();
			System.out.print(" kann user nicht speichern "+Fehlermeldung+ " user speichern ");
			return false;
		}
	}
	public boolean readUser(String email) throws SQLException{

		Statement stmt;
		stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from user40 where email ='"+email +"';");
		if(rs.next())
			return true;	
		else 
			System.out.println("user nicht gefunden");
			return false;
	}
	public void closeConnection() throws SQLException{
		con.close();
	}
	public String getpassw(String email) throws SQLException {
		Statement stmt = null;
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select passw from user40 where email ='"+email +"';");
		return rs.getString(0);
		
	}
}