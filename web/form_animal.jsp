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
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <title>Agropet</title>
    </head>
    <body>
        <div class="container-fluid">
           
            <h1>Cadastrar Animal</h1>
            
            <form action="gerenciar_animal.do" method="POST">
                <input type="hidden" name="idAnimal" value="${animal.idAnimal}"/>
                <div>
                    <h2>Dados do Funcionário</h2>
                    <label for="nome_ani">Nome:</label>
                    <input type="text" id="nome_ani" name="nome_ani" required="" value="${animal.nome_ani}" maxlength="45"/><br>
                    <label for="raca_ani">Raça:</label>
                    <input type="text" id="raca_ani" name="raca_ani" required="" value="${animal.raca_ani}" maxlength="45"/><br>
                    <label for="idade_ani">Idade:</label>
                    <input type="text" id="idade_ani" name="idade_ani" required="" value="${animal.idade_ani}" maxlength="11"/><br>
                    <label for="tamanho_ani">Tamanho:</label>
                    <select name="tamanho_ani" required="">
                        <option value="">Selecione o Porte do Animal</option>
                        <option value="Pequeno" <c:if test="${animal.tamanho_ani == 'Pequeno'}">selected</c:if>>Pequeno</option>
                        <option value="Medio" <c:if test="${animal.tamanho_ani == 'Medio'}">selected</c:if>>Médio</option>
                        <option value="Grande" <c:if test="${animal.tamanho_ani == 'Grande'}">selected</c:if>>Grande</option>
                    </select>
                    <br>
                    <label for="idCliente">Dono:</label>
                    <select name="idCliente" required="">
                        <option value="">Selecione o Dono</option>
                        <jsp:useBean class="model.dao.ClienteDAO" id="idCliente"/>
                        <c:forEach var="c" items="${idCliente.lista}">
                            <option value="${c.idCliente}"
                                <c:if test="${c.idCliente==animal.idCliente.idCliente}">selected=""</c:if>
                            >   ${c.nome_cli}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button>Gravar</button>
                    <a href="listar_animal.jsp" class="voltar">Voltar</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
