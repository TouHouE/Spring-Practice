var canvas = document.getElementById("coor");
var can = canvas.getContext("2d");
var width = canvas.width, height = canvas.height;
var w = width - 50, h = height;
var x0 = w / 2, y0 = (h - 50) / 2 + 50;
var excelData = "";
var dataX = [], dataY = [];
var dataIsPut = false;
/*
var idFile = document.getElementById("file");
idFile.addEventListener("change", (event) => {
    console.log(event.target.files);
})
*/

function putPoint() {
    var betterData = excelData.replace(new RegExp("\t{2, }\n", "gm"), "\n");
    console.log(betterData);
    var eachLine = betterData.split("\n");
    
    let len = eachLine.length;
    let str = "";
    let sumY = 0, sumX = 0;


    for(let i = 0; i < len - 1; ++i) {
        let xy = eachLine[i].split("\t{1,}");
        let xys = xy[0].split("	");
        sumX += Number(xys[0]);
        sumY += Number(xys[1]);
        //console.log("line " + i + "\nx:" + xys[0] + "\ny:" + xys[1]);


        dataX[i] = Number(xys[0]);
        dataY[i] = Number(xys[1]);

        drawPoint(Number(xys[0]), Number(xys[1]));        
    }
	console.log("totally x:" + sumX + "\ntotally y:" + sumY);
    doRegression(sumX,sumY, len - 1);
    
}

var counter = 0;

function drawPoint(x, y) {
    //console.log(x + "," + y);

    if(Number.isNaN(x) || Number.isNaN(y)) {

    } else {
        //console.log("" + (counter += 1) + " : " + x + ", " + y);
        can.beginPath();
        //console.log("Round:" + (counter += 1) + "(x, y) = (" + x + ", " + y + ")");
        //can.arc(getX(x), getY(y), 2.5, 0, Math.PI * 2);
        //can.fillText("" + counter, getX(x), getY(y));
        //console.log("getX, getY = " + getX(x) + ", " + getY(y));
        can.stroke();
        can.beginPath();
        can.arc(getX(x), getY(y), 3, 0, 2 * Math.PI);
        can.stroke();
    }

}

function getX(x) {
    return x0 + x;
}


function getY(y) {
    return (width - 50) / 2 - y + 50;
}


function readFile(file) {
	excelData = "";
	dataX = [];
	dataY = [];



    if(!dataIsPut) {
        dataIsPut = !dataIsPut;
	let url = "http://itc.nkust.edu.tw/" + file;
        var sender = new XMLHttpRequest();
        sender.open("GET", file, false);

        sender.onreadystatechange = function() {
            excelData = sender.responseText;
        
            putPoint();
        
        }
        sender.send();
    }
}

function init() {
    
    //draw x-axis
    can.moveTo(0, 450);
    can.lineTo(800, 450);

    //draw y-axix
    can.moveTo(400, 50);
    can.lineTo(400, 850)
    can.stroke();

    can.beginPath();
    for(let i = getX(0 - w); i < w - 50; i += 25) {
        can.moveTo(getX(i), getY(5));
        can.lineTo(getX(i), getY(-5));
        can.moveTo(getX(5), getY(i));
        can.lineTo(getX(-5), getY(i));
    }
    can.stroke();

    can.beginPath();
    for(let i = getY(-400); i < 850; i += 25) {
        can.moveTo(getX(5), getY(i));
        can.lineTo(getX(-5), getY(i));
    }
    can.stroke();


    can.font = "30px serif";

    can.fillText("x", w + 10, getY(0) + 5);
    can.fillText("y", getX(0) - 10, 50 - 10);

    can.font = "10px serif";



    for(let i = getX(0 - w); i < w; i += 25) {
        can.fillText("" + i, getX(i), getY(-10));
        can.fillText("" + i, getX(5), getY(i));

    }
    
}

function clean() {
    counter = 0;
    dataIsPut = false;
    can.clearRect(0, 0, canvas.width + 1, canvas.height + 1);
    can.clearRect(0, 0, canvas.width + 1, canvas.height + 1);
    showFunction.innerText = "";
    init();
}

var showFunction = document.getElementById("func_name");
/**
 * 
 * @param {Number} sumX 
 * @param {Number} sumY 
 * @param {Number} size 
 */
//regression line is y = b1x + b0
function doRegression(sumX, sumY, size) {
    console.log("sum:\n" + sumX + "\n" + sumY);
    let xBar = sumX / size;
    let yBar = sumY / size;
    let b1 = 0;
    let b0 = 0;
    let b1Son = 0, b1Mom = 0;
    console.log(xBar + "\n" + yBar + "\n"); 


    for(let i = 0; i < size; ++i) {
        b1Son += (dataX[i] - xBar) * (dataY[i] - yBar);
        b1Mom += Math.pow((dataX[i] - xBar), 2);
    }
    b1 = b1Son / b1Mom;
    b0 = yBar - b1 * xBar;
    console.log("y = " + b1 +"x + " + b0);

    if(b0 < 0) {
        showFunction.innerText = "y = " + b1 +"x - " + Math.abs(b0);
        console.log("is neg");
    } else {
        showFunction.innerText = "y = " + b1 +"x + " + b0;
    }

    //showFunction.innerText = "y = " + b1 +"x + " + b0;
    can.beginPath();
    //console.log("Start (X, Y) = (" + getX(-400))
    console.log("(-400, " + (-400 * b1 + b0) + ")");
	console.log("(400, " + (400 * b1 + b0) + ")");
	
	
	can.moveTo(getX(-400), getY((-400) * b1 + b0));
    can.lineTo(getX(400), getY(400 * b1 + b0));
    can.stroke();
}

function main() {
	init();
}


main();
