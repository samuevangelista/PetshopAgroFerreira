package model;

public class Menu {
    
    private int idMenu;
    private String nome_men;
    private String link_men;
    private int exibir_men;

    public Menu() {
    }

    public Menu(int idMenu, String nome_men, String link_men, int exibir_men) {
        this.idMenu = idMenu;
        this.nome_men = nome_men;
        this.link_men = link_men;
        this.exibir_men = exibir_men;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNome_men() {
        return nome_men;
    }

    public void setNome_men(String nome_men) {
        this.nome_men = nome_men;
    }

    public String getLink_men() {
        return link_men;
    }

    public void setLink_men(String link_men) {
        this.link_men = link_men;
    }

    public int getExibir_men() {
        return exibir_men;
    }

    public void setExibir_men(int exibir_men) {
        this.exibir_men = exibir_men;
    }

    @Override
    public String toString() {
        return "Menu{" + "idMenu=" + idMenu + ", nome_men=" + nome_men + ", link_men=" + link_men + ", exibir_men=" + exibir_men + '}';
    }
    
}
