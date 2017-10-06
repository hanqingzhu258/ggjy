window.onload = function() {
	$.ajax({
		url : '/getPostByGroupParentId',
		type : 'get',
		dataType : 'json',
		traditional : false,
		contentType : 'application/json; charset=UTF-8',
		timeout : 1000,
		cache : false,
		bedforeSend : LoadFunction,
		error : erryFunction,
		success : succFunction
	})

	function LoadFunction() {

	}

	function erryFunction() {
		alert("error")
	}

	function succFunction(data) {
		/* insert(data.data.list, data.data.backPage); */
		var content1 = document.getElementById("index_content1");
		var html1 = '';
		$.each(data.data[0], function(index, val) {
			
			html1 += "<div><a onclick='getPostById("+val.id+")'>"
						+"<img src='image/首页_14.jpg' /></a>"
						+"<div><a onclick='getPostById("+val.id+")'>"
						+"<p> "+ val.title + "</p></a><p>作者："
						+val.author+""+val.time+ "</p></div></div>";
				content1.innerHTML = html1;
				
		});
		
		var content2 = document.getElementById("index_content2");
		var html2 = '';
		$.each(data.data[1], function(index, val) {
			
			html2 += "<div><a onclick='getPostById("+val.id+")'>"
						+"<img src='image/首页_14.jpg' /></a>"
						+"<div><a onclick='getPostById("+val.id+")'>"
						+"<p> "+ val.title + "</p></a><p>作者："
						+val.author+""+val.time+ "</p></div></div>";
				content2.innerHTML = html2;
				
		});
		
		var content3 = document.getElementById("index_content3");
		var html3 = '';
		$.each(data.data[2], function(index, val) {
			
			html3 += "<div><a onclick='getPostById("+val.id+")'>"
						+"<img src='image/首页_14.jpg' /></a>"
						+"<div><a onclick='getPostById("+val.id+")'>"
						+"<p> "+ val.title + "</p></a><p>作者："
						+val.author+""+val.time+ "</p></div></div>";
				content3.innerHTML = html3;
				
		});
		
		var content4 = document.getElementById("index_content4");
		var html4 = '';
		$.each(data.data[3], function(index, val) {
			
			html4 += "<div><a onclick='getPostById("+val.id+")'>"
						+"<img src='image/首页_14.jpg' /></a>"
						+"<div><a onclick='getPostById("+val.id+")'>"
						+"<p> "+ val.title + "</p></a><p>作者："
						+val.author+""+val.time+ "</p></div></div>";
				content4.innerHTML = html4;
				
		});
		
		var content5 = document.getElementById("index_content5");
		var html5 = '';
		$.each(data.data[4], function(index, val) {
			
			html5 += "<div><a onclick='getPostById("+val.id+")'>"
						+"<img src='image/首页_14.jpg' /></a>"
						+"<div><a onclick='getPostById("+val.id+")'>"
						+"<p> "+ val.title + "</p></a><p>作者："
						+val.author+""+val.time+ "</p></div></div>";
				content5.innerHTML = html5;
				
		});
		
	}
}

function getPostById(id) {

	$.ajax({
		url : '/getPostById?id=' + id,
		type : 'get',
		dataType : 'json',
		traditional : false,
		contentType : 'application/json; charset=UTF-8',
		timeout : 1000,
		cache : false,
		bedforeSend : LoadFunction,
		error : erryFunction,
		success : succFunction
	})

	function LoadFunction() {

	}
	function erryFunction() {
		alert("error");
	}
	function succFunction(data) {
		
		/*alert("title="+data.data.title+"time="+data.data.time
				+"author="+data.data.author+"editor="+data.data.editor
				+"content="+data.data.content);*/
		
		/*alert(data.data.content);*/
		
		var myurl= "/page1-1?id="+data.data.id+"&title="
				+ data.data.title+"&time="+data.data.time+"&author="+data.data.author
				+"&editor="+data.data.editor+"&content="+data.data.content;
		window.location.assign(encodeURI(myurl));
	}

}