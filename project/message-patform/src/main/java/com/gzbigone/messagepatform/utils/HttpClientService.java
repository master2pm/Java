package com.gzbigone.messagepatform.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HttpClientService {

	private CloseableHttpClient httpClient = HttpClients.createDefault();

	private ObjectMapper mapper = new ObjectMapper();

	/*
	 * get请求
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	/*public HttpResponseEntity sendGet(String url, Map<String, String> map) {

		CloseableHttpResponse response = null;

		try {
			// 创建URIBuilder对象
			URIBuilder builder = new URIBuilder(url);
			// 判断map集合
			if (map != null && map.size() > 0) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					builder.addParameter(entry.getKey(), entry.getValue());
				}
			}
			// 创建HttpGet对象
			HttpGet get = new HttpGet(builder.build());
			// 执行get请求
			response = httpClient.execute(get);

			HttpResponseEntity entity = new HttpResponseEntity();
			// 设置状态码
			entity.setStatusCode(response.getStatusLine().getStatusCode());
			// 判断响应体
			if (response.getEntity() != null) {
				// 设置响应内容
				entity.setContent(EntityUtils.toString(response.getEntity(), Consts.UTF_8));
			}
			return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}*/

	/*
	 * post请求
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	/*public HttpResponseEntity sendPost(String url, Map<String, String> map, boolean json) {
		// 定义可关闭的HttpResponse对象
		CloseableHttpResponse response = null;

		try {
			HttpPost httpPost = new HttpPost(url);

			if (map != null && map.size() > 0) {
				List<BasicNameValuePair> list = new ArrayList<>();
				// 请求参数用json来封装
				if (json) {
					httpPost.setEntity(new StringEntity(mapper.writeValueAsString(map), ContentType.APPLICATION_JSON));
				} else {
					for (Map.Entry<String, String> entry : map.entrySet()) {
						list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					}
					httpPost.setEntity(new UrlEncodedFormEntity(list, Consts.UTF_8));
				}
			}
			// 执行
			response = httpClient.execute(httpPost);
			// 创建HttpResponseEntity对象
			HttpResponseEntity entity = new HttpResponseEntity();
			// 设置响应状态码
			entity.setStatusCode(response.getStatusLine().getStatusCode());
			// 判断响应体
			if (response.getEntity() != null) {
				// 设置响应体
				entity.setContent(EntityUtils.toString(response.getEntity(), Consts.UTF_8));
			}
			return entity;
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (Exception e2) {
			}
		}
	}*/

	/**
	 * 获取token
	 * 
	 * @param url
	 * @param key base64加密后的key
	 * @return
	 */
	public Map<String, Object> getToket(String url, String key) {

		// 定义可关闭的HttpResponse对象
		CloseableHttpResponse response = null;

		try {
			HttpPost httpPost = new HttpPost(url);
			// 设置头部信息
			httpPost.setHeader("Authorization", "Basic " + key);
			// 执行
			response = httpClient.execute(httpPost);

			Map<String, Object> map = new HashMap<String, Object>();
			if (response.getStatusLine().getStatusCode() == 200) {

				// 获取json数据
				String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
				// json数据转换为map
				map = mapper.readValue(json, new TypeReference<HashMap<String, String>>() {
				});

				return map;
			}
			map.put("result", false);
			map.put("resultInfo", "获取token失败");
			return map;
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (Exception e2) {
			}
		}

	}


	public Map<String, Object> getMapByUrl(String url, String token) {

		CloseableHttpResponse response = null;
		try {
			// 创建URIBuilder对象
			URIBuilder builder = new URIBuilder(url);
			// 创建HttpGet对象
			HttpGet get = new HttpGet(builder.build());
			// 设置头部信息
			get.setHeader("Authorization", "Bearer " + token);
			// 执行get请求
			response = httpClient.execute(get);

			Map<String, Object> map = new HashMap<String, Object>();
			// 获取json数据
			String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
			// json数据转换为map
			map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
			});

			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public Map<String, Object> getMapByUrlPost(String url, String token) {

		CloseableHttpResponse response = null;
		try {
			// 创建URIBuilder对象
			HttpPost httpPost = new HttpPost(url);

			// 设置头部信息
			httpPost.setHeader("Authorization", "Bearer " + token);
			// 执行get请求
			response = httpClient.execute(httpPost);

			Map<String, Object> map = new HashMap<String, Object>();
			// 获取json数据
			String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
			// json数据转换为map
			map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
			});

			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Map<String, Object>> getMapByUrlOutArrery(String url, String token) {

		CloseableHttpResponse response = null;
		try {
			// 创建URIBuilder对象
			URIBuilder builder = new URIBuilder(url);
			// 创建HttpGet对象
			HttpGet get = new HttpGet(builder.build());
			// 设置头部信息
			get.setHeader("Authorization", "Bearer " + token);
			// 执行get请求
			response = httpClient.execute(get);

			List<Map<String, Object>> list = new ArrayList<>();
			// 获取json数据
			String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
			// json数据转换为map
			list = mapper.readValue(json, new TypeReference<List<HashMap<String, Object>>>() {
			});
			list = (List) JSON.parse(json);
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Map<String, Object>> getMapByUrlOutArreryPost(String url, String token) {

		CloseableHttpResponse response = null;
		try {
			// 创建URIBuilder对象
			URIBuilder builder = new URIBuilder(url);
			// 创建HttpGet对象
			HttpPost post = new HttpPost(builder.build());
			// 设置头部信息
			post.setHeader("Authorization", "Bearer " + token);
			// 执行get请求
			response = httpClient.execute(post);

			List<Map<String, Object>> list = new ArrayList<>();
			// 获取json数据
			String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
			// json数据转换为map
			list = mapper.readValue(json, new TypeReference<List<HashMap<String, Object>>>() {
			});
			list = (List) JSON.parse(json);
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
