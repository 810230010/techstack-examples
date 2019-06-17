package com.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户手机号注册校验
 * 
 * @author Your Name
 *
 */
public class Test2 {
	private static final String CHECK_SUCCESS_MSG = "SUCCESS";
	private static final String REGISTER_SUCCESS = "注册成功";
	static {

	}
	public static void main(String[] args) {
		// TODO 通过此手机号注册成功
		String phoneNum1 = "138 1234 1234";
		UmsService.register(phoneNum1);
		// TODO 通过本手机号无法注册，提示为非法手机号
		String phoneNum2 = "13812345abc";
		UmsService.register(phoneNum2);
		// TODO 通过此手机号注册成功
		String phoneNum3 = "13812345678";
		UmsService.register(phoneNum3);
		// TODO 提示此手机号已经被其他用户注册
		String phoneNum4 = "138 1234 5678";
		UmsService.register(phoneNum4);
		// TODO 此手机号码为中国大陆非法手机号码
		String phoneNum5 = "98765432101";
		UmsService.register(phoneNum5);
	}



	/**
	 * 用户中心
	 */
	static class UmsService{
		public static String register(String userInputPhone){
			System.out.println("待注册的手机号: " + userInputPhone);
			String registerResult = null;
			registerResult = RegisterInterceptor.doInterceptor(userInputPhone);
			System.out.println("注册结果: " + registerResult);
			System.out.println();
			return registerResult;
		}
		public static List<String> listRegisteredPhones(){
			//正常从数据库取
			List<String> registeredPhones = null;
			registeredPhones = new ArrayList<String>();
			List<String> data = Arrays.asList("138 1234 5678", "98765432101");
			registeredPhones.addAll(data);
			return registeredPhones;
		}

		public static void addPhone(String phone) {
			//添加手机号操作
		}
	}
//	interface Interceptor{
//		String doInterceptor(Object... params);
//	}
	static class RegisterInterceptor{

		static List<PhoneCheckStrategy> phoneCheckStrategies = new ArrayList<PhoneCheckStrategy>();
		static {
			phoneCheckStrategies.add(new NotNullStrategy());
			phoneCheckStrategies.add(new ChinaPhoneStrategy());
			phoneCheckStrategies.add(new ValidPhoneStrategy());
			phoneCheckStrategies.add(new CheckRegisteredStrategy());
		}
		public static String doInterceptor(String phone) {

			for(PhoneCheckStrategy phoneCheckStrategy : phoneCheckStrategies){
				String checkMsg = phoneCheckStrategy.check(phone);
				if(checkMsg.equals(CHECK_SUCCESS_MSG)){
					continue;
				}else {
					return checkMsg;
				}
			}
			//添加到数据库
			UmsService.addPhone(phone);
			return REGISTER_SUCCESS;
		}
	}


	interface PhoneCheckStrategy{
		String check(String phone);
	}

	/**
	 * 检验为空策略
	 */
	static class NotNullStrategy implements PhoneCheckStrategy{
		private static final String NULL_PHONE_MSG = "用户输入手机号不得为空";

		@Override
		public String check(String phone) {
			if(null == phone || "".equals(phone.trim())){
				return NULL_PHONE_MSG;
			}
			return CHECK_SUCCESS_MSG;
		}
	}
	/**
	 * 检验是否注册过
	 */
	static class CheckRegisteredStrategy implements PhoneCheckStrategy{
		private static final String PHONE_REGISTERED_MSG = "此手机号已经被其他用户注册";

		@Override
		public String check(String phone) {
			List<String> registeredPhones = UmsService.listRegisteredPhones();
			if(registeredPhones.contains(phone)){
				return PHONE_REGISTERED_MSG;
			}
			return CHECK_SUCCESS_MSG;
		}
	}

	/**
	 * 检验是否为中国大陆手机号
	 */
	static class ChinaPhoneStrategy implements PhoneCheckStrategy{
		private static final String NOT_CHINA_PHONE_MSG = "此手机号码为中国大陆非法手机号码";
		private static final String CHINA_PHONE_PREFIX = "1";
		@Override
		public String check(String phone) {
			if(!phone.startsWith(CHINA_PHONE_PREFIX)){
				return NOT_CHINA_PHONE_MSG;
			}
			return CHECK_SUCCESS_MSG;
		}
	}


	/**
	 * 验证手机合法性策略
	 */
	static class ValidPhoneStrategy implements PhoneCheckStrategy{
		private static final String INVALID_PHONE_MSG = "非法手机号";

		@Override
		public String check(String phone) {
			String formattedPhone = phone.replace(" ", "");
			if(!isValidPhone(formattedPhone)){
				return INVALID_PHONE_MSG;
			}
			return CHECK_SUCCESS_MSG;
		}
		private boolean isValidPhone(String phone){
			/**
			 * 移动号段正则表达式
			 */
			String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
			/**
			 * 联通号段正则表达式
			 */
			String pat2  = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
			/**
			 * 电信号段正则表达式
			 */
			String pat3  = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$";
			/**
			 * 虚拟运营商正则表达式
			 */
			String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

			Pattern pattern1 = Pattern.compile(pat1);
			Matcher match1 = pattern1.matcher(phone);
			boolean isMatch1 = match1.matches();
			if(isMatch1){
				return true;
			}
			Pattern pattern2 = Pattern.compile(pat2);
			Matcher match2 = pattern2.matcher(phone);
			boolean isMatch2 = match2.matches();
			if(isMatch2){
				return true;
			}
			Pattern pattern3 = Pattern.compile(pat3);
			Matcher match3 = pattern3.matcher(phone);
			boolean isMatch3 = match3.matches();
			if(isMatch3){
				return true;
			}
			Pattern pattern4 = Pattern.compile(pat4);
			Matcher match4 = pattern4.matcher(phone);
			boolean isMatch4 = match4.matches();
			if(isMatch4){
				return true;
			}
			return false;
		}
	}

}
