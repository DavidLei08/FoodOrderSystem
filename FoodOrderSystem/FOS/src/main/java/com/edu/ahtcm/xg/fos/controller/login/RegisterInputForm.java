package com.edu.ahtcm.xg.fos.controller.login;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RegisterInputForm {
	private String userId;
	
	@NotEmpty
	private String userName;

	private String likeTaste;

	private String originPlace;

	private String dislikeFood;
	@NotEmpty
	private String password;
	@NotEmpty
	private String tel;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLikeTaste() {
		return likeTaste;
	}

	public void setLikeTaste(String likeTaste) {
		this.likeTaste = likeTaste;
	}

	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	public String getDislikeFood() {
		return dislikeFood;
	}

	public void setDislikeFood(String dislikeFood) {
		this.dislikeFood = dislikeFood;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
