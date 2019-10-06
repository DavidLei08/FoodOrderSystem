package com.edu.ahtcm.xg.fos.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.ahtcm.xg.fos.authorization.annotation.Authorizated;
import com.edu.ahtcm.xg.fos.service.food.FoodMenuService;


@Controller
public class CommonController {

	@Autowired
	private FoodMenuService foodService;
	
	@Authorizated
	@GetMapping("/menu")
	public String menuPage(){
		
		return "menu";
		
	}
	
	@Authorizated
	@GetMapping("/foodImg/{foodId}")
	public void getFoodImage(@PathVariable("foodId")String foodId,HttpServletResponse response){
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			response.setContentType("image/*");
			try {
				outputStream.write(foodService.getFoodImg(foodId));
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
	}
}
