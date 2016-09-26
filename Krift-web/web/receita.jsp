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
                    <div class="navblock"> 
                        <h2 class="title whitetitle" style="text-align: center;">
                            <img src="http://image.prntscr.com/image/61e330fff563461ab885b941dde0ef67.png" align="middle" style="height: 28px;">
                        </h2>

                        <div style="padding: 30px;padding-bottom: 35px;"> 

                            <header>
                                <div style="display: inline-block;">
                                    <h2>Nome</h2>

                                    <div style="display: inline-block;">
                                        <h5 class="onivoro">ONÍVORO</h5>
                                    </div>
                                </div>
                                <div class="receitaAutor">
                                    <div class="idt">enviado por
                                        <a href="#">Bruce Vayne</a>
                                    </div>
                                    <img src="http://i.imgur.com/VVZYJel.png">
                                </div>
                            </header>

                            <div style="margin-top: 20px;box-sizing: border-box;">  
                                <div class="receitaImg" style="background-image:url(http://lucioamorim.com.br/wp-content/uploads/2015/06/sushi.jpg);">
                                </div>
                                <div class="receitaDesc">
                                    <h4>DESCRIÇÃO</h4>
                                    <span>Descrição</span>
                                    <div class="nota">
                                        <h3>5.0</h3>
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </div>

                    <div class="IRT">
                        <div class="ingredientes"> 
                            <h2 class="title">INGREDIENTES</h2>
                            <ul class="passos" style="width: 100%;">
                                <li>
                                    <h4>INGREDIENTE</h4>
                                </li>
                            </ul>   

                        </div>
                        <div style="display: inline-block; margin-left: 15px;">
                            <div class="small"> 
                                <h2 class="title">RENDIMENTO</h2>
                                <span>QUANTIDADE</span>
                            </div>

                            <div style="margin-bottom:0px" class="small"> 
                                <h2 class="title" style="font-size: 18px;">TEMPO ESTIMADO</h2>
                                <span>TEMPO</span>
                            </div>
                        </div>
                    </div>



                    <div class="navblock"> 
                        <h2 class="title whitetitle">MODO DE PREPARO</h2>
                        <div style="padding: 15px;"> 
                            <ul class="passos" style="width: 100%;">
                                <li>
                                    <h4>PASSO</h4>
                                    <p>Descrição do passo</p>
                                </li>
                            </ul>    
                        </div> 
                    </div>

                    <form id="form">                        
                        <div class="navblock" style="padding-bottom: 20px;"> 
                            <h2 class="title whitetitle">AVALIAÇÕES</h2>
                            <div class="comentar">
                                <fieldset class="rating" style="position: absolute;right: -5px;top: 10;">
                                    <input type="radio" id="star5" name="rating" value="5"><label class="full" for="star5" title="Awesome - 5 stars"></label>
                                    <input type="radio" id="star4half" name="rating" value="4 and a half"><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
                                    <input type="radio" id="star4" name="rating" value="4"><label class="full" for="star4" title="Pretty good - 4 stars"></label>
                                    <input type="radio" id="star3half" name="rating" value="3 and a half"><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
                                    <input type="radio" id="star3" name="rating" value="3"><label class="full" for="star3" title="Meh - 3 stars"></label>
                                    <input type="radio" id="star2half" name="rating" value="2 and a half"><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
                                    <input type="radio" id="star2" name="rating" value="2"><label class="full" for="star2" title="Kinda bad - 2 stars"></label>
                                    <input type="radio" id="star1half" name="rating" value="1 and a half"><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
                                    <input type="radio" id="star1" name="rating" value="1"><label class="full" for="star1" title="Sucks big time - 1 star"></label>
                                    <input type="radio" id="starhalf" name="rating" value="half"><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                                </fieldset>                            
                                <h3>DEIXE SEU COMENTÁRIO</h3>

                                <div style="padding-bottom: 20px">
                                    <input name="comentario" placeholder="Comente" type="text">
                                    <button>ENVIAR</button>
                                </div> 

                                <% 
                                    /* for(varrercomentarios) */
                                %>

                                <ul class="passos" style="width: 100%;">
                                    <li>
                                        <h4>USUARIO</h4>
                                        <p>COMENTARIO</p>
                                    </li>
                                </ul>

                                <% 
                                    /* } */
                                %>

                            </div>
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
    </div>

</body></html>