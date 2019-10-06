package com.edu.ahtcm.xg.fos.service.food;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ahtcm.xg.fos.model.FoodModel;
import com.edu.ahtcm.xg.fos.model.UserModel;
import com.edu.ahtcm.xg.fos.repository.FoodRespository;
import com.edu.ahtcm.xg.fos.utils.RecommendUtil;

@Service
public class FoodMenuServiceImpl implements FoodMenuService {

	@Autowired
	private FoodRespository foodRespository;
	
	@Override
	public List<FoodModel> getAll() {
		
		return foodRespository.findAllFoood();
	}

	@Override
	public List<FoodModel> getRecommend(UserModel userModel) {
		List<FoodModel> allFoodList=foodRespository.findAllFoood();
		List<FoodModel> sortRecommendFoodList=RecommendUtil.getSortFoods(userModel, allFoodList);
		
		return sortRecommendFoodList;
	}

	@Override
	public byte[] getFoodImg(String foodId)  {
		String imgPath=foodRespository.findById(foodId).getFoodImgPath();
		//String imgPath="F://Documents//Downloads//FOS//FOS//src//main//resources//static//image//men1.jpg";
		File file=new File(imgPath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] imgBuffer=new byte[(int)file.length()];
		try {
			inputStream.read(imgBuffer);
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return imgBuffer;
	}

}
