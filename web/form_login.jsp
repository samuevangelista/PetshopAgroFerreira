<%-- 
    Document   : form_login
    Created on : 20/06/2023, 20:43:49
    Author     : ybiel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Formulário de Login</h1>
        <%
          String mensagem = (String) request.getSession().getAttribute("mensagem");
          if(mensagem !=null) {
              request.getSession().removeAttribute("mensagem");
        %>
        <div><%=mensagem%></div>
        <%
          }  
        %>
        <form action="gerenciar_login.do" method="POST">
            
            <div>
                <label for="login_per">Login</label>
                <input type="text" name="login_per" id="login_per" value="" required="">
            </div>
            <div>
                <label for="senha_per">Senha</label>
                <input type="password" name="senha_per" id="senha_per" value="" required="">
            </div>
            <div>
                <button>Entrar</button>
            </div>
            
        </form>
    </body>
</html>
