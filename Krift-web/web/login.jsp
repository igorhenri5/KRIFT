<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html ><head>
        <meta charset="utf-8">
        <title>Login do KRIFT</title>
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <link rel="stylesheet" href="css/login.css"> 
        <script type="text/javascript" src="script.js"></script>
        <link rel="shortcut icon" type="image/png" href="favicon.png"/>

    </head>

    <body>

        <div class="login-page">
            <div class="form">
                <a href="index.jsp"><img class="login-logo" src="klogo.png"></a>
                <form id="form" method="POST" action="/Krift/servletweb?acao=Login" class="login-form">
                    <input type="text" name="usuario" placeholder="Usuário">
                    <input type="password" name="senha" placeholder="Senha">
                    <button>login</button> 
                    <p class="message">Ainda não está registrado? 
                        <a href="cadastro.jsp">Crie uma conta</a>
                    </p>
                </form> 
            </div>

        </div>
    </body></html>