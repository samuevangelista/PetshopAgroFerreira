package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Animal;
import model.Funcionario;
import model.Servico;

public class ServicoDAO extends DataBaseDAO {

    public ServicoDAO() throws Exception {
    }

    public ArrayList<Servico> getLista() throws Exception {
        ArrayList<Servico> lista = new ArrayList<Servico>();
        String sql = "SELECT s.*, f.nome_fun, a.nome_ani FROM servico s "
            + "INNER JOIN funcionario f ON f.idFuncionario = s.idFuncionario "
            + "INNER JOIN animal a ON a.idAnimal = s.idAnimal";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Servico s = new Servico();
            // parâmetro com o mesmo nome da coluna do banco de dados
            s.setIdServico(rs.getInt("s.idServico"));
            s.setTipo_ser(rs.getString("s.tipo_ser"));
            s.setValor_ser(rs.getDouble("s.valor_ser"));
            Funcionario f = new Funcionario();
            f.setIdFuncionario(rs.getInt("s.idFuncionario"));
            f.setNome_fun(rs.getString("f.nome_fun"));
            s.setIdFuncionario(f);
            Animal a = new Animal();
            a.setIdAnimal(rs.getInt("s.idAnimal"));
            a.setNome_ani(rs.getString("a.nome_ani"));
            s.setIdAnimal(a);
            lista.add(s);
        }
        this.desconectar();
        return lista;
    }

    public boolean grava(Servico s) {
        try {
            String sql;
            this.conectar();
            if (s.getIdServico() == 0) {
                sql = "INSERT INTO servico(tipo_ser,valor_ser,idFuncionario,idAnimal) VALUES(?,?,?,?)";
            } else {
                sql = "UPDATE servico SET tipo_ser = ?, valor_ser = ?, idFuncionario = ?, idAnimal = ? WHERE idServico = ?";
            }

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, s.getTipo_ser());
            pstm.setDouble(2, s.getValor_ser());
            pstm.setInt(3, s.getIdFuncionario().getIdFuncionario());
            pstm.setInt(4, s.getIdAnimal().getIdAnimal());
            if (s.getIdServico() > 0) {
                pstm.setInt(5, s.getIdServico());
            }
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean exclui(Servico s) {
        try {
            this.conectar();
            String sql = "DELETE FROM servico WHERE idServico=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, s.getIdServico());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Servico getCarregaPorID(int idServico) throws Exception {

        Servico s = new Servico();
        String sql = "SELECT s.*, f.nome_fun, a.nome_ani FROM servico s "
                + "INNER JOIN funcionario f ON f.idFuncionario = s.idFuncionario "
                + "INNER JOIN animal a ON a.idAnimal = s.idAnimal "
                + "WHERE s.idServico=? ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idServico);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            s.setIdServico(rs.getInt("idServico"));
            s.setTipo_ser(rs.getString("tipo_ser"));
            s.setValor_ser(rs.getDouble("valor_ser"));
            Funcionario f = new Funcionario();
            f.setIdFuncionario(rs.getInt("s.idFuncionario"));
            f.setNome_fun(rs.getString("f.nome_fun"));
            s.setIdFuncionario(f);
            Animal a = new Animal();
            a.setIdAnimal(rs.getInt("s.idAnimal"));
            a.setNome_ani(rs.getString("a.nome_ani"));
            s.setIdAnimal(a);

        }
        this.desconectar();
        return s;

    }

}
