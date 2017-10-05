package com.ggjy.dao.dynamicSQLProvider;

import org.apache.ibatis.jdbc.SQL;

import com.ggjy.entity.Post;
/**
 * 提供动态SQL
 * 
 * @author zhuhanqing
 * @version  
 * @since JDK 1.7
 */
public class DynamicSQLProvider {
	public String update(final Post post){
        return new SQL() {
        	{  
                UPDATE("post");
                if (post.getTitle()!= null)  
                {  
                    SET("title = #{post.title}");  
                }  
                if (post.getTime() != null)  
                {  
                    SET("time = #{post.time}");  
                }  
                if (post.getAuthor() != null)  
                {  
                    SET("author = #{post.author}");  
                }  
                if (post.getEditor() != null)  
                {  
                    SET("editor = #{post.editor}");  
                }  
                if (post.getContent() != null)  
                {  
                    SET("content = #{post.content}");  
                }  
                if (post.getParentId() !=0)  
                {  
                    SET("parentId = #{post.parentId}");  
                }  
                
                WHERE("id = #{post.id}");  
            }  
        }.toString();
                
    }
}
