package javafxsolo.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesLocal extends Acces{

    private AccesLocal(){
        super();

    }
    public static Connection getInstance(){
        if(connectionSqlite == null){
            try {
                Class.forName("org.sqlite.JDBC");

                String url = "jdbc:sqlite:../javafxsolo/src/ressources/do/base.jin";

                Connection connectionSqlite = DriverManager.getConnection(url);
                return connectionSqlite;
            }
            catch (SQLException e) {

                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            catch (Exception e) {
                System.err.println("ConnectionUtil : "+e.getMessage());
                return null;
            }


        }
        else {
            return connectionSqlite;
        }
    }
}
