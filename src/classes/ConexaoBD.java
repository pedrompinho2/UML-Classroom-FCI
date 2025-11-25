import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    private static final String URL = "jdbc:sqlite:docs/BancoDeDados.db";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do SQLite não encontrado.", e);
        }

        return DriverManager.getConnection(URL);
    }
}
