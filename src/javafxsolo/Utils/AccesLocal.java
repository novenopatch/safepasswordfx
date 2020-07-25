package javafxsolo.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesLocal {
   private static Connection connectionSqlite = null;
    private AccesLocal(){
        super();
    }
    public static Connection getInstance(){
        if(AccesLocal.connectionSqlite == null){
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
