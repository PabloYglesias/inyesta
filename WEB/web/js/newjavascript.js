function EpochToHuman() {
    var inputtext = (document.ef.TimeStamp.value * 1);
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
    outputtext += "<b>GMT</b>: " + datum.toGMTString() + "<br/><b>En su tiempo</b>: " + datum.toLocaleString();
    outputtext += "<hr class=\"lefthr\">";
    document.getElementById('result1').innerHTML = outputtext;
}
function HumanToEpoch() {
    var datum = new Date(Date.UTC(document.hf.yyyy.value, document.hf.mm.value - 1, document.hf.dd.value, document.hf.hh.value, document.hf.mn.value, document.hf.ss.value));
    document.getElementById('result2').innerHTML = "<b>Epoch</b>: " + (datum.getTime() / 1000.0) + "<hr class=\"lefthr\">";
}
function HumanToEpoch2() {
    var datum = new Date(document.fs.DateTime.value);
    document.getElementById('result3').innerHTML = "<b>Epoch</b>: " + (datum.getTime() / 1000.0);
}
function TimeCounter() {
    var t = parseInt(document.tc.DateTime.value);
    var days = parseInt(t / 86400);
    t = t - (days * 86400);
    var hours = parseInt(t / 3600);
    t = t - (hours * 3600);
    var minutes = parseInt(t / 60);
    t = t - (minutes * 60);
    var content = "";
    if (days)
        content += days + " días";
    if (hours || days) {
        if (content)
            content += ", ";
        content += hours + " horas";
    }
    if (content)
        content += ", ";
    content += minutes + " minotos y " + t + " segundos.";
    document.getElementById('result4').innerHTML = content;
}
var currentBeginEnd = "month";
function updateBe(a) {
    if (a != currentBeginEnd) {
        if (a == "day") {
            document.br.mm.disabled = 0;
            document.br.dd.disabled = 0;
        }
        if (a == "month") {
            document.br.mm.disabled = 0;
            document.br.dd.disabled = 1;
        }
        if (a == "year") {
            document.br.mm.disabled = 1;
            document.br.dd.disabled = 1;
        }
        currentBeginEnd = a;
        beginEnd();
    }
}
function beginEnd() {
    if (currentBeginEnd == "year")
        escrito = "año";
    if (currentBeginEnd == "month")
        escrito = "mes";
    if (currentBeginEnd == "day")
        escrito = "día";
    var outputText = "<table cellpadding=2 border=0><tr><td></td><td><b>Epoch</b></td><td>&nbsp;&nbsp;<b>Fecha</b></td></tr><tr><td>Principio del " + escrito + ":&nbsp;</td><td>";
    var mon = 0;
    var day = 1;
    var yr = document.br.yyyy.value;
    if (currentBeginEnd != "year") {
        mon = document.br.mm.value - 1;
    }
    if (currentBeginEnd == "day") {
        day = document.br.dd.value;
    }
    var startDate = new Date(Date.UTC(yr, mon, day, 0, 0, 0));
    outputText = outputText + (startDate.getTime() / 1000.0) + "</td><td>&nbsp;&nbsp;" + startDate.toGMTString() + "</td></tr>";
    outputText = outputText + "<tr><td>Fin del " + escrito + ":&nbsp;</td><td>";
    if (currentBeginEnd == "year")
        yr++;
    if (currentBeginEnd == "month")
        mon++;
    if (currentBeginEnd == "day")
        day++;
    var endDate = new Date(Date.UTC(yr, mon, day, 0, 0, -1));
    outputText = outputText + (endDate.getTime() / 1000.0) + "</td><td>&nbsp;&nbsp;" + endDate.toGMTString() + "</td></tr>";
    document.getElementById('resultbe').innerHTML = outputText;
}
function addbookmark() {
    if (document.all)
        window.external.AddFavorite(bookmarkurl, bookmarktitle);
}
function kp(id) {
    $('#' + id).focus();
    $('#' + id).select();
}
function homepageStart() {
    var today = new Date();
    $('#ef input:text[name=TimeStamp]').val(Math.round(today.getTime() / 1000.0));
    document.hf.yyyy.value = today.getUTCFullYear();
    document.br.yyyy.value = today.getUTCFullYear();
    document.hf.mm.value = today.getUTCMonth() + 1;
    document.br.mm.value = today.getUTCMonth() + 1;
    document.hf.dd.value = today.getUTCDate();
    document.br.dd.value = today.getUTCDate();
    document.hf.hh.value = today.getUTCHours();
    document.hf.mn.value = today.getUTCMinutes();
    document.hf.ss.value = today.getUTCSeconds();
    document.fs.DateTime.value = today.toGMTString();
    var clockActive = 1;
    $("#ecclock").mouseover(function() {
        clockActive = 0;
        $(this).css('backgroundColor', '#dadafc');
    });
    $("#ecclock").mouseout(function() {
        clockActive = 1;
        $(this).css('backgroundColor', '#eaeafa');
        $("#clocknotice").html('');
    });
    setInterval(function() {
        if (clockActive) {
            var epoch = Math.round(new Date().getTime() / 1000.0);
            $("#ecclock").html(epoch);
        }
    }, 1000);
}