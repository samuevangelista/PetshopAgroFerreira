package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Menu;

public class MenuDAO extends DataBaseDAO {
    
    public MenuDAO() throws Exception{}
    
    public ArrayList<Menu> getLista() throws Exception {
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT * FROM menu";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("idMenu"));
            m.setNome_men(rs.getString("nome_men"));
            m.setLink_men(rs.getString("link_men"));
            m.setExibir_men(rs.getInt("exibir_men"));
            lista.add(m);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean grava(Menu m) {
        try {
            String sql;
            this.conectar();
            if(m.getIdMenu()==0) {
                sql = "INSERT INTO menu (nome_men, link_men, exibir_men) VALUES (?,?,?)";
            } else {
                sql = "UPDATE menu SET nome_men=?, link_men=?, exibir_men=? WHERE idMenu=?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, m.getNome_men());
            pstm.setString(2, m.getLink_men());
            pstm.setInt(3, m.getExibir_men());
            if(m.getIdMenu()>0) {
                pstm.setInt(4, m.getIdMenu());
            }
            pstm.execute();
            this.desconectar();
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean exclui(Menu m) {
        try{
            this.conectar();
            String sql = "DELETE FROM menu WHERE idMenu=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,m.getIdMenu());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public Menu getCarregaPorID(int idMenu) throws Exception {
        Menu m = new Menu();
        String sql = "SELECT * FROM menu WHERE idMenu=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idMenu);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()) {
            m.setIdMenu(idMenu);
            m.setNome_men(rs.getString("nome_men"));
            m.setLink_men(rs.getString("link_men"));
            m.setExibir_men(rs.getInt("exibir_men"));
        }
        this.desconectar();
        return m;
    }
    
}
