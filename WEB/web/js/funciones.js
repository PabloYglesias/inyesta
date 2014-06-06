/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function convierteFecha(fecha) {
    fecha = fecha.substring(6, 19);
    var inputtext = (fecha * 1);
    var epoch = inputtext;
    var outputtext = "";
    var extraInfo = 0;
    if (inputtext >= 100000000000000) {
        outputtext += "<b>Tiempo Unix en <a target=\"_blank\" href=\"http://es.wikipedia.org/wiki/Microsegundo\">microsegundos</a>:</b><br/>";
        epoch = Math.round(inputtext / 1000000);
        inputtext = Math.round(inputtext / 1000);
    } else if (inputtext >= 100000000000) {
        outputtext += "<b>Tiempo Unix en milisegundos:</b><br/>";
        epoch = Math.round(inputtext / 1000);
    } else {
        if (inputtext > 10000000000)
            extraInfo = 1;
        inputtext = (inputtext * 1000);
    }
    var datum = new Date(inputtext);
    var localeString = datum.toLocaleString();
    var localeStringEnd = localeString.search(/GMT/i);
    if (localeStringEnd > 0) {
        localeString = localeString.substring(0, localeStringEnd);
    }
    return datum.toISOString().substring(0, 10);
}


function baseData(url) {
    $.getJSON(url, function(data) {
        var output = "";
        output += "<div id'image'><img src=" + data.bulkbeef.cow.farm.imageurl + " alt = 'map' class= 'image'/>";
        output += '<div id="ranking">';
        if(parseInt(data.bulkbeef.quality)==1){
           output += "<div id='info-rank'><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object></div>";}                                    
        if(parseInt(data.bulkbeef.quality)==2){
            output += "<div id='info-rank'><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object></div>";}                                   
        if(parseInt(data.bulkbeef.quality)==3){
            output += "<div id='info-rank'><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object></div>";}
        if(parseInt(data.bulkbeef.quality)==4){
            output += "<div id='info-rank'><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/0.svg' width='20' height='20'></object></div>";}
        if(parseInt(data.bulkbeef.quality)==5){
            output += "<div id='info-rank'><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object><object type='image/svg+xml' data='images/1.svg' width='20' height='20'></object></div>";}
        output += '</div><div id="shadow"><h1>';
        output +=  data.weight + ' Pounds';
        output += '<span>Best Before: ' + convierteFecha(data.bulkbeef.bestbefore);
        output += '</h1></div><ul id="lista-recomend" class="lista-recomend"><li><a href="file.html"><div class="izquierda"><object type="image/svg+xml" data="images/1s.svg" width=20 height=20></object><object type="image/svg+xml" data="images/1s.svg" width=20 height=20></object><object type="image/svg+xml" data="images/1s.svg" width=20 height=20></object><div>Beef With Potatoes</a></li><li><a href="file.html"><div class="izquierda"><object type="image/svg+xml" data="images/1s.svg" width=20 height=20></object><object type="image/svg+xml" data="images/0s.svg" width=20 height=20></object><object type="image/svg+xml" data="images/0s.svg" width=20 height=20></object><div>Asian Beef</a></li><li><a href="file.html"><div class="izquierda"><object type="image/svg+xml" data="images/1s.svg" width=20 height=20></object><object type="image/svg+xml" data="images/1s.svg" width=20 height=20></object><object type="image/svg+xml" data="images/0s.svg" width=20 height=20></object><div>Asparagous beef</a></li></ul>';

        document.getElementById("contenido").innerHTML = output;
    });
}

function Retailer(url) {
    $.getJSON(url, function(data) {
        var lt;
        var ln;
        console.log(output);
        var output = '<h1>Retailer <span><div id="name" class="name">' + data.bulkbeef.retailer.name + '</div></h1><br>';
        output += '<div id="mapita" style="width:148%;height:300px;z-index:0;margin-left:-50px;"></div>';
        document.getElementById("contenido").innerHTML = output;
        lt = data.bulkbeef.retailer.location.latitude;
        ln = data.bulkbeef.retailer.location.longitude;
        google.maps.event.addDomListener(window, 'load', initialize(lt, ln));
    });
}

