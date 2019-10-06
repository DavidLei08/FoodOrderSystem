package com.edu.ahtcm.xg.fos.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.edu.ahtcm.xg.fos.controller.login.LoginInputForm;
import com.edu.ahtcm.xg.fos.controller.login.RegisterInputForm;
import com.edu.ahtcm.xg.fos.model.UserModel;

@Mapper
public interface UserRespository {

	
	@Select("SELECT user_id userId,"
			+ " user_name userName,"
			+ " like_taste likeTaste,"
			+ " origin_place originPlace,"
			+ " dislike_food dislikeFood,"
			+ " tel tel,"
			+ " password password"
			+ " FROM tb_user"
			+ " WHERE (user_name=#{inputForm.userName} OR tel=#{inputForm.userName})"
			+ " AND password=#{inputForm.password}")
	public UserModel userLogin(@Param("inputForm")LoginInputForm inputForm);
	
	@Insert("INSERT INTO tb_user"
			+ "(user_id,user_name,"
			+ " like_taste,origin_place,"
			+ " dislike_food,password,tel) "
			+ " VALUES("
			+ " #{inputForm.userId},"
			+ " #{inputForm.userName},"
			+ " #{inputForm.likeTaste},"
			+ " #{inputForm.originPlace},"
			+ " #{inputForm.dislikeFood},"
			+ " #{inputForm.password},"
			+ " #{inputForm.tel})" )
	public int userAdd(@Param("inputForm")RegisterInputForm inputForm);
	public int userUpdate(UserModel userModel);
	public UserModel findUserById(String userId);
}
