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
import model.Animal;
import model.Cliente;
import model.dao.AnimalDAO;

/**
 *
 * @author ybiel
 */
public class GerenciarAnimal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String idAnimal = request.getParameter("idAnimal");
        
        Animal a = new Animal();
        
        try {
            AnimalDAO aDAO = new AnimalDAO();
            if(acao.equals("alterar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                a = aDAO.getCarregaPorID(Integer.parseInt(idAnimal));
                if(a.getIdAnimal() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_animal.jsp");
                    request.setAttribute("animal", a);
                    disp.forward(request, response);
                } else {
                    mensagem = "Funcionário não encontrado!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
            if(acao.equals("deletar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                a.setIdAnimal(Integer.parseInt(idAnimal));
                if(aDAO.exclui(a)) {
                    mensagem = "Excluído com sucesso!";
                } else {
                    mensagem = "Erro ao excluir o funcionário!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
        } catch(Exception e) {
            out.print(e);
            mensagem = "Erro ao Executar!";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_animal.jsp'");
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
        String idAnimal = request.getParameter("idAnimal");
        String nome_ani = request.getParameter("nome_ani");
        String raca_ani = request.getParameter("raca_ani");
        String idade_ani = request.getParameter("idade_ani");
        String tamanho_ani = request.getParameter("tamanho_ani");
        String idCliente = request.getParameter("idCliente");
        
        String mensagem="";
        
        Animal a = new Animal();
        if(!idAnimal.isEmpty()) {
            a.setIdAnimal(Integer.parseInt(idAnimal));
        } 
        if(nome_ani.equals("") || raca_ani.equals("") || idade_ani.equals("") || tamanho_ani.equals("") || idCliente.equals("")) {
            mensagem = "Campos obrigatórios deverão ser preenchidos";
        } else {
            a.setNome_ani(nome_ani);
            a.setRaca_ani(raca_ani);
            a.setIdade_ani(Integer.parseInt(idade_ani));
            a.setTamanho_ani(tamanho_ani);
            Cliente c = new Cliente();
            c.setIdCliente(Integer.parseInt(idCliente));
            a.setIdCliente(c);
            
            try {
            AnimalDAO aDAO = new AnimalDAO();
                if(aDAO.grava(a)) {
                mensagem = "Gravado com Sucesso!";
            } else {
                mensagem = "Erro ao Gravar no Banco de Dados!";
            } 
        
        } catch(Exception e) {
            out.print(e);
            mensagem = "Erro ao executar";
        }
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_animal.jsp'");
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
