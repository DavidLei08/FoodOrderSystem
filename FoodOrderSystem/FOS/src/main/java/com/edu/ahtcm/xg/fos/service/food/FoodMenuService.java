package com.edu.ahtcm.xg.fos.service.food;

import java.io.IOException;
import java.util.List;

import com.edu.ahtcm.xg.fos.model.FoodModel;
import com.edu.ahtcm.xg.fos.model.UserModel;

public interface FoodMenuService {
	
	public  List<FoodModel> getAll();
	
	public List<FoodModel> getRecommend(UserModel userModel);
	
	public byte[] getFoodImg(String foodId) throws IOException;

}
