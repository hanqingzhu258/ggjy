<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title>硅谷精英页面</title>
    <link rel="stylesheet" type="text/css" href="./css/list/all.css" />
</head>
<body>
    <header>
		<img src="image/首页_03.jpg" />
		<a href="http://online.hfut.edu.cn/" target="_blank"><img src="image/首页_05.jpg" /></a>
    </header>
	<div class="title">
		<div>
			<a href="index1">首页</a>
		</div>
		<div>
			<a class="a_check" href="list1.html">硅谷资讯</a>
		</div>
		<div>
			<a href="list2.html">高新技术</a>
		</div>
		<div>
			<a href="list3.html">校园硅谷</a>
		</div>
		<div>
			<a href="list4.html">创业先锋</a>
		</div>
		<div>
			<a href="list5.html">管理之道</a>
		</div>
		<div>
			<a href="list6.html">创业平台</a>
		</div>
		<div>
			<a href="list7.html">创业资源</a>
		</div>
	</div>
	<div class="title_add">
		<a href="list1.html">所有</a>
		<a class="a_check" href="list1-1.html">硅谷要闻</a>
		<a href="list1-2.html">硅谷专区</a>
		<a></a>
		<a></a>
		<a></a>
		<a></a>
		<a></a>
	</div>
	<div id="page_main">
		<div>
			<span>当前位置：</span>
			<a href="index1">硅谷精英 >></a>
			<a href="list1.html">硅谷资讯 >></a>
			<a href="list1-1.html">硅谷要闻</a>
		</div>
		<div>
			<p class="cha_title">${post.title}</p>
			<p class="cha_inf">
				<span>${post.time}</span>
				<span>${post.author}</span>
				<span>${post.editor}</span>
			</p>
			<p>
				${post.content}
			</p>
		</div>
	</div>
	<footer>
		<p>Copyright <span>©</span> 2016 党委学生工作部 明理苑大学生网络文化工作室 All rights reserved.Powerd By 工大学子</p>
		<p>
			常用链接：
			<a href="http://www.univs.cn/" target="_blank">中国大学生在线</a>
			<a href="http://www.hfut.edu.cn/ch/" target="_blank">合肥工业大学</a>
			<a href="http://xgb.hfut.edu.cn/" target="_blank">合工大党委学工部</a>
		</p>
	</footer>
	<script type="text/javascript" src="./js/front/all.js"></script>
</body>
</html>

