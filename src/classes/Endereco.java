


public class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String rua, String numero, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    
    //Valida o formato do CEP
    public boolean validarFormato() {
        return this.cep != null && this.cep.length() == 8;
    }

    @Override
    public String toString() {
        return rua + ", " + numero + " - " + cidade + "/" + estado + " (" + cep + ")";
    }

    public String getRua() { return rua; }
    public String getNumero() { return numero; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
}
