<%@page import="java.util.ArrayList"%>
<%@page import="krift.common.model.domain.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html><head>
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
                <div class="profileimage">
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
                    <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                    <ul class="sidenav"> 
                        <%
                            if (session.getAttribute("logado") != null) {
                        %>
                        <a href="#">
                            <li> 
                                <span>MINHAS RECEITAS</span>
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">  
                            </li>
                        </a>
                        <a href="/Krift/servletweb?acao=VisualizarUsuario&nome=<%= ((Usuario)session.getAttribute("logado")).getNom_login() %>">
                            <li>  
                                <span>MEU PERFIL</span>  
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                            </li>
                        </a>
                        <a href="#"> 
                            <li>  
                                <span>FAVORITOS</span>
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                            </li>
                        </a>  
                        <%
                            }
                        %>
                        <a href="ajuda.jsp">
                            <li>
                                <span>SOBRE</span> 
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                            </li>
                        </a>
                        <a href="ajuda.jsp">
                            <li> 
                                <span>AJUDA</span>
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                            </li>
                        </a> 
                    </ul>  
                </div>

                <div>
                    <div class="navblock" style="padding-bottom: 20px;"> 
                        <h2 class="title whitetitle" >RANKING</h2>

                        <table class="ranking">                               
                            <tbody>
                                <tr>
                                    <th>Rank</th>
                                    <th>Nome do usuário</th> 
                                    <th>Pontos</th>
                                </tr>
                                <%   
                                    ArrayList<Usuario> usuarios = (ArrayList) request.getAttribute("listaRanking");
                                    if(usuarios != null){
                                    for (Usuario user: usuarios) {
                                    
                                %>
                                <tr>
                                    <td><%= user.getPos_ranking() %></td>
                                    <td><a href="/Krift/servletweb?acao=VisualizarUsuario&nome=<%= user.getNom_login() %>"><%= user.getNom_perfil_usuario() %></a></td> 
                                    <td><%= user.getNro_pontos() %></td>
                                </tr>                                
                                <%   }}   %>    
                            </tbody>                            
                        </table>

                    </div>
                </div>


                <div class="sidebox">
                    <h2 class="title" style="font-size: 20px;">TENDÊNCIAS NA BUSCA</h2>
                    <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                    <ul class="sidenav"> 
                        <a href="#">
                            <li> 
                                <span style="color: #42b1d7;">ONIVORISMO</span>
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">  
                            </li>
                        </a>
                        <a href="#">
                            <li>  
                                <span style="color: #8ee04c;">VEGETARIANISMO</span>  
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png"></li>
                        </a>
                        <a href="#"> 
                            <li>  
                                <span style="color: #d5d548;">VEGANISMO</span>
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png"></li>
                        </a>  
                        <a href="#">
                            <li>
                                <span style="color: #ee4c58;">CARNIVORISMO</span> 
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png"></li>
                        </a>
                        <a href="#">
                            <li> 
                                <span style="color: black;">SEM TENDÊNCIA</span>
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png"></li>
                        </a> 
                    </ul>  
                </div>    
            </div>
        </div>
    </div>

</body></html>