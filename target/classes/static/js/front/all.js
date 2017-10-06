title_hover_adjust();
var roll_wait_time = 2000;
var cur_roll = 1;
var timeout_roll = setTimeout("roll_img(1)",roll_wait_time);
for(var i=1;i<9;i++){
	document.getElementById("roll"+i).addEventListener("click",function(i2){
		return function(){
			clearTimeout(timeout_roll);
			roll_img(i2-cur_roll);
		}
	}(i),false);
}
document.getElementById("roll_left").addEventListener("click",function(){
	clearTimeout(timeout_roll);
	roll_img(-1);
},false);
document.getElementById("roll_right").addEventListener("click",function(){
	clearTimeout(timeout_roll);
	roll_img(1);
},false);
function title_hover_adjust(){
	var title = document.querySelector(".title");
	var child = title.firstElementChild;
	do{
		child = child.nextElementSibling;
		var a = child.firstElementChild;
		var ul = a.nextElementSibling;
		if(ul){
			ul.style.left = a.offsetLeft + "px";
		}
	}while(child != title.lastElementChild);
}
function roll_img(next){
	document.getElementById("roll"+cur_roll).setAttribute("class","");
	cur_roll += next;
	cur_roll = (cur_roll + 7) % 8 + 1;
	document.getElementById("roll_frame").style.backgroundImage = "url(image/roll/roll" + cur_roll + ".jpg)";
	document.getElementById("roll"+cur_roll).setAttribute("class","roll_get");
	timeout_roll = setTimeout("roll_img(1)",roll_wait_time);
}
for(var i=1;i<6;i++){
	document.getElementById("title2"+i).addEventListener("mouseenter",function(i2){
		return function(){
			var id = document.querySelector(".title2_get").getAttribute("id");
			document.querySelector(".title2_get").setAttribute("class","");
			document.getElementById("index_content"+id[6]).setAttribute("class","index_content hidden");
			document.getElementById("title2"+i2).setAttribute("class","title2_get");
			document.getElementById("index_content"+i2).setAttribute("class","index_content");
		}
	}(i),false);
}