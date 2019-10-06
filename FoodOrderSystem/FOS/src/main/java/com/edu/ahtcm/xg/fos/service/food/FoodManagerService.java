package com.edu.ahtcm.xg.fos.service.food;

import java.util.List;

import com.edu.ahtcm.xg.fos.model.FoodModel;

public interface FoodManagerService {

	public List<FoodModel> getAllFood();
	
	public void foodAdd(FoodModel foodModel);
	
	public void foodUpdate(FoodModel foodModel);
}
