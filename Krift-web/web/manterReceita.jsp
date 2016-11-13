<%@page import="krift.common.model.domain.Receita"%>
<%@page import="java.util.ArrayList"%>
<%@page import="krift.common.model.domain.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html><head>
        <meta charset="utf-8"> 
        <title>Krift</title>
        <meta name="viewport" content="width=device-width,initial-scale=1, user-scalable=no"> 
        <link rel="stylesheet" href="css/css.css">   
        <script type="text/javascript" src="script.js"></script>
        <link rel="shortcut icon" type="image/png" href="favicon.png">
        <style type="text/css"></style></head>

    <body> 
        <div class="header">
            <div class="left">
                 <% 
                    if(session.getAttribute("logado")!=null){ 
                %>
                <div id="add">
                    <button onclick="window.location='cadastrarReceita.jsp'">
                        <div class="addicon">                            
                        </div>
                    </button>                        
                </div> 
                <% } %>
                <div id="options">
                    <% 
                    if(session.getAttribute("logado")!=null){ 
                %>
                    <button onclick="alert('MSG')">
                        <div class="iconMessage">
                        </div>
                    </button>
                    <% } %>
                    <button onclick="window.location = '/Krift/servletweb?acao=ListarUsuarios'">
                        <div class="iconRanking">
                        </div>
                    </button>
                    <button onclick="window.location = 'index.jsp'">
                        <div class="iconHome">
                        </div>
                    </button>
                </div> 
            </div> 

            <div class="middle">
                <a href="index.jsp"><img src="klogo.png" class="klogo"></a>
            </div>
            <div class="right">
                <div>
                    <input class="searchbar" placeholder="Buscar" type="text">	
                    <a class="sbutton" href="/Krift/servletweb?acao=Busca"></a>
                </div>

                <%
                    if (session.getAttribute("logado") != null) {
                %>
                <div class="containerimage">
                    <img class="profileimage" id="pimg" src="data:image/png;base64,<%=((Usuario)session.getAttribute("logado")).getImagem()%>" style='margin:0px'>
                    <div class="useropt">
                        <a href="/Krift/servletweb?acao=Logout">SAIR</a>
                    </div>
                </div>
                <% } else { %>
                <a href="login.jsp" class="toLogin">LOGIN</a>    
                <% } %>  

            </div>  
        </div>

        <div class="content">
            <div class="wrapper">     
                <div class="sidebox">
                    <h2 class="title">MENU</h2>
                    <img class="navicon" src="menuicons.png">
                    <ul class="sidenav"> 
                        <%
                            if(session.getAttribute("logado")!=null){;                                
                        %>
                        <a href="/Krift/servletweb?acao=ManterReceita&nome=<%= ((Usuario)session.getAttribute("logado")).getNom_login() %>">
                            <li> 
                                <span>MINHAS RECEITAS</span>
                                <img class="navicon" src="menuicons.png">  
                            </li>
                        </a>
                        <a href="/Krift/servletweb?acao=VisualizarUsuario&nome=<%= ((Usuario)session.getAttribute("logado")).getNom_login() %>"> 
                            <li>  
                                <span>MEU PERFIL</span>  
                                <img class="navicon" src="menuicons.png">
                            </li>
                        </a>
                        <a href="#"> 
                            <li>  
                                <span>FAVORITOS</span>
                                <img class="navicon" src="menuicons.png">
                            </li>
                        </a>  
                        <%
 }
                        %>
                        <a href="ajuda.jsp">
                            <li>
                                <span>SOBRE</span> 
                                <img class="navicon" src="menuicons.png">
                            </li>
                        </a>
                        <a href="ajuda.jsp">
                            <li> 
                                <span>AJUDA</span>
                                <img class="navicon" src="menuicons.png">
                            </li>
                        </a> 
                    </ul>  
                </div>

                <div>
                    <div class="navblock"> 
                        <h2 class="title">SUAS RECEITAS</h2>
                        <ul class="manterReceita">
                            <% 
                                ArrayList<Receita> receitas = (ArrayList)request.getAttribute("receitas");
                                if(receitas!=null){
                                for(Receita receita : receitas){
                            %>
                            <li>
                                <img src="data:image/png;base64,<%= receita.getImagem() %>" id="img"/>
                                <div class="title">
                                    <div>
                                        <span><%= receita.getNom_receita() %></span>
                                        <div class="actions">
                                            <a href="/Krift/servletweb?acao=EditarReceita&idReceita=<%= receita.getNro_seq_receita()%>">EDITAR</a>
                                            <a href="/Krift/servletweb?acao=ExcluirReceita&idReceita=<%= receita.getNro_seq_receita()%>">EXCLUIR</a>
                                        </div>
                                    </div>
                                    <div>
                                        <p><%= receita.getDes_receita() %></p>
                                    </div>
                                </div>
                            </li>
                            <%
                                }
                            }else{
                            %>
                            <h3>Você não possui receitas cadastradas.</h3>
                            <%
                               } 
                            %>
                        </ul>

                    </div>
                </div>

                <div class="sidebox">
                    <h2 class="title" style="font-size: 20px;">TENDÊNCIAS NA BUSCA</h2>
                    <img class="navicon" src="menuicons.png">
                    <ul class="sidenav"> 
                        <a href="#">
                            <li> 
                                <span style="color: #42b1d7;">ONIVORISMO</span>
                                <img class="navicon" src="menuicons.png">  
                            </li>
                        </a>
                        <a href="#">
                            <li>  
                                <span style="color: #8ee04c;">VEGETARIANISMO</span>  
                                <img class="navicon" src="menuicons.png"></li>
                        </a>
                        <a href="#"> 
                            <li>  
                                <span style="color: #d5d548;">VEGANISMO</span>
                                <img class="navicon" src="menuicons.png"></li>
                        </a>  
                        <a href="#">
                            <li>
                                <span style="color: #ee4c58;">CARNIVORISMO</span> 
                                <img class="navicon" src="menuicons.png"></li>
                        </a>
                        <a href="#">
                            <li> 
                                <span style="color: black;">SEM TENDÊNCIA</span>
                                <img class="navicon" src="menuicons.png"></li>
                        </a> 
                    </ul>  
                </div>
            </div>
        </div>


    </body></html>