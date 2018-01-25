package Testing;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/register")
public class Register {
	private String vorname, nachname, password, email;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String register(){
		System.out.println("bin im register-path-resgister");

		return "<html><body><titel>"
				+ "<h2><center>Registrierung</center></h2></titel>+"
				+ "<form action='/WebserviceRest/rest/register/formparam' method='post'><center>"
				//+"<form method='get'>"
				+ "First name:<br><input type='text' name='vorname' value=''><br>"
				+ "Last name:<br><input type='text' name='nachname' value=''><br>"
				+ "Password: <br><input type='password' name='password' value=''><br>"
				+ "E-mail:<br><input type='email' name='email'><br><br>"
				+ "<input type='submit' vallue='Submit'>"
				+ "</form> </center></body></html>";
	}	
	
	@POST
	@Path("/formparam")
	public String weiterleitung(@FormParam("vorname") String vorname,
								@FormParam("nachname") String nachname,
								@FormParam("password") String password,
								@FormParam("email") String email
								) throws SQLException{
		this.vorname=vorname;	
		this.nachname=nachname;
		this.password=password;
		this.email=email;
		//System.out.println("es hat funktioniert- Fromparam ---- :");
		//System.out.println("vorname : "+this.vorname);
		Database db=new Database();
		db.createConnection();
		db.createTable();
		boolean b=db.saveUser(vorname, nachname, email, password);
		
		if(b){
		return "<html><title>Registrierung abgeschlossen</title><head><h2><center>Registrierung abgeschlossen</center></h2><head></br>  <center>Willkommen <b>"+vorname+" "+nachname+"</b> </br> Sie sind mit der email <b>" +email+"</b> registriert <br> Moechten Sie sich <a href="+"http://localhost:8080/WebserviceRest/rest/login"+">einloggen</a></center></body></html>";
		}else {
			return "<html><body><titel>"
					+ "<h2><center>Registrierung</center></h2></titel>+"
					+ "<form action='/WebserviceRest/rest/register/formparam' method='post'><center>"
					//+"<form method='get'>"
					+ "First name:<br><input type='text' name="+vorname+" value=''><br>"
					+ "Last name:<br><input type='text' name="+nachname+" value=''><br>"
					+ "Password: <br><input type='password' name="+password+" value=''><br>"
					+ "E-mail:<br><input type='email' name='email'><br><br>"
					+ "<input type='submit' vallue='Submit'><br>"
					+"Benutzer ist schon angelegt , bitte andere email verwenden"
					+ "</form> </center></body></html>";
		}
	}
}