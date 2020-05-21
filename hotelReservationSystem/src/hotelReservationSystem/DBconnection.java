/*(hasan) SQL server ile baglamak icin olusturdugum class*/

package hotelReservationSystem;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	
	public Connection getConnection() {
		
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/group3?useSSL=false","root","root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

	
}
