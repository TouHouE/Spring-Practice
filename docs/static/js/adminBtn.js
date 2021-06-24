var menuBtn = document.getElementById("menuState");
menuBtn.setAttribute("onclick", "blockMenu()");

function showMenu() {
    let menu_container = document.getElementById("menu_container");
    //menu setting
    //let menu = document.getElementById("selector");
    menu_container.appendChild(menu);

    //button setting
    //menuBtn.onclick = showMenu();
    menuBtn.setAttribute("onclick", "blockMenu()");
    menuBtn.value = "隱藏選單";
    
}

function blockMenu() {
    //menu setting
    //let menu = document.getElementById("selector");
    menu.remove();
    //button setting
    //menuBtn.onclick = showMenu();
    menuBtn.setAttribute("onclick", "showMenu()");
    menuBtn.value = "顯示選單";
}
