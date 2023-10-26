<%@page import="model.Perfil"%>
<%@page import="controller.GerenciarLogin"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  Perfil ulogado = GerenciarLogin.verificarAcesso(request, response);
  request.setAttribute("ulogado", ulogado);
%>
<nav class="navbar">
            <div>
                <a href="index.jsp"><img src="assets/logo.png" width=135 height= 105 ></a>
            </div>
            <ul>
            <%--    
            <c:if test="${ulogado!=null}">
                <c:forEach var="menu" items="${ulogado.menus}">
                    <c:if test="${menu.exibir_men==1}">
                        <li><a href="${menu.link_men}">${menu.nome_men}</a></li>
                    </c:if>
                </c:forEach>
            </c:if>
            --%>    
                <li><a href="index.jsp">Home</a></li>
                <li><a href="listar_produto.jsp">Produtos</a></li>
                <li><a href="listar_servico.jsp">Servico</a></li>
                <li><a href="listar_perfil.jsp">Perfis</a></li>
                <li><a href="listar_funcionario.jsp">Funcionários</a></li>
                <li><a href="listar_cliente.jsp">Clientes</a></li>
                <li><a href="listar_animal.jsp">Pets</a></li>
                <li><a href="listar_venda.jsp">Vendas</a></li>
                <li><a href="listar_menu.jsp">Menus</a></li>
                <li class="test"><a>Bem Vindo, <c:if test="${ulogado!=null}">${ulogado.nome}</c:if></a></li>

                <a href="gerenciar_login.do" class="text">Sair</a>
            </ul>
                
                        
            <div class="menu-icon">
                <img src="assets/menu.png">
            </div>
</nav>
<style>
*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Roboto',sans-serif;
}
nav{
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 100px;
}
nav .menu-icon{
    cursor: pointer;
    display: none;
}

nav ul{
    display: flex;
    list-style: none;
    align-items: center;
}
nav ul li{
    padding: 0 15px;
}
nav ul li a{
    text-decoration: none;
    font-size: 15px;
    text-transform: uppercase;
    color: #049749;
}
nav ul li.test a{
    text-decoration: none;
    font-size: 15px;
    text-transform: uppercase;
    color: #016e34;
}
nav ul a.text{
    border: none;
    background-color: #049749;
    color: white;
    padding: 10px 20px;
    border-radius: 5px;
    font-size: 16px;
    letter-spacing: 1px;
    margin-left: 20px;
    cursor: pointer;
}
</style>