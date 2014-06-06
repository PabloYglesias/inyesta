-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 06-05-2014 a las 17:27:00
-- Versión del servidor: 5.6.12-log
-- Versión de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


--
-- Base de datos: `lfp`
--
CREATE DATABASE IF NOT EXISTS `lfp` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `lfp`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acta`
--

CREATE TABLE IF NOT EXISTS `acta` (
  `IDActa` int(20) NOT NULL AUTO_INCREMENT,
  `NumeroJornada` int(20) NOT NULL,
  `NombreEstadio` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `NombreEquipoLocal` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `NombreEquipoVisitante` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `NumeroColegiado` int(20) NOT NULL,
  `Fecha` date NOT NULL,
  PRIMARY KEY (`IDActa`),
  KEY `NombreEstadio` (`NombreEstadio`,`NombreEquipoLocal`,`NombreEquipoVisitante`,`NumeroColegiado`),
  KEY `NombreEquipoLocal` (`NombreEquipoLocal`),
  KEY `NombreEquipoVisitante` (`NombreEquipoVisitante`),
  KEY `NumeroColegiado` (`NumeroColegiado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `acta`
--

INSERT INTO `acta` (`IDActa`, `NumeroJornada`, `NombreEstadio`, `NombreEquipoLocal`, `NombreEquipoVisitante`, `NumeroColegiado`, `Fecha`) VALUES
(1, 22, 'La rosaleda', 'Málaga', 'Real Madrid', 222333, '2013-10-15'),
(2, 22, 'Mestalla', 'Valencia', 'Real Sociedad', 456721, '2013-10-15'),
(3, 23, 'Camp Nou', 'Barcelona', 'Valencia', 456722, '2013-10-31'),
(4, 23, 'Anoeta', 'Real Sociedad', 'Getafe', 333222, '2013-10-31'),
(5, 23, 'Santiago Bernabéu', 'Málaga', 'Real Madrid', 111222, '2013-10-31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apuestaexperto`
--

CREATE TABLE IF NOT EXISTS `apuestaexperto` (
  `IDApuesta` int(5) NOT NULL AUTO_INCREMENT,
  `IDExperto` int(5) NOT NULL,
  `IDActa` int(5) NOT NULL,
  `Resultado` varchar(1) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`IDApuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colegiado`
--

CREATE TABLE IF NOT EXISTS `colegiado` (
  `NumeroColegiado` int(6) NOT NULL,
  `NombreColegiado` varchar(40) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `Apellido1Colegiado` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `Apellido2Colegiado` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `NombreFederacion` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`NumeroColegiado`),
  UNIQUE KEY `NumeroColegiado` (`NumeroColegiado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `colegiado`
--

INSERT INTO `colegiado` (`NumeroColegiado`, `NombreColegiado`, `Apellido1Colegiado`, `Apellido2Colegiado`, `NombreFederacion`) VALUES
(111222, 'Carlos', 'Velasco', 'Carballo', 'Madrileña'),
(222333, 'Fernando', 'Teixeira', 'Vittienes', 'Cántabra'),
(333222, 'Alberto', 'Undiano', 'Mallenco', 'Navarra'),
(456721, 'César ', 'Muñiz', 'Fernández', 'Asturiana'),
(456722, 'Miguel Ángel', 'Pérez', 'Lasa', 'Guipuzcoana');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE IF NOT EXISTS `direccion` (
  `IDDireccion` int(20) NOT NULL AUTO_INCREMENT,
  `TipoCalle` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `NombreCalle` varchar(30) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `NumeroCalle` int(5) NOT NULL,
  `CodigoPostal` int(6) NOT NULL,
  PRIMARY KEY (`IDDireccion`),
  KEY `CodigoPostal` (`CodigoPostal`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`IDDireccion`, `TipoCalle`, `NombreCalle`, `NumeroCalle`, `CodigoPostal`) VALUES
(1, 'Avenida', 'EstadioAnoeta', 0, 2001),
(2, 'Calle', 'EstadioDeMestalla', 25, 46010),
(3, 'Calle', 'CalledeCampNOU', 21, 2000),
(4, 'Calle', 'CalleDelBernabeu', 1, 28025),
(5, 'Calle', 'DeGetafe', 1, 2321);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE IF NOT EXISTS `equipo` (
  `NombreEquipo` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `NombreEstadio` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `Victorias` int(4) NOT NULL,
  `Derrotas` int(4) NOT NULL,
  `Empates` int(4) NOT NULL,
  `LigaNacional` int(4) NOT NULL,
  `RankingUEFA` int(4) NOT NULL,
  `GolesMarcados` int(4) NOT NULL,
  `GolesEncajados` int(4) NOT NULL,
  `EstiloJuego` varchar(200) COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`NombreEquipo`),
  UNIQUE KEY `NombreEquipo` (`NombreEquipo`),
  KEY `NombreEstadio` (`NombreEstadio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`NombreEquipo`, `NombreEstadio`, `Victorias`, `Derrotas`, `Empates`, `LigaNacional`, `RankingUEFA`, `GolesMarcados`, `GolesEncajados`, `EstiloJuego`) VALUES
('Barcelona', 'Camp Nou', 0, 0, 0, 0, 0, 0, 0, ''),
('Getafe', 'Coliseum ', 0, 0, 0, 0, 0, 0, 0, ''),
('Málaga', 'La rosaleda', 0, 0, 0, 0, 0, 0, 0, ''),
('Real Madrid', 'Santiago Bernabéu', 0, 0, 0, 0, 0, 0, 0, ''),
('Real Sociedad', 'Anoeta', 0, 0, 0, 0, 0, 0, 0, ''),
('Valencia', 'Mestalla', 0, 0, 0, 0, 0, 0, 0, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadio`
--

CREATE TABLE IF NOT EXISTS `estadio` (
  `NombreEstadio` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `AforoMaximo` int(9) NOT NULL,
  PRIMARY KEY (`NombreEstadio`),
  UNIQUE KEY `NombreEstadio` (`NombreEstadio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `estadio`
--

INSERT INTO `estadio` (`NombreEstadio`, `AforoMaximo`) VALUES
('Anoeta', 32000),
('Camp Nou', 100000),
('Coliseum ', 25000),
('La rosaleda', 35000),
('Mestalla', 52600),
('Santiago Bernabéu', 85000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `experto`
--

CREATE TABLE IF NOT EXISTS `experto` (
  `IDExperto` int(5) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `URL` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`IDExperto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gol`
--

CREATE TABLE IF NOT EXISTS `gol` (
  `IDActa` int(20) NOT NULL,
  `Minuto` int(20) NOT NULL,
  `DorsalGoleador` int(20) NOT NULL,
  `EquipoGoleador` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `Forma` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  KEY `IDActa` (`IDActa`),
  KEY `IDActa_2` (`IDActa`),
  KEY `EquipoGoleador` (`EquipoGoleador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `gol`
--

INSERT INTO `gol` (`IDActa`, `Minuto`, `DorsalGoleador`, `EquipoGoleador`, `Forma`) VALUES
(2, 40, 5, 'Real Sociedad', 'Cabeza'),
(2, 42, 9, 'Real Sociedad', 'Pie izquierdo'),
(3, 1, 6, 'Valencia', 'Cabeza'),
(3, 3, 6, 'Valencia', 'Pie izquierdo'),
(4, 45, 10, 'Real Sociedad', 'Cabeza'),
(4, 47, 10, 'Real Sociedad', 'Pie derecho'),
(4, 90, 10, 'Real Sociedad', 'Mano'),
(2, 90, 11, 'Real Sociedad', 'Cabeza'),
(2, 87, 6, 'Valencia', 'Cabeza'),
(2, 89, 5, 'Valencia', 'Cabeza'),
(2, 90, 1, 'Valencia', 'Cabeza'),
(5, 1, 7, 'Real Madrid', 'Cabeza'),
(5, 90, 7, 'Real Madrid', 'Cabeza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juegaen`
--

CREATE TABLE IF NOT EXISTS `juegaen` (
  `IDJugador` int(20) NOT NULL,
  `NombreEquipo` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `DorsalJugador` int(20) NOT NULL,
  KEY `NombreJugador` (`IDJugador`,`NombreEquipo`),
  KEY `NombreEquipo` (`NombreEquipo`),
  KEY `NombreJugador_2` (`IDJugador`),
  KEY `IDJugador` (`IDJugador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `juegaen`
--

INSERT INTO `juegaen` (`IDJugador`, `NombreEquipo`, `DorsalJugador`) VALUES
(1, 'Valencia', 1),
(2, 'Valencia', 2),
(3, 'Valencia', 4),
(4, 'Valencia', 5),
(5, 'Valencia', 6),
(7, 'Real Sociedad', 10),
(8, 'Real Sociedad', 5),
(9, 'Real Sociedad', 9),
(10, 'Real Sociedad', 1),
(11, 'Real Sociedad', 11),
(12, 'Real Madrid', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE IF NOT EXISTS `jugador` (
  `IDJugador` int(100) NOT NULL AUTO_INCREMENT,
  `NombreJugador` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `Apellido1Jugador` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `Apellido2Jugador` varchar(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `Goles` int(5) NOT NULL,
  `Posicion` varchar(150) CHARACTER SET armscii8 NOT NULL,
  `PartidosTitular` int(5) NOT NULL,
  `PartidosSuplente` int(5) NOT NULL,
  `Lesiones` int(3) NOT NULL,
  `Nacionalidad` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `Altura` int(3) NOT NULL,
  `FechaNacimiento` datetime NOT NULL,
  PRIMARY KEY (`IDJugador`),
  UNIQUE KEY `IDJugador` (`IDJugador`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci AUTO_INCREMENT=13 ;

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`IDJugador`, `NombreJugador`, `Apellido1Jugador`, `Apellido2Jugador`, `Goles`, `Posicion`, `PartidosTitular`, `PartidosSuplente`, `Lesiones`, `Nacionalidad`, `Altura`, `FechaNacimiento`) VALUES
(1, 'Diego', 'Alves', 'Carreira', 0, '', 0, 0, 0, 'Brasil', 188, '1985-06-24 00:00:00'),
(2, 'Pablo Daniel', 'Piatti', NULL, 0, '', 0, 0, 0, 'Argentina', 163, '1989-03-31 00:00:00'),
(3, 'Adil', 'Rami', NULL, 0, '', 0, 0, 0, 'Francia', 190, '1985-12-27 00:00:00'),
(4, 'Víctor', 'Ruiz', 'Torre', 0, '', 0, 0, 0, 'España', 185, '1989-01-25 00:00:00'),
(5, 'Oriol', 'Romeu', 'Vidal', 0, '', 0, 0, 0, 'España', 183, '1991-09-24 00:00:00'),
(7, 'Antoine', 'Griezzman', 'Test', 0, '', 0, 0, 0, 'Francia', 170, '1993-10-31 00:00:00'),
(8, 'Markel', 'Bergara', 'Larrañaga', 0, '', 0, 0, 0, 'España', 180, '1986-05-05 00:00:00'),
(9, 'Imanol', 'Aguirretxe', 'Arruti', 0, '', 0, 0, 0, 'España', 187, '1987-02-24 00:00:00'),
(10, 'Claudio Andrés', 'Bravo', 'Muñoz', 0, '', 0, 0, 0, 'Chile', 190, '1985-06-24 00:00:00'),
(11, 'Carlos Alberto', 'Vela', 'Garrido', 0, '', 0, 0, 0, 'Mexico', 175, '1987-02-24 00:00:00'),
(12, 'Cristiano', 'Ronaldo', NULL, 0, '', 0, 0, 0, 'Portugal', 185, '1985-10-29 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `población`
--

CREATE TABLE IF NOT EXISTS `población` (
  `CodigoPostal` int(6) NOT NULL,
  `NombrePoblacion` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`CodigoPostal`),
  UNIQUE KEY `CodigoPostal` (`CodigoPostal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `población`
--

INSERT INTO `población` (`CodigoPostal`, `NombrePoblacion`) VALUES
(2000, 'Barcelona'),
(2001, 'SanSebastian'),
(2321, 'Getafe'),
(28025, 'Madrid'),
(46010, 'Valencia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultado`
--

CREATE TABLE IF NOT EXISTS `resultado` (
  `IDActa` int(20) NOT NULL,
  `GolesLocal` int(20) NOT NULL,
  `GolesVisitante` int(20) NOT NULL,
  KEY `IDActa` (`IDActa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `resultado`
--

INSERT INTO `resultado` (`IDActa`, `GolesLocal`, `GolesVisitante`) VALUES
(2, 3, 3),
(3, 0, 2),
(4, 3, 0),
(1, 0, 0),
(5, 2, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sedeestadio`
--

CREATE TABLE IF NOT EXISTS `sedeestadio` (
  `NombreEstadio` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `IDDireccion` int(20) NOT NULL,
  KEY `NombreEstadio` (`NombreEstadio`,`IDDireccion`),
  KEY `IDDireccion` (`IDDireccion`),
  KEY `IDDireccion_2` (`IDDireccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `sedeestadio`
--

INSERT INTO `sedeestadio` (`NombreEstadio`, `IDDireccion`) VALUES
('Anoeta', 1),
('Camp Nou', 3),
('Coliseum ', 5),
('Mestalla', 2),
('Santiago Bernabéu', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tendenciaapuesta`
--

CREATE TABLE IF NOT EXISTS `tendenciaapuesta` (
  `IDApuesta` int(20) NOT NULL AUTO_INCREMENT,
  `CasaApuestas` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `URLCasaApuestas` varchar(500) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `IDActa` int(20) NOT NULL,
  `TendenciaApuesta` int(1) NOT NULL,
  `NombreEquipo1` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `NombreEquipo2` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`IDApuesta`),
  KEY `NombreEquipo1` (`NombreEquipo1`),
  KEY `NombreEquipo2` (`NombreEquipo2`),
  KEY `IDActa` (`IDActa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `IDUser` int(5) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(25) CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,
  `contrasena` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`IDUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=1 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `acta`
--
ALTER TABLE `acta`
  ADD CONSTRAINT `acta_ibfk_1` FOREIGN KEY (`NombreEstadio`) REFERENCES `estadio` (`NombreEstadio`),
  ADD CONSTRAINT `acta_ibfk_2` FOREIGN KEY (`NombreEquipoLocal`) REFERENCES `equipo` (`NombreEquipo`),
  ADD CONSTRAINT `acta_ibfk_3` FOREIGN KEY (`NombreEquipoVisitante`) REFERENCES `equipo` (`NombreEquipo`),
  ADD CONSTRAINT `acta_ibfk_4` FOREIGN KEY (`NumeroColegiado`) REFERENCES `colegiado` (`NumeroColegiado`);

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`CodigoPostal`) REFERENCES `población` (`CodigoPostal`);

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`NombreEstadio`) REFERENCES `estadio` (`NombreEstadio`);

--
-- Filtros para la tabla `gol`
--
ALTER TABLE `gol`
  ADD CONSTRAINT `gol_ibfk_1` FOREIGN KEY (`IDActa`) REFERENCES `acta` (`IDActa`),
  ADD CONSTRAINT `gol_ibfk_2` FOREIGN KEY (`EquipoGoleador`) REFERENCES `equipo` (`NombreEquipo`);

--
-- Filtros para la tabla `juegaen`
--
ALTER TABLE `juegaen`
  ADD CONSTRAINT `juegaen_ibfk_2` FOREIGN KEY (`NombreEquipo`) REFERENCES `equipo` (`NombreEquipo`),
  ADD CONSTRAINT `juegaen_ibfk_3` FOREIGN KEY (`IDJugador`) REFERENCES `jugador` (`IDJugador`);

--
-- Filtros para la tabla `resultado`
--
ALTER TABLE `resultado`
  ADD CONSTRAINT `resultado_ibfk_1` FOREIGN KEY (`IDActa`) REFERENCES `acta` (`IDActa`);

--
-- Filtros para la tabla `sedeestadio`
--
ALTER TABLE `sedeestadio`
  ADD CONSTRAINT `sedeestadio_ibfk_1` FOREIGN KEY (`NombreEstadio`) REFERENCES `estadio` (`NombreEstadio`);

--
-- Filtros para la tabla `tendenciaapuesta`
--
ALTER TABLE `tendenciaapuesta`
  ADD CONSTRAINT `tendenciaapuesta_ibfk_1` FOREIGN KEY (`IDActa`) REFERENCES `acta` (`IDActa`),
  ADD CONSTRAINT `tendenciaapuesta_ibfk_2` FOREIGN KEY (`NombreEquipo1`) REFERENCES `equipo` (`NombreEquipo`),
  ADD CONSTRAINT `tendenciaapuesta_ibfk_3` FOREIGN KEY (`NombreEquipo2`) REFERENCES `equipo` (`NombreEquipo`);

--
-- Filtros para la tabla `apuestaexperto`
--
ALTER TABLE `apuestaexperto`
  ADD CONSTRAINT `apuestaexperto_ibfk_1` FOREIGN KEY (`IDActa`) REFERENCES `acta` (`IDActa`),
  ADD CONSTRAINT `apuestaexperto_ibfk_2` FOREIGN KEY (`IDExperto`) REFERENCES `experto` (`IDExperto`);
