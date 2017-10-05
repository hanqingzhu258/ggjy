 

function addData() {
	var titleResult = false;
	var authorResult = false;
	var editorResult = false;
	var parentIdResult=false;
	var contentResult = false;
	
	titleResult = checkString('标题', "title", 100);
	authorResult = checkString('作者', "author", 20);
	editorResult = checkString('编辑人', "editor", 20);
	parentIdResult=checkString('分类',"parentId",3);
	contentResult = checkString('正文', "content",10000);
	
	if (titleResult == false || authorResult == false || editorResult == false
			|| contentResult == false || parentIdResult == false) {

	} else {

		$.ajax({
			url : '/addPost',
			data : JSON.stringify({
				"title" : $("#title").val(),
				"author" : $("#author").val(),
				"editor" : $("#editor").val(),
				"parentId":$("#parentId").val(),
				"content" : $("#content").val()
			}),
			traditional : false,
			type : 'post',
			cache : false,
			async : false,
			dataType : 'json',
			contentType : 'application/json; charset=UTF-8',
			success : function(data) {
				if (data.success == true) {
					alert(data.message);
					window.location.reload()
				} else {
					alert(data.message);
				}
			},
			error : function() {
				alert("添加失败");
			}
		})
	}
}
function checkNumber(name, object, limitLength) {

	if (/\D/.test(document.getElementById(object).value)) {
		alert(name + '只能输入数字!');
		document.getElementById(object).value = '';
		return false;
	} else if (document.getElementById(object).value.length == 0) {
		alert(name + '不能为空' + '!');

		return false;
	} else if (document.getElementById(object).value.length > limitLength ||document.getElementById(object).value.length<0) {
		alert(name + '长度必须小于' + limitLength + '且不小于0!');
		document.getElementById(object).value = '';
		return false;
	} else {
		return true;
	}
}
function checkString(name, object, limitLength) {

	if (document.getElementById(object).value.length > limitLength) {
		alert(name + '长度必须小于' + limitLength + '!');
		document.getElementById(object).value = '';
		return false;
	} else if (document.getElementById(object).value.length == 0) {
		alert(name + '不能为空' + '!');
		/*alert(document.getElementById(object).value);*/
		return false;
	} else {
		return true;
	}
}