package javafxsolo.Utils;

import javafx.scene.paint.Color;
import javafxsolo.Modele.Connect;
import javafxsolo.Modele.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Acces {
    protected static Connection connectionSqlite  = null;
    protected static Connection connection = null;

    /**
     * methode a redefinir dans acceslocal et distant
     * @return
     */
    public static Connection getInstance() {
        return null;
    }

}
