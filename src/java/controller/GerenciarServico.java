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
import model.Funcionario;
import model.Servico;
import model.dao.ServicoDAO;

/**
 *
 * @author maria
 */
public class GerenciarServico extends HttpServlet {

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
        String idServico = request.getParameter("idServico");

        Servico s = new Servico();

        try {
            ServicoDAO sDAO = new ServicoDAO();
            if (acao.equals("alterar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                s = sDAO.getCarregaPorID(Integer.parseInt(idServico));
                if (s.getIdServico() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_servico.jsp");
                    request.setAttribute("servico", s);
                    disp.forward(request, response);
                } else {
                    mensagem = "Serviço não encontrado!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
            if (acao.equals("deletar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                s.setIdServico(Integer.parseInt(idServico));
                if (sDAO.exclui(s)) {
                    mensagem = "Excluído com sucesso!";
                } else {
                    mensagem = "Erro ao excluir o Serviço!";
                }
                //} else {
                //    mensagem = "Acesso Negado!";
                //}
            }
        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao Executar!";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_servico.jsp'");
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
        String idServico = request.getParameter("idServico");
        String tipo_ser = request.getParameter("tipo_ser");
        String valor_ser = request.getParameter("valor_ser");
        String idFuncionario = request.getParameter("idFuncionario");
        String idAnimal = request.getParameter("idAnimal");

        String mensagem = "";

        Servico s = new Servico();
        if (!idServico.isEmpty()) {
            s.setIdServico(Integer.parseInt(idServico));
        }
        if (tipo_ser.equals("") || valor_ser.equals("") || idFuncionario.equals("") || idAnimal.equals("")) {
            mensagem = "Campos obrigatórios deverão ser preenchidos";
        } else {
            s.setTipo_ser(tipo_ser);
            s.setValor_ser(Double.parseDouble(valor_ser));
            Funcionario f = new Funcionario();
            f.setIdFuncionario(Integer.parseInt(idFuncionario));
            s.setIdFuncionario(f);
            Animal a = new Animal();
            a.setIdAnimal(Integer.parseInt(idAnimal));
            s.setIdAnimal(a);

            try {
                ServicoDAO sDAO = new ServicoDAO();
                if (sDAO.grava(s)) {
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
        out.println("location.href='listar_servico.jsp'");
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
