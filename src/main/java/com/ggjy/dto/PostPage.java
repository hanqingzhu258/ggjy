package com.ggjy.dto;

import java.util.List;

import com.ggjy.entity.Post;

public class PostPage {
	private List<Post> list;
	private BackPage backPage;
	private int parentId;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<Post> getList() {
		return list;
	}

	public void setList(List<Post> list) {
		this.list = list;
	}

	public BackPage getBackPage() {
		return backPage;
	}

	public void setBackPage(BackPage backPage) {
		this.backPage = backPage;
	}

	@Override
	public String toString() {
		return "PostPage [list=" + list + ", backPage=" + backPage
				+ ", parentId=" + parentId + "]";
	}
	
	
    
}
