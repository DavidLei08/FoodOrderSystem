package com.edu.ahtcm.xg.fos.service.user;

import com.edu.ahtcm.xg.fos.controller.login.LoginInputForm;
import com.edu.ahtcm.xg.fos.controller.login.RegisterInputForm;
import com.edu.ahtcm.xg.fos.model.UserModel;

public interface UserLoginService {

	public UserModel userLogin(LoginInputForm inputForm);
	
	public void userRegister(RegisterInputForm inputForm);
	
	public void userLogout(UserModel userModel);
	
	public int getOrderCount(String userId);
}
