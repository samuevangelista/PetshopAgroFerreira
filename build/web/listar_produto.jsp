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
                if(confirm('Deseja realmente excluir o produto '+nome+'?')) {
                    location.href='gerenciar_produto.do?acao=deletar&idProduto='+id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
           
            <h1>Lista de Perfis</h1>
            
            <a href="form_produto.jsp" class="cadastro">Novo cadastro</a>
            
            <table>
                <th>ID</th>
                <th>Tipo</th>
                <th>Quantidade(em KG)</th>
                <th>Código de Barras</th>
                <th>Valor</th>
                <th>Descrição</th>
                <th>Opções</th>
                
                <jsp:useBean class="model.dao.ProdutoDAO" id="pDAO"/>
                
                <c:forEach var="p" items="${pDAO.lista}">
                
                <tr>
                    <td>${p.idProduto}</td>
                    <td>${p.tipo_pro}</td>
                    <td>${p.quantidade_pro}</td>
                    <td>${p.codBarras_pro}</td>
                    <td>${p.valor_pro}</td>
                    <td>${p.descricao_pro}</td>
                    <td>
                        <a href="gerenciar_produto.do?acao=alterar&idProduto=${p.idProduto}" class="alterar">
                            Alterar
                        </a>
                        <button onclick="confirmarExclusao(${p.idProduto},'${p.tipo_pro}')">
                            Excluir
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
