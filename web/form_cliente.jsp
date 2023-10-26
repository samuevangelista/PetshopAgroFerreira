<%-- 
    Document   : index
    Created on : 11/05/2023, 16:55:50
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <%@include file="menu.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <title>Agropet</title>
    </head>
    <body>
        <div class="container-fluid">
            
            <h1>Cadastrar Cliente</h1>
            
            <form action="gerenciar_cliente.do" method="POST">
                <input type="hidden" name="idCliente" value="${cliente.idCliente}"/>
                <div>
                    <h2>Dados do Cliente</h2>
                    <label for="nome_cli">Nome:</label>
                    <input type="text" id="nome_cli" name="nome_cli" required="" value="${cliente.nome_cli}" maxlength="45"/><br>
                    <label for="telefone_cli">Telefone:</label>
                    <input type="text" id="telefone_cli" name="telefone_cli" required="" value="${cliente.telefone_cli}" maxlength="45"/><br>
                    <label for="endereco_cli">Endereço:</label>
                    <input type="text" id="endereco_cli" name="endereco_cli" required="" value="${cliente.endereco_cli}" maxlength="45"/><br>
                    <label for="email_cli">Email:</label>
                    <input type="text" id="email_cli" name="email_cli" required="" value="${cliente.email_cli}" maxlength="45"/><br>
                </div>
                <div>
                    <button>Gravar</button>
                    <a href="listar_cliente.jsp" class="voltar">Voltar</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
