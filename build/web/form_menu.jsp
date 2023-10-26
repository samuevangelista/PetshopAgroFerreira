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
        <title>Agropet</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="menu.jsp" %>
            <h1>Cadastrar Menu</h1>
            
            <form action="gerenciar_menu.do" method="POST">
                <input type="hidden" name="idMenu" value="${menu.idMenu}"/>
                <div>
                    <h2>Dados do Menu</h2>
                    <label for="nome_men">Nome Menu:</label>
                    <input type="text" id="nome_men" name="nome_men" required="" value="${menu.nome_men}" maxlength="45"/><br>
                    <label for="link_men">Link: </label>
                    <input type="text" id="link_men" name="link_men" required="" value="${menu.link_men}" maxlength="150"/><br>
                    <label for="exibir_men">Exibir: </label>
                    <select name="exibir_men" required="">
                        <option value="">Selecione se Deseja Exibir o Menu</option>
                        <option value="1" <c:if test="${menu.exibir_men == '1'}">selected</c:if>>Sim</option>
                        <option value="2" <c:if test="${menu.exibir_men == '2'}">selected</c:if>>Não</option>
                    </select>
                    <br>
                </div>
                <div>
                    <button>Gravar</button>
                    <a href="listar_menu.jsp" class="voltar">Voltar</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
