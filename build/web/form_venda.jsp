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

            
            <h1>Cadastrar Serviço</h1>
            
            <form action="gerenciar_venda.do" method="POST">
                <input type="hidden" name="idVenda" value="${venda.idVenda}"/>
                <div>
                    <h2>Dados do Serviço</h2>
                    <label for="valor_ven">Valor:</label>
                    <input type="text" id="valor_ven" name="valor_ven" required="" value="${venda.valor_ven}" maxlength="50"/><br>
                    <label for="data_ven">Data:</label>
                    <input type="text" id="data_ven" name="data_ven" required="" value="${venda.data_ven}" maxlength="11"/><br>
                    <label for="formaPagamento_ven">Forma de Pagamento:</label> <br>
                    <select name="formaPagamento_ven" required="">
                        <option value="">Selecione a Forma de Pagamento</option>
                        <option value="${venda.formaPagamento_ven}">PIX</option>
                        <option value="${venda.formaPagamento_ven}">Dinheiro</option>
                        <option value="${venda.formaPagamento_ven}">Cartão de Crédito</option>
                        <option value="${venda.formaPagamento_ven}">Cartão de Débito</option>
                    </select> <br>
                    <label for="idProduto">Produto:</label>
                    <select name="idProduto" required="">
                        <option value="">Selecione o Produto</option>
                        <jsp:useBean class="model.dao.ProdutoDAO" id="idProduto"/>
                        <c:forEach var="pr" items="${idProduto.lista}">
                            <option value="${pr.idProduto}"
                                <c:if test="${pr.idProduto==venda.idProduto.idProduto}">selected=""</c:if>
                            >   ${pr.descricao_pro}</option>
                        </c:forEach>
                    </select> <br>
                    <label for="idServico">Serviço:</label>
                    <select name="idServico" required="">
                        <option value="">Selecione o Serviço</option>
                        <jsp:useBean class="model.dao.ServicoDAO" id="idServico"/>
                        <c:forEach var="s" items="${idServico.lista}">
                            <option value="${s.idServico}"
                                <c:if test="${s.idServico==venda.idServico.idServico}">selected=""</c:if>
                            >   ${s.tipo_ser}</option>
                        </c:forEach>
                    </select> <br>
                    <label for="idCliente">Cliente:</label>
                    <select name="idCliente" required="">
                        <option value="">Selecione o Cliente</option>
                        <jsp:useBean class="model.dao.ClienteDAO" id="idCliente"/>
                        <c:forEach var="c" items="${idCliente.lista}">
                            <option value="${c.idCliente}"
                                <c:if test="${c.idCliente==venda.idCliente.idCliente}">selected=""</c:if>
                            >   ${c.nome_cli}</option>
                        </c:forEach>
                    </select> <br>
                    <label for="idPerfil">Perfil:</label>
                    <select name="idPerfil" required="">
                        <option value="">Selecione o Perfil</option>
                        <jsp:useBean class="model.dao.PerfilDAO" id="idPerfil"/>
                        <c:forEach var="p" items="${idPerfil.lista}">
                            <option value="${p.idPerfil}"
                                <c:if test="${p.idPerfil==venda.idPerfil.idPerfil}">selected=""</c:if>
                            >   ${p.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button>Gravar</button>
                    <a href="listar_venda.jsp" class="voltar">Voltar</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