function Slaughterh(url) {
    $.getJSON(url, function(data) {
        console.log(data);
        var lt;
        var ln;
        console.log(output);
        var output = '<h1>SlaughterHouse <span><div id="name" class="name">' + data.bulkbeef.slaughterhouse.name + '</div></h1><br>';
        output += '<div id="mapita" style="width:148%;height:300px;z-index:0;margin-left:-50px;"></div>';
        document.getElementById("contenido").innerHTML = output;
        lt = data.bulkbeef.slaughterhouse.location.latitude;
        ln = data.bulkbeef.slaughterhouse.location.longitude;
        google.maps.event.addDomListener(window, 'load', initialize(lt, ln));
    });
}

function Cow(url) {
    var lt;
    var ln;
    $.getJSON(url, function(data) {
        var output = '<div id="image"><img src="' + data.bulkbeef.cow.image + '" alt="map" class="image"/>';
        output += '<div id="shadow1"><h1><div id="race" class="race">' + (data.bulkbeef.cow.race == 0 ? "Hereford" : data.bulkbeef.cow.race) + '</div><span><div id="birth" class="birth">Born: ' + convierteFecha(data.bulkbeef.cow.birth) + '</div></span>';
        output += '</h1></div></div>';
        var medications = data.bulkbeef.cow.medications;
        output += "<table class='gridtable'>";
        for (var i in medications) {
            output += "<tr>";
            var aux = parseInt(i) + parseInt(1);
            output += "<th> Medication " + aux + "</th>";
            for (var j in medications[i]) {
                console.log(j);
                if (j == "datetime") {
                    output += "<td  style='width:26%;'>" + convierteFecha(medications[i][j]) + "</td>";
                }
                else {
                    output += "<td style='width:20%;'>" + medications[i][j] + "</td>";
                }
            }
            output += "</tr>";
        }
        output += "</table>";
        output += '<div id="mapita" style="width:145%;height:300px;z-index:0;"></div>';
        
        document.getElementById("contenido").innerHTML = output;
        lt = data.bulkbeef.cow.farm.position.latitude;
        ln = data.bulkbeef.cow.farm.position.longitude;
        google.maps.event.addDomListener(window, 'load', initialize(lt, ln));

    });

}

function initialize(lt, ln)
{
    var mapProp = {
        center: new google.maps.LatLng(lt, ln),
        zoom: 8,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("mapita"), mapProp);
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(lt, ln),
    });
    marker.setMap(map);
}

function transport(url) {
    $.getJSON(url, function(data) {
        var transportes = data.bulkbeef.transports;
        var outputEspecifico = "<table class='gridtable'  style='width:136%;'>";
        var outputGeneral = "";
        console.log(transportes);
        var distanciaTotal = 0;
        var tiempoTotal = 0;
        for (var i in transportes) {
            outputEspecifico += "<tr>";
            var aux = parseInt(i) + parseInt(1);
            outputEspecifico += "<th> Transport " + aux + "</th>";
            for (var j in transportes[i]) {
                console.log(j);
                if (j == "distance") {
                    outputEspecifico += "<td> Distance " + Math.round(transportes[i][j]) + "</td>";
                    distanciaTotal += parseFloat(transportes[i][j]);
                }
                else {
                    outputEspecifico += "<td> Minutes " + Math.round(transportes[i][j]) + "</td>";
                    tiempoTotal += parseFloat(transportes[i][j]);
                }
            }
            outputEspecifico += "</tr>";
        }
        outputEspecifico += "</table>";
        outputGeneral += "<h2> Total time: " + tiempoTotal + "</h2>";
        outputGeneral += "<h2> Total distance: " + Math.round(distanciaTotal) + "</h2>";
        outputGeneral += outputEspecifico;
        console.log(tiempoTotal);
        console.log(distanciaTotal);
        document.getElementById("contenido").innerHTML = outputGeneral;


    });
}






