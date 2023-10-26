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
import model.dao.ClienteDAO;

/**
 *
 * @author ybiel
 */
public class GerenciarCliente extends HttpServlet {

    

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
        String idCliente = request.getParameter("idCliente");
        
        Cliente c = new Cliente();
        
        try {
            ClienteDAO cDAO = new ClienteDAO();
            if(acao.equals("alterar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                c = cDAO.getCarregaPorID(Integer.parseInt(idCliente));
                if(c.getIdCliente() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_cliente.jsp");
                    request.setAttribute("cliente", c);
                    disp.forward(request, response);
                } else {
                    mensagem = "Cliente não encontrado!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
            if(acao.equals("deletar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                c.setIdCliente(Integer.parseInt(idCliente));
                if(cDAO.exclui(c)) {
                    mensagem = "Excluído com sucesso!";
                } else {
                    mensagem = "Erro ao excluir o cliente";
                }
                //} else {
                //   mensagem = "Acesso Negado!";
                //}
            }
        } catch(Exception e) {
            out.print(e);
            mensagem = "Erro ao Executar!";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_cliente.jsp'");
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
        String idCliente = request.getParameter("idCliente");
        String nome_cli = request.getParameter("nome_cli");
        String telefone_cli = request.getParameter("telefone_cli");
        String endereco_cli = request.getParameter("endereco_cli");
        String email_cli = request.getParameter("email_cli");
 
        String mensagem="";
        
        
        try {
        ClienteDAO cDAO = new ClienteDAO();
        Cliente c = new Cliente();
        if(!idCliente.isEmpty()) {
            c.setIdCliente(Integer.parseInt(idCliente));
        } 
        
        if(nome_cli.equals("") || telefone_cli.equals("") || endereco_cli.equals("")) {
            mensagem = "Campos obrigatórios deverão ser preenchidos";
        } else {
            c.setNome_cli(nome_cli);
            c.setTelefone_cli(telefone_cli);
            c.setEndereco_cli(endereco_cli);
            c.setEmail_cli(email_cli);
            if(cDAO.grava(c)) {
                mensagem = "Gravado com Sucesso!";
            } else {
                mensagem = "Erro ao Gravar no Banco de Dados!";
            }
        }
        } catch(Exception e) {
            out.print(e);
            mensagem = "Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_cliente.jsp'");
        out.println("</script>");
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
