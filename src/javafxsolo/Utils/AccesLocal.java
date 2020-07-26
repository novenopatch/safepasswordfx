package javafxsolo.Utils;

import javafxsolo.Modele.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccesLocal extends Acces{

    private AccesLocal(){
        super();

    }
    public static Connection getInstance(){
        if(connectionSqlite == null){
            try {
                Class.forName("org.sqlite.JDBC");

                //String url = "jdbc:sqlite:../javafxsolo/src/ressources/do/base.jin";
                String url = "jdbc:sqlite:do/base.jin";

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



    public static String ajout(Connection conn, User user) {
        String st = "INSERT INTO T_Users   (Login, Password,userQuestion, userAnswer) VALUES (?,?,?,?)";
        try ( PreparedStatement stUpdate = conn.prepareStatement(st)) {

            stUpdate.setString(1, user.getUserName());
            stUpdate.setString(2, user.getPassword());

            stUpdate.setInt(3, user.getUserQuestion());
            stUpdate.setString(4, user.getUserAnswer());
            stUpdate.executeUpdate();
            return "Success";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "Exception";
        }
    }
}
