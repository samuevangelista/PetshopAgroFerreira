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
                if(confirm('Deseja realmente excluir o funcionário '+nome+'?')) {
                    location.href='gerenciar_funcionario.do?acao=deletar&idFuncionario='+id;
                }
            }
        </script>        
    </head>
    <body>
        <div class="container-fluid">
            
            <h1>Lista de Funcionários</h1>
            
            <a href="form_funcionario.jsp" class="cadastro">Novo cadastro</a>
            
            <table id="listarFuncionario">
            <thead>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Endereço</th>
                <th>Telefone</th>
                <th>Função</th>
                <th>Email</th>
                <th>Perfil</th>
                <th>Opções</th>
            </thead>    
                <jsp:useBean class="model.dao.FuncionarioDAO" id="fDAO"/>
                
                <c:forEach var="f" items="${fDAO.lista}">
                
                <tr>
                    <td>${f.idFuncionario}</td>
                    <td>${f.nome_fun}</td>
                    <td>${f.cpf_fun}</td>
                    <td>${f.endereco_fun}</td>
                    <td>${f.telefone_fun}</td>
                    <td>${f.funcao_fun}</td>
                    <td>${f.email_fun}</td>
                    <td>${f.idPerfil.nome}</td>
                    <td>
                        <a href="gerenciar_funcionario.do?acao=alterar&idFuncionario=${f.idFuncionario}" class="alterar">
                            Alterar
                        </a>
                        <button onclick="confirmarExclusao(${f.idFuncionario},'${f.nome_fun}')">
                            Excluir
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
