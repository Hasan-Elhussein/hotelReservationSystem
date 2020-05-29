package hotelReservationSystem;
import java.sql.Connection;
import java.sql.DriverManager;

public class main {

	public static void main(String[] args) {
		//(hasan) sql objesi olusturma kodu.
		DBconnection dbObj = new DBconnection();
		Connection connection = null;
		connection=dbObj.getConnection();
		System.out.println(connection);
		//====================================================================================================//
		
		
		
		
		
		
		
		
		
		
	}
	
}
