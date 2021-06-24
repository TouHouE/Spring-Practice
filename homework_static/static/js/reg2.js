var showGroup1Info = document.getElementById("group1_info");
var showGroup2Info = document.getElementById("group2_info");

var canvas = document.getElementById("coor");
var can = canvas.getContext("2d");
var MARGIN = 50;
var realWidth = canvas.width, realHeight = canvas.height;
var realMaxX = realWidth - MARGIN, realMaxY = realHeight;
var realMinX = 0, realMinY = MARGIN;
var realCenterX = (realWidth - MARGIN) / 2, realCenterY = (realHeight - MARGIN) / 2 + MARGIN;
var IM_MAX_X = 200, IM_MAX_Y = 200;
var IM_MIN_X = -200, IM_MIN_Y = -200;
var xSteps = 1, ySteps = 1;
var halfBar = 2.5;
var dataIsPut = false;
var totallyPoint = [];
var group1 = [], group2 = [];
var w1 = 0, w2 = 0, b1 = 0, b2 = 0;

function test() {
    let lenG1 = group1.length;
    let lenG2 = group2.length;
    let str = "";

    for(let i = 0; i < lenG1; ++i) {
        str += "group1[" + i + "][X] = " + group1[i]["x"] + "\n";
        str += "group1[" + i + "][Y] = " + group1[i]["y"] + "\n";
    }
    console.log(str);
    str = "";

    for(let i = 0; i < lenG2; ++i) {
        str += "group2[" + i + "] = " + group2[i] + "\n";
    }
    console.log(str);
}

function getRealX(imX) {
    return 2 * imX + realCenterX;
}

function getRealY(imY) {
    return -2 * imY + realCenterY;
}

function g1(imX) {
    console.log(imX * w1 + b1);
}

function g2(imX) {
    console.log(imX * w2 + b2);
}

function doGroup2Regression() {
    let info = "";
    let xSum = 0, ySum = 0;
    let son = 0, mom = 0;
    let len = group2.length;

    for(let i = 0; i < len; ++i) {
        drawPoint(group2[i]["x"], group2[i]["y"], "red");

        xSum += group2[i]["x"];
        ySum += group2[i]["y"];
    }
    let xBar = xSum / len, yBar = ySum / len;

    for(let i = 0; i < len; ++i) {
        son += (group2[i]["x"] - xBar) * (group2[i]["y"] - yBar);
        mom += Math.pow((group2[i]["x"] - xBar), 2);
    }
    let w = son / mom;
    let b = yBar - w * xBar;
    w2 = w;
    b2 = b;


    console.log("Group2: y = " + w + "x + " + b);
    let showB = "";

    if(b < 0) {
        showB ="" + -1 * b;
    } else {
        showB = " + " + b;
    }


    info = "Group2(Red object) : \nRegression Function : y = " + w + "x" + showB;  
    info += "\nSet Size:" + len;
    showGroup2Info.innerText = info;

    can.beginPath();
    can.moveTo(getRealX(IM_MIN_X), getRealY(IM_MIN_X * w + b));
    can.lineTo(getRealX(IM_MAX_X), getRealY(IM_MAX_X * w + b));
    can.stroke();
}

function doGroup1Regression() {
    
    let xSum = 0, ySum = 0;
    let son = 0, mom = 0;
    let len = group1.length;

    for(let i = 0; i < len; ++i) {
        drawPoint(group1[i]["x"], group1[i]["y"], "blue");

        xSum += group1[i]["x"];
        ySum += group1[i]["y"];
    }
    let xBar = xSum / len, yBar = ySum / len;

    for(let i = 0; i < len; ++i) {
        son += (group1[i]["x"] - xBar) * (group1[i]["y"] - yBar);
        mom += Math.pow((group1[i]["x"] - xBar), 2);
    }
    let w = son / mom;
    let b = yBar - w * xBar;
    console.log("Group1: y = " + w + "x + " + b);
    w1 = w;
    b1 = b;


    let showB = "";

    if(b < 0) {
        showB ="" + -1 * b;
    } else {
        showB = " + " + b;
    }


    info = "Group1(Blue Object) : \nRegression Function : y = " + w + "x" + showB;  
    info += "\nSet Size:" + len;
    showGroup1Info.innerText = info;


    
    can.beginPath();
    can.moveTo(getRealX(IM_MIN_X), getRealY(IM_MIN_X * w + b));
    can.lineTo(getRealX(IM_MAX_X), getRealY(IM_MAX_X * w + b));
    can.stroke();

}

