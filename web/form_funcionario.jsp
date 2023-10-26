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

          
            <h1>Cadastrar Funcionário</h1>
            
            <form action="gerenciar_funcionario.do" method="POST">
                <input type="hidden" name="idFuncionario" value="${funcionario.idFuncionario}"/>
                <div>
                    <h2>Dados do Funcionário</h2>
                    <label for="nome_fun">Nome:</label>
                    <input type="text" id="nome_fun" name="nome_fun" required="" value="${funcionario.nome_fun}" maxlength="50"/><br>
                    <label for="cpf_fun">CPF:</label>
                    <input type="text" id="cpf_fun" name="cpf_fun" required="" value="${funcionario.cpf_fun}" maxlength="11"/><br>
                    <label for="endereco_fun">Endereço:</label>
                    <input type="text" id="endereco_fun" name="endereco_fun" required="" value="${funcionario.endereco_fun}" maxlength="45"/><br>
                    <label for="telefone_fun">Telefone:</label>
                    <input type="text" id="telefone_fun" name="telefone_fun" required="" value="${funcionario.telefone_fun}" maxlength="45"/><br>
                    <label for="funcao_fun">Função:</label>
                    <select id="funcao_fun" name="funcao_fun" required="">
                        <option value="">Selecione a Função</option>
                        <option value="Administrador" <c:if test="${funcionario.funcao_fun == 'Administrador'}">selected</c:if>>Administrador</option>
                        <option value="Gerente" <c:if test="${funcionario.funcao_fun == 'Gerente'}">selected</c:if>>Gerente</option>
                        <option value="Atendente" <c:if test="${funcionario.funcao_fun == 'Atendente'}">selected</c:if>>Atendente</option>
                    </select>
                     <br>
                    <label for="email_fun">Email:</label>
                    <input type="text" id="email_fun" name="email_fun" value="${funcionario.email_fun}" maxlength="60"/><br>
                    <label for="idPerfil">Perfil:</label>
                    <select name="idPerfil" required="">
                        <option value="">Selecione o Perfil</option>
                        <jsp:useBean class="model.dao.PerfilDAO" id="idPerfil"/>
                        <c:forEach var="p" items="${idPerfil.lista}">
                            <option value="${p.idPerfil}"
                                <c:if test="${p.idPerfil==funcionario.idPerfil.idPerfil}">selected=""</c:if>
                            >   ${p.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button>Gravar</button>
                    <a href="listar_funcionario.jsp" class="voltar">Voltar</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
