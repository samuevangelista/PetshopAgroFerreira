/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Perfil;
import model.Produto;
import model.Servico;
import model.Venda;
import model.dao.VendaDAO;

/**
 *
 * @author maria
 */
public class GerenciarVenda extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";

        String acao = request.getParameter("acao");
        String idVenda = request.getParameter("idVenda");

        Venda v = new Venda();

        try {
            VendaDAO vDAO = new VendaDAO();
            if (acao.equals("alterar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                v = vDAO.getCarregaPorID(Integer.parseInt(idVenda));
                if (v.getIdVenda() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_venda.jsp");
                    request.setAttribute("venda", v);
                    disp.forward(request, response);
                } else {
                    mensagem = "Venda não encontrada!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
            if (acao.equals("deletar")) {
                if(GerenciarLogin.verificarPermissao(request, response)) {
                v.setIdVenda(Integer.parseInt(idVenda));
                if (vDAO.exclui(v)) {
                    mensagem = "Excluído com sucesso!";
                } else {
                    mensagem = "Erro ao excluir a venda!";
                }
                } else {
                    mensagem = "Acesso Negado!";
                }
            }
        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao Executar!";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_venda.jsp'");
        out.println("</script>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String idVenda = request.getParameter("idVenda");
        String valor_ven = request.getParameter("valor_ven");
        String data_ven = request.getParameter("data_ven");
        String formaPagamento_ven = request.getParameter("formaPagamento_ven");
        String idProduto = request.getParameter("idProduto");
        String idServico = request.getParameter("idServico");
        String idCliente = request.getParameter("idCliente");
        String idPerfil = request.getParameter("idPerfil");

        String mensagem = "";

        Venda v = new Venda();
        if (!idVenda.isEmpty()) {
            v.setIdVenda(Integer.parseInt(idVenda));
        }
        if (valor_ven.equals("") || data_ven.equals("") || formaPagamento_ven.equals("") || idProduto.equals("") || idServico.equals("") || idCliente.equals("") || idPerfil.equals("")) {
            mensagem = "Campos obrigatórios deverão ser preenchidos";
        } else {
            v.setValor_ven(Double.parseDouble(valor_ven));
            v.setData_ven(data_ven);
            v.setFormaPagamento_ven(formaPagamento_ven);

            Produto pr = new Produto();
            pr.setIdProduto(Integer.parseInt(idProduto));
            v.setIdProduto(pr);
            
            Servico s = new Servico();
            s.setIdServico(Integer.parseInt(idServico));
            v.setIdServico(s);
            
            Cliente c = new Cliente();
            c.setIdCliente(Integer.parseInt(idCliente));
            v.setIdCliente(c);

            Perfil p = new Perfil();
            p.setIdPerfil(Integer.parseInt(idPerfil));
            v.setIdPerfil(p);

            try {
                VendaDAO vDAO = new VendaDAO();
                if (vDAO.grava(v)) {
                    mensagem = "Gravado com Sucesso!";
                } else {
                    mensagem = "Erro ao Gravar no Banco de Dados!";
                }

            } catch (Exception e) {
                out.print(e);
                mensagem = "Erro ao executar";
            }
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_venda.jsp'");
        out.println("</script>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