//function is y = wx + b
function doClassification() {
    let xSum = 0, ySum = 0;
    let son = 0, mom = 0;
    let len = totallyPoint.length - 1;

    for(let i = 0; i < len; ++i) {
        xSum += totallyPoint[i]["x"];
        ySum += totallyPoint[i]["y"];
    }
    let xBar = xSum / len, yBar = ySum / len;

    for(let i = 0; i < len; ++i) {
        son += (totallyPoint[i]["x"] - xBar) * (totallyPoint[i]["y"] - yBar);
        mom += Math.pow((totallyPoint[i]["x"] - xBar), 2);
    }
    let w = son / mom;
    let b = yBar - w * xBar;
    let group1Count = 0, group2Count = 0;

    for(let i = 0; i < len; ++i) {
        if(w * totallyPoint[i]["x"] + b - totallyPoint[i]["y"] > 0) {
            group1[group1Count++] = totallyPoint[i];
        } else {
            group2[group2Count++] = totallyPoint[i];
        }
    }
    
    
}



function putPoint() {
    let eachLine = excelData.split("\n");
    let len = eachLine.length;

    for(let i = 0; i < len; ++i) {
        let xy = eachLine[i].split("\t");
        let p = {
            "x":Number(xy[0]),
            "y":Number(xy[1])
        };
        totallyPoint[i] = p;
        //drawPoint(Number(xy[0]), Number(xy[1]));
    }
    
}

function drawPoint(imX, imY, color) {
    console.log("IM_X:" + imX + "\nIM_Y:" + imY);
    can.beginPath();
    can.arc(getRealX(imX), getRealY(imY), 2, 0, Math.PI * 2);
    can.strokeStyle = String(color);
    can.stroke();
    //console.log("Draw");
}

function drawPointB(imX, imY) {
    drawPoint(imX, imY, "black");
}

function readFile(file) {

    if(!dataIsPut) {
        dataIsPut = !dataIsPut;
        var sender = new XMLHttpRequest();
        sender.open("GET", file, false);

        sender.onreadystatechange = function() {
            excelData = sender.responseText;       
            putPoint();
            doClassification();
            doGroup1Regression();
            doGroup2Regression();
            //test();
        }
        sender.send();
    }
}

function init() {
    can.strokeStyle = "black";

    //draw x-axis
    can.moveTo(getRealX(-200), getRealY(0));
    can.lineTo(getRealX(200), getRealY(0));

    //draw y-axix
    can.moveTo(getRealX(0), getRealY(200));
    can.lineTo(getRealX(0), getRealY(-200));
    can.stroke();
    setSteps();

    //draw x coordinate
    can.beginPath();
    for(let i = IM_MIN_X; i <= IM_MAX_X; i += xSteps * 20) {
        can.moveTo(getRealX(i), getRealY(halfBar));
        can.lineTo(getRealX(i), getRealY(-1 * halfBar));
        //can.moveTo(getRealX(5), getRealY(i));
        //can.lineTo(getRealX(-5), getRealY(i));
    }
    can.stroke();
    


    //draw y coordinate
    can.beginPath();
    for(let i = IM_MIN_Y; i <= IM_MAX_Y; i += ySteps * 20) {
        can.moveTo(getRealX(halfBar), getRealY(i));
        can.lineTo(getRealX(-1 * halfBar), getRealY(i));
    }
    can.stroke();


    can.font = "30px serif";

    can.fillText("x", getRealX(IM_MAX_X) + 10, getRealY(0) + 5);
    can.fillText("y", getRealX(0) - 10, getRealY(IM_MAX_Y) - 10);

    can.font = "10px serif";



    for(let i = IM_MIN_X; i <= IM_MAX_X; i += xSteps * 20) {
        can.fillText("" + i, getRealX(i), getRealY(-10));
        can.fillText("" + i, getRealX(5), getRealY(i));
    }
    
}

function setSteps() {
    let x = realWidth - MARGIN;
    let y = realHeight - MARGIN;
    let imWidth = IM_MAX_X - IM_MIN_X;
    let imHeight = IM_MAX_Y - IM_MIN_Y;
    xSteps = x / imWidth;
    ySteps = y / imHeight;

}

function clean() {
    counter = 0;
    dataIsPut = false;
    can.clearRect(0, 0, canvas.width + 1, canvas.height + 1);
    can.clearRect(0, 0, canvas.width + 1, canvas.height + 1);
    can.clearRect(0, 0, canvas.width + 1, canvas.height + 1);
    showGroup1Info.innerText = "";
    showGroup2Info.innerText = "";

    init();
}


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
    can.beginPath();
    //console.log("Start (X, Y) = (" + getRealX(-400))
    can.moveTo(getRealX(-400), getRealY((-400) * b1 + b0));
    can.lineTo(getRealX(400), getRealY(400 * b1 + b0));
    can.stroke();
}

function main() {
    init();
}

window.load = main();


