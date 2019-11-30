package com.edu.ahtcm.xg.fos.utils.math;

import java.util.List;

import com.edu.ahtcm.xg.fos.model.OrderItemModel;

public class MathUtil {

	
	public static float getOrderAmount(List<OrderItemModel> itemModels) {
		float amount=0;
		for(OrderItemModel itemModel:itemModels){
			amount+=itemModel.getFoodPrice()*itemModel.getFoodCount();
		}
		return amount;
		
	}
	
	public static int getOrderCount(List<OrderItemModel> itemModels){
		int count=0;
		for(OrderItemModel itemModel:itemModels){
			count+=itemModel.getFoodCount();
		}
		return count;
	}
}
