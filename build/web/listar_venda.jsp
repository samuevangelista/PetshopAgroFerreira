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
                if(confirm('Deseja realmente excluir a venda '+nome+'?')) {
                    location.href='gerenciar_venda.do?acao=deletar&idVenda='+id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            
            <h1>Lista de Vendas</h1>
            
            <a href="form_venda.jsp" class="cadastro">Novo cadastro</a>
            
            <table id="listarVenda">
            <thead>
                <th>ID</th>
                <th>Valor</th>
                <th>Data</th>
                <th>Pagamento</th>
                <th>Produto</th>
                <th>Servico</th>
                <th>Cliente</th>
                <th>Perfil</th>
                <th>Opções</th>
            </thead>    
                <jsp:useBean class="model.dao.VendaDAO" id="vDAO"/>
                
                <c:forEach var="v" items="${vDAO.lista}">
                
                <tr>
                    <td>${v.idVenda}</td>
                    <td>${v.valor_ven}</td>
                    <td>${v.data_ven}</td>
                    <td>${v.formaPagamento_ven}</td>
                    <td>${v.idProduto.descricao_pro}</td>
                    <td>${v.idServico.tipo_ser}</td>
                    <td>${v.idCliente.nome_cli}</td>
                    <td>${v.getIdPerfil().getNome()}</td>
                    <td>
                        <a href="gerenciar_venda.do?acao=alterar&idVenda=${v.idVenda}" class="alterar">
                            Alterar
                        </a>
                        <button onclick="confirmarExclusao(${v.idVenda},'${v.data_ven}')">
                            Excluir
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
