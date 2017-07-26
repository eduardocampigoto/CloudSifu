package br.com.cloudsifu.db;
import java.sql.Connection;

public class CsConnection {
	
		private Connection serverConnection;
		
		public Connection openConnection(){
			
			try{
				
				Class.forName("org.gjt.mm.mysql.Driver");
				serverConnection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/feihokphai","root","master");
				
			}catch(Exception e){
				
				e.printStackTrace();
			
			}
			return serverConnection;
			
		}// END OPENCONNECTION
		
		
		
		public void closeConnection(){
			
			try{
				serverConnection.close();
			}catch(Exception e){
				
				e.printStackTrace();
				
			}	
			
		}// END CLOSECONNECTION
}
