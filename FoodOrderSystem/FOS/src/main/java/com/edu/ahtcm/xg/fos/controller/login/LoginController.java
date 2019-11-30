package com.edu.ahtcm.xg.fos.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.edu.ahtcm.xg.fos.utils.encrypt.EncryptInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.ahtcm.xg.fos.model.UserModel;
import com.edu.ahtcm.xg.fos.service.user.UserLoginService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录Controller
 * @author DavidLei
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	@GetMapping("/login")
	public String loginPage(Model model, HttpServletRequest request) {
		model.addAttribute("twoImgUrl", "http://qr.liantu.com/api.php?w=120&h=120&m=5&text=http://"
				+ request.getRemoteAddr() + ":8080/fos/login");
		return "login";
	}

	@PostMapping("/login")
	public String userLogin(@Valid LoginInputForm inputForm,BindingResult result,HttpSession session,RedirectAttributes redirectAttributes) {
		if(!result.hasErrors()){
			UserModel userModel=userLoginService.userLogin(inputForm);
			if(userModel!=null){
				session.setAttribute("userInfo", userModel);
				session.setAttribute("orderCount", userLoginService.getOrderCount(userModel.getUserId()));
				redirectAttributes.addFlashAttribute("successMsg", "登录成功！欢饮使用自助点餐系统。");
				return "redirect:/menu";
			}
		}
			redirectAttributes.addFlashAttribute("errorMsg", "账号或密码有误，请重新登录");
			return "redirect:/login";	
	}

	@GetMapping("/register")
	public String registerPage() {

		return "register";
	}

	@PostMapping("/register")
	public String userRegister(RegisterInputForm inputForm,RedirectAttributes redirectAttributes) {

		userLoginService.userRegister(inputForm);
		redirectAttributes.addFlashAttribute("successMsg", "用户注册成功");
		return "redirect:/login";
	}


	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login";
	}

	@ResponseBody
	@GetMapping("/test")
	public EncryptInfo test() {
		List<String> encryptFieldNames = new ArrayList<String>();
		encryptFieldNames.add("password");
		encryptFieldNames.add("token");
		EncryptInfo info = new EncryptInfo();
		info.setEncryptNames(encryptFieldNames);
		info.setPackageName("com.edu.ahtcm.xg.fos.utils.encrypt");
		info.setVersion("1.0.1");
		return info;
	}
}
