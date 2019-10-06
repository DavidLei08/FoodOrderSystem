package com.edu.ahtcm.xg.fos.controller.order;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.ahtcm.xg.fos.authorization.annotation.Authorizated;
import com.edu.ahtcm.xg.fos.model.UserModel;
import com.edu.ahtcm.xg.fos.service.order.OrderOperationService;

/**
 * 点单操作用Controller
 * 
 * @author DavidLei
 *
 */
@RequestMapping("/order")
@Controller
public class OrderOperationController {

	@Autowired
	private OrderOperationService orderOperationService;

	/**
	 * 创建订单
	 * 
	 * @param session
	 * @return
	 */
	@Authorizated
	@GetMapping("/createOrder")
	public String createOrder(HttpSession session) {
		// 从sessioon中取出订单信息
		OrderWrapperBean orderInfo = (OrderWrapperBean) session.getAttribute("orderInfo");
		// 判空
		if (orderInfo != null) {
			// 执行创建订单
			orderOperationService.createOrder(orderInfo);
			session.removeAttribute("orderInfo");

		}
		// 从定向到订单页面
		return "redirect:/order/showOrder";
	}

	/**
	 * 获取当前绘画中的订单信息
	 * 
	 * @param session
	 * @return
	 */
	@Authorizated
	@ResponseBody
	@GetMapping("/getNowOrder")
	public OrderWrapperBean getNowOrder(HttpSession session) {
		// 订单信息取出
		OrderWrapperBean orderInfo = (OrderWrapperBean) session.getAttribute("orderInfo");
		return orderInfo;

	}

	/**
	 * 向订单中添加一例菜品
	 * 
	 * @param foodId
	 * @param session
	 * @return
	 */
	@Authorizated
	@ResponseBody
	@PostMapping("/addOrderItem/{foodId}")
	public OrderWrapperBean addOrderItem(@PathVariable("foodId") String foodId, HttpSession session) {
		// 订单详细信息初始化
		OrderWrapperBean orderInfo = new OrderWrapperBean();
		// 订单信息取得
		orderInfo = (OrderWrapperBean) session.getAttribute("orderInfo");
		// 订单信息运算
		orderInfo = orderOperationService.addItem(orderInfo, foodId);
		// 将更新后的订单信息存入session
		session.setAttribute("orderInfo", orderInfo);
		return orderInfo;

	}

	/**
	 * 当前会话中的订单减去一例菜品
	 * 
	 * @param foodId
	 * @param session
	 * @return
	 */
	@Authorizated
	@ResponseBody
	@PostMapping("/increaseFoodItem/{foodId}")
	public OrderWrapperBean increaseOrderItem(@PathVariable("foodId") String foodId, HttpSession session) {
		// 订单详细初始化
		OrderWrapperBean orderInfo = new OrderWrapperBean();
		// 订单信息取得
		orderInfo = (OrderWrapperBean) session.getAttribute("orderInfo");
		// 信息判空
		if (orderInfo != null && orderInfo.getOrderItems().size() > 0) {
			// 运算订单信息
			orderInfo = orderOperationService.increaseItem(orderInfo, foodId);
		}
		// 更新session的订单信息
		session.setAttribute("orderInfo", orderInfo);
		return orderInfo;

	}

	/**
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@Authorizated
	@GetMapping("/confrimOrderInfo")
	public String confirmOrderInfo(HttpSession session, Model model) {
		// 订单信息取得
		OrderWrapperBean orderInfo = (OrderWrapperBean) session.getAttribute("orderInfo");
		// 用户信息取得
		UserModel userModel = (UserModel) session.getAttribute("userInfo");
		// 信息判空
		if (orderInfo != null && orderInfo.getOrderItems().size() > 0) {
			// 订单详细信息填写
			orderInfo = orderOperationService.getWrapperOrder(orderInfo, userModel);
			// 存入session
			session.setAttribute("orderInfo", orderInfo);
			// 跳转确认页
			return "orderConfirm";
		} else {
			return null;
		}
	}

	/**
	 * 订单列表页
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@Authorizated
	@GetMapping("/showOrder")
	public String showOrder() {
		return "orderList";
	}
	
	@Authorizated
	@GetMapping("/showInfo/{orderId}")
    public String showInfo(@PathVariable("orderId")String orderId,Model model){
		model.addAttribute("orderDetailInfo", orderOperationService.findOrderByOrderId(orderId));
    	return "orderInfo";
    }
	
	@Authorizated
	@ResponseBody
	@GetMapping("/getOrderlist")
	public List<OrderWrapperBean> getOrderList(HttpSession session){
		// 用户信息取得
		UserModel userModel = (UserModel) session.getAttribute("userInfo");
		return  orderOperationService.findOrder(userModel.getUserId());
	}

}
