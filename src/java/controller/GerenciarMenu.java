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
import model.Menu;
import model.dao.MenuDAO;

/**
 *
 * @author ybiel
 */
public class GerenciarMenu extends HttpServlet {


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
        String idMenu = request.getParameter("idMenu");
        
        Menu m = new Menu();
        
        try {
            MenuDAO mDAO = new MenuDAO();
            if(acao.equals("alterar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                    
                
                m = mDAO.getCarregaPorID(Integer.parseInt(idMenu));
                if(m.getIdMenu() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu.jsp");
                    request.setAttribute("menu", m);
                    disp.forward(request, response);
                } else {
                    mensagem = "Menu não encontrado!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
            if(acao.equals("deletar")) {
                if(GerenciarLogin.verificarPermissao(request, response)) {
                m.setIdMenu(Integer.parseInt(idMenu));
                if(mDAO.exclui(m)) {
                    mensagem = "Excluído com sucesso!";
                } else {
                    mensagem = "Erro ao excluir o menu";
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
        out.println("location.href='listar_menu.jsp'");
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
        String idMenu = request.getParameter("idMenu");
        String nome_men = request.getParameter("nome_men");
        String link_men = request.getParameter("link_men");
        String exibir_men = request.getParameter("exibir_men");
 
        String mensagem="";
        
        Menu m = new Menu();
        
        try {
        MenuDAO mDAO = new MenuDAO();
        if(!idMenu.isEmpty()) {
            m.setIdMenu(Integer.parseInt(idMenu));
        } 
        
        if(nome_men.equals("") || link_men.equals("") || exibir_men.equals("")) {
            mensagem = "Campos obrigatórios deverão ser preenchidos";
        } else {
            m.setNome_men(nome_men);
            m.setLink_men(link_men);
            m.setExibir_men(Integer.parseInt(exibir_men));
            if(mDAO.grava(m)) {
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
        out.println("location.href='listar_menu.jsp'");
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
