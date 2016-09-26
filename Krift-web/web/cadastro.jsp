<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login do KRIFT</title>
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <link rel="stylesheet" href="css/login.css"> 
        <link rel="shortcut icon" type="image/png" href="favicon.png"/>
        <script type="text/javascript" src="script.js"></script>
    </head>

    <body>
        <div class="login-page">
            <div class="form">

                <img class="login-logo" src="klogo.png">
                <form id="form" class="login-form">
                    <input type="text" name="email" placeholder="Email"/>
                    <input type="text" name="usuario" placeholder="Usuário"/>
                    <input type="password" name="senha" placeholder="Senha"/>
                    <input type="password" name="confirmacao" placeholder="Confirmação de senha"/>
                    <button>Cadastrar</button>  
                    <p class="message">Já possui um registro? 
                        <a href="login.html">Entre em sua conta</a>
                    </p>
                </form>
            </div> 
            <div>
            </div>
        </div>

    </body>
