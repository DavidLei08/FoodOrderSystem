package com.edu.ahtcm.xg.fos.controller.order;

import java.util.List;

import com.edu.ahtcm.xg.fos.model.OrderItemModel;
import com.edu.ahtcm.xg.fos.model.OrderModel;

public class OrderWrapperBean extends OrderModel{
	
	private List<OrderItemModel> orderItems;

	public List<OrderItemModel> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemModel> orderItems) {
		this.orderItems = orderItems;
	}

}
