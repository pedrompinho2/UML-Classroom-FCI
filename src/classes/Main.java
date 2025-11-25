import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== TESTE DO SISTEMA (BANCO REAL) =====");

        testarConexao();

        Sistema sistema = new Sistema(); // já carrega DRONES do banco
        List<Drone> drones = sistema.getDronesDisponiveis();

        System.out.println("\n===== DRONES CARREGADOS DO BANCO =====");
        mostrarDrones(drones);

        System.out.println("\n===== CARREGANDO CLIENTES DO BANCO =====");
        List<Cliente> clientes = carregarClientesDoBanco();
        mostrarClientes(clientes);

        System.out.println("\n===== CARREGANDO PEDIDOS DO BANCO =====");
        carregarPedidosDoBanco(sistema, clientes, drones);

        System.out.println("\n===== PEDIDOS REGISTRADOS NO SISTEMA =====");
        mostrarPedidos(sistema.getPedidos());

        System.out.println("\n===== FIM =====");
    }

    // ----------------------------------------------------------
    // Teste de conexão
    // ----------------------------------------------------------
    private static void testarConexao() {
        try (Connection conn = ConexaoBD.conectar()) {
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO ao conectar ao banco:");
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    // Mostrar drones (usa o toString() do Drone)
    // ----------------------------------------------------------
    private static void mostrarDrones(List<Drone> drones) {
        if (drones.isEmpty()) {
            System.out.println("Nenhum drone encontrado.");
            return;
        }
        for (Drone d : drones) {
            System.out.println(d);
        }
    }

    // ----------------------------------------------------------
    // Carregar CLIENTES + ENDERECOS + USUARIOS do banco
    // (usa construtores reais de Endereco e Cliente)
    // ----------------------------------------------------------
    private static List<Cliente> carregarClientesDoBanco() {
        List<Cliente> lista = new ArrayList<>();

        String sql = ""
            + "SELECT C.id AS cid, C.login, C.senha_hash, "
            + "       E.rua, E.numero, E.cidade, E.estado, E.cep, "
            + "       U.nome, U.email "
            + "FROM CLIENTES C "
            + "JOIN ENDERECOS E ON C.endereco_id = E.id "
            + "JOIN USUARIOS U ON U.id = C.id";

        try (Connection conn = ConexaoBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Endereco end = new Endereco(
                    rs.getString("rua"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("cep")
                );

                // Cliente(nome, login, email, senha, Endereco)
                // usamos a senha_hash do banco como "senha base"
                Cliente c = new Cliente(
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("email"),
                    rs.getString("senha_hash"),
                    end
                );

                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes:");
            e.printStackTrace();
        }

        return lista;
    }

    private static void mostrarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        for (Cliente c : clientes) {
            Endereco e = c.getEndereco();
            System.out.println(
                c.getNome()
                + " | login=" + c.getLogin()
                + " | email=" + c.getEmail()
                + " | endereço=" + e.getRua() + ", " + e.getNumero()
                + " - " + e.getCidade() + "/" + e.getEstado()
            );
        }
    }

    // ----------------------------------------------------------
    // Carregar PEDIDOS do banco e transformar em objetos Pedido
    // usando: Pedido(Cliente, Endereco, double)
    // e setDroneAtribuido(Drone) quando possível.
    // ----------------------------------------------------------
    private static void carregarPedidosDoBanco(Sistema sistema,
                                               List<Cliente> clientes,
                                               List<Drone> drones) {

        String sql = ""
            + "SELECT P.id, P.peso_kg, P.status, P.criado_em, P.entregue_em, "
            + "       P.cliente_id, P.destino_endereco_id, P.drone_id, "
            + "       E.rua, E.numero, E.cidade, E.estado, E.cep, "
            + "       U.nome, U.email, C.login, C.senha_hash "
            + "FROM PEDIDOS P "
            + "JOIN ENDERECOS E ON P.destino_endereco_id = E.id "
            + "JOIN CLIENTES C ON P.cliente_id = C.id "
            + "JOIN USUARIOS U ON U.id = C.id";

        try (Connection conn = ConexaoBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                // Endereço de destino do pedido
                Endereco destino = new Endereco(
                    rs.getString("rua"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("cep")
                );

                // Cliente associado ao pedido (a partir dos dados do banco)
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("email"),
                    rs.getString("senha_hash"),
                    destino // aqui usamos o mesmo endereco como simplificação
                );

                // Cria Pedido usando o construtor real que você implementou
                Pedido pedido = new Pedido(cliente, destino, rs.getDouble("peso_kg"));

                // Tenta atribuir drone com base no ID armazenado no banco
                String droneId = rs.getString("drone_id");
                Drone droneEncontrado = null;
                for (Drone d : drones) {
                    if (d.getId().equals(droneId)) {
                        droneEncontrado = d;
                        break;
                    }
                }
                if (droneEncontrado != null) {
                    pedido.setDroneAtribuido(droneEncontrado);
                }

                // Adiciona o pedido na lista interna do Sistema
                sistema.getPedidos().add(pedido);
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar pedidos:");
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    // Mostrar pedidos registrados no Sistema
    // ----------------------------------------------------------
    private static void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado no banco ou registrado no sistema.");
            return;
        }

        for (Pedido p : pedidos) {
            Cliente c = p.getCliente();
            Endereco e = p.getDestino();
            Drone d = p.getDroneAtribuido();

            System.out.println(
                p.getId()
                + " | cliente=" + (c != null ? c.getNome() : "null")
                + " | destino=" + e.getRua() + ", " + e.getNumero()
                + " - " + e.getCidade() + "/" + e.getEstado()
                + " | peso=" + p.getPesoKg() + "kg"
                + " | drone=" + (d != null ? d.getId() : "SEM DRONE")
                + " | status=" + p.getStatus()
            );
        }
    }
}
