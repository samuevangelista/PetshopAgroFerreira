package model;

public class Venda {

    private int idVenda;
    private Double valor_ven;
    private String data_ven;
    private String formaPagamento_ven;
    private Produto idProduto;
    private Servico idServico;
    private Cliente idCliente;
    private Perfil idPerfil;

    public Venda() {

    }

    public Venda(int idVenda, Double valor_ven, String data_ven, String formaPagamento_ven, Produto idProduto, Servico idServico, Cliente idCliente, Perfil idPerfil) {
        this.idVenda = idVenda;
        this.valor_ven = valor_ven;
        this.data_ven = data_ven;
        this.formaPagamento_ven = formaPagamento_ven;
        this.idProduto = idProduto;
        this.idServico = idServico;
        this.idCliente = idCliente;
        this.idPerfil = idPerfil;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Double getValor_ven() {
        return valor_ven;
    }

    public void setValor_ven(Double valor_ven) {
        this.valor_ven = valor_ven;
    }

    public String getData_ven() {
        return data_ven;
    }

    public void setData_ven(String data_ven) {
        this.data_ven = data_ven;
    }

    public String getFormaPagamento_ven() {
        return formaPagamento_ven;
    }

    public void setFormaPagamento_ven(String formaPagamento_ven) {
        this.formaPagamento_ven = formaPagamento_ven;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Servico getIdServico() {
        return idServico;
    }

    public void setIdServico(Servico idServico) {
        this.idServico = idServico;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public String toString() {
        return "Venda{" + "idVenda=" + idVenda + ", valor_ven=" + valor_ven + ", data_ven=" + data_ven + ", formaPagamento_ven=" + formaPagamento_ven + ", idProduto=" + idProduto + ", idServico=" + idServico + ", idCliente=" + idCliente + ", idPerfil=" + idPerfil + '}';
    }
    
}
