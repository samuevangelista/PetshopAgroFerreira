<%-- 
    Document   : index
    Created on : 11/05/2023, 16:55:50
    Author     : Gabriel
--%>

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
            
            <h1>Cadastrar Produto</h1>
            
            <form action="gerenciar_produto.do" method="POST">
                <input type="hidden" name="idProduto" value="${produto.idProduto}"/>
                <div>
                    <h2>Dados do Produto</h2>
                    <label for="tipo_pro">Tipo:</label>
                    <input type="text" id="tipo_pro" name="tipo_pro" required="" value="${produto.tipo_pro}" maxlength="45"/><br>
                    <label for="quantidade_pro">Quantidade:</label>
                    <input type="text" id="quantidade_pro" name="quantidade_pro" required="" value="${produto.quantidade_pro}" maxlength="45"/><br>
                    <label for="codBarras_pro">Código de Barras:</label>
                    <input type="text" id="codBarras_pro" name="codBarras_pro" required="" value="${produto.codBarras_pro}" maxlength="45"/><br>
                    <label for="valor_pro">Valor:</label>
                    <input type="text" id="valor_pro" name="valor_pro" required="" value="${produto.valor_pro}" maxlength="45"/><br>
                    <label for="descricao_pro">Descrição:</label>
                    <input type="text" id="descricao_pro" name="descricao_pro" required="" value="${produto.descricao_pro}" maxlength="45"/><br>
                </div>
                <div>
                    <button>Gravar</button>
                    <a href="listar_produto.jsp" class="voltar">Voltar</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
