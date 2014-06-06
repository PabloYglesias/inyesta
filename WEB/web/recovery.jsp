<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>INYESTA</title>
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <link rel="shortcut icon" href="../favicon.ico">
        <link rel="stylesheet" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <script src="js/modernizr.custom.js"></script>
    </head>
    <body>
        <div class="container">
            <ul id="gn-menu" class="gn-menu-main">
                <li class="gn-trigger">
                    <a class="gn-icon gn-icon-menu"><span>Menu</span></a>
                    <nav class="gn-menu-wrapper">
                        <div class="gn-scroller">
                            <ul class="gn-menu">
                                
                                <li><a class="gn-icon gn-icon-cog" href="#"><input type="text" name="user" style="width:250px;margin-top:1rem;" value="usuario"></a></li>
                                <li><a class="gn-icon gn-icon-cog" href="#"><input type="password" name="password" style="width:250px;margin-top:1rem;" value="contraseÃ±a"></a></li>
                                <li><a href="login.jsp"><center><b>Acceder</b></center></a></li>
                                <li><a class="gn-icon gn-icon-help" href="./recovery.jsp"><small>Recuperar Contraseña</small></a></li>
                               
                            </ul>
                        </div><!-- /gn-scroller -->
                    </nav>
                </li>
               
                <li ><a href="index.jsp"><span>INYESTA</span></a></li>
            </ul>
            <header>
                <div id="contenido">
                    <br/><br/>Ingrese el email, por favor.<br/><br/>
                   <center><input type="text" name="user" style="width:30%;float:left;" value="usuario o email">
                   <button style="width:20rem;float:left;margin-top:-1rem;margin-left:.5rem;">Recuperar contraseña</button></center>
                </div>
            </header> 
        </div><!-- /container -->
          <script src="js/classie.js"></script>
        <script src="js/gnmenu.js"></script>
        <script src="js/funciones.js"></script>
        <script src="js/jquery-1.8.2.min.js"></script>
        <script src="js/cookieHandle.js"></script>
        <script>
            $(document).ready(function() {
                Cow("recursos/"+getCookie('url')+".json");
            });
            new gnMenu(document.getElementById('gn-menu'));
        </script>
    </body>
</html>