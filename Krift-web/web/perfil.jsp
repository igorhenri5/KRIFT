<%@page import="krift.common.model.domain.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8"> 
        <title>Krift</title>
        <meta name="viewport" content="width=device-width,initial-scale=1, user-scalable=no"> 
        <link rel="stylesheet" href="css/css.css">   
        <script type="text/javascript" src="script.js"></script>
        <link rel="shortcut icon" type="image/png" href="favicon.png"/>
    </head>

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
                    <div class="sbutton"></div>
                </div>
                <%
                    if (session.getAttribute("logado") != null) { %>
                <div class="containerimage">
                    <img class="profileimage" id="pimg" src="data:image/png;base64,<%=((Usuario)session.getAttribute("logado")).getImagem()%>" style='margin:0px'>
                    <div class="useropt">
                        <a href="/Krift/servletweb?acao=Logout">SAIR</a>
                    </div>
                </div>
                <% } else { %>
                <a href="login.jsp" class="toLogin">LOGIN</a>    
                <% }%>
            </div> 
        </div>

        <div class="content">
            <div class="wrapper">     
                <div class="sidebox">
                    <h2 class="title">MENU</h2>
                    <img class="navicon" src="menuicons.png">
                    <ul class="sidenav"> 

                        <a href="#">
                            <li> 
                                <span>MINHAS RECEITAS</span>
                                <img class="navicon" src="menuicons.png">  
                            </li>
                        </a>
                        <a href="/Krift/servletweb?acao=VisualizarUsuario&nome=<%= ((Usuario) session.getAttribute("logado")).getNom_login()%>">
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
                    <div class="navblock" style="display: block;"> 
                        <div class="perfilBG"> 

                            <div class="perfil">
                                <% if(session.getAttribute("logado")!=null){ 
                                    if(((Usuario)session.getAttribute("logado")).getNom_login().equals(request.getAttribute("nome"))){                                        
                                %>
                                <a class="edit" href="editarPerfil.jsp">EDITAR</a>
                                <% }} %>
                                <img class="imgperfil" src="data:image/png;base64,<%=request.getAttribute("perfilImage")%>">
                                <div class="nomerank">
                                    <h2><%= request.getAttribute("perfilNome") %></h2>
                                    RANK <%= request.getAttribute("perfilRank") %>
                                </div>
                                <div class="estastitica">
                                    <span><%= request.getAttribute("perfilPontos") %> KP</span>
                                </div>
                                <div class="estastitica">
                                    <span><%= request.getAttribute("perfilTendencia") %></span>
                                </div>
                            </div>
                        </div> 
                    </div>

                    <div class="navblock" style="max-width: 780px;display: block;"> 
                        <div class="sobrePerfil">
                            <h4>SOBRE MIM</h4>
                            <span><%= request.getAttribute("perfilSobre") %></span>
                        </div>
                    </div>

                    <div>
                        <div class="navblock" style="min-width: 500px;display: inline-block;"> 
                            <h2 class="title whitetitle">RECEITAS DE <%= request.getAttribute("perfilNome") %></h2>


                            <ul class="resultados ulx3">  
                                
                                <li>
                                    <div class="img" style="background-image:url(picanha.jpg);">
                                        <div class="desc"><span id="rcp">Nome da receita</span>
                                            <span class="autor">por Autor</span><span class="tendencia">tendência</span>

                                            <span class="nota">5.0</span>

                                        </div>
                                    </div>
                                </li>
                                
                            </ul>

                        </div>
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