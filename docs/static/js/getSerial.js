function getSerial() {
    //var textArea = document.getElementById("student_serial");
var dom = document.getElementById("student_serial");
var data = dom.value;
var os_type = getOS();
var urlVar = "";
var dataJSON = {};
dataJSON["serial"] = data;
dataJSON["os_type"] = os_type;


if(os_type === "ios" || os_type === "android" || os_type === "windowsPhone" || os_type === "linux") {
    urlVar = "mob/";
} else {
    urlVar = "";
}


$.ajax({
url:"http://192.168.0.156:8080/game/post",
data:JSON.stringify(dataJSON),
type: "POST",
dataType: "text/html;charset=utf-8",
contentType: "application/json;charset=utf-8",
success: function(successData) {
    console.log("Success");
    console.log(successData.toString());
   // this = successData;
   window.location = "http://localhost:8080/game/" + data;
   window.location.reload(true);
},
error: function(errorData) {
     console.log("Success");
        console.log(errorData.toString());
       // this = successData;
       window.location = "http://192.168.0.156:8080/" + urlVar + "game/" + data;
       //this.location.reload(true);
}
});

}

function getOS() {
    var userAgent = 'navigator' in window && 'userAgent' in navigator && navigator.userAgent.toLowerCase() || '';
    var vendor = 'navigator' in window && 'vendor' in navigator && navigator.vendor.toLowerCase() || '';
    var appVersion = 'navigator' in window && 'appVersion' in navigator && navigator.appVersion.toLowerCase() || '';

    if(/mac/i.test(appVersion)) {
        return "MacOSX";
    }
    if(/win/i.test(appVersion)) {
        return "windows";
    }
    if(/linux/i.test(appVersion)) {
        return "linux";
    }
    if(/iphone/i.test(userAgent) || /ipad/i.test(userAgent) || /ipod/i.test(userAgent)) {
        return "ios";
    }
    if(/android/i.test(userAgent)) {
        return "android";
    }
    if(/win/i.test(appVersion) && /phone/i.test(userAgent)) {
        return "windowsPhone";
    }
}