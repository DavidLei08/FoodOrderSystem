package com.edu.ahtcm.xg.fos.FOS;


import com.edu.ahtcm.xg.fos.controller.login.LoginInputForm;
import com.edu.ahtcm.xg.fos.utils.encrypt.EncryptInfo;
import com.edu.ahtcm.xg.fos.utils.encrypt.ObjectEncryptionUtil;
import com.edu.ahtcm.xg.fos.utils.json.JsonTransferUtil;
import com.edu.ahtcm.xg.fos.utils.reader.FileReadUtil;
import com.edu.ahtcm.xg.fos.utils.thread.ThreadPoolUtilInterface;
import net.sf.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.spec.EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class FosApplicationTests {

	@Autowired
	private ThreadPoolUtilInterface threadPoolUtil;

	@Value("${fos.keystore.path}")
	private String keystorePath;

	@Test
	public void contextLoads() {

//		Runnable runnable = new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("test thread");
//			}
//		};
//		threadPoolUtil.pushTaskToThreadPool(runnable);
//
//		Method method = null;
//		try {
//			method= IPAddrUtil.class.getMethod("getLocalIpAddr");
//
//
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		}
//		threadPoolUtil.pushMethodToThreadPool(method,null);
		LoginInputForm inputForm = new LoginInputForm();
		inputForm.setPassword("4545");
		inputForm.setUserName("Davidlei");
//		JsonTransferUtil.getJSONStr(inputForm);
//		JsonTransferUtil.getJSONStrToObject(JsonTransferUtil.getJSONStr(inputForm),LoginInputForm.class);
		List<String> encryptFieldNames = new ArrayList<String>();
		String str = FileReadUtil.getStringForFile(keystorePath);
		EncryptInfo info = (EncryptInfo) JSONObject.toBean(JSONObject.fromObject(str));


//		String temp3 = JsonTransferUtil.getJSONStr(encryptFieldNames);
		ObjectEncryptionUtil util =new ObjectEncryptionUtil(info.getEncryptNames());
		LoginInputForm temp = util.encryptObject(inputForm);
		LoginInputForm temp2= util.decryptObject(temp);

	}

}
