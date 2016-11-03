<%@page import="krift.common.model.domain.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8"> 
        <title>Krift</title>
        <meta name="viewport" content="width=device-width,initial-scale=1, user-scalable=no"> 
        <link rel="stylesheet" href="css/css.css">   
        <link rel="shortcut icon" type="image/png" href="favicon.png"/>
        <script type="text/javascript" src="script.js"></script>
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
                    if(session.getAttribute("logado")!=null){ %>
                <div class="profileimage">
                    <div class="useropt">
                        <a href="/Krift/servletweb?acao=Logout">SAIR</a>
                    </div>
                </div>
                <% }else{ %>
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
                            if(session.getAttribute("logado")!=null){
                        %>
                        <a href="#">
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
                    <form id="form" method="POST" action="/Krift/servletweb?acao=EditarPerfil">
                        <div class="navblock" style="padding-bottom: 20px;"> 
                            <h2 class="title whitetitle">EDIÇÃO DE PERFIL</h2>

                            <div class="formulario">
                                <div class="formsection"><h4>Nome</h4>
                                    <div>
                                        <input name="nome" value="<%= ((Usuario)session.getAttribute("logado")).getNom_perfil_usuario() %>" placeholder="Digite um novo nome" type="text">
                                        <span>NECESSÁRIO</span>
                                    </div>
                                </div>
                                <div class="formsection">
                                    <h4>Tendência</h4>
                                    <div>
                                        <select name="tendencia" id="selectTendencia">
                                            <% if(((Usuario)session.getAttribute("logado")).getIdt_tendencia().equals("SEM")){ %>  
                                            <option value="ONV">Onivorismo - Contém carne, vegetais e/ou derivados de animais simultaneamente</option>
                                            <option value="VGT">Vegetarianismo - Contém vegetais e derivados de animais</option>
                                            <option value="VGN">Veganismo - Contém apenas vegetais</option>
                                            <option value="CRN">Carnivorismo - Contém apenas carne</option>	
                                            <option selected value="SEM">Sem tendência - Não classificado</option>
                                            <% } %>  
                                            
                                            <% if(((Usuario)session.getAttribute("logado")).getIdt_tendencia().equals("ONV")){ %>  
                                            <option selected value="ONV">Onivorismo - Contém carne, vegetais e/ou derivados de animais simultaneamente</option>
                                            <option value="VGT">Vegetarianismo - Contém vegetais e derivados de animais</option>
                                            <option value="VGN">Veganismo - Contém apenas vegetais</option>
                                            <option value="CRN">Carnivorismo - Contém apenas carne</option>	
                                            <option value="SEM">Sem tendência - Não classificado</option>
                                            <% } %> 
                                            
                                            <% if(((Usuario)session.getAttribute("logado")).getIdt_tendencia().equals("VGN")){ %>  
                                            <option value="ONV">Onivorismo - Contém carne, vegetais e/ou derivados de animais simultaneamente</option>
                                            <option value="VGT">Vegetarianismo - Contém vegetais e derivados de animais</option>
                                            <option selected value="VGN">Veganismo - Contém apenas vegetais</option>
                                            <option value="CRN">Carnivorismo - Contém apenas carne</option>	
                                            <option value="SEM">Sem tendência - Não classificado</option>
                                            <% } %> 
                                            
                                            <% if(((Usuario)session.getAttribute("logado")).getIdt_tendencia().equals("VGT")){ %>  
                                            <option value="ONV">Onivorismo - Contém carne, vegetais e/ou derivados de animais simultaneamente</option>
                                            <option selected value="VGT">Vegetarianismo - Contém vegetais e derivados de animais</option>
                                            <option value="VGN">Veganismo - Contém apenas vegetais</option>
                                            <option value="CRN">Carnivorismo - Contém apenas carne</option>	
                                            <option value="SEM">Sem tendência - Não classificado</option>
                                            <% } %> 
                                            
                                            <% if(((Usuario)session.getAttribute("logado")).getIdt_tendencia().equals("CRN")){ %>  
                                            <option value="ONV">Onivorismo - Contém carne, vegetais e/ou derivados de animais simultaneamente</option>
                                            <option value="VGT">Vegetarianismo - Contém vegetais e derivados de animais</option>
                                            <option value="VGN">Veganismo - Contém apenas vegetais</option>
                                            <option selected value="CRN">Carnivorismo - Contém apenas carne</option>	
                                            <option value="SEM">Sem tendência - Não classificado</option>
                                            <% } %> 
                                        </select>
                                        <span>NECESSÁRIO</span>
                                    </div>

                                </div>
                                <div class="formsection">
                                    <h4>Imagem</h4>

                                    <div>
                                        <input name="imagem" style="height: 36px;padding-top: 8px;" id="fileupload" value="fileupload" name="fileupload" type="file"  onchange='startRead()'> 
                                        <input type="hidden" name="img64" id="fileaux"> 
                                        <span style="color: #a2a2a2;">OPCIONAL</span>
                                    </div>

                                </div>
                                <div class="formsection">
                                    <h4>Sobre você</h4>
                                    <div>
                                        <textarea name="sobre" placeholder="Digite um texto sobre você" rows="4" cols="50" maxlength="255" style="margin: 0px; height: 66px; width: 579px;"><%=((Usuario)session.getAttribute("logado")).getDes_usuario() %></textarea>
                                        <span style="color: #a2a2a2;">OPCIONAL</span>
                                    </div>

                                </div>

                            </div>


                        </div>

                        <div class="navblock" style="position: relative;"> 
                            <h2 class="title">FIM DO FORMULÁRIO</h2>
                            <button onclick="window.location='/Krift/servletweb?acao=EditarPerfil'" class="formBotao">ENVIAR</button>
                        </div>
                    </form>
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


        </div></div>




</body></html>