package Testing;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/login")
public class Login {
	private String vorname, nachname, password, email;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String login(){
		System.out.println("bin im register-path-resgister");

		return "<html><body><titel>"
				+ "<h2><center>Login</center></h2></titel>+"
				+ "<form action='/WebserviceRest/rest/login/formparam' method='post'><center>"
				//+"<form method='get'>"
				+ "Password: <br><input type='password' name='password' value=''><br>"
				+ "E-mail:<br><input type='email' name='email'><br><br>"
				+ "<input type='submit' vallue='Submit'>"
				+ "</form> </center></body></html>";
	}	
	@POST
	@Path("/formparam")
	public String weiterleitung(@FormParam("password") String password,
								@FormParam("email") String email
								) throws SQLException{
		this.vorname=vorname;	
		this.nachname=nachname;
		this.password=password;
		this.email=email;
		System.out.println("es hat funktioniert- Fromparam ---- :");
		System.out.println("vorname : "+this.vorname);
		Database db=new Database();
		
		db.createConnection();
		boolean a=db.readUser(email);
		/*if(password.equals(db.getpassw(email))&&a){
			return "<html><title>Willkommen</title><head><h2><center>Willkommen</center></h2><head></body></html>";
		}else{
			return "<html><title>Willkommenfalsch</title><head><h2><center>Willkommen</center></h2><head></body></html>";
			*/
		if(a){
		return "<html><title>Willkommen</title><head><h2><center>Willkommen</center></h2><head></body></html>";
		}else{
			return "<html><body><titel>"
					+ "<h2><center>Login</center></h2></titel>+"
					+ "<form action='/WebserviceRest/rest/login/formparam' method='post'><center>"
					//+"<form method='get'>"
					+ "Password: <br><input type='password' name='password' value=''><br>"
					+ "E-mail:<br><input type='email' name='email'><br><br>"
					+ "<input type='submit' vallue='Submit'><br> User nicht gefunden, bitte nochaml versuchen"
					+ "</form> </center></body></html>";
		}
		}
	}

