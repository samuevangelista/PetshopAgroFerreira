package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDAO extends DataBaseDAO {
    
    public ClienteDAO() throws Exception{}
    
    public ArrayList<Cliente> getLista() throws Exception{
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String SQL = "SELECT * FROM cliente";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()) {
            Cliente c = new Cliente();
            c.setIdCliente(rs.getInt("idCliente")); // parâmetro com o mesmo nome da coluna do banco de dados
            c.setNome_cli(rs.getString("nome_cli"));
            c.setTelefone_cli(rs.getString("telefone_cli"));
            c.setEndereco_cli(rs.getString("endereco_cli"));
            c.setEmail_cli(rs.getString("email_cli"));
            lista.add(c);
        }
        this.desconectar();
        return lista;
}
    
    public boolean grava(Cliente c) {
        try{
            String sql;
            this.conectar();
            if(c.getIdCliente()==0) {
                sql = "INSERT INTO cliente(nome_cli,telefone_cli,endereco_cli,email_cli) VALUES(?,?,?,?)";
            } else {
                sql = "UPDATE cliente SET nome_cli = ?, telefone_cli = ?, endereco_cli = ?, email_cli = ? WHERE idCliente = ?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, c.getNome_cli());
            pstm.setString(2, c.getTelefone_cli());
            pstm.setString(3, c.getEndereco_cli());
            pstm.setString(4, c.getEmail_cli());
            if(c.getIdCliente() > 0) {
                pstm.setInt(5, c.getIdCliente());
            }
            pstm.execute();
            this.desconectar();
            return true;
            
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean exclui(Cliente c) {
        try{
            this.conectar();
            String sql = "DELETE FROM cliente WHERE idCliente=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,c.getIdCliente());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public Cliente getCarregaPorID(int idCliente) throws Exception {
        
        Cliente c = new Cliente();
        String sql = "SELECT * FROM cliente WHERE idCliente=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idCliente);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            c.setIdCliente(rs.getInt("idCliente"));
            c.setNome_cli(rs.getString("nome_cli"));
            c.setTelefone_cli(rs.getString("telefone_cli"));
            c.setEndereco_cli(rs.getString("endereco_cli"));
            c.setEmail_cli(rs.getString("email_cli"));
        }
        this.desconectar();
        return c;
    }
    
}