package com.edu.ahtcm.xg.fos.controller.food;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.ahtcm.xg.fos.model.FoodModel;

@RequestMapping("/food")
@Controller
public class FoodManagerController {

	@GetMapping("/show")
	public List<FoodModel> showFood(){
		return null;
		
	}

	@GetMapping("/add")
	public String foodAddPage(){
		return null;
	}
	
	@PostMapping("/add")
	public String  doFoodAdd(FoodModel foodModel){
		return null;
	}
	
	
	@GetMapping("/update")
	public String foodUpdatePage(String foodId){
		return "";
	}
	
	@PostMapping("/update")
	public String doFoodUpdate(FoodModel foodModel){
		return null;
		
	}
	
}
