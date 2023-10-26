package model;

public class Produto {

    private int idProduto;
    private String tipo_pro;
    private int quantidade_pro;
    private String codBarras_pro;
    private Double valor_pro;
    private String descricao_pro;

    public Produto() {

    }

    public Produto(int idProduto, String tipo_pro, int quantidade_pro, String codBarras_pro, Double valor_pro, String descricao_pro) {
        this.idProduto = idProduto;
        this.tipo_pro = tipo_pro;
        this.quantidade_pro = quantidade_pro;
        this.codBarras_pro = codBarras_pro;
        this.valor_pro = valor_pro;
        this.descricao_pro = descricao_pro;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getTipo_pro() {
        return tipo_pro;
    }

    public void setTipo_pro(String tipo_pro) {
        this.tipo_pro = tipo_pro;
    }

    public int getQuantidade_pro() {
        return quantidade_pro;
    }

    public void setQuantidade_pro(int quantidade_pro) {
        this.quantidade_pro = quantidade_pro;
    }

    public String getCodBarras_pro() {
        return codBarras_pro;
    }

    public void setCodBarras_pro(String codBarras_pro) {
        this.codBarras_pro = codBarras_pro;
    }

    public Double getValor_pro() {
        return valor_pro;
    }

    public void setValor_pro(Double valor_pro) {
        this.valor_pro = valor_pro;
    }

    public String getDescricao_pro() {
        return descricao_pro;
    }

    public void setDescricao_pro(String descricao_pro) {
        this.descricao_pro = descricao_pro;
    }

    @Override
    public String toString() {
        return "Produto{" + "idProduto=" + idProduto + ", tipo_pro=" + tipo_pro + ", quantidade_pro=" + quantidade_pro + ", codBarras_pro=" + codBarras_pro + ", valor_pro=" + valor_pro + ", descricao_pro=" + descricao_pro + '}';
    }

}
