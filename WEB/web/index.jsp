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
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <script src="js/modernizr.custom.js"></script>

        <!-- Skitter Styles -->
        <link href="css/skitter.styles.css" type="text/css" media="all" rel="stylesheet" />
        
        <!-- Skitter JS -->
        <script type="text/javascript" language="javascript" src="js/jquery-1.6.3.min.js"></script>
        <script type="text/javascript" language="javascript" src="js/jquery.easing.1.3.js"></script>
        <script type="text/javascript" language="javascript" src="js/jquery.animate-colors-min.js"></script>
        <script type="text/javascript" language="javascript" src="js/jquery.skitter.min.js"></script>
        
        <!-- Init Skitter -->
        <script type="text/javascript" language="javascript">
            $(document).ready(function() {
                $('.box_skitter_large').skitter({
                    theme: 'clean',
                    numbers_align: 'center',
                    progressbar: true, 
                    dots: true, 
                    preview: true
                });
            });
        </script>

        <link rel="stylesheet" type="text/css" href="css/demo.css" />
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
                   <div id="partidos">
                        <div id="partido">
                            <div id="equipo1">Universidad</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">Real Garcilaso</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result1.png" width="100%"/></div>
                            </div>
                        </div>

                        <div id="partido">
                            <div id="equipo1">Universidad</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">Real Garcilaso</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result1.png" width="100%"/></div>
                            </div>
                        </div>

                        <div id="partido">
                            <div id="equipo1">Nacional</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">Atlético</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result2.png" width="100%"/></div>
                            </div>
                        </div>

                        <div id="partido">
                            <div id="equipo1">Botafogo</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">Independiente</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result3.png" width="100%"/></div>
                            </div>
                        </div>

                        <div id="partido">
                            <div id="equipo1">Santa Fe</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">Zamora</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result4.png" width="100%"/></div>
                            </div>
                        </div>

                        <div id="partido">
                            <div id="equipo1">Santos Laguna</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">Deportivo</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result1.png" width="100%"/></div>
                            </div>
                        </div>

                        <div id="partido">
                            <div id="equipo1">Atlético Madrid</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">Espanyol</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result2.png" width="100%"/></div>
                            </div>
                        </div>

                        <div id="partido">
                            <div id="equipo1">Elche</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">Real Betis</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result2.png" width="100%"/></div>
                            </div>
                        </div>

                        <div id="partido">
                            <div id="equipo1">Velez</div>
                            <div id="apostar"><a href="consulta.jsp"><button id="apuestabutton"> Apuesta </button></a></div>
                            <div id="equipo2">The Strongest</div>
                            <div id="resultado">
                                <div id="result1"><img src="images/result4.png" width="100%"/></div>
                            </div>
                        </div>
                        

                        <!--<img src="TEMP/partidos.png"/>-->
                   </div>
                   <div id="home">
                        <div class="border_box">
                        <div class="box_skitter box_skitter_large">
                            <ul>
                                <li><a href="#cube"><img src="images/001.jpg" class="cube" /></a><div class="label_text"><p>Elche - Real Betis</p></div></li>
                                <li><a href="#cubeRandom"><img src="images/002.jpg" class="cubeRandom" /></a><div class="label_text"><p>Santos Laguna - Deportivo</p></div></li>
                                <li><a href="#block"><img src="images/003.jpg" class="block" /></a><div class="label_text"><p>Santa Fe - Zamora</p></div></li>
                            </ul>
                        </div>
                    </div>
                        <img src="images/home.png" style="float:left; width:40%;"/>
                        <div id="participa">
                            <h1 id="homeh1"><a href="login.jsp">Participa</a></h1>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel risus eu mi condimentum bibendum. Cras fermentum ligula erat, ac posuere felis tincidunt vitae. Aenean tincidunt nulla ut purus interdum aliquet. Donec id egestas mi. Mauris placerat fermentum nibh vitae placerat.</p>
                        </div>
                        <div id="clear"></div>
                        <div id="prediccion">
                            <h1 id="homeh1"><a href="consulta.jsp">Consulta una predicción</a></h1>
                            <p>Maecenas at feugiat lacus, a pulvinar ante. In eu nunc consequat, varius est eget, euismod tortor. Nam a faucibus neque, in blandit nulla. Etiam varius eu felis et pharetra. </p>
                        </div>
                        <!--<img src="TEMP/home.png" usemap="#homemap">

                        <map name="homemap">
                          <area shape="rect" coords="280,20,610,110" href="login.html" alt="Sun">
                          <area shape="rect" coords="20,380,480,420" href="consulta.html" alt="Sun">
                        </map>-->
                   </div>
                </div>
            </header> 
            <footer>
                <div id="footerleft"><a href="index.jsp">Portal</a> - <a href="login.jsp">Participa</a> - <a href="consulta.jsp">Consulta</a></div>
                <div id="footerright">@INYESTA es un desarrollo del Máster de Ingeniería en Informática de la UCM</div>
            </footer>
        </div><!-- /container -->
          <script src="js/classie.js"></script>
        <script src="js/gnmenu.js"></script>
        <script src="js/funciones.js"></script>
        <script src="js/cookieHandle.js"></script>
        <script>
            $(document).ready(function() {
                Cow("recursos/"+getCookie('url')+".json");
            });
            new gnMenu(document.getElementById('gn-menu'));
        </script>
    </body>
</html>