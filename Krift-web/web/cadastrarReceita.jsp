<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html><head>
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
                <div id="add">
                    <button onclick="window.location='cadastrarReceita.jsp'">
                        <div class="addicon">                            
                        </div>
                    </button>                        
                </div>  
                <div id="options">
                    <button onclick="alert('MSG')">
                        <div class="iconMessage">
                        </div>
                    </button>
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
                        <a href="#">
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
                    <form id="form" method="POST" action="/Krift/servletweb?acao=CriarReceita">
                        <div class="navblock" style="padding-bottom: 20px;"> 

                            <h2 class="title whitetitle">CADASTRO DE RECEITA - DADOS BÁSICOS</h2>

                            <div class="formulario">

                                <div class="formsection">
                                    <h4>Nome</h4>
                                    <div>
                                        <input name="nome" placeholder="Digite aqui o nome da receita" type="text">
                                        <span>NECESSÁRIO</span>
                                    </div>
                                </div>

                                <div class="formsection">
                                    <h4>Descrição</h4>
                                    <div>
                                        <textarea nome="descricao" placeholder="Digite aqui a descrição da receita" rows="4" cols="50" maxlength="255" style="margin: 0px; height: 66px; width: 579px;"></textarea>
                                        <span style="color: #a2a2a2;">OPCIONAL</span>
                                    </div>

                                </div>

                                <div class="formsection">
                                    <h4>Rendimento</h4>
                                    <div>
                                        <input name="rendimento" placeholder="Digite aqui o rendimento da receita" type="text">
                                        <span>NECESSÁRIO</span>
                                    </div>
                                </div>

                                <div class="formsection">
                                    <h4>Tempo de preparo</h4>
                                    <div>
                                        <input name="tempo" placeholder="Digite o tempo em minutos, exemplo: 45" type="text">
                                        <span style="color: #a2a2a2;">OPCIONAL</span>
                                    </div>
                                </div>

                                <div class="formsection"><h4>Tendência</h4>
                                    <div>
                                        <select name="tendencia" id="selectTendencia">
                                            <option value="onivorismo">Onivorismo - Contém carne, vegetais e/ou derivados de animais simultaneamente</option>
                                            <option value="vegetarianismo">Vegetarianismo - Contém vegetais e derivados de animais</option>
                                            <option value="veganismo">Veganismo - Contém apenas vegetais</option>
                                            <option value="carnivorismo">Carnivorismo - Contém apenas carne</option>	
                                            <option value="semtendencia">Sem tendência - Não classificado</option>
                                        </select>
                                        <span>NECESSÁRIO</span>
                                    </div>

                                </div>
                                <div class="formsection"><h4>Imagem</h4>
                                    <div>
                                        <input name="imagem" style="height: 36px;padding-top: 8px;" id="fileupload" value="fileupload" name="fileupload" type="file">
                                        <span style="color: #a2a2a2;">OPCIONAL</span>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="navblock" style="padding-bottom: 20px;"> 
                            <h2 class="title whitetitle">CADASTRO DE RECEITA - INGREDIENTES</h2>
                            <div class="formulario">
                                <div class="formsection">
                                    <h4>Ingrediente 1</h4>
                                    <div>
                                        <input name="ingrediente" maxlength="30" placeholder="Digite aqui o nome do ingrediente" type="text" style="width: 280px; margin-right: 10px;"/>
                                        <input name="quantidade" maxlength="40" placeholder="Digite aqui a quantidade" type="text" style="width: 290px;"/>
                                        <span>NECESSÁRIO</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="navblock" style="padding-bottom: 20px;"> 
                            <h2 class="title whitetitle">CADASTRO DE RECEITA - MODO DE PREPARO</h2>
                            <div class="formulario"><div class="formsection"><h4>Passo 1</h4>
                                    <div>
                                        <input name="passo" maxlength="255" placeholder="Digite aqui o passo" type="text"/>
                                        <span>NECESSÁRIO</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="navblock" style="position: relative;"> 
                            <h2 class="title whitetitle">FIM DO FORMULÁRIO</h2>
                            <button onclick="alert('nao vai nao')" class="formBotao" >ENVIAR
                            </button>
                        </div>
                    </form>    
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




    </body></html>