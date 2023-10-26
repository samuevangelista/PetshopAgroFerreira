package model;

public class Servico {

    private int idServico;
    private String tipo_ser;
    private Double valor_ser;
    private Funcionario idFuncionario;
    private Animal idAnimal;


    public Servico() {

    }

    public Servico(int idServico, String tipo_ser, Double valor_ser, Funcionario idFuncionario, Animal idAnimal) {
        this.idServico = idServico;
        this.tipo_ser = tipo_ser;
        this.valor_ser = valor_ser;
        this.idFuncionario = idFuncionario;
        this.idAnimal = idAnimal;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getTipo_ser() {
        return tipo_ser;
    }

    public void setTipo_ser(String tipo_ser) {
        this.tipo_ser = tipo_ser;
    }

    public Double getValor_ser() {
        return valor_ser;
    }

    public void setValor_ser(Double valor_ser) {
        this.valor_ser = valor_ser;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Animal getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Animal idAnimal) {
        this.idAnimal = idAnimal;
    }

    @Override
    public String toString() {
        return "Servico{" + "idServico=" + idServico + ", tipo_ser=" + tipo_ser + ", valor_ser=" + valor_ser + ", idFuncionario=" + idFuncionario + ", idAnimal=" + idAnimal + '}';
    }
    
}
