package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Cliente;
import model.Funcionario;
import model.Perfil;
import model.Produto;
import model.Servico;
import model.Venda;

public class VendaDAO extends DataBaseDAO {

    public VendaDAO() throws Exception {
    }

    public ArrayList<Venda> getLista() throws Exception {
    ArrayList<Venda> lista = new ArrayList<Venda>();
    String sql = "SELECT v.*, pr.idProduto, pr.descricao_pro, s.idServico, s.tipo_ser, c.idCliente, c.nome_cli, p.idPerfil, p.nome FROM venda v "
        + "INNER JOIN produto pr ON pr.idProduto = v.idProduto "
        + "INNER JOIN servico s ON s.idServico = v.idServico "
        + "INNER JOIN cliente c ON c.idCliente = v.idCliente "
        + "INNER JOIN perfil p ON p.idPerfil = v.idPerfil";


    this.conectar();
    PreparedStatement pstm = conn.prepareStatement(sql);
    ResultSet rs = pstm.executeQuery();
    while (rs.next()) {
        Venda v = new Venda();
        // parâmetro com o mesmo nome da coluna do banco de dados
        v.setIdVenda(rs.getInt("v.idVenda"));
        v.setValor_ven(rs.getDouble("v.valor_ven"));
        v.setData_ven(rs.getString("v.data_ven"));
        v.setFormaPagamento_ven(rs.getString("v.formaPagamento_ven"));

        Produto pr = new Produto();
        pr.setIdProduto(rs.getInt("pr.idProduto"));
        pr.setDescricao_pro(rs.getString("pr.descricao_pro"));
        v.setIdProduto(pr);

        Servico s = new Servico();
        s.setIdServico(rs.getInt("s.idServico"));
        s.setTipo_ser(rs.getString("s.tipo_ser"));
        v.setIdServico(s);

        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("c.idCliente"));
        c.setNome_cli(rs.getString("c.nome_cli"));
        v.setIdCliente(c);

        Perfil p = new Perfil();
        p.setIdPerfil(rs.getInt("p.idPerfil"));
        p.setNome(rs.getString("p.nome"));
        v.setIdPerfil(p);

        lista.add(v);
    }
        this.desconectar();
        return lista;
    }


    public boolean grava(Venda v) {
        try {
            String sql;
            this.conectar();
            if (v.getIdVenda() == 0) {
                sql = "INSERT INTO venda(valor_ven,data_ven,formaPagamento_ven,idProduto,idServico,idCliente,idPerfil) VALUES(?,?,?,?,?,?,?)";
            } else {
                sql = "UPDATE venda SET valor_ven = ?, data_ven = ?, formaPagamento_ven = ?, idProduto = ?, idServico = ?, idCliente = ?, idPerfil = ? WHERE idVenda = ?";
            }

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, v.getValor_ven());
            pstm.setString(2, v.getData_ven());
            pstm.setString(3, v.getFormaPagamento_ven());
            pstm.setInt(4, v.getIdProduto().getIdProduto());
            pstm.setInt(5, v.getIdServico().getIdServico());
            pstm.setInt(6, v.getIdCliente().getIdCliente());
            pstm.setInt(7, v.getIdPerfil().getIdPerfil());
            if (v.getIdVenda() > 0) {
                pstm.setInt(8, v.getIdVenda());
            }
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean exclui(Venda v) {
        try {
            this.conectar();
            String sql = "DELETE FROM venda WHERE idVenda=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, v.getIdVenda());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Venda getCarregaPorID(int idVenda) throws Exception {

        Venda v = new Venda();
        String sql = "SELECT v.*, pr.descricao_pro, s.tipo_ser, c.nome_cli, p.nome FROM venda v "
            + "INNER JOIN produto pr ON pr.idProduto = v.idProduto "
            + "INNER JOIN servico s ON s.idServico = v.idServico "
            + "INNER JOIN cliente c ON c.idCliente = v.idCliente "
            + "INNER JOIN perfil p ON p.idPerfil = v.idPerfil "
            + "WHERE v.idVenda=? ";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idVenda);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            v.setIdVenda(rs.getInt("idVenda"));
            v.setValor_ven(rs.getDouble("valor_ven"));
            v.setData_ven(rs.getString("data_ven"));
            v.setFormaPagamento_ven(rs.getString("formaPagamento_ven"));

            ProdutoDAO prDAO = new ProdutoDAO();
            Produto pr = prDAO.getCarregaPorID(rs.getInt("idProduto"));
            v.setIdProduto(pr);
            
            ServicoDAO sDAO = new ServicoDAO();
            Servico s = sDAO.getCarregaPorID(rs.getInt("idServico"));
            v.setIdServico(s);
            
            ClienteDAO cDAO = new ClienteDAO();
            Cliente c = cDAO.getCarregaPorID(rs.getInt("idCliente"));
            v.setIdCliente(c);

            PerfilDAO pDAO = new PerfilDAO();
            Perfil p = pDAO.getCarregaPorID(rs.getInt("idPerfil"));
            v.setIdPerfil(p);

        }
        this.desconectar();
        return v;

    }
}
