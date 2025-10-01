package src;

public class Cliente
{
    private String id;
    private String login;
    private String nome;
    private String email;
    private Endereco endereco;

    // Novo construtor sem hashSenha e telefone
    public Cliente(String nome, String login, String email, Endereco endereco)
    {
        this.id = "CLI" + System.currentTimeMillis();
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.endereco = endereco;
    }

    public String getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome;
    }

    public String getLogin()
    {
        return login;
    }

    public String getEmail()
    {
        return email;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }
}
