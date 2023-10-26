package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Produto;

public class ProdutoDAO extends DataBaseDAO {

    public ProdutoDAO() throws Exception {
    }

    public ArrayList<Produto> getLista() throws Exception {
        ArrayList<Produto> lista = new ArrayList<Produto>();
        String sql = "SELECT * FROM produto";

        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Produto p = new Produto();
            // parâmetro com o mesmo nome da coluna do banco de dados
            p.setIdProduto(rs.getInt("idProduto"));
            p.setTipo_pro(rs.getString("tipo_pro"));
            p.setQuantidade_pro(rs.getInt("quantidade_pro"));
            p.setCodBarras_pro(rs.getString("codBarras_pro"));
            p.setValor_pro(rs.getDouble("valor_pro"));
            p.setDescricao_pro(rs.getString("descricao_pro"));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    }

    public boolean grava(Produto p) {
        try {
            String sql;
            this.conectar();
            if (p.getIdProduto() == 0) {
                sql = "INSERT INTO produto(tipo_pro,quantidade_pro,codBarras_pro,valor_pro,descricao_pro) VALUES(?,?,?,?,?)";
            } else {
                sql = "UPDATE produto SET tipo_pro = ?, quantidade_pro = ?, codBarras_pro = ?, valor_pro = ?, descricao_pro = ? WHERE idProduto = ?";
            }

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, p.getTipo_pro());
            pstm.setInt(2, p.getQuantidade_pro());
            pstm.setString(3, p.getCodBarras_pro());
            pstm.setDouble(4, p.getValor_pro());
            pstm.setString(5, p.getDescricao_pro());
            
            if (p.getIdProduto() > 0) {
                pstm.setInt(6, p.getIdProduto());
            }
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean exclui(Produto p) {
        try {
            this.conectar();
            String sql = "DELETE FROM produto WHERE idProduto=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, p.getIdProduto());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Produto getCarregaPorID(int idProduto) throws Exception {

        Produto p = new Produto();
        String sql = "SELECT * FROM produto WHERE idProduto=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idProduto);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            p.setIdProduto(rs.getInt("idProduto"));
            p.setTipo_pro(rs.getString("tipo_pro"));
            p.setQuantidade_pro(rs.getInt("quantidade_pro"));
            p.setCodBarras_pro(rs.getString("codBarras_pro"));
            p.setValor_pro(rs.getDouble("valor_pro"));
            p.setDescricao_pro(rs.getString("descricao_pro"));

        }
        this.desconectar();
        return p;

    }
}
