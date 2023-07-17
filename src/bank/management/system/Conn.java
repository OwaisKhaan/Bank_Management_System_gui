package bank.management.system;
import java.sql.*;

public class Conn {
    Connection con;
    public Conn(){
        try {
            con = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","root");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
