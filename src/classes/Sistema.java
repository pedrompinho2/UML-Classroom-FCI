

import java.util.List;
import java.util.ArrayList;

public class Sistema {
    private List<Drone> dronesDisponiveis = new ArrayList<>();
    private List<Pedido> pedidosRegistrados = new ArrayList<>();

    public Sistema() {
        dronesDisponiveis.add(new Drone("DRN-001", 10.0, 95));
        dronesDisponiveis.add(new Drone("DRN-002", 5.0, 80));
    }

   
    public void solicitarEntrega(Cliente cliente, String destino, double pesoKg) {
        System.out.println("Sistema: Recebendo solicitacao de entrega para " + destino);

        if (!validarEndereco(destino)) {
            cliente.notificarFalha("Sistema: Endereco invalido.");
            return;
        }

        Drone droneParaAtribuir = buscarDroneDisponivel(pesoKg);
        Pedido novoPedido = new Pedido(cliente, destino, pesoKg);
        pedidosRegistrados.add(novoPedido);


        if (droneParaAtribuir != null) {
            try {
                droneParaAtribuir.atribuirMissao();
       
                novoPedido.setDroneAtribuido(droneParaAtribuir); 
                
                cliente.notificarSucesso("Sistema: Entrega agendada. Drone " + droneParaAtribuir.getId() + " atribuido.");
                
            } catch (Exception e) {
    
                novoPedido.cancelar();
                cliente.notificarFalha("Sistema: Erro na atribuicao do drone: " + e.getMessage());
            }

        } else {
            novoPedido.cancelar();
            cliente.notificarFalha("Sistema: Nenhum drone disponivel ou adequado para esta carga.");
        }
    }

    private Drone buscarDroneDisponivel(double pesoKg) {
        for (Drone drone : dronesDisponiveis) {
            if (drone.disponivel(pesoKg) && drone.checarBateria()) {
                return drone;
            }
        }
        return null;
    }
}