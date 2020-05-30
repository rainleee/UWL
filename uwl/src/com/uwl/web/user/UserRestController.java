package com.uwl.web.user;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uwl.common.Page;
import com.uwl.common.Search;
import com.uwl.service.domain.User;
import com.uwl.service.user.UserService;

@RestController
@RequestMapping("/user/*")
public class UserRestController {

	/// Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	public UserRestController() {
		System.out.println(this.getClass());
	}

	// 회원가입
	@RequestMapping(value = "json/addUser", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) throws Exception {
		System.out.println("/user/json/addUser : POST");

		userService.addUser(user);
		System.out.println("[addUser end...]");
	}

	// 회원정보 , id 중복체크
	@RequestMapping(value = "json/getUser/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) throws Exception {

		System.out.println("/user/json/getUser : GET");

		// Business Logic
		return userService.getUser(userId);
	}

	// nickname 중복체크
	@RequestMapping(value = "json/getUserByNick/{userId}", method = RequestMethod.GET)
	public User getUserByNickname(@PathVariable String nickname) throws Exception {

		System.out.println("/user/json/getUser : GET");

		// Business Logic
		return userService.getUserByNickname(nickname);
	}

	// 회원정보 수정
	@RequestMapping(value = "json/updateUser/{userId}", method = RequestMethod.GET)
	public User updateUser(@PathVariable String userId) throws Exception {

		System.out.println("/user/updateUser : GET");

		User user = userService.getUser(userId);

		return user;
	}

	// 회원정보 수정
	@RequestMapping(value = "json/updateUser", method = RequestMethod.POST)
	public void updateUser(@RequestBody User user, HttpSession session) throws Exception {

		System.out.println("/user/updateUser : POST");
		userService.updateUser(user);

	}

	// 로그인
	@RequestMapping(value = "json/login", method = RequestMethod.POST)
	public User login(@RequestBody User user, HttpSession session) throws Exception {

		System.out.println("/user/json/login : POST");
		// Business Logic
		System.out.println("::" + user);
		User dbUser = userService.getUser(user.getUserId());

		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}

		return dbUser;
	}

	// 회원전체 목록
	@RequestMapping(value = "json/listUser", method = RequestMethod.GET)
	public Map listUser() throws Exception {
		Search search = new Search();

		System.out.println("/user/listUser : GET ");
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = userService.getUserList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		map.put("search", search);
		map.put("resultPage", resultPage);
		return map;
	}

	// 회원전체 목록
	@RequestMapping(value = "json/listUser", method = RequestMethod.POST)
	public Map listUser(@RequestBody Search search) throws Exception {

		System.out.println("/user/listUser : POST ");
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String , Object> map = userService.getUserList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		search.setPageSize((Integer) map.get("totalCount"));

		List<String> listUserId = new ArrayList<String>();
		List<String> listUserName = new ArrayList<String>();
//		List<User> list = (List<User>) userService.getUserList(search).get("list");
		List<User> list = (List<User>) map.get("list");

		for (int i = 0; i < list.size(); i++) {
			listUserId.add(list.get(i).getUserId());
		}

		for (int i = 0; i < list.size(); i++) {
			if (!listUserName.contains(list.get(i).getName())) {
				listUserName.add(list.get(i).getName());
			}
		}

		map.put("listUserId", listUserId);
		map.put("listUserName", listUserName);
		map.put("search", search);
		map.put("resultPage", resultPage);
		return map;
	}
	
	
