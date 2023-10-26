package model;

import java.util.ArrayList;

public class Perfil {

    private int idPerfil;
    private String nome;
    private String login_per;
    private String senha_per;
    private int status_per;
    
    private ArrayList<Menu> menus;
    private ArrayList<Menu> naoMenus;

    public Perfil() {
    }

    public Perfil(int idPerfil, String nome, String login_per, String senha_per, int status_per, ArrayList<Menu> menus, ArrayList<Menu> naoMenus) {
        this.idPerfil = idPerfil;
        this.nome = nome;
        this.login_per = login_per;
        this.senha_per = senha_per;
        this.status_per = status_per;
        this.menus = menus;
        this.naoMenus = naoMenus;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin_per() {
        return login_per;
    }

    public void setLogin_per(String login_per) {
        this.login_per = login_per;
    }

    public String getSenha_per() {
        return senha_per;
    }

    public void setSenha_per(String senha_per) {
        this.senha_per = senha_per;
    }

    public int getStatus_per() {
        return status_per;
    }

    public void setStatus_per(int status_per) {
        this.status_per = status_per;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public ArrayList<Menu> getNaoMenus() {
        return naoMenus;
    }

    public void setNaoMenus(ArrayList<Menu> naoMenus) {
        this.naoMenus = naoMenus;
    }

    @Override
    public String toString() {
        return "Perfil{" + "idPerfil=" + idPerfil + ", nome=" + nome + ", login_per=" + login_per + ", senha_per=" + senha_per + ", status_per=" + status_per + '}';
    }
    
}
