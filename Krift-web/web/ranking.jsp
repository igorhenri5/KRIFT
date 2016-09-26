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
                    <div class="navblock" style="padding-bottom: 20px;"> 
                        <h2 class="title whitetitle" >RANKING</h2>
                        
                        <table class="ranking">
                               <% 
                                   /* for(varrerusuarios){  */
                               %>
                               <tbody><tr>
                                    <th>Rank</th>
                                    <th>Nome do usuário</th> 
                                    <th>Pontos</th>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td>Bruce Vayne</td> 
                                    <td>4910</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jackson</td> 
                                    <td>94</td>
                                </tr>
                            </tbody>
                            <% 
                               /*   } */
                            %>
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