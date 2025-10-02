

import java.time.LocalDateTime;

public class Pedido {
    private String id;
    private double pesoKg;
    private String destino;
    private PedidoStatus status;
    private LocalDateTime criadoEm;
    private LocalDateTime entregueEm;
    private Cliente cliente;
    private Drone droneAtribuido;

    public Pedido(Cliente cliente, String destino, double pesoKg) {
        this.id = "P" + LocalDateTime.now().getSecond() + LocalDateTime.now().getNano();
        this.cliente = cliente;
        this.destino = destino;
        this.pesoKg = pesoKg;
        this.status = PedidoStatus.CRIADO;
        this.criadoEm = LocalDateTime.now();
        System.out.println("Pedido " + this.id + " CRIADO.");
    }

    /**
     * Altera o status do pedido para CANCELADO.
     */
    public void cancelar() {
        this.status = PedidoStatus.CANCELADO;
        System.out.println("Pedido " + this.id + " CANCELADO.");
    }

    /**
     * Associa o Drone ao Pedido e atualiza o status para ATRIBUIDO.
     */
    public void setDroneAtribuido(Drone drone) {
        this.droneAtribuido = drone;
        this.status = PedidoStatus.ATRIBUIDO;
    }

    public String getId() { return id; }
    public double getPesoKg() { return pesoKg; }
    public String getDestino() { return destino; }
    public PedidoStatus getStatus() { return status; }
    public Cliente getCliente() { return cliente; }
    public Drone getDroneAtribuido() { return droneAtribuido; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public LocalDateTime getEntregueEm() { return entregueEm; }
}