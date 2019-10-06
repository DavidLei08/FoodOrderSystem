package com.edu.ahtcm.xg.fos.service.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.ahtcm.xg.fos.common.APIException;
import com.edu.ahtcm.xg.fos.controller.order.OrderWrapperBean;
import com.edu.ahtcm.xg.fos.model.FoodModel;
import com.edu.ahtcm.xg.fos.model.OrderItemModel;
import com.edu.ahtcm.xg.fos.model.OrderModel;
import com.edu.ahtcm.xg.fos.model.UserModel;
import com.edu.ahtcm.xg.fos.repository.FoodRespository;
import com.edu.ahtcm.xg.fos.repository.OrderItemRepository;
import com.edu.ahtcm.xg.fos.repository.OrderRespository;
import com.edu.ahtcm.xg.fos.utils.MathUtil;
import com.edu.ahtcm.xg.fos.utils.RandIDUtil;

/**
 * 订单操作用serviceImpl
 * @author DavidLei
 *
 */
@Service
public class OrderOperationServiceImpl implements OrderOperationService {

	@Autowired
	private OrderRespository orderRespository;

	@Autowired
	private FoodRespository foodRespository;

	@Autowired
	private OrderItemRepository orderItemRepository;


	@Transactional
	@Override
	public void createOrder(OrderWrapperBean orderWrapperBean) {
		//向上转型
		OrderModel orderModel = orderWrapperBean;
		try {
			//DB插入订单数据
			int total = orderRespository.insertOrder(orderModel);
			//判断影响条数
			if (total != 1) {
				//抛出异常
				throw new APIException(400, "order created is failed");
			}
		} catch (Exception e) {
			//添加失败，抛出异常
			throw new APIException(400, "order created has error");
		}
		//订单项集合取得
		List<OrderItemModel> itemModels = orderWrapperBean.getOrderItems();

		try {
			int total = 0;
			//迭代订单项集合
			for (OrderItemModel itemModel : itemModels) {
				//DB订单项数据添加
				total += orderItemRepository.inserItem(itemModel);

			}
			//判断影响条数
			if (total != itemModels.size()) {
				//抛出异常
				throw new APIException(400, "orderItem created is failed");
			}
		} catch (Exception e) {
			//添加失败抛出异常
			throw new APIException(400, "orderItem created has error");
		}

	}

	@Override
	public List<OrderWrapperBean> findOrder(String userId) {
		//个人订单集合取得
		List<OrderModel> orderModels = orderRespository.findByUserId(userId);
		//订单信息集合初始化
		List<OrderWrapperBean> orderWrapperBeans = new ArrayList<OrderWrapperBean>();
		//迭代订单集合
		for (OrderModel orderModel : orderModels) {
			//订单详细信息初始化
			OrderWrapperBean orderWrapperBean = new OrderWrapperBean();
			//订单项集合初始化
			List<OrderItemModel> itemModels = new ArrayList<OrderItemModel>();
			//强转订单详细信息对象
			orderWrapperBean.setOrderId(orderModel.getOrderId());
			orderWrapperBean.setFoodCount(orderModel.getFoodCount());
			orderWrapperBean.setOrderAmount(orderModel.getOrderAmount());
			orderWrapperBean.setOrderPerson(orderModel.getOrderPerson());
			orderWrapperBean.setOrderTime(orderModel.getOrderTime());
			orderWrapperBean.setRestaurant(orderModel.getRestaurant());
			orderWrapperBean.setTel(orderModel.getTel());
			//订单项集合取得
			itemModels = orderItemRepository.findItemByOrderId(orderModel.getOrderId());
			//订单项集合设置
			orderWrapperBean.setOrderItems(itemModels);
			//添加到订单详细信息集合中
			orderWrapperBeans.add(orderWrapperBean);

		}
		return orderWrapperBeans;
	}

	@Override
	public OrderWrapperBean addItem(OrderWrapperBean orderInfo, String foodId) {
		//
		FoodModel foodModel = foodRespository.findById(foodId);
		
		List<OrderItemModel> itemModels = new ArrayList<OrderItemModel>();
		out: if (orderInfo != null) {
			itemModels = orderInfo.getOrderItems();

			for (OrderItemModel itemModel : itemModels) {
				if (itemModel.getFoodId().equals(foodId)) {
					itemModels.remove(itemModel);
					itemModel.setFoodCount(itemModel.getFoodCount() + 1);
					itemModels.add(itemModel);
					orderInfo.setOrderItems(itemModels);
					orderInfo.setFoodCount(MathUtil.getOrderCount(itemModels));
					orderInfo.setOrderAmount(MathUtil.getOrderAmount(itemModels));
					break out;
				}
			}
			OrderItemModel orderItemModel = new OrderItemModel();
			orderItemModel.setFoodCount(1);
			orderItemModel.setFoodId(foodId);
			orderItemModel.setFoodName(foodModel.getFoodName());
			orderItemModel.setFoodPrice(foodModel.getFoodPrice());
			itemModels.add(orderItemModel);
			orderInfo.setOrderItems(itemModels);
			orderInfo.setFoodCount(MathUtil.getOrderCount(itemModels));
			orderInfo.setOrderAmount(MathUtil.getOrderAmount(itemModels));

		} else {
			orderInfo = new OrderWrapperBean();
			orderInfo.setFoodCount(1);
			orderInfo.setOrderAmount(foodModel.getFoodPrice());
			OrderItemModel orderItemModel = new OrderItemModel();
			orderItemModel.setFoodCount(1);
			orderItemModel.setFoodId(foodId);
			orderItemModel.setFoodName(foodModel.getFoodName());
			orderItemModel.setFoodPrice(foodModel.getFoodPrice());
			itemModels.add(orderItemModel);
			orderInfo.setOrderItems(itemModels);
		}
		return orderInfo;
	}

	@Override
	public OrderWrapperBean increaseItem(OrderWrapperBean orderInfo, String foodId) {
		List<OrderItemModel> itemModels = new ArrayList<OrderItemModel>();
		itemModels=orderInfo.getOrderItems();
		out:for(OrderItemModel itemModel:itemModels){
			if(itemModel.getFoodId().equals(foodId)){
				if(itemModel.getFoodCount()>1){
					itemModels.remove(itemModel);
					itemModel.setFoodCount(itemModel.getFoodCount()-1);
					itemModels.add(itemModel);
				}else if(itemModel.getFoodCount()==1){
					itemModels.remove(itemModel);
				}
				break out;
			}
		}
		orderInfo.setOrderItems(itemModels);
		orderInfo.setFoodCount(MathUtil.getOrderCount(itemModels));
		orderInfo.setOrderAmount(MathUtil.getOrderAmount(itemModels));
		return orderInfo;
	}

	@Override
	public OrderWrapperBean getWrapperOrder(OrderWrapperBean orderWrapperBean, UserModel userModel) {
		orderWrapperBean.setOrderId(RandIDUtil.getTenId());
		orderWrapperBean.setOrderPerson(userModel.getUserId());
		orderWrapperBean.setOrderTime(LocalDateTime.now());
		orderWrapperBean.setRestaurant("fos测试用饭餐馆");
		orderWrapperBean.setTel(userModel.getTel());
		List<OrderItemModel> itemModels=orderWrapperBean.getOrderItems();
		for(OrderItemModel itemModel:itemModels){
			itemModel.setItemId(RandIDUtil.getTenId());
			itemModel.setOrderId(orderWrapperBean.getOrderId());
		}
		return orderWrapperBean;
	}

	@Override
	public OrderWrapperBean findOrderByOrderId(String orderId) {
		OrderModel orderModel=orderRespository.findById(orderId);
		OrderWrapperBean orderWrapperBean = new OrderWrapperBean();
		//订单项集合初始化
		List<OrderItemModel> itemModels = new ArrayList<OrderItemModel>();
		//强转订单详细信息对象
		orderWrapperBean.setOrderId(orderModel.getOrderId());
		orderWrapperBean.setFoodCount(orderModel.getFoodCount());
		orderWrapperBean.setOrderAmount(orderModel.getOrderAmount());
		orderWrapperBean.setOrderPerson(orderModel.getOrderPerson());
		orderWrapperBean.setOrderTime(orderModel.getOrderTime());
		orderWrapperBean.setRestaurant(orderModel.getRestaurant());
		orderWrapperBean.setTel(orderModel.getTel());
		//订单项集合取得
		itemModels = orderItemRepository.findItemByOrderId(orderModel.getOrderId());
		//订单项集合设置
		orderWrapperBean.setOrderItems(itemModels);
		return orderWrapperBean;
	}



}
