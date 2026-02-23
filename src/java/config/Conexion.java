
package config;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    String url = "jdbc:mysql://localhost:3306/cesystemdb";
    //String user = "ADMIN";
    //String pass = "Septiembre-23*";
    String user = "root";
    String pass = "";
    
    public Connection Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con =(Connection) DriverManager.getConnection(url,user,pass);            
            System.out.println("Conexion exitosa a :"+url+" con: "+user);
        }catch(Exception e){  
            System.out.println("Error en la conexion");
        }
        return con;
    }
}
