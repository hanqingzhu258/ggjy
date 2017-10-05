window.onload = function() {
	var html = "<div class = ></div>";
	$.ajax({
		url : '/getPost',
		type : 'POST',
		dataType : 'json',
		timeout : 1000,
		cache : false,
		bedforeSend : LoadFunction,
		error : erryFunction,
		success : succFunction
	})

	function LoadFunction() {
		$("#list").html('加载中...请稍后！');
	}

	function erryFunction() {
		alert("error")
	}

	function succFunction(data) {
		var table = $('#list')
				.dataTable(
						{
							"data" : data.data,
							"columns" : [{
								"data" : "title"
							},{
								"data" : "author"
							},{
								"data" : "pushTime"
							},{
								/*"data" : $("#parentId").find("option").eq(data.parentId).text()*/
								"data":"classification"
							},{
								"data" : null
							} ],
							"lengthChange" : true,
							"searching" : false,
							"pageLength" : 10,
							"serverSide" : false,
							"processing" : true,
							"ordering":false,
							"oLanguage" : {// 插件的汉化
								"sLengthMenu" : "每页显示 _MENU_ 条记录",
								"sZeroRecords" : "抱歉， 没有找到",
								"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
								"sInfoEmpty" : "没有数据",
								"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",

								"oPaginate" : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								},
								"sZeroRecords" : "没有检索到数据",
								"sProcessing" : "<img src='' />",
								"sSearch" : "搜索"
							},
							columnDefs : [ {
								targets : 4,
								render : function(data, type, row, meta) {

									return '<a type="button" href="#" class="change"  data-toggle="modal" data-target="#myModal">修改</a>&nbsp;&nbsp;&nbsp;<a type="button" id="btn'
											+ meta.row
											+ '" onclick="del('
											+ row.id + ')" href="#">删除</a>'
								}

							} ],
							"rowCallback" : function(row, data, index) {

								$(row).bind('click', function() {

									$("#id").val(data.id)
									$("#title").val(data.title)
									$("#time").val(data.pushTime)
									$("#author").val(data.author)
									$("#editor").val(data.editor)
									editorContent.html(data.content)
									$("#parentId").find("option").eq(data.parentId).attr("selected", "selected")
							
								})

							},

							"lengthMenu" : [ [ 5, 10, 25, 50, -1 ],
									[ 5, 10, 25, 50, "全部" ] ]

						});
		
	}

}


function update() {
	
	var titleResult = false;
	var authorResult = false;
	var editorResult = false;
	var parentIdResult=false;
	var contentResult = false;
	
	titleResult = checkString('标题', "title", 100);
	authorResult = checkString('作者', "author", 20);
	editorResult = checkString('编辑人', "editor", 20);
	parentIdResult=checkString('分类',"parentId",3);
	contentResult = checkString('正文', "content", 2000);

	if (titleResult == false || authorResult == false || editorResult == false
			|| contentResult == false || parentIdResult == false) {

	} else {

		$.ajax({
			url : "updatePost",
			data : JSON.stringify({
				"id":$("#id").val(),
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
			success : function(result) {
				if (result.success) {
					alert(result.message);
					location.reload();
				}
			},
			error : function() {
				alert(result.message);
			}
		})
	}
}
function del(id) {
	$.ajax({
		url : "deletePost",
		data : JSON.stringify({
			"id" : id
		}),
		traditional : false,
		type : 'post',
		cache : false,
		async : false,
		dataType : 'json',
		contentType : 'application/json; charset=UTF-8',
		success : function(data) {
			if (data.success == true) {
				// 登录成功，页面跳转
				// window.location.href = loginAndRegister.URL.loginSuccess();
				alert(data.message);
				window.location.href = "manage";
			} else {
				alert(data.message);
			}
		},
		error : function() {
			alert("删除失败，请稍后重试！")
		}
	})
}
function checkNumber(name, object, limitLength) {


	if (/\D/.test(document.getElementById(object).value)) {
		alert(name + '只能输入数字!');
		document.getElementById(object).value = '';
		return false;
	} else if (document.getElementById(object).value.length == 0) {
		alert(name + '不能为空'  + '!');

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
		alert(name + '不能为空'  + '!');

		return false;
	} else {
		return true;
	}
}