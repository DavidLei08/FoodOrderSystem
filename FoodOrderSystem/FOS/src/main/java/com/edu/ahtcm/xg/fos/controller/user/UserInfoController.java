package com.edu.ahtcm.xg.fos.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.edu.ahtcm.xg.fos.authorization.annotation.Authorizated;
import com.edu.ahtcm.xg.fos.model.UserModel;

@Controller
public class UserInfoController {

	@Authorizated
	@GetMapping("/getUserInfo")
	public String  getUserInfo(){
		
		return "userUpdate";
		
	}
	
	@Authorizated
	@PostMapping("/updateUserInfo")
	public String updateUserInfo(UserModel userModel){
		
		return "";
		
	}
}
