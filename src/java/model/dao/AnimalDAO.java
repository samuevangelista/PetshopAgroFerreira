package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Animal;
import model.Cliente;

public class AnimalDAO extends DataBaseDAO {

    public AnimalDAO() throws Exception {
    }

    public ArrayList getLista() throws Exception {
        ArrayList lista = new ArrayList();
        String sql = "SELECT a.*, c.nome_cli FROM animal a "
                + "INNER JOIN cliente c ON c.idCliente = a.idCliente";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Animal a = new Animal();
// parâmetro com o mesmo nome da coluna do banco de dados
            a.setIdAnimal(rs.getInt("a.idAnimal"));
            a.setNome_ani(rs.getString("a.nome_ani"));
            a.setRaca_ani(rs.getString("a.raca_ani"));
            a.setIdade_ani(rs.getInt("a.idade_ani"));
            a.setTamanho_ani(rs.getString("a.tamanho_ani"));
            Cliente c = new Cliente();
            c.setIdCliente(rs.getInt("a.idCliente"));
            c.setNome_cli(rs.getString("c.nome_cli"));
            a.setIdCliente(c);
            lista.add(a);
        }
        this.desconectar();
        return lista;
    }

    public boolean grava(Animal a) {
        try {
            String sql;
            this.conectar();
            if (a.getIdAnimal() == 0) {
                sql = "INSERT INTO animal(nome_ani,raca_ani,idade_ani,tamanho_ani,idCliente) VALUES(?,?,?,?,?)";
            } else {
                sql = "UPDATE animal SET nome_ani = ?, raca_ani= ?, idade_ani = ?, tamanho_ani = ?, idCliente = ? WHERE idAnimal = ?";
            }

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, a.getNome_ani());
            pstm.setString(2, a.getRaca_ani());
            pstm.setInt(3, a.getIdade_ani());
            pstm.setString(4, a.getTamanho_ani());
            pstm.setInt(5, a.getIdCliente().getIdCliente());
            if (a.getIdAnimal() > 0) {
                pstm.setInt(6, a.getIdAnimal());
            }
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean exclui(Animal a) {
        try {
            this.conectar();
            String sql = "DELETE FROM animal WHERE idAnimal=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, a.getIdAnimal());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Animal getCarregaPorID(int idAnimal) throws Exception {

        Animal a = new Animal();
        String sql = "SELECT a.*, c.nome_cli FROM animal a "
                + "INNER JOIN cliente c ON c.idCliente = a.idCliente "
                + "WHERE a.idAnimal=? ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idAnimal);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            a.setIdAnimal(rs.getInt("a.idAnimal"));
            a.setNome_ani(rs.getString("a.nome_ani"));
            a.setRaca_ani(rs.getString("a.raca_ani"));
            a.setIdade_ani(rs.getInt("a.idade_ani"));
            a.setTamanho_ani(rs.getString("a.tamanho_fun"));
            Cliente c = new Cliente();
            c.setIdCliente(rs.getInt("a.idCliente"));
            c.setNome_cli(rs.getString("c.nome_cli"));
            a.setIdCliente(c);
        }
        this.desconectar();
        return a;
    }

}
