package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Perfil;
import model.dao.FuncionarioDAO;

public class GerenciarFuncionario extends HttpServlet {

    

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
        String idFuncionario = request.getParameter("idFuncionario");
        
        Funcionario f = new Funcionario();
        
        try {
            FuncionarioDAO fDAO = new FuncionarioDAO();
            if(acao.equals("alterar")) {
                //if(GerenciarLogin.verificarPermissao(request, response)) {
                f = fDAO.getCarregaPorID(Integer.parseInt(idFuncionario));
                if(f.getIdFuncionario() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_funcionario.jsp");
                    request.setAttribute("funcionario", f);
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
                f.setIdFuncionario(Integer.parseInt(idFuncionario));
                if(fDAO.exclui(f)) {
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
        out.println("location.href='listar_funcionario.jsp'");
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
        String idFuncionario = request.getParameter("idFuncionario");
        String nome_fun = request.getParameter("nome_fun");
        String cpf_fun = request.getParameter("cpf_fun");
        String endereco_fun = request.getParameter("endereco_fun");
        String telefone_fun = request.getParameter("telefone_fun");
        String funcao_fun = request.getParameter("funcao_fun");
        String email_fun = request.getParameter("email_fun");
        String idPerfil = request.getParameter("idPerfil");
        
        String mensagem="";
        
        Funcionario f = new Funcionario();
        if(!idFuncionario.isEmpty()) {
            f.setIdFuncionario(Integer.parseInt(idFuncionario));
        } 
        if(nome_fun.equals("") || cpf_fun.equals("") || endereco_fun.equals("") || telefone_fun.equals("") || funcao_fun.equals("") || idPerfil.equals("")) {
            mensagem = "Campos obrigatórios deverão ser preenchidos";
        } else {
            f.setNome_fun(nome_fun);
            f.setCpf_fun(cpf_fun);
            f.setEndereco_fun(endereco_fun);
            f.setTelefone_fun(telefone_fun);
            f.setFuncao_fun(funcao_fun);
            f.setEmail_fun(email_fun);
            Perfil p = new Perfil();
            p.setIdPerfil(Integer.parseInt(idPerfil));
            f.setIdPerfil(p);
            
            try {
            FuncionarioDAO fDAO = new FuncionarioDAO();
                if(fDAO.grava(f)) {
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
        out.println("location.href='listar_funcionario.jsp'");
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
