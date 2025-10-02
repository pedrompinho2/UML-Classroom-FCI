
public abstract class Usuario {
    private final String idUsuario;
    private final String nome;
    private final String email;

    public Usuario(String idUsuario, String nome, String email) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
    }

    // Método abstrato que subclasses devem implementar (polimorfismo)
    public abstract void notificarFalha(String mensagem);

    public String getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}