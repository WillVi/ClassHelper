package cn.willvi.controller.appUser;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.willvi.controller.BaseController;
import cn.willvi.service.appUser.AppUserManager;
import cn.willvi.util.PageData;

@Controller
@RequestMapping(value = "/app")
public class AppUserController extends BaseController{
	@Resource(name = "appUserService")
	private AppUserManager appUserManager;

	/*
	 * 用户注册
	 * 前台传递过来的参数：s_name（学生姓名）和s_id（学生学号）
	 * 发送给前台的String数据：Registration Successful/Registration Failed
	 */
	@RequestMapping(value = "/regist")
	@ResponseBody
	public String regist() throws Exception{
		PageData pd = this.getPageData();
		if(!pd.isEmpty() && pd.containsKey("s_id") && pd.containsKey("s_name")) {
			String s_id = pd.getString("s_id");
			if(s_id.length()<8 || s_id.length()>15 || !s_id.matches("[0-9]+")) {
				return "Registration Failed";
			} else {
				return appUserManager.Regist(pd);
			}
		} else {
			return "Registration Failed";
		}
	}
	
	/*
	 * 学生扫码登录并自动完成签到
	 * 前台传递过来的参数：s_id（学生学号）和url(二维码所对应的字符串)和time（学生签到时间）
	 * 发送给前台的String数据：Login Successful/Login Failed
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public String login() throws Exception {
		PageData pd = this.getPageData();
		if(!pd.isEmpty() && pd.containsKey("s_id") && pd.containsKey("url") && pd.containsKey("time")) {
			String s_id = pd.getString("s_id");
			if(s_id.length()<8 || s_id.length()>15 || !s_id.matches("[0-9]+")) {
				return "login Failed";
			} else {
				return appUserManager.login(pd);
			}
		} else {
			return "Login Failed";
		}
	}
}
