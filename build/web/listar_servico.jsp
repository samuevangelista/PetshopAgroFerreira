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
            function confirmarExclusao(id,tipo) {
                if(confirm('Deseja realmente excluir o serviço '+tipo+'?')) {
                    location.href='gerenciar_servico.do?acao=deletar&idServico='+id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            
            <h1>Lista de Serviços</h1>
            
            <a href="form_servico.jsp" class="cadastro">Novo cadastro</a>
            
            <table id="listarServico">
            <thead>
                <th>ID</th>
                <th>Tipo</th>
                <th>Valor</th>
                <th>Funcionário</th>
                <th>Animal</th>
                <th>Opções</th>
            </thead>    
                <jsp:useBean class="model.dao.ServicoDAO" id="sDAO"/>
                
                <c:forEach var="s" items="${sDAO.lista}">
                
                <tr>
                    <td>${s.idServico}</td>
                    <td>${s.tipo_ser}</td>
                    <td>${s.valor_ser}</td>
                    <td>${s.idFuncionario.nome_fun}</td>
                    <td>${s.idAnimal.nome_ani}</td>
                    <td>
                        <a href="gerenciar_servico.do?acao=alterar&idServico=${s.idServico}" class="alterar">
                            Alterar
                        </a>
                        <button onclick="confirmarExclusao(${s.idServico},'${s.tipo_ser}')">
                            Excluir
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
