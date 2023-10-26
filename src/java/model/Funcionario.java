package model;

public class Funcionario {
    
    private int idFuncionario;
    private String nome_fun;
    private String cpf_fun;
    private String endereco_fun;
    private String telefone_fun;
    private String funcao_fun;
    private String email_fun;
    private Perfil idPerfil;

    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String nome_fun, String cpf_fun, String endereco_fun, String telefone_fun, String funcao_fun, String email_fun, Perfil idPerfil) {
        this.idFuncionario = idFuncionario;
        this.nome_fun = nome_fun;
        this.cpf_fun = cpf_fun;
        this.endereco_fun = endereco_fun;
        this.telefone_fun = telefone_fun;
        this.funcao_fun = funcao_fun;
        this.email_fun = email_fun;
        this.idPerfil = idPerfil;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome_fun() {
        return nome_fun;
    }

    public void setNome_fun(String nome_fun) {
        this.nome_fun = nome_fun;
    }

    public String getCpf_fun() {
        return cpf_fun;
    }

    public void setCpf_fun(String cpf_fun) {
        this.cpf_fun = cpf_fun;
    }

    public String getEndereco_fun() {
        return endereco_fun;
    }

    public void setEndereco_fun(String endereco_fun) {
        this.endereco_fun = endereco_fun;
    }

    public String getTelefone_fun() {
        return telefone_fun;
    }

    public void setTelefone_fun(String telefone_fun) {
        this.telefone_fun = telefone_fun;
    }

    public String getFuncao_fun() {
        return funcao_fun;
    }

    public void setFuncao_fun(String funcao_fun) {
        this.funcao_fun = funcao_fun;
    }

    public String getEmail_fun() {
        return email_fun;
    }

    public void setEmail_fun(String email_fun) {
        this.email_fun = email_fun;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "idFuncionario=" + idFuncionario + ", nome_fun=" + nome_fun + ", cpf_fun=" + cpf_fun + ", endereco_fun=" + endereco_fun + ", telefone_fun=" + telefone_fun + ", funcao_fun=" + funcao_fun + ", email_fun=" + email_fun + ", idPerfil=" + idPerfil + '}';
    }

}
