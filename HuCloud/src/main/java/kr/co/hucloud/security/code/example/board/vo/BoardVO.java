package kr.co.hucloud.security.code.example.board.vo;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {

	private int id;
	private String subject;
	private String content;
	private String userName;
	private String userId;
	private String createdDate;
	private String modifiedDate;
	private int recommend;
	private int hit;
	private String fileName;
	private boolean isDelete;
	
	private MultipartFile file;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		if(content == null) {
			content = "";
		}
		
		return content;
	}
	public String getTextAreaContent() {
		if(content == null) {
			content = "";
		}
		
		return content.replaceAll("<br/>", "\n");
	}
	public void setContent(String content) {
		if(content == null) {
			content = "";
		}
		
		this.content = content.replace("\n", "<br/>").replace("\r", "");
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFileName() {
		return (fileName == null) ? "" : fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}
