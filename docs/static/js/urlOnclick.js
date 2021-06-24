function linkByID(domID, link) {
	let dom = document.getElementById(domID);
	alert(dom.toString());

	dom.onclick = function() {
		this.location = link;
	}

	return dom;
}


function linkByClass(domClass, link) {
	let dom = document.getElementsByClassName(domClass);

	dom.onclick = function() {
		this.location = link;
	}
	
}
