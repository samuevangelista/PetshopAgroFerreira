package model;

public class Animal {

private int idAnimal;
private String nome_ani;
private String raca_ani;
private int idade_ani;
private String tamanho_ani;
private Cliente idCliente;

public Animal() {
}

public Animal(int idAnimal, String nome_ani, String raca_ani, int idade_ani, String tamanho_ani, Cliente idCliente) {
this.idAnimal = idAnimal;
this.nome_ani = nome_ani;
this.raca_ani = raca_ani;
this.idade_ani = idade_ani;
this.tamanho_ani = tamanho_ani;
this.idCliente = idCliente;
}

public int getIdAnimal() {
return idAnimal;
}

public void setIdAnimal(int idAnimal) {
this.idAnimal = idAnimal;
}

public String getNome_ani() {
return nome_ani;
}

public void setNome_ani(String nome_ani) {
this.nome_ani = nome_ani;
}

public String getRaca_ani() {
return raca_ani;
}

public void setRaca_ani(String raca_ani) {
this.raca_ani = raca_ani;
}

public int getIdade_ani() {
return idade_ani;
}

public void setIdade_ani(int idade_ani) {
this.idade_ani = idade_ani;
}

public String getTamanho_ani() {
return tamanho_ani;
}

public void setTamanho_ani(String tamanho_ani) {
this.tamanho_ani = tamanho_ani;
}

public Cliente getIdCliente() {
return idCliente;
}

public void setIdCliente(Cliente idCliente) {
this.idCliente = idCliente;
}

@Override
public String toString() {
return "Animal{" + "idAnimal=" + idAnimal + ", nome_ani=" + nome_ani + ", raca_ani=" + raca_ani + ", idade_ani=" + idade_ani + ", tamanho_ani=" + tamanho_ani + ", idCliente=" + idCliente + '}';
}

}