var BR = document.createElement("br");

function showUI() {
    let container = document.getElementById("container");
    let form = document.createElement("form");
    form.setAttribute("id", "data_form");

    let fileInput = document.createElement("input");
    fileInput.setAttribute("type", "file");
    fileInput.setAttribute("class", "file_input");
    fileInput.setAttribute("name", "file_input");

    let submit = document.createElement("input");
    submit.setAttribute("type", "button");
    submit.setAttribute("id", "send");
    submit.setAttribute("onclick", "sendFile()")

    form.appendChild(fileInput);
    form.appendChild(BR);
    form.appendChild(submit);

    container.appendChild(form);
}

function deleteUI() {
    let form = document.getElementById("data_form");
    form.remove();
}


function sendFile() {
    let formObj = document.querySelector("form");
    let formData = new FormData(formObj);
    let data = {};
    data["file"] = formData.get("file_input");
    console.log(data["file"]);
    data["file"] = data

    $.ajax({
        type:"POST",
        url:"http://itc.nkust.edu.tw/admin/page/post",
        dataType:"application/json",
        data:JSON.stringify(data),
        contentType:"application/json",
        success:function(res) {
            alert(res["status"]);
        },

        error: function(errorRes) {
            alert(errorRes["status"]);
        }
    });
}
