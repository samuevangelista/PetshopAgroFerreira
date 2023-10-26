<%-- 
    Document   : index
    Created on : 11/05/2023, 16:55:50
    Author     : Gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <%@include file="menu.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Agropet</title>
        <script type="text/javascript">
            function confirmarExclusao(id,nome) {
                if(confirm('Deseja realmente excluir o cliente '+nome+'?')) {
                    location.href='gerenciar_cliente.do?acao=deletar&idCliente='+id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            
            <h1>Lista de Clientes</h1>
            
            <a href="form_cliente.jsp" class="cadastro">Novo cadastro</a>
            
            <table>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Endereço</th>
                <th>Email</th>
                <th>Opções</th>
                
                <jsp:useBean class="model.dao.ClienteDAO" id="cDAO"/>
                
                <c:forEach var="c" items="${cDAO.lista}">
                
                <tr>
                    <td>${c.idCliente}</td>
                    <td>${c.nome_cli}</td>
                    <td>${c.telefone_cli}</td>
                    <td>${c.endereco_cli}</td>
                    <td>${c.email_cli}</td>
                    <td>
                        <a href="gerenciar_cliente.do?acao=alterar&idCliente=${c.idCliente}" class="alterar">
                            Alterar
                        </a>
                        <button onclick="confirmarExclusao(${c.idCliente},'${c.nome_cli}')">
                            Excluir
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
