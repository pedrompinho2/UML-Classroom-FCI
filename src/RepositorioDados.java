package src;

public class RepositorioDados
{
    private List<Pedido> pedidos;
    private List<Drone> drones;
    private List<Cliente> clientes;

    public RepositorioDados()
    {
        this.pedidos = new ArrayList<>();
        this.drones = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void registrarPedido(Pedido p)
    {
        this.pedidos.add(p);
    }

    public void salvarDadosDrone(Drone d)
    {
        if (!this.drones.contains(d))
        {
            this.drones.add(d);
        }
    }

    public List<Pedido> buscarHistoricoPorCliente(String clienteId)
    {
        return pedidos.stream()
                .filter(p -> p.getClienteId().equals(clienteId))
                .collect(Collectors.toList());
    }

    public List<Drone> buscarTodosDrones()
    {
        return drones;
    }

    public void salvarCliente(Cliente c)
    {
        this.clientes.add(c);
    }
}
