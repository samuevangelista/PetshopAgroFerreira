package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Funcionario;
import model.Perfil;

public class FuncionarioDAO extends DataBaseDAO {
    
    public FuncionarioDAO() throws Exception{}
    
    public ArrayList<Funcionario> getLista() throws Exception{
        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
        String sql = "SELECT f.*, p.nome FROM funcionario f "
                + "INNER JOIN perfil p ON p.idPerfil = f.idPerfil";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            Funcionario f = new Funcionario();
            // parâmetro com o mesmo nome da coluna do banco de dados
            f.setIdFuncionario(rs.getInt("f.idFuncionario")); 
            f.setNome_fun(rs.getString("f.nome_fun"));
            f.setCpf_fun(rs.getString("f.cpf_fun"));
            f.setEndereco_fun(rs.getString("f.endereco_fun"));
            f.setTelefone_fun(rs.getString("f.telefone_fun"));
            f.setFuncao_fun(rs.getString("f.funcao_fun"));
            f.setEmail_fun(rs.getString("f.email_fun"));
            Perfil p = new Perfil();
            p.setIdPerfil(rs.getInt("f.idPerfil"));
            p.setNome(rs.getString("p.nome"));
            f.setIdPerfil(p);
            lista.add(f);
        }
        this.desconectar();
        return lista;
}
    
    public boolean grava(Funcionario f) {
        try{
            String sql;
            this.conectar();
            if(f.getIdFuncionario()==0) {
                sql = "INSERT INTO funcionario(nome_fun,cpf_fun,endereco_fun,telefone_fun,funcao_fun,email_fun,idPerfil) VALUES(?,?,?,?,?,?,?)";
            } else {
                sql = "UPDATE funcionario SET nome_fun = ?, cpf_fun = ?, endereco_fun = ?, telefone_fun = ?, funcao_fun = ?, email_fun = ?, idPerfil = ? WHERE idFuncionario = ?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, f.getNome_fun());
            pstm.setString(2, f.getCpf_fun());
            pstm.setString(3, f.getEndereco_fun());
            pstm.setString(4, f.getTelefone_fun());
            pstm.setString(5, f.getFuncao_fun());
            pstm.setString(6, f.getEmail_fun());
            pstm.setInt(7, f.getIdPerfil().getIdPerfil());
            if(f.getIdFuncionario() > 0) {
                pstm.setInt(8, f.getIdFuncionario());
            }
            pstm.execute();
            this.desconectar();
            return true;
            
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean exclui(Funcionario f) {
        try{
            this.conectar();
            String sql = "DELETE FROM funcionario WHERE idFuncionario=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,f.getIdFuncionario());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public Funcionario getCarregaPorID(int idFuncionario) throws Exception {
        
        Funcionario f = new Funcionario();
        String sql = "SELECT f.*, p.nome FROM funcionario f "
                + "INNER JOIN perfil p ON p.idPerfil = f.idPerfil "
                + "WHERE f.idFuncionario=? ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idFuncionario);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()) {      
            f.setIdFuncionario(rs.getInt("f.idFuncionario"));
            f.setNome_fun(rs.getString("f.nome_fun"));
            f.setCpf_fun(rs.getString("f.cpf_fun"));
            f.setEndereco_fun(rs.getString("f.endereco_fun"));
            f.setTelefone_fun(rs.getString("f.telefone_fun"));
            f.setFuncao_fun(rs.getString("f.funcao_fun"));
            f.setEmail_fun(rs.getString("f.email_fun"));
            Perfil p = new Perfil();
            p.setIdPerfil(rs.getInt("f.idPerfil"));
            p.setNome(rs.getString("p.nome"));
            f.setIdPerfil(p);
        }
        this.desconectar();
        return f;
    }
    
}