//	// 회원전체 목록
//	@RequestMapping(value ="json/listUser", method = RequestMethod.GET)
//	public String listUser(@RequestParam("userId") String userId, Model model) throws Exception {
//
//		System.out.println("/user/listUser : GET");
//		// Business Logic
//		List<User> user = userService.getUserList(userId);
//		// Model 과 View 연결
//		model.addAttribute("user", user);
//
//		return "forward:/user/listUser.jsp";
//	}
	

	// 수정!!!!!!!!!!!!!!!!!!!!!!!!!
	// id 중복체크
	@RequestMapping(value = "json/checkDuplicationUserId", method = RequestMethod.POST)
	public Map checkDuplicationUserId(@RequestBody User user) throws Exception {
		System.out.println("/user/json/checkDuplicationUserId : POST");
		boolean result = userService.checkDuplicationUserId(user.getUserId());

		Map map = new HashMap();
		map.put("result", new Boolean(result));
		return map;
	}

	// 수정!!!!!!!!!!!!!!!!!!!!!!!!!
	// nickname 중복체크
	@RequestMapping(value = "json/checkDuplicationNickname", method = RequestMethod.POST)
	public Map checkDuplicationNickname(@RequestBody User user) throws Exception {
		System.out.println("/user/json/checkDuplicationNickname : POST");
		boolean result = userService.checkDuplicationNickname(user.getNickname());

		Map map = new HashMap();
		map.put("result", new Boolean(result));
		return map;
	}

	// 나의 문의사항 내역
	@RequestMapping(value = "json/getUserQuestions", method = RequestMethod.GET)
	public Map getUserQuestions() throws Exception{
		Search search = new Search();
		
		System.out.println("/user/getUserQuestions : GET ");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String , Object> map=userService.getUserQuestions(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		map.put("search", search);
		map.put("resultPage", resultPage);
		return map;
	}

//	// 내가 쓴 게시글
//	@RequestMapping(value = "getUserPostList", method = RequestMethod.GET)
//	public Map getUserPostList() throws Exception{
//		Search search = new Search();
//		
//		System.out.println("/user/getUserPostList : GET ");
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//		
//		Map<String , Object> map=userService.getUserPostList(search);
//		
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//		
//		map.put("search", search);
//		map.put("resultPage", resultPage);
//		return map;
//	}
//
//	// 내가 쓴 댓글
//	@RequestMapping(value = "getUserCommentList", method = RequestMethod.GET)
//	public Map getUserCommentList() throws Exception{
//		Search search = new Search();
//		
//		System.out.println("/user/getUserCommentList : GET ");
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//		
//		Map<String , Object> map=userService.getUserCommentList(search);
//		
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//		
//		map.put("search", search);
//		map.put("resultPage", resultPage);
//		return map;
//	}
//
//	// 내가 좋아요한 글
//	@RequestMapping(value = "getUserLikePostList", method = RequestMethod.GET)
//	public Map getUserLikePostList() throws Exception{
//		Search search = new Search();
//		
//		System.out.println("/user/getUserLikePostList : GET ");
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//		
//		Map<String , Object> map=userService.getUserLikePostList(search);
//		
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//		
//		map.put("search", search);
//		map.put("resultPage", resultPage);
//		return map;
//	}

	// id 찾기
	@RequestMapping(value = "json/findId", method = RequestMethod.POST)
	public User findId(@RequestBody User user) throws Exception {

		System.out.println("/user/json/findId : POST");
		// Business Logic
		User dbUser = userService.getUser(user.getMail());

		if (user.getMail().equals(dbUser.getMail())) {
			return dbUser;
		}
		return null;

	}

	// pw 찾기
	@RequestMapping(value = "json/findPw", method = RequestMethod.POST)
	public User findPw(@RequestBody User user) throws Exception {

		System.out.println("/user/json/findPw : POST");
		// Business Logic
		User dbUser = userService.getUser(user.getUserId());

		if (user.getUserId().equals(dbUser.getUserId())) {
		}

		return userService.getUser(user.getPassword());
	}

	// 문자인증
	@RequestMapping("/sendSms")
	public String sendSms(String receiver, HttpSession session) {

		// 6자리 인증 코드 생성
		int rand = (int) (Math.random() * 899999) + 100000;
		System.out.println("인증 코드 : " + rand);

		session.setAttribute("rand", rand);

		// 인증 코드를 데이터베이스에 저장하는 코드는 생략했습니다.

		// 문자 보내기
		String hostname = "api.bluehouselab.com";
		String url = "https://" + hostname + "/smscenter/v1.0/sendsms";

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(hostname, 443, AuthScope.ANY_REALM),
				new UsernamePasswordCredentials(Config.appid, Config.apikey));

		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		authCache.put(new HttpHost(hostname, 443, "https"), new BasicScheme());

		// Add AuthCache to the execution context
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);

		DefaultHttpClient client = new DefaultHttpClient();

		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/json; charset=utf-8");

			String json = "{\"sender\":\"" + Config.sender + "\",\"receivers\":[\"" + receiver + "\"],\"content\":\""
					+ rand + "\"}";

			StringEntity se = new StringEntity(json, "UTF-8");
			httpPost.setEntity(se);

			HttpResponse httpResponse = client.execute(httpPost, context);

			InputStream inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String line = "";
				while ((line = bufferedReader.readLine()) != null)
					inputStream.close();
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getLocalizedMessage());
		} finally {
			client.getConnectionManager().shutdown();
		}
		return "true";
	}

	@RequestMapping("/smsCheck")
	public String smsCheck(String code, HttpSession session) {

		String saveCode = "967698";
		System.out.println("발행한 인증 코드 :" + saveCode);

		if (code.equals(saveCode)) {
			return "ok";
		} else {
			return "no";
		}
	}

	final class Config {
		public static final String appid = "keundeok2";
		public static final String apikey = "e7dc07dc1ff111ea98850cc47a1fcfae";
		public static final String content = "나는 유리를 먹을 수 있어요. 그래도 아프지 않아요";
		public static final String sender = "01022269883";
		public static final String receiver = "01022269883";
	}
}
