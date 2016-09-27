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
                <div id="add">
                    <button onclick="alert('ADD')">
                        <div class="addicon">                            
                        </div>
                    </button>                        
                </div>  
                <div id="options">
                    <button onclick="alert('MSG')">
                        <div class="iconMessage">
                        </div>
                    </button>
                    <button onclick="window.location='ranking.jsp'">
                        <div class="iconRanking">
                        </div>
                    </button>
                    <button onclick="window.location='index.jsp'">
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
                </div><div class="profileimage"></div>
            </div>  
        </div>

        <div class="content">
            <div class="wrapper">     
                <div class="sidebox">
                    <h2 class="title">MENU</h2>
                    <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                    <ul class="sidenav"> 
                        <%
                            /* if(usuariocadastrado){ */
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
                            /* } */
                        %>
                        <a href="#">
                            <li>
                                <span>SOBRE</span> 
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                            </li>
                        </a>
                        <a href="#">
                            <li> 
                                <span>AJUDA</span>
                                <img class="navicon" src="https://cdn4.iconfinder.com/data/icons/wirecons-free-vector-icons/32/menu-alt-512.png">
                            </li>
                        </a> 
                    </ul>  
                </div>

                <div>
                    <div class="navblock" style="display: block;"> 
                        <h2 class="title whitetitle">SOBRE O KRIFT</h2>
                        <div class="sobreKrift">
                            <span> TEXTO SOBRE NÓS  </span>
                        </div>
                    </div>
                    <div class="navblock"> 
                        <h2 class="title whitetitle">AJUDA</h2>

                        <div style="margin: 10px;">
                            <ul class="passos" style="min-width: inherit;">
                                <li>
                                    <h4>BUSCA</h4>
                                    <p style="font-size: smaller;padding: 5px 0px;">Para efetivar a busca, utilize a barra de pesquisa no canto direito do cabeÃ§alho. Digitando o nome da receita que deseja buscar e pressionando, em seguida, a tecla ENTER do seu teclado, vocÃª será levado Ã  tela com os resultados de sua pesquisa.</p>
                                </li>

                                <li>
                                    <h4>TENDÊNCIAS</h4>
                                    <p style="font-size: smaller;">O menu de tendências é uma ferramenta para você selecionar sua preferÃªncia, filtrando os alimentos encontrados na busca. Selecionando a opÃ§Ã£o "SEM TENDÊNCIA", vocÃª estará ativando a busca sem filtros de tendÃªncia e encontrará todo tipo de receita.</p>
                                </li>

                                <li>
                                    <h4>NAVEGAÇÃO</h4>
                                    <p style="font-size: smaller;">Para navegar pelo KRIFT, utilize o menu que está sempre na parte da esquerda ou use as funcionalidades  do cabeÃ§alho, as quais estÃ£o inseridas nos Ã­cones da parte esquerda, no logo do KRIFT e em sua foto de perfil.</p>
                                </li>
                            </ul>
                        </div>
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


        </div></div>




</body></html>