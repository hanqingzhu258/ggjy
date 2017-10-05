package com.ggjy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ggjy.dao.PostDao;
import com.ggjy.entity.Post;
import com.ggjy.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Resource
	private PostDao postDao;
	
	
	@Override
	public Post queryById(int id) throws Exception {
		// TODO Auto-generated method stub
		return postDao.queryById(id);
	}

	@Override
	public Boolean addPost(Post post) throws Exception {
		// TODO Auto-generated method stub
		int count=postDao.add(post);
		
		if(count!=1){
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean updatePost(Post post) throws Exception {
		// TODO Auto-generated method stub
		
		int count=postDao.update(post);
		if(count!=1){
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean deletePost(int id) throws Exception {
		// TODO Auto-generated method stub
		
		int count=postDao.deleteById(id);
		if(count!=1){
			return false;
		}

		return true;
	}

	@Override
	public List<Post> getPostByParentId(int startPos, int pageSize, int parentId)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<Post> posts=new ArrayList<Post>();
		posts=postDao.getPostByParentId(startPos, pageSize, parentId);
		if(posts==null){
			throw new RuntimeException("查询失败");
		}
		return posts;
	}

	@Override
	public int getCountByParentId(int parentId) throws Exception {
		// TODO Auto-generated method stub
		
		int count=postDao.getCountByParentId(parentId);
		
		return count;
	}

	@Override
	public int getCount() throws Exception {
		// TODO Auto-generated method stub
		return postDao.getCount();
	}

	@Override
	public List<Post> getPost(int startPos, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		
		List<Post> posts=new ArrayList<Post>();
		posts=postDao.getPost(startPos, pageSize);
		if(posts==null){
			throw new RuntimeException("查询失败");
		}
		
		
		
		return posts;
	}

}
