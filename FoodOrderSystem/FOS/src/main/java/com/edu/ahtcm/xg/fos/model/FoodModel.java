package com.edu.ahtcm.xg.fos.model;


public class FoodModel {

	/**
	 * 菜品id
	 */
	private String foodId;
	
	/**
	 * 菜品名称
	 */
	private String foodName;
	
	/**
	 * 菜品产地
	 */
	private String originPlace;

	/**
	 * 菜品口味
	 */
	private String foodTaste;
	
	/**
	 * 菜品描述
	 */
	private String description;
	
	/**
	 * 菜品单价
	 */
	private float foodPrice;
	
	/**
	 *图片位置
	 */
	private String foodImgPath;
	
	/*
	 * 菜品数量，初始为0
	 */
	private  Integer count=0;
	

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	public String getFoodTaste() {
		return foodTaste;
	}

	public void setFoodTaste(String foodTaste) {
		this.foodTaste = foodTaste;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getFoodImgPath() {
		return foodImgPath;
	}

	public void setFoodImgPath(String foodImgPath) {
		this.foodImgPath = foodImgPath;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
