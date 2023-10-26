<%-- 
    Document   : index
    Created on : 11/05/2023, 16:55:50
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <%@include file="menu.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <title>Agropet</title>
        <script type="text/javascript">
            function confirmarExclusao(idMenu,nome,idPerfil) {
                if(confirm('Deseja realmente desvincular o menu  '+nome+'?')) {
                    location.href='gerenciar_menu_perfil.do?acao=desvincular&idMenu='+idMenu+'&idPerfil='+idPerfil;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            
            <h1>Gerenciar Perfil</h1>
            
            <form action="gerenciar_menu_perfil.do" method="POST">
                <input type="hidden" name="idPerfil" value="${perfilv.idPerfil}"/>
                <div>
                    <h2>Dados do Perfil</h2>
                    <label for="nome">${perfilv.nome}</label> <br>
                    <label for="menu">Menus</label>
                    <select name="idMenu" required="" id="idMenu">
                        <option value="">Selecione o Menu</option>
                        <c:forEach var="m" items="${perfilv.naoMenus}">
                            <option value="${m.idMenu}">${m.nome_men}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button>Vincular</button>
                    <a href="listar_perfil.jsp" class="voltar">Voltar</a>
                </div>    
            </form>
                    
            <table id="listarMenu">
                <th>ID</th>
                <th>Nome Menu</th>
                <th>Link</th>
                <th>Exibir</th>
                <th>Desvincular</th>
                
                <%-- <jsp:useBean class="model.dao.MenuDAO" id="mDAO"/> --%>
                
                <c:forEach var="m" items="${perfilv.menus}">
                
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
                        <button onclick="confirmarExclusao(${m.idMenu},'${m.nome_men}',${perfilv.idPerfil})">
                            Desvincular
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
