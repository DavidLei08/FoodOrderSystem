package com.edu.ahtcm.xg.fos.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.edu.ahtcm.xg.fos.model.OrderModel;


@Mapper
public interface OrderRespository {

	@Insert("INSERT INTO tb_order"
			+ " (order_id,order_person,tel,"
			+ "restaurant,order_amount,food_count,order_time) VALUES"
			+ " (#{orderInfo.orderId},#{orderInfo.orderPerson},"
			+ "#{orderInfo.tel},#{orderInfo.restaurant},"
			+ "#{orderInfo.orderAmount},#{orderInfo.foodCount},"
			+ "#{orderInfo.orderTime})")
	public int insertOrder(@Param("orderInfo")OrderModel orderModel);

	@Delete("")
	public int delOrder(String orderId);
	
	@Update("")
	public int updateOrder(OrderModel orderModel);

	@Select("")
	public List<OrderModel> findAll();
	
	@Select("SELECT order_id orderId,"
			+ "order_person orderPerson,"
			+ "tel tel,"
			+ "restaurant restaurant,"
			+ "order_amount orderAmount,"
			+ "food_count foodCount,"
			+ "order_time orderTime"
			+ " FROM tb_order "
			+ " WHERE order_person=#{userId} ORDER BY order_time desc")
	public List<OrderModel> findByUserId(@Param("userId")String userId);
	
	@Select("SELECT order_id orderId,"
			+ "order_person orderPerson,"
			+ "tel tel,"
			+ "restaurant restaurant,"
			+ "order_amount orderAmount,"
			+ "food_count foodCount,"
			+ "order_time orderTime"
			+ " FROM tb_order "
			+ " WHERE order_id=#{orderId} ORDER BY order_time desc")
	public OrderModel findById(String orderId);
}
