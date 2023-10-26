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
import model.Produto;
import model.dao.ProdutoDAO;

/**
 *
 * @author maria
 */
public class GerenciarProduto extends HttpServlet {

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
        String idProduto = request.getParameter("idProduto");

        Produto p = new Produto();

        try {
            ProdutoDAO pDAO = new ProdutoDAO();
            if (acao.equals("alterar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                p = pDAO.getCarregaPorID(Integer.parseInt(idProduto));
                if (p.getIdProduto() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_produto.jsp");
                    request.setAttribute("produto", p);
                    disp.forward(request, response);
                } else {
                    mensagem = "Produto não encontrado!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
            if (acao.equals("deletar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                p.setIdProduto(Integer.parseInt(idProduto));
                if (pDAO.exclui(p)) {
                    mensagem = "Excluído com sucesso!";
                } else {
                    mensagem = "Erro ao excluir o produto!";
                }
                //} else {
                //   mensagem = "Acesso Negado!";
                //}
            }
        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao Executar!";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_produto.jsp';");
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
        String idProduto = request.getParameter("idProduto");
        String tipo_pro = request.getParameter("tipo_pro");
        String quantidade_pro = request.getParameter("quantidade_pro");
        String codBarras_pro = request.getParameter("codBarras_pro");
        String valor_pro = request.getParameter("valor_pro");
        String descricao_pro = request.getParameter("descricao_pro");

        String mensagem = "";

        Produto p = new Produto();
        if (!idProduto.isEmpty()) {
            p.setIdProduto(Integer.parseInt(idProduto));
        }
        if (tipo_pro.equals("") || quantidade_pro.equals("") || codBarras_pro.equals("") || valor_pro.equals("") || descricao_pro.equals("")) {
            mensagem = "Campos obrigatórios deverão ser preenchidos";
        } else {
            p.setTipo_pro(tipo_pro);
            p.setQuantidade_pro(Integer.parseInt(quantidade_pro));
            p.setCodBarras_pro(codBarras_pro);
            p.setValor_pro(Double.parseDouble(valor_pro));
            p.setDescricao_pro(descricao_pro);

            try {
                ProdutoDAO pDAO = new ProdutoDAO();
                if (pDAO.grava(p)) {
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
        out.println("location.href='listar_produto.jsp'");
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
