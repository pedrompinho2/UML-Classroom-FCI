import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Drone> dronesDisponiveis;
    private List<Pedido> pedidosRegistrados;
    private List<Drone> totalDrones;
    private DroneDAO droneDAO;

    public Sistema() {
        this.pedidosRegistrados = new ArrayList<>();
        
        this.droneDAO = new DroneDAO(); 
        
        this.totalDrones = this.droneDAO.carregarTodos(); 
        this.dronesDisponiveis = new ArrayList<>();
        
        for (Drone d : this.totalDrones) {
            if (d.getStatus().equals("DISPONIVEL")) {
                this.dronesDisponiveis.add(d);
            }
        }
    }
    
    public Pedido solicitarEntrega(Cliente cliente, Endereco destino, double pesoKg) throws DroneIndisponivelException {
        
        if (!destino.validarFormato()) {
            cliente.notificarFalha("Endereço inválido. CEP não está no formato correto.");
            return null;
        }

        Pedido novoPedido = new Pedido(cliente, destino, pesoKg); 
        this.pedidosRegistrados.add(novoPedido);

        Drone droneAtribuido = atribuirDrone(novoPedido); 
        
        if (droneAtribuido != null) {
            droneAtribuido.atribuirMissao();
            novoPedido.setDroneAtribuido(droneAtribuido);
            
            this.droneDAO.atualizarStatus(droneAtribuido.getId(), "ATRIBUIDO"); 
            
            System.out.println("Entrega Agendada para " + cliente.getNome());
            return novoPedido;
        } else {
            novoPedido.cancelar(); 
            cliente.notificarFalha("Nenhum drone disponível que suporte o peso.");
            throw new DroneIndisponivelException("Nenhum drone disponível.");
        }
    }
    
    private Drone atribuirDrone(Pedido pedido) {
        double peso = pedido.getPesoKg();
        
        for (Drone drone : this.dronesDisponiveis) {
            if (drone.disponivel(peso)) {
                this.dronesDisponiveis.remove(drone);
                return drone;
            }
        }
        return null;
    }

   public List<Drone> getDronesDisponiveis(){
        return this.dronesDisponiveis;
   }

   public List<Drone> getToDrones(){
        return this.totalDrones;
   }

   public List<Pedido> getPedidos(){
        return this.pedidosRegistrados;
   }

   public void setDronesDisponiveis(List<Drone> dronesdisp){
        this.dronesDisponiveis = dronesdisp;
   }

   public void setToDrones(List<Drone>  totalDrones){
        this.totalDrones = totalDrones;
   }

   public void setPedidos(List<Pedido> pedidosRegistrados){
        this.pedidosRegistrados = pedidosRegistrados;
   }
}