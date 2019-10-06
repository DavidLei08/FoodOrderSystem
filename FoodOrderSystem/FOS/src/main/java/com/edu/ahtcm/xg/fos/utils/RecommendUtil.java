package com.edu.ahtcm.xg.fos.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edu.ahtcm.xg.fos.model.FoodModel;
import com.edu.ahtcm.xg.fos.model.FoodWeightBean;
import com.edu.ahtcm.xg.fos.model.UserModel;

/**
 * 推荐菜品算法类
 * 
 * @author DavidLei
 *
 */
public class RecommendUtil {

	/**
	 * 获取排序后的推荐菜品集合
	 * 
	 * @param userModel
	 *            用户对象
	 * @param foodModels
	 *            菜品集合
	 * @return 推荐菜品集合
	 */
	public static List<FoodModel> getSortFoods(UserModel userModel, List<FoodModel> foodModels) {

		// 初始工具类
		RecommendUtil recommendUtil = new RecommendUtil();
		// 菜品权重集合获取
		List<FoodWeightBean> foodWeightBeans = recommendUtil.getFoodWeightBeans(userModel, foodModels);
		// 菜品排序集合初始化
		List<FoodModel> sortFoodModels = new ArrayList<FoodModel>();
		// 菜品权重集合排序
		Collections.sort(foodWeightBeans);
		// 截取排序的前十个菜品
		for (int i = 0; i < 10; i++) {
			sortFoodModels.add(foodWeightBeans.get(i));
		}
		return sortFoodModels;
	}

	/**
	 * 获取带有权重的菜品集合
	 * 
	 * @param userModel
	 *            用户对象
	 * @param foodModels
	 *            菜品集合
	 * @return 菜品权重集合
	 */
	public List<FoodWeightBean> getFoodWeightBeans(UserModel userModel, List<FoodModel> foodModels) {
		// 菜品权重集合初始化
		List<FoodWeightBean> foodWeightBeans = new ArrayList<FoodWeightBean>();
		// 迭代菜品集合
		for (FoodModel foodModel : foodModels) {
			// 菜品权重对象初始化
			FoodWeightBean foodWeightBean = new FoodWeightBean();
			// 菜品对象强转菜品权重对象
			foodWeightBean.setFoodId(foodModel.getFoodId());
			foodWeightBean.setFoodName(foodModel.getFoodName());
			foodWeightBean.setDescription(foodModel.getDescription());
			foodWeightBean.setCount(foodModel.getCount());
			foodWeightBean.setFoodImgPath(foodModel.getFoodImgPath());
			foodWeightBean.setFoodPrice(foodModel.getFoodPrice());
			foodWeightBean.setFoodTaste(foodModel.getFoodTaste());
			foodWeightBean.setOriginPlace(foodModel.getOriginPlace());
			// 设置菜品权重对象的权重
			foodWeightBean.setFoodWeight(weightAnalyze(userModel, foodModel));
			// 菜品权重对象添加到集合
			foodWeightBeans.add(foodWeightBean);
		}
		return foodWeightBeans;
	}

	/**
	 * 菜品的权重分析
	 * 
	 * @param userModel
	 *            用户对象
	 * @param foodModels
	 *            菜品集合
	 * @return 菜品权重
	 */
	Integer weightAnalyze(UserModel userModel, FoodModel foodModel) {
		// 初始权重为0
		Integer weight = 0;
		if (isContains(userModel.getLikeTaste(), foodModel.getFoodTaste())) {
			// 口味权重为3
			weight += 3;

		}
		if (isContains(userModel.getOriginPlace(), foodModel.getOriginPlace())) {
			// 地域权重为3
			weight += 3;
		}

		if (isContains(userModel.getDislikeFood(), foodModel.getFoodName())) {
			// 不喜欢食物权重为-5
			weight += -5;
		}

		return weight;
	}

	/**
	 * 字符匹配判断
	 * 
	 * @param str1
	 *            字符串1
	 * @param str2
	 *            字符串2
	 * @return 匹配结果
	 */
	boolean isContains(String str1, String str2) {

		if (str1.contains(str2) || str2.contains(str1)) {
			return true;
		} else {
			for (int i = 1; i < str1.length(); i++) {
				if (str2.contains(str1.substring(i - 1, i))) {
					return true;
				}
			}
			for (int i = 1; i < str2.length(); i++) {
				if (str1.contains(str2.substring(i - 1, i))) {
					return true;
				}
			}
			return false;
		}
	}
}
