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
                                
                                <li><a class="gn-icon gn-icon-cog" href="#"><input type="text" name="user" style="width:250px;margin-top:1rem;" value="usuario" readonly></a></li>
                                <li><center><b>Ver Perfil</b></center></li>
                                <li><a href="index.jsp"><center><b>Salir</b></center></a></li>
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
                    <img src="images/persona.png" style="float:left; width:40%;"/>
                    <div id="participa">
                            <h1 id="homeh1">Usuario</h1>
                            <a href="consulta.jsp"><button>Hacer apuesta</button></a>
                        </div>

                        <div id="prediccion">
                            <h1 id="homeh1">Historial de Apuestas</h1>
                            <div style="position: relative;">
                                <img src="images/historial.png" />
                                <div style="position: absolute;left: 1rem; top: 1rem;">
                                    <div id="historialtext">
                                        <div id="estimacion">Barcelona 2 - 1 Real Madrid</div>
                                        <div id="solucion"><img src="images/resultado1.png"/></div>
                                        <div id="tiempo"><i>Hace 2 días</i></div>
                                        <div id="clear">
                                    </div>

                                    <div id="historialtext">
                                        <div id="estimacion">Atlético Mad 1 - 2 AC Milán</div>
                                        <div id="solucion"><img src="images/resultado2.png"/></div>
                                        <div id="tiempo"><i>Hace 3 días</i></div>
                                        <div id="clear">
                                    </div>

                                    <div id="historialtext">
                                        <div id="estimacion">Sevilla 2 - 1 Manchester City</div>
                                        <div id="solucion"><img src="images/resultado1.png"/></div>
                                        <div id="tiempo"><i>Hace 7 días</i></div>
                                        <div id="clear">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--<img src="TEMP/apuestas.png">-->
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