package com.edu.ahtcm.xg.fos.utils;

import java.util.List;

import com.edu.ahtcm.xg.fos.model.FoodModel;
import com.edu.ahtcm.xg.fos.model.OrderItemModel;

public class MenuSetUtil {

	public static List<FoodModel> getSetMenu(List<FoodModel> foodModels,List<OrderItemModel> itemModels) {
		for(OrderItemModel item:itemModels){
			for(FoodModel food:foodModels){
				if(food.getFoodId().equals(item.getFoodId())){
					food.setCount(item.getFoodCount());
					break;
				}
			}
		}
		return foodModels;
	}
}
