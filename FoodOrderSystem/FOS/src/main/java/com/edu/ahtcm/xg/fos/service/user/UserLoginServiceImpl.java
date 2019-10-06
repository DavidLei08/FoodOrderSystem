package com.edu.ahtcm.xg.fos.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ahtcm.xg.fos.common.APIException;
import com.edu.ahtcm.xg.fos.controller.login.LoginInputForm;
import com.edu.ahtcm.xg.fos.controller.login.RegisterInputForm;
import com.edu.ahtcm.xg.fos.model.UserModel;
import com.edu.ahtcm.xg.fos.repository.OrderRespository;
import com.edu.ahtcm.xg.fos.repository.UserRespository;
import com.edu.ahtcm.xg.fos.utils.RandIDUtil;

@Service
public class UserLoginServiceImpl implements UserLoginService {

@Autowired
private UserRespository userRespository;

@Autowired
private OrderRespository orderRespository;
	
	@Override
	public UserModel userLogin(LoginInputForm inputForm) {
		UserModel userModel=userRespository.userLogin(inputForm);
		return userModel;
	}

	@Override
	public void userRegister(RegisterInputForm inputForm) {
		inputForm.setUserId(RandIDUtil.getFifteenId());
		try {
			int total=userRespository.userAdd(inputForm);
			if(total!=1){
				throw new APIException(400,"user register has error");
			}
		} catch (Exception e) {
			throw new APIException(400,"user register has error");
		}

	}

	@Override
	public void userLogout(UserModel userModel) {
		
	}

	@Override
	public int getOrderCount(String userId) {
		
		return orderRespository.findByUserId(userId).size();
	}

}
