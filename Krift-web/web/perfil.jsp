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
                    <button onclick="alert('RANKING')">
                        <div class="iconRanking">
                        </div>
                    </button>
                    <button onclick="alert('HOME')">
                        <div class="iconHome">
                        </div>
                    </button>
                </div> 
            </div>  

            <div class="middle">
                <img src="klogo.png" class="klogo">
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
                        <div class="perfilBG"> 

                            <div class="perfil">
                                <img class="imgperfil" src="http://i.imgur.com/VVZYJel.png">
                                <div class="nomerank">
                                    <h2>Bruce Vayne</h2>
                                    RANK 1
                                </div>
                                <div class="estastitica">
                                    <span> 4935 KP</span>
                                </div>
                                <div class="estastitica">
                                    <span>CARNÍVORO</span>
                                </div>
                            </div>
                        </div> 
                    </div>

                    <div class="navblock" style="max-width: 780px;display: block;"> 
                        <div class="sobrePerfil">
                            <h4>SOBRE MIM</h4>
                            <span>SOBRE</span>
                        </div>
                    </div>

                    <div>
                        <div class="navblock" style="min-width: 500px;display: inline-block;"> 
                            <h2 class="title whitetitle">RECEITAS DE BRUCE VAYNE</h2>


                            <ul class="resultados ulx3">  
                                <li>
                                    <div class="img" style="background-image:url(http://www.sisenor.com.br/wp-content/uploads/2016/01/banner_lamberjack.jpg);">
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