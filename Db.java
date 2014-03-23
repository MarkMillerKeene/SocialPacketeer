package sp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {

	
public static void writeToDb(String name, String sourceIPFormatted, String destinationIPFormatted, String mac) // Method to write to the database
{
	 Statement stmt = null;
	 
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		
		String url = "jdbc:mysql://localhost/packet"; // Url (localhost) followed by the database name (packet)
		String dbpass = ""; //Database password. Here, it is null.
		String dbuser = "root"; //Database username
	  final Connection conn = DriverManager.getConnection(url, dbuser, dbpass); //Connect
	  stmt = conn.createStatement();
	 String tableName2 = Userinput.getTableName2();
	   String sqlupd ="INSERT INTO "+ tableName2 +"(site, sourceip, destip, mac) VALUE " + //Write to the database you created when the program was first run. 
			  	"('"+name+"','"+sourceIPFormatted+"'," +
		  	"'"+destinationIPFormatted+"'," +
		  	"'"+mac+"')";
	   stmt.executeUpdate(sqlupd);
	   conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
