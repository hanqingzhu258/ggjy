window.onload = function() {
	function getParam(name) {
		var url=decodeURI(location.href);
		var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		/* var r = window.location.search.substr(1).match(reg); */
		var r = url.substr(1).match(reg); 
		if (r != null) {
			return unescape(r[2]);
		}
		return null;
	}
	
		function aha() {
			var title = getParam("title");
			var time = getParam("time");
			var author = getParam("author");
			var editor = getParam("editor");
			var content = getParam("content");

			//alert(content);


			document.getElementById("postTitle").innerHTML="标题："+title;
			document.getElementById("time").innerHTML="时间："+time;
			document.getElementById("author").innerHTML="作者："+author;
			document.getElementById("editor").innerHTML="编辑："+editor;
			document.getElementById("content").innerHTML="正文："+content;
		}
		 aha();
	}