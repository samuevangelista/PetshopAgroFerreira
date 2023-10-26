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
                if(confirm('Deseja realmente excluir o menu '+nome+'?')) {
                    location.href='gerenciar_menu.do?acao=deletar&idMenu='+id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            
            <h1>Lista de Menus</h1>
            
            <a href="form_menu.jsp" class="cadastro">Novo cadastro</a>
            
            <table id="listarMenu">
                <th>ID</th>
                <th>Nome Menu</th>
                <th>Link</th>
                <th>Exibir</th>
                <th>Opções</th>
                
                <jsp:useBean class="model.dao.MenuDAO" id="mDAO"/>
                
                <c:forEach var="m" items="${mDAO.lista}">
                
                <tr>
                    <td>${m.idMenu}</td>
                    <td>${m.nome_men}</td>
                    <td>${m.link_men}</td>
                    <td>
                        <c:if test="${m.exibir_men==1}">
                            Sim
                        </c:if>
                        <c:if test="${m.exibir_men==2}">
                            Não
                        </c:if>
                    </td>
                    <td>
                        <a href="gerenciar_menu.do?acao=alterar&idMenu=${m.idMenu}" class="alterar">
                            Alterar
                        </a>
                        <button onclick="confirmarExclusao(${m.idMenu},'${m.nome_men}')">
                            Excluir
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
