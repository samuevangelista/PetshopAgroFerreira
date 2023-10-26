package model;

public class Cliente {
    
    private int idCliente;
    private String nome_cli;
    private String telefone_cli;
    private String endereco_cli;
    private String email_cli;

    public Cliente() {
    }

    public Cliente(int idCliente, String nome_cli, String telefone_cli, String endereco_cli, String email_cli) {
        this.idCliente = idCliente;
        this.nome_cli = nome_cli;
        this.telefone_cli = telefone_cli;
        this.endereco_cli = endereco_cli;
        this.email_cli = email_cli;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome_cli() {
        return nome_cli;
    }

    public void setNome_cli(String nome_cli) {
        this.nome_cli = nome_cli;
    }

    public String getTelefone_cli() {
        return telefone_cli;
    }

    public void setTelefone_cli(String telefone_cli) {
        this.telefone_cli = telefone_cli;
    }

    public String getEndereco_cli() {
        return endereco_cli;
    }

    public void setEndereco_cli(String endereco_cli) {
        this.endereco_cli = endereco_cli;
    }

    public String getEmail_cli() {
        return email_cli;
    }

    public void setEmail_cli(String email_cli) {
        this.email_cli = email_cli;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nome_cli=" + nome_cli + ", telefone_cli=" + telefone_cli + ", endereco_cli=" + endereco_cli + ", email_cli=" + email_cli + '}';
    }
    
}
