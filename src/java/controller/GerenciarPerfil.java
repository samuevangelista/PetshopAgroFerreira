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
import model.Perfil;
import model.dao.PerfilDAO;

/**
 *
 * @author ybiel
 */
public class GerenciarPerfil extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        String idPerfil = request.getParameter("idPerfil");
        
        Perfil p = new Perfil();
        
        try {
            PerfilDAO pDAO = new PerfilDAO();
            if(acao.equals("alterar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                p = pDAO.getCarregaPorID(Integer.parseInt(idPerfil));
                if(p.getIdPerfil() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_perfil.jsp");
                    request.setAttribute("perfil", p);
                    disp.forward(request, response);
                } else {
                    mensagem = "Perfil não encontrado!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
            if(acao.equals("deletar")) {
                if(GerenciarLogin.verificarPermissao(request, response)) {
                p.setIdPerfil(Integer.parseInt(idPerfil));
                if(pDAO.exclui(p)) {
                    mensagem = "Desativado com sucesso!";
                } else {
                    mensagem = "Erro ao desativar o perfil!";
                }
                } else {
                    mensagem = "Acesso Negado!";
                }
            }
        } catch(Exception e) {
            out.print(e);
            mensagem = "Erro ao Executar!";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_perfil.jsp'");
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
        String idPerfil = request.getParameter("idPerfil");
        String nome = request.getParameter("nome");
        String login_per = request.getParameter("login_per");
        String senha_per = request.getParameter("senha_per");
        String status_per = request.getParameter("status_per");
 
        String mensagem="";
        
        
        try {
        PerfilDAO pDAO = new PerfilDAO();
        Perfil p = new Perfil();
        if(!idPerfil.isEmpty()) {
            p.setIdPerfil(Integer.parseInt(idPerfil));
        } 
        
        if(nome.equals("") || nome.isEmpty() || login_per.equals("") || login_per.isEmpty() 
                || senha_per.equals("") || senha_per.isEmpty() || status_per.equals("") || status_per.isEmpty()) {
            mensagem = "Campos obrigatórios deverão ser preenchidos";
        } else {
            p.setNome(nome);
            p.setLogin_per(login_per);
            p.setSenha_per(senha_per);
            p.setStatus_per(Integer.parseInt(status_per));
            if(pDAO.grava(p)) {
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
        out.println("location.href='listar_perfil.jsp'");
        out.println("</script>");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
