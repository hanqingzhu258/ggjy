package com.ggjy.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggjy.dao.PostDao;
import com.ggjy.dto.BackPage;
import com.ggjy.dto.PostPage;
import com.ggjy.dto.Result;
import com.ggjy.entity.Post;
import com.ggjy.entity.User;
import com.ggjy.service.PostService;
import com.ggjy.util.Page;

@Controller
public class PostController {
	@Resource
	private PostService postService;

	@ResponseBody
	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public Result<String> addPost(@RequestBody Post post,
			HttpSession session) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return new Result<String>(false, "您还没有登陆", null);
		}
		/* System.out.println(bringInfo.toString()); */

		if (post == null) {
			return new Result<String>(false, "参数有误", null);
		}
		// System.out.println(bringInfo.toString());
		// 发帖名称长度检验
		if (post.getTitle().length() > 100 || post.getTitle().length() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		// 发帖作者长度检验
		if (post.getAuthor().length() > 20 || post.getAuthor().length() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		// 发帖编辑人长度检验
		if (post.getEditor().length() > 200 || post.getEditor().length() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		// 发帖内容长度检验
		if (post.getContent().length() > 10000
				|| post.getContent().length() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		Boolean bool = false;

		try {
			bool = postService.addPost(post);
			// System.out.println(bool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new Result<String>(false, "未知错误!", null);
		}

		if (bool == false) {

			return new Result<String>(false, "添加失败", null);
		}

		return new Result<String>(true, "添加成功", null);
	}

	@ResponseBody
	@RequestMapping(value = "/getPostByParentId", method = RequestMethod.GET)
	public Result<List<Post>> getPostByParentId(HttpServletRequest request) {

		/*System.out.println("method: getPostByParentId is being used!!!");*/
		
		int parentId = Integer.parseInt(((ServletRequest) request)
				.getParameter("parentId"));

		/*User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return new Result<List<Post>>(false, "您还没有登陆", null);
		}*/

		List<Post> list = new ArrayList<Post>();
		try {
			list = postService.getPostByParentId(0, 100, parentId);
		} catch (Exception e) {
		}

		/*for(Post post:list){
			System.out.println("title:"+post.getTitle());
		}*/
		
		return new Result<List<Post>>(true, "success", list);
	}

	@ResponseBody
	@RequestMapping(value = "/postList", method = RequestMethod.POST)
	public Result<PostPage> postList(HttpRequest request) {
		int parentId = Integer.parseInt(((ServletRequest) request)
				.getParameter("parentId"));
		PostPage postPage = new PostPage();
		BackPage backPage = new BackPage();
		Page page = new Page();

		List<Post> list = new ArrayList<Post>();

		try {
			int count = postService.getCount();
			page.setTotalCount(count);
			backPage.setPageNow(page.getPageNow());
			backPage.setTotalPageCount(page.getTotalPageCount());
			list = postService.getPostByParentId(0, page.getPageSize(),
					parentId);
		} catch (Exception e) {
		}
		postPage.setBackPage(backPage);
		postPage.setList(list);

		return new Result<PostPage>(true, "success", postPage);
	}

	@ResponseBody
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public Result<String> deletePost(@RequestBody Post post, HttpSession session) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return new Result<String>(false, "您还没有登陆", null);
		}

		if (post.getId() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		Boolean bool = false;
		try {
			bool = postService.deletePost(post.getId());
		} catch (Exception e) {
		}
		if (bool == false) {
			return new Result<String>(false, "删除失败", null);
		}

		return new Result<String>(true, "删除成功", null);
	}

	@ResponseBody
	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public Result<String> updatePost(@RequestBody Post post, HttpSession session) {

		User user = (User) session.getAttribute("user");

		if (user == null) {
			return new Result<String>(false, "您还没有登陆", null);
		}
		/*System.out.println(post.toString());*/

		if (post == null) {
			return new Result<String>(false, "参数有误", null);
		}

		// 发帖名称长度检验
		if (post.getTitle().length() > 100 || post.getTitle().length() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		// 发帖作者长度检验
		if (post.getAuthor().length() > 20 || post.getAuthor().length() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		// 发帖编辑人长度检验
		if (post.getEditor().length() > 200 || post.getEditor().length() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		// 发帖内容长度检验
		if (post.getContent().length() > 2000
				|| post.getContent().length() == 0) {
			return new Result<String>(false, "参数有误", null);
		}

		Boolean bool = false;

		try {
			bool = postService.updatePost(post);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new Result<String>(false, "未知错误!", null);
		}
		;

		if (bool == false) {
			return new Result<String>(false, "更新失败", null);
		}

		return new Result<String>(true, "更新成功", null);
	}

	
	@ResponseBody
	@RequestMapping(value = "/getPostById", method = RequestMethod.GET)
	public Result<Post> getPostById(HttpServletRequest request) {

		int id=Integer.parseInt(request.getParameter("id"));

		Post findedPost = null;
		try {
			findedPost = postService.queryById(id);
		} catch (Exception e) {}
		
		return new Result<Post>(true, "success", findedPost);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getPostByPPId", method = RequestMethod.GET)
	public Result<List<Post>> getPostByPPId(HttpServletRequest request) {

		int ppId=Integer.parseInt(request.getParameter("ppId"));

		List<Post> posts=new ArrayList<Post>();
		try {
			posts = postService.getPostByPPId(0, 100, ppId);
		} catch (Exception e) {}
		
		return new Result<List<Post>>(true, "success", posts);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getPostByGroupParentId", method = RequestMethod.GET)
	public Result<List<ArrayList<Post>>> getPostByGroupParentId(HttpServletRequest request) {

		
//		System.out.println("the method is being used");
/*		int parentId1=Integer.parseInt(request.getParameter("parentId1"));
		int parentId2=Integer.parseInt(request.getParameter("parentId2"));
		int parentId3=Integer.parseInt(request.getParameter("parentId3"));
		int parentId4=Integer.parseInt(request.getParameter("parentId4"));
		int parentId5=Integer.parseInt(request.getParameter("parentId5"));*/

		List<ArrayList<Post>> posts=new ArrayList<ArrayList<Post>>();
		ArrayList<Post> posts1=new ArrayList<Post>();
		ArrayList<Post> posts2=new ArrayList<Post>();
		ArrayList<Post> posts3=new ArrayList<Post>();
		ArrayList<Post> posts4=new ArrayList<Post>();
		ArrayList<Post> posts5=new ArrayList<Post>();
		
/*		posts.add(posts1);
		posts.add(posts2);
		posts.add(posts3);
		posts.add(posts4);
		posts.add(posts5);*/
		
		try {
			
			posts1=(ArrayList<Post>) postService.getPostByParentId(0, 5, 18);
			posts2=(ArrayList<Post>) postService.getPostByParentId(0, 5, 10);
			posts3=(ArrayList<Post>) postService.getPostByParentId(0, 5, 17);
			posts4=(ArrayList<Post>) postService.getPostByParentId(0, 5, 12);
			posts5=(ArrayList<Post>) postService.getPostByParentId(0, 5, 28);
			posts.add(posts1);
			posts.add(posts2);
			posts.add(posts3);
			posts.add(posts4);
			posts.add(posts5);
			
		} catch (Exception e) {}
		
		return new Result<List<ArrayList<Post>>>(true, "success", posts);
	}
	

	@ResponseBody
	@RequestMapping(value = "/nextOrBeforePage", method = RequestMethod.POST)
	public Result<PostPage> nextOrBeforePage(@RequestBody BackPage backPage,
			HttpRequest request) {
		// System.out.println(backPage.toString());
		if (backPage.getPageNow() < 0) {
			return new Result<PostPage>(false, "false", null);
		}

		int parentId = Integer.parseInt(((ServletRequest) request)
				.getParameter("parentId"));

		Page page = new Page();

		List<Post> list = new ArrayList<Post>();
		try {
			int count = postService.getCountByParentId(parentId);
			page.setPageNow(backPage.getPageNow());
			page.setTotalCount(count);
			list = postService.getPostByParentId(page.getPageNow(),
					page.getPageSize(), parentId);
		} catch (Exception e) {
		}
		BackPage returnPage = new BackPage();
		returnPage.setPageNow(backPage.getPageNow() + 1);
		returnPage.setTotalPageCount(page.getTotalPageCount());

		PostPage postPage = new PostPage();
		postPage.setList(list);
		postPage.setBackPage(returnPage);
		// System.out.println(returnPage.toString());
		return new Result<PostPage>(true, "success", postPage);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getPost", method = RequestMethod.POST)
	public Result<List<Post>> getPost(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return new Result<List<Post>>(false, "您还没有登陆", null);
		}

		List<Post> list=new ArrayList<Post>();
		
		try{
		 list = postService.getPost(0, 100);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		/*for(Post post:list){
			System.out.println(post.getTime());
		}*/
		
		return new Result<List<Post>>(true, "success", list);
	}
}
