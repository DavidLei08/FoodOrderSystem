package com.edu.ahtcm.xg.fos.controller.food;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.ahtcm.xg.fos.authorization.annotation.Authorizated;
import com.edu.ahtcm.xg.fos.controller.order.OrderWrapperBean;
import com.edu.ahtcm.xg.fos.model.FoodModel;
import com.edu.ahtcm.xg.fos.model.OrderItemModel;
import com.edu.ahtcm.xg.fos.model.UserModel;
import com.edu.ahtcm.xg.fos.service.food.FoodMenuService;
import com.edu.ahtcm.xg.fos.utils.local.MenuSetUtil;

/**
 * 点餐用菜单Controller
 * 
 * @author DavidLei
 *
 */
@RequestMapping("/food")
@Controller
public class FoodMenuController {

	@Autowired
	private FoodMenuService foodMenudService;

	@Authorizated
	@ResponseBody
	@GetMapping("/getAll")
	public List<FoodModel> getAllFood(HttpSession session) {

		List<FoodModel> foodModels = foodMenudService.getAll();

		OrderWrapperBean orderWrapperBean = (OrderWrapperBean) session.getAttribute("orderInfo");

		if (orderWrapperBean != null && orderWrapperBean.getOrderItems().size() > 0) {
			List<OrderItemModel> itemModels = orderWrapperBean.getOrderItems();
			foodModels = MenuSetUtil.getSetMenu(foodModels, itemModels);
		}
		
		// return foodMenudService.getAll();
		// FoodModel foodModel=new FoodModel();
		// foodModel.setFoodId("mpdf");
		// foodModel.setCount(0);
		// foodModel.setFoodName("麻婆豆腐");
		// foodModel.setFoodPrice(28);
		// List<FoodModel> list=new ArrayList<FoodModel>();
		// list.add(foodModel);
		return foodModels;
	}

	@Authorizated
	@ResponseBody
	@GetMapping("/getRecommend")
	public List<FoodModel> getRecommendFood(HttpSession session) {

		List<FoodModel> foodModels = foodMenudService.getRecommend((UserModel) session.getAttribute("userInfo"));
		OrderWrapperBean orderWrapperBean;
		orderWrapperBean = (OrderWrapperBean) session.getAttribute("orderInfo");

		if (orderWrapperBean != null && orderWrapperBean.getOrderItems().size() > 0) {
			List<OrderItemModel> itemModels = orderWrapperBean.getOrderItems();
			foodModels = MenuSetUtil.getSetMenu(foodModels, itemModels);
		}
		return foodModels;
	}

}
