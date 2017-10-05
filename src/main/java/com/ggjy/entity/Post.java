package com.ggjy.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {

	private int id;
	private String title;
	//private Date time;
	private Timestamp time;
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Timestamp getTime(){
		return time;
	}
	
	private String pushTime;

	public String getPushTime() {
		
		pushTime=time.toString();
		
		return pushTime;
	}
	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	private String author;
	private String editor;
	private String content;
	private int parentId;

	public String getClassification() {

		switch (parentId) {

		case 1:
			classification = "硅谷要闻";
			break;
		case 2:
			classification = "硅谷专区";
			break;
		case 3:
			classification = "IT";
			break;
		case 4:
			classification = "通信";
			break;
		case 5:
			classification = "汽车";
			break;
		case 6:
			classification = "家电";
			break;
		case 7:
			classification = "生物医药";
			break;
		case 8:
			classification = "新材料";
			break;
		case 9:
			classification = "新能源";
			break;
		case 10:
			classification = "科技创新";
			break;
		case 11:
			classification = "学子竞技";
			break;
		case 12:
			classification = "高校平台展示";
			break;
		case 13:
			classification = "创业团队";
			break;
		case 14:
			classification = "创业之星";
			break;
		case 15:
			classification = "业界精英";
			break;
		case 16:
			classification = "创业新秀";
			break;
		case 17:
			classification = "创业心得";
			break;
		case 18:
			classification = "管理理论";
			break;
		case 19:
			classification = "企业战略";
			break;
		case 20:
			classification = "创业管理";
			break;
		case 21:
			classification = "创业文化";
			break;
		case 22:
			classification = "书籍推荐";
			break;
		case 23:
			classification = "人才推介";
			break;
		case 24:
			classification = "创业融资";
			break;
		case 25:
			classification = "技术推广";
			break;
		case 26:
			classification = "高新园区";
			break;
		case 27:
			classification = "孵化器";
			break;
		case 28:
			classification = "特色产业基地";
			break;
		case 29:
			classification = "创业基金";
			break;
		case 30:
			classification = "创业政策";
			break;
		}

		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	private String classification;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}*/

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", time=" + time
				+ ", author=" + author + ", editor=" + editor + ", content="
				+ content + ", parentId=" + parentId + ", classification="
				+ classification + "]";
	}

}
