function setCookie(nombre, valor, tiempo) {
    var fecha = new Date();
    fecha.setTime(fecha.getTime() + tiempo);
    document.cookie = nombre + ' = ' + escape(valor) + ((tiempo == null) ? '' : '; expires = ' + fecha.toGMTString()) + '; path=/';
}

function getCookie(nombre) {
    var nombreCookie, valorCookie, cookie = null, cookies = document.cookie.split(';');
    for (i = 0; i < cookies.length; i++) {
        valorCookie = cookies[i].substr(cookies[i].indexOf('=') + 1);
        nombreCookie = cookies[i].substr(0, cookies[i].indexOf('=')).replace(/^\s+|\s+$/g, '');
        if (nombreCookie == nombre)
            cookie = unescape(valorCookie);
    }
    return cookie;
}


