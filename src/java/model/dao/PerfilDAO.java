package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Menu;
import model.Perfil;

public class PerfilDAO extends DataBaseDAO {
    
    public PerfilDAO() throws Exception{}
    
    public ArrayList<Perfil> getLista() throws Exception{
        ArrayList<Perfil> lista = new ArrayList<Perfil>();
        String SQL = "SELECT * FROM perfil";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()) {
            Perfil p = new Perfil();
            p.setIdPerfil(rs.getInt("idPerfil")); // parâmetro com o mesmo nome da coluna do banco de dados
            p.setNome(rs.getString("nome"));
            p.setLogin_per(rs.getString("login_per"));
            p.setSenha_per(rs.getString("senha_per"));
            p.setStatus_per(rs.getInt("status_per"));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean grava(Perfil p) {
        try{
            String sql;
            this.conectar();
            if(p.getIdPerfil()==0) {
                sql = "INSERT INTO perfil(nome,login_per,senha_per,status_per) VALUES(?,?,?,?)";
            } else {
                sql = "UPDATE perfil SET nome = ?, login_per = ?, senha_per = ?, status_per = ? WHERE idPerfil = ?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, p.getNome());
            pstm.setString(2, p.getLogin_per());
            pstm.setString(3, p.getSenha_per());
            pstm.setInt(4, p.getStatus_per());
            if(p.getIdPerfil() > 0) {
                pstm.setInt(5, p.getIdPerfil());
            }
            pstm.execute();
            this.desconectar();
            return true;
            
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean exclui(Perfil p) {
        try{
            this.conectar();
            String sql = "UPDATE perfil SET status_per=2 WHERE idPerfil=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,p.getIdPerfil());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public ArrayList<Menu> menusVinculadosPorPerfil(int idPerfil) throws Exception {
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT m.* FROM perfil_menu as mp, menu as m "
                + "WHERE mp.idMenu = m.idMenu AND mp.idPerfil = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idPerfil);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("m.idMenu"));
            m.setNome_men(rs.getString("m.nome_men"));
            m.setLink_men(rs.getString("m.link_men"));
            m.setExibir_men(rs.getInt("m.exibir_men"));
            lista.add(m);
        }
        this.desconectar();
        return lista;
    }
    
    public ArrayList<Menu> menusNaoVinculadosPorPerfil(int idPerfil) throws Exception {
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT m.* FROM menu as m "
                + "WHERE m.idMenu NOT IN (SELECT mp.idMenu FROM perfil_menu as mp WHERE mp.idPerfil=?)";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idPerfil);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("m.idMenu"));
            m.setNome_men(rs.getString("m.nome_men"));
            m.setLink_men(rs.getString("m.link_men"));
            m.setExibir_men(rs.getInt("m.exibir_men"));
            lista.add(m);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean vincular(int idMenu, int idPerfil) {
        try {
            String sql = "INSERT INTO perfil_menu (idMenu, idPerfil) "
                    + "VALUES(?,?)";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idMenu);
            pstm.setInt(2, idPerfil);
            pstm.execute();
            this.desconectar();
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean desvincular(int idMenu, int idPerfil) {
        try {
            String sql = "DELETE FROM perfil_menu WHERE idMenu=? AND idPerfil=?";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idMenu);
            pstm.setInt(2, idPerfil);
            pstm.execute();
            this.desconectar();
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public Perfil getCarregaPorID(int idPerfil) throws Exception {
        
        Perfil p = new Perfil();
        String sql = "SELECT * FROM perfil WHERE idPerfil=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idPerfil);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            p.setIdPerfil(rs.getInt("idPerfil"));
            p.setNome(rs.getString("nome"));
            p.setLogin_per(rs.getString("login_per"));
            p.setSenha_per(rs.getString("senha_per"));
            p.setStatus_per(rs.getInt("status_per"));
            p.setMenus(menusVinculadosPorPerfil(idPerfil));
            p.setNaoMenus(menusNaoVinculadosPorPerfil(idPerfil));
        }
        this.desconectar();
        return p;
    }
    
    public Perfil getRecuperarPerfil(String login_per) {
        
        Perfil p = new Perfil();
        String sql = "SELECT p.* FROM perfil p "
                + "WHERE p.login_per=?";
        
        try {
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, login_per);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                p.setIdPerfil(rs.getInt("p.idPerfil"));
                p.setNome(rs.getString("p.nome"));
                p.setLogin_per(rs.getString("p.login_per"));
                p.setSenha_per(rs.getString("p.senha_per"));
                p.setStatus_per(rs.getInt("p.status_per"));
            }
            this.desconectar();
            return p;
            
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    
}
