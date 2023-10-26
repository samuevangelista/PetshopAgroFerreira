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
                if(confirm('Deseja realmente excluir o animal '+nome+'?')) {
                    location.href='gerenciar_animal.do?acao=deletar&idAnimal='+id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
           
            <h1>Lista de Animais</h1>
            
            <a href="form_animal.jsp" class="cadastro">Novo cadastro</a>
            
            <table id="listarAnimal">
            <thead>
                <th>ID</th>
                <th>Nome</th>
                <th>Raça</th>
                <th>Idade</th>
                <th>Tamanho</th>
                <th>Dono</th>
                <th>Opções</th>
            </thead>    
                <jsp:useBean class="model.dao.AnimalDAO" id="aDAO"/>
                
                <c:forEach var="a" items="${aDAO.lista}">
                
                <tr>
                    <td>${a.idAnimal}</td>
                    <td>${a.nome_ani}</td>
                    <td>${a.raca_ani}</td>
                    <td>${a.idade_ani}</td>
                    <td>${a.tamanho_ani}</td>
                    <td>${a.idCliente.nome_cli}</td>
                    <td>
                        <a href="gerenciar_animal.do?acao=alterar&idAnimal=${a.idAnimal}" class="alterar">
                            Alterar
                        </a>
                        <button onclick="confirmarExclusao(${a.idAnimal},'${a.nome_ani}')">
                            Excluir
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
