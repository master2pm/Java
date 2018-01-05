package com.gzbigone.messagepatform.utils;




import com.gzbigone.messagepatform.entity.User;
import org.apache.commons.codec.binary.Base64;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {
		private final String  clientId= "EC-4sjMJGTL";
		private final String  clientSecret= "eTRk8Ijw9xjfPXhG";

    private HttpClientService clientService=new HttpClientService();


	public String getToken(){
		
		try {
			byte[] encodeBase64 = Base64.encodeBase64(new String(clientId+":"+clientSecret).getBytes("UTF-8"));
			String t = new String(encodeBase64);
			//获取token
			Map<String,Object> tokenMap = clientService.getToket("http://118.89.63.221:8080/oauth/token?grant_type=client_credentials",new String(encodeBase64));
			//判断有没有获取到token
			if(tokenMap.get("access_token")!=null){
				return tokenMap.get("access_token").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserInfo(Integer userId, String token){
		Map<String, Object> userInfo = clientService.getMapByUrl("http://118.89.63.221:8092/api/user/" + userId, token);
		User user = null;
		try {
			user =(User) mapToObject((Map<String, Object>) userInfo.get("user"),User.class);
		}catch (Exception e){
			e.printStackTrace();
		}

		return user;
	}
	// 根据用户id和机构id获取机构身份信息
	public Object getOrgByIdAndOrgId(Integer userId, String orgId, String token) {
		Map<String, Object> userInfo = clientService.getMapByUrlPost(
				"http://118.89.63.221:8092/api/org/with/user/" + userId + "/"
						+ orgId, token);
		return (Map)(userInfo.get("userOrg"));
	}

	// 根据机构id获取机构身份信息
	public Map getOrgById( String orgId, String token) {
		Map<String, Object> org = clientService.getMapByUrl(
				"http://118.89.63.221:8092/api/org/get/info" + orgId , token);
		return (Map)(org.get("org"));
	}
	// 根据机构id获取机构身份信息
	public Map getDomainById( String domainId, String token) {
		Map<String, Object> org = clientService.getMapByUrl(
				"http://118.89.63.221:8092/api/domain/get/info/" + domainId , token);
		return (Map)(org.get("domain"));
	}
	// 根据用户id获取应用域信息
	public List<Integer> getAppByUserId(Integer userOrgId, String token) {
		List<Map<String, Object>> list = clientService.getMapByUrlOutArreryPost(
				"http://118.89.63.221:8092/api/org/domain/" + userOrgId, token);
		List<Integer> domainIds = new ArrayList();
		for(Map<String,Object> map : list){
			Integer o = (Integer)map.get("domainId");
			if(o!=null){
				domainIds.add(o);
			}
		}

		return domainIds;
	}
	// 根据用户id获取所有机构
	public List getOrgByUserId(Integer userId, String token) {
		List<Map<String, Object>> list = clientService.getMapByUrlOutArreryPost(
				"http://118.89.63.221:8092/api/org/with/user/" + userId, token);
		String  str = "";
		List orgList = new ArrayList();
		for(Map<String,Object> map : list){
			String orgId = String.valueOf(map.get("orgId"));
			if(orgId!=null){
				Map org = getOrgById(orgId, token);
				orgList.add(org);
			}
		}
		if (str.length() >0){
			str = str.substring(1,str.length());
		}
		return orgList;
	}
	//判断所有


	/**
	 * map转实体类
	 * @param map
	 * @param beanClass
	 * @return	Object
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (PropertyDescriptor property : propertyDescriptors) {
			Method setter = property.getWriteMethod();
			if (setter != null) {
				Class<?> propertyType = property.getPropertyType();
				if(propertyType.getName().equals("java.util.Date")){
					setter.invoke(obj,  new Date((Long) map.get(property.getName())));
				}else{
					if("personalHomepage".equals(property.getName())){
						setter.invoke(obj, map.get("personalHomePage"));
					}else if("provincesAutoregions".equals(property.getName())){
						setter.invoke(obj, map.get("provincesAutoRegions"));
					}else{
						setter.invoke(obj, map.get(property.getName()));
					}

				}
			}
		}

		return obj;
	}
}

