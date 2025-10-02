public class Cliente extends Usuario {
    private Endereco endereco;

    public Cliente(String idUsuario, String nome, String email, Endereco endereco) {
        // Chamada obrigatória ao construtor da superclasse Usuario
        super(idUsuario, nome, email);
        this.endereco = endereco;
    }

    /**
     * Inicia a interacao de solicitacao de entrega, chamando o Sistema.
     */
    public void solicitarEntrega(Sistema sistema, String destino, double pesoKg) {
        System.out.println("Cliente " + getNome() + ": Solicitando entrega para " + destino);
        sistema.solicitarEntrega(this, destino, pesoKg);
    }

    /**
     * Recebe a notificacao de sucesso do Sistema.
     */
    public void notificarSucesso(String mensagem) {
        System.out.println("Cliente " + getNome() + ": SUCESSO. " + mensagem);
    }

    /**
     * Implementacao do metodo abstrato herdado para notificar falhas.
     */
    @Override
    public void notificarFalha(String mensagem) {
        System.out.println("Cliente " + getNome() + ": FALHA. " + mensagem);
    }

    public Endereco getEndereco() { return endereco; }
}