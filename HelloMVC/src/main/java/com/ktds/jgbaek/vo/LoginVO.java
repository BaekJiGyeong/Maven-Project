package com.ktds.jgbaek.vo;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class LoginVO {
	
	@NotEmpty(message="ID는 필수 입력값 입니다.")	
	private String id;
	@NotEmpty(message="PASSWORD는 필수 입력값 입니다.")
	private String password;
	@Min(value=10, message="숫자는 10이하를 적을 수 없습니다.")
	@Max(value=50, message="숫자는 50이상을 적을 수 없습니다.")
	private String memberNumber;
	private boolean enableAutoLogin;
	private List<String> hobby;
	
	private MultipartFile uploadFile;
	
	public List<String> getHobby() {
		return hobby;
	}
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public boolean isEnableAutoLogin() {
		return enableAutoLogin;
	}
	public void setEnableAutoLogin(boolean enableAutoLogin) {
		this.enableAutoLogin = enableAutoLogin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

}
