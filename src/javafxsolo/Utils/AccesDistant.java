package javafxsolo.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AccesDistant  extends Acces{

    private AccesDistant(){
        super();
    }

    /**
     * this comment block contain an methode who return an object connection for mysql database
     * @return
     */
     public static Connection getInstance () {
         if (connection == null) {
                     Properties props = new Properties();
                     try {
                         FileInputStream fis = new FileInputStream("conf.properties");
                         props.load(fis);
                         Class.forName(props.getProperty("jdbc.driver.class"));
                         String url = props.getProperty("jdbc.url");
                         String dbLogin = props.getProperty("jdbc.login");
                         String dbPassword = props.getProperty("jdbc.password");
                         try {
                             Connection connection = DriverManager.getConnection(url, dbLogin, dbPassword);
                             return connection;
                         } catch (SQLException e) {

                             e.printStackTrace();
                             return null;
                         }
                     } catch (FileNotFoundException e) {
                         e.printStackTrace();
                         return null;
                     } catch (IOException e) {
                         e.printStackTrace();
                         return null;
                     } catch (ClassNotFoundException e) {

                         e.printStackTrace();
                         return null;
                     } catch (Exception e) {
                         System.err.println("ConnectionUtil : " + e.getMessage());
                         return null;
                     }
         }else{
                    return null;
         }
     }

}
