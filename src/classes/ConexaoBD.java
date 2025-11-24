import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    private static final String URL = "jdbc:sqlite:/Users/hissapinto/Library/CloudStorage/OneDrive-Pessoal/_CC/S4/Projeto de Software/UML-Classroom-FCI/docs/BancoDeDados.db"; 

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do SQLite não encontrado.");
            throw new SQLException(e);
        }
        
        return DriverManager.getConnection(URL);
    }
}