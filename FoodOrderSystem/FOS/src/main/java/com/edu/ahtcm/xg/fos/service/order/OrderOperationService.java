package com.edu.ahtcm.xg.fos.service.order;

import java.util.List;

import com.edu.ahtcm.xg.fos.controller.order.OrderWrapperBean;
import com.edu.ahtcm.xg.fos.model.UserModel;

/**
 * 订单操作用service
 * @author DavidLei
 *
 */
public interface OrderOperationService {
	
	/**
	 * 创建订单
	 * @param orderWrapperBean
	 */
	public void createOrder(OrderWrapperBean orderWrapperBean);
	
	/**
	 * 查询个人订单
	 * @param userId
	 * @return
	 */
	public List<OrderWrapperBean> findOrder(String userId);
	
	public OrderWrapperBean findOrderByOrderId(String orderId);
	
	/**
	 * 订单项添加
	 * @param orderInfo
	 * @param foodId
	 * @return
	 */
	public OrderWrapperBean addItem(OrderWrapperBean orderInfo,String foodId);
	
	/**
	 * 订单项减去
	 * @param orderInfo
	 * @param foodId
	 * @return
	 */
	public OrderWrapperBean increaseItem(OrderWrapperBean orderInfo,String foodId);
	
	/**
	 * 订单明细信息的填写
	 * @param orderWrapperBean
	 * @param userModel
	 * @return
	 */
	public OrderWrapperBean getWrapperOrder(OrderWrapperBean orderWrapperBean,UserModel userModel);

}
