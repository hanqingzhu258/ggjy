package com.ggjy.service;

import java.util.List;

import com.ggjy.entity.Post;

public interface PostService {

	//按id查
	public Post queryById(int id) throws Exception;

	//添加
	public Boolean addPost(Post post) throws Exception;

	//更新
	public Boolean updatePost(Post post) throws Exception;
	
	//删除
	public Boolean deletePost(int id) throws Exception;
	
	//按分类分页查询
	public List<Post> getPostByParentId(int startPos, int pageSize, int parentId)
			throws Exception;
	
	//按分类分页查询
		public List<Post> getPostByPPId(int startPos, int pageSize, int ppId)
				throws Exception;

	//按分类获取总数
	public int getCountByParentId(int parentId) throws Exception;

	//获取总数
	public int getCount() throws Exception;

	//分页查询
	public List<Post> getPost(int startPos, int pageSize) throws Exception;

}
