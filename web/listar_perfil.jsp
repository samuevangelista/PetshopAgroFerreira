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
                if(confirm('Deseja realmente desativar o perfil '+nome+'?')) {
                    location.href='gerenciar_perfil.do?acao=deletar&idPerfil='+id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            
            <h1>Lista de Perfis</h1>
            
            <a href="form_perfil.jsp" class="cadastro">Novo cadastro</a>
            
            <table id="listarPerfil">
                <th>ID</th>
                <th>Nome</th>
                <th>Login</th>
                <th>Status</th>
                <th>Opções</th>
                
                <jsp:useBean class="model.dao.PerfilDAO" id="pDAO"/>
                
                <c:forEach var="p" items="${pDAO.lista}">
                
                <tr>
                    <td>${p.idPerfil}</td>
                    <td>${p.nome}</td>
                    <td>${p.login_per}</td>
                    <td>
                        <c:if test="${p.status_per==1}">
                            Ativo
                        </c:if>
                        <c:if test="${p.status_per==2}">
                            Inativo
                        </c:if>
                    </td>
                    <td>
                        <a href="gerenciar_perfil.do?acao=alterar&idPerfil=${p.idPerfil}" class="alterar">
                            Alterar
                        </a>
                        <button onclick="confirmarExclusao(${p.idPerfil},'${p.nome}')">
                            Desativar
                        </button>
                        <a href="gerenciar_menu_perfil.do?acao=gerenciar&idPerfil=${p.idPerfil}" class="voltar">
                            Acessos
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
