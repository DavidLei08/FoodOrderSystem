package com.edu.ahtcm.xg.fos.model;


public class FoodWeightBean extends FoodModel implements Comparable<FoodWeightBean>{

	private Integer foodWeight;
	
	@Override
	public int compareTo(FoodWeightBean foodWeightBean) {
		
		return this.foodWeight<foodWeightBean.foodWeight?1:-1;
	}

	public Integer getFoodWeight() {
		return foodWeight;
	}

	public void setFoodWeight(Integer foodWeight) {
		this.foodWeight = foodWeight;
	}
	

}
