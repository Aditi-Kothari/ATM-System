
import java.sql.*;
public class Connection {
     //5 steps for JDBC Connectivity
       // Register the driver--1
       // Create Connection --2
       // Create Statement --3
       // Exexute Query--4
       // Close --5
java.sql.Connection c = null;
Statement s=null;
    public Connection(){
        try{

            //Create connection
            c= DriverManager.getConnection("jdbc:mysql:///ATMSystem","root","");
            //create a statement
            s=c.createStatement();


        }catch(Exception e){
            System.out.println(e);
        }
    }

}
