var pageNow = 1;
var totalPageCount = null;
window.onload = function() {
	$.ajax({
		url : '/getPostByParentId?parentId=10',
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
		/*insert(data.data.list, data.data.backPage);*/
		var content = document.getElementById("content");
		var html = '';
		$.each(data.data,function(index,val) {

							html += "<div><a onclick='getPostById("
									+ val.id
									+ ")'>"
									+ "<img src='image/首页_14.jpg' />"
									+ "</a> <div><p><a onclick='getPostById("
									+val.id
									+")'>"
									+val.title
									+"</a></p></div></div>";
							content.innerHTML = html;
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
		
		var myurl= "/page3-1?id="+data.data.id+"&title="
				+ data.data.title+"&time="+data.data.time+"&author="+data.data.author
				+"&editor="+data.data.editor+"&content="+data.data.content;
		window.location.assign(encodeURI(myurl));
	}

}


function insert(data, page) {
	var content = document.getElementById("content");
	var pagediv = document.getElementById("pagediv");
	pageNow = page.pageNow;
	totalPageCount = page.totalPageCount;
	var html = '';
	var pageMessage = '';
	$
			.each(
					data,
					function(index, val) {

						html += "<div><a onclick='getPostById("
								+ val.id
								+ ")'>"
								+ "<img src='image/首页_14.jpg' />"
								+ "</a> <div><p><a onclick='getPostById("
								+val.id
								+")'>"
								+val.title
								+"</a></p></div></div>";
						content.innerHTML = html;
					});

	pageMessage += "<span>第"
			+ pageNow
			+ "页/共"
			+ totalPageCount
			+ "页</span><a onclick=\"page(0)\" href=\"#\">&nbsp;\&nbsp;\&nbsp;首页\&nbsp;\&nbsp;\&nbsp;</a><a onclick=\"page(1)\" href=\"#\">上一页\&nbsp;\&nbsp;\&nbsp;</a><a onclick=\"page(2)\" href=\"#\">下一页\&nbsp;\&nbsp;\&nbsp;</a><a onclick=\"page(3)\" href=\"#\">尾页</a>";

	pagediv.innerHTML = pageMessage;
}
function getBringInfo(BringInfoId) {
	document.cookie = "BringInfoId=" + BringInfoId;
	document.cookie = "pageNow=" + pageNow;

	window.location.href = "getBringInfo";
}
function page(id) {
	if (id == 0) {
		if (pageNow == '1') {
			alert("已经是首页");
		} else {
			window.location.href = '/list';
		}
	} else if (id == 1) {
		if (pageNow == 1) {
			alert("已经是首页");
		} else {
			pageAjax(pageNow - 2);
		}
	} else if (id == 2) {
		if (pageNow == totalPageCount) {
			alert("已经是尾页");
		} else {
			pageAjax(pageNow);
		}
	} else if (id == 3) {
		if (totalPageCount == pageNow) {
			alert("已经是尾页");
		} else {
			pageAjax(totalPageCount - 1);
		}
	} else {

	}

}
function pageAjax(wantPage) {
	$.ajax({
		url : '/nextOrBeforePage',
		data : JSON.stringify({
			"pageNow" : wantPage
		}),
		type : 'POST',
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
		insert(data.data.list, data.data.backPage);
	}
}
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return unescape(arr[2]);
    else
        return null;
}