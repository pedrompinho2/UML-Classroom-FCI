public class Cliente extends Usuario
{
    private String login;
    private String senhaHash; //armazena a senha em formato de hash
    private Endereco endereco;

    public Cliente(String nome, String login, String email, String senha, Endereco endereco)
    {
        super("CLI" + System.currentTimeMillis(), nome, email);//Construtor da superclasse usuário
        this.login = login;
        this.senhaHash = gerarHash(senha);
        this.endereco = endereco;
    }

    @Override
    public void notificarFalha(String mensagem) {
        System.err.println("Notificação para " + getNome() + ": " + mensagem);
    }
    
    public boolean validarLogin(String login) {
        return login != null && login.length() > 3; 
    }

    public boolean validarEmail(String email) {
        return email != null && email.contains("@"); 
    }

    private String gerarHash(String senha) {
        return String.valueOf(senha.hashCode()); 
    }

    public String getId() {
        return super.getIdUsuario();
    }

    public String getNome() {
        return super.getNome();
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public Endereco getEndereco() {
        return endereco;
    }
}