package com.ggjy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ggjy.dao.dynamicSQLProvider.DynamicSQLProvider;
import com.ggjy.entity.Post;

@Mapper
public interface PostDao {

	/**
	 * 
	 * @author zhuhanqing
	 * @param id
	 * @return
	 * @since JDK 1.7
	 * @condition 按照帖子id查询
	 */
	@Select("select * from post where id=#{id}")
	@Results({ @Result(property = "id", column = "id"),
			@Result(property = "title", column = "title"),
			@Result(property = "time", column = "time"),
			@Result(property = "author", column = "author"),
			@Result(property = "editor", column = "editor"),
			@Result(property = "content", column = "content"),
			@Result(property = "parentId", column = "parentId") })
	Post queryById(@Param("id") int id);

	/**
	 * 
	 * @author zhuhanqing
	 * @param post
	 * @return
	 * @since JDK 1.7
	 * @condition 插入一条记录
	 */

	@Insert("insert ignore into post(title,time,author,editor,content,parentId) values(#{post.title},NOW(), #{post.author},#{post.editor}, #{post.content}, #{post.parentId})")
	Integer add(@Param("post") Post post);

	/**
	 * 
	 * @author zhuhanqing
	 * @param id
	 * @return
	 * @since JDK 1.7
	 * @condition 按照帖子id删除该帖子
	 */
	@Delete("delete from post where id=#{id}")
	Integer deleteById(@Param("id") Integer id);

	/**
	 * @author zhuhanqing
	 * @param post
	 * @return
	 * @since JDK 1.7
	 * @condition 更新帖子
	 */
	@UpdateProvider(type = DynamicSQLProvider.class, method = "update")
	Integer update(@Param("post") Post post);

	/**
	 * @author zhuhanqing
	 * @param post
	 * @return
	 * @since JDK 1.7
	 * @condition 分页功能
	 */
	@Select("select * from post order by id desc limit #{startPos},#{pageSize}")
	List<Post> getPost(@Param("startPos") int startPos,
			@Param("pageSize") int pageSize);

	/**
	 * @author zhuhanqing
	 * @param post
	 * @return
	 * @since JDK 1.7
	 * @condition 获取帖子总数
	 */
	@Select("select count(*) from post")
	int getCount();

	/**
	 * @author zhuhanqing
	 * @param post
	 * @return
	 * @since JDK 1.7
	 * @condition 按照帖子类型分页功能
	 */
	@Select("select * from post where parentId=#{parentId} order by id desc limit #{startPos},#{pageSize}")
	List<Post> getPostByParentId(@Param("startPos") int startPos,
			@Param("pageSize") int pageSize, @Param("parentId") int parentId);

	/**
	 * @author zhuhanqing
	 * @param post
	 * @return
	 * @since JDK 1.7
	 * @condition 根据类型获取帖子总数
	 */
	@Select("select count(*) from post where parentId=#{parentIds}")
	int getCountByParentId(@Param("parentId") int parentId);

	/**
	 * @author zhuhanqing
	 * @param post
	 * @return
	 * @since JDK 1.7
	 * @condition 根据类型获取帖子总数
	 */
	@Select("select * from post where parentId in ( select id from submenu where parentId=#{ppId} ) order by id desc limit #{startPos},#{pageSize}")
	List<Post> getPostByPPId(@Param("startPos") int startPos,
			@Param("pageSize") int pageSize, @Param("ppId") int ppId);
}
