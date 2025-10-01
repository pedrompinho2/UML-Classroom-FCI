package src;

public class ServicoEntrega
{
    private IRepositorio repositorio;

    public ServicoEntrega(IRepositorio repositorio)
    {
        this.repositorio = repositorio;
    }

    public Pedido solicitarEntrega(Cliente cliente, Endereco destino, double pesoKg) throws Exception
    {
        if (!destino.validarFormato())
        {
            throw new IllegalArgumentException("O formato do endereço não é permitido");
        }

        Optional<Drone> droneAtribuido = atribuirDrone(pesoKg);

        if (droneAtribuido.isEmpty())
        {
            throw new Exception("Não hà drones disponiveis");
        }

        Drone drone = droneAtribuido.get();
        
        
        Pedido novoPedido = new Pedido(cliente.getId() + "PED" + System.currentTimeMillis(), cliente.getId(), pesoKg, destino); 
        
        novoPedido.setDroneAtribuido(drone);
        novoPedido.setStatus("ATRIBUIDO");
        
        drone.setStatus("EM_VOO");
        repositorio.salvarDadosDrone(drone);

        repositorio.registrarPedido(novoPedido);
        
        return novoPedido;
    }

    private Optional<Drone> atribuirDrone(double pesoKg)
    {
        List<Drone> drones = repositorio.buscarTodosDrones();
        
        Optional<Drone> droneOpt = drones.stream()
                .filter(d -> d.disponivel(pesoKg))
                .findFirst(); 
                
        return droneOpt;
    }

    public List<Pedido> consultarHistorico(String clienteId)
    {
        return repositorio.buscarHistoricoPorCliente(clienteId);
    }
}
