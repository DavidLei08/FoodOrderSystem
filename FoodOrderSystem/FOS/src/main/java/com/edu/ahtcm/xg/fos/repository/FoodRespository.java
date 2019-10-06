package com.edu.ahtcm.xg.fos.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.edu.ahtcm.xg.fos.model.FoodModel;

@Mapper
public interface FoodRespository {
	
	@Insert(""
			+ "")
	public int insertFood(FoodModel foodModel);
	
	@Delete("")
	public int delFood(String foodId);
	
	@Update("")
	public int updateFood(FoodModel foodModel);
	
	@Select("SELECT food_id foodId,"
			+ "food_name foodName,"
			+ "origin_place originPlace,"
			+ "food_taste foodTaste,"
			+ "description description,"
			+ "food_price foodPrice,"
			+ "food_img_path foodImgPath"
			+ " FROM tb_food ")
	public List<FoodModel> findAllFoood();
	
	@Select("SELECT food_id foodId,"
			+ "food_name foodName,"
			+ "origin_place originPlace,"
			+ "food_taste foodTaste,"
			+ "description description,"
			+ "food_price foodPrice,"
			+ "food_img_path foodImgPath"
			+ " FROM tb_food "
			+ " WHERE food_Id=#{foodId}")
	public FoodModel findById(@Param("foodId")String foodId);

}
