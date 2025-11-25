import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DroneDAO {
    
    public List<Drone> carregarTodos() {
        List<Drone> drones = new ArrayList<>();
        String sql = "SELECT id, capacidade_kg, bateria_percent, status FROM DRONES";
        
        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Drone d = new Drone(
                    rs.getString("id"),
                    rs.getDouble("capacidade_kg"),
                    rs.getInt("bateria_percent")
                );
                d.setStatus(rs.getString("status"));
                drones.add(d);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar drones: " + e.getMessage());
        }

        return drones;
    }

    public void atualizarStatus(String droneId, String novoStatus) {
        String sql = "UPDATE DRONES SET status = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, novoStatus);
            ps.setString(2, droneId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao persistir status do drone: " + e.getMessage());
        }
    }
}
