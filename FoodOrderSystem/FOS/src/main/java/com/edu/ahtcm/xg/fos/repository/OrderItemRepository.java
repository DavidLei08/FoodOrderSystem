package com.edu.ahtcm.xg.fos.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.edu.ahtcm.xg.fos.model.OrderItemModel;

@Mapper
public interface OrderItemRepository {

	@Insert("INSERT INTO tb_order_item"
			+ "(item_id,order_id,food_id,food_name,"
			+ "food_price,food_count)VALUES"
			+ "(#{item.itemId},#{item.orderId},#{item.foodId},"
			+ "#{item.foodName},#{item.foodPrice},#{item.foodCount})")
	public int inserItem(@Param("item")OrderItemModel itemModel);
	
	public int delItemByOrderId(String orderId);
	
	public int delItemByItem(String itemId);
	
	@Select("SELECT item_id itemId,"
			+ "order_id orderId,"
			+ "food_id foodId,"
			+ "food_name foodName,"
			+ "food_price foodPrice,"
			+ "food_count foodCount"
			+ " FROM tb_order_item"
			+ " WHERE order_id=#{orderId}")
	public List<OrderItemModel> findItemByOrderId(@Param("orderId")String orderId);
	
	@Select("SELECT item_id itemId,"
			+ "order_id orderId,"
			+ "food_id foodId,"
			+ "food_name foodName,"
			+ "food_price foodPrice,"
			+ "food_count foodCount"
			+ " FROM tb_order_item"
			+ " WHERE food_id=#{foodId}")
	public List<OrderItemModel> findItemByfoodId(@Param("foodId")String foodId);
	
	
}
