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
            
            <form action="gerenciar_servico.do" method="POST">
                <input type="hidden" name="idServico" value="${servico.idServico}"/>
                <div>
                    <h2>Dados do Serviço</h2>
                    <label for="tipo_ser">Tipo:</label>
                    <input type="text" id="tipo_ser" name="tipo_ser" required="" value="${servico.tipo_ser}" maxlength="50"/><br>
                    <label for="valor_ser">Valor:</label>
                    <input type="text" id="valor_ser" name="valor_ser" required="" value="${servico.valor_ser}" maxlength="11"/><br>
                    <label for="idFuncionario">Funcionário:</label>
                    <select name="idFuncionario" required="">
                        <option value="">Selecione o Funcionário</option>
                        <jsp:useBean class="model.dao.FuncionarioDAO" id="idFuncionario"/>
                        <c:forEach var="f" items="${idFuncionario.lista}">
                            <option value="${f.idFuncionario}"
                                <c:if test="${f.idFuncionario==servico.idFuncionario.idFuncionario}">selected=""</c:if>
                            >   ${f.nome_fun}</option>
                        </c:forEach>
                    </select>
                        <br>
                    <label for="idAnimal">Animal:</label>
                    <select name="idAnimal" required="">
                        <option value="">Selecione o Pet</option>
                        <jsp:useBean class="model.dao.AnimalDAO" id="idAnimal"/>
                        <c:forEach var="a" items="${idAnimal.lista}">
                            <option value="${a.idAnimal}"
                                <c:if test="${a.idAnimal==servico.idAnimal.idAnimal}">selected=""</c:if>
                            >   ${a.nome_ani}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button>Gravar</button>
                    <a href="listar_servico.jsp" class="voltar">Voltar</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
