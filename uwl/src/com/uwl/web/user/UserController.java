package com.uwl.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uwl.common.Page;
import com.uwl.common.Search;
import com.uwl.service.domain.User;
import com.uwl.service.schoolRank.SchoolRankService;
import com.uwl.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {

	/// Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("schoolRankServiceImpl")
	private SchoolRankService schoolRankService;
	
	// setter Method 구현 않음

	public UserController() {
		System.out.println(this.getClass());
		System.out.println("UserController() 객체 생성");
	}

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	// 회원가입
	@RequestMapping(value = "addUser", method = RequestMethod.GET)
	public String addUser() throws Exception {
		System.out.println("UserController : addUser() 호출");

		System.out.println("/user/addUser : GET");

		return "redirect:/user/addUserView.jsp";
	}

	// 회원가입
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) throws Exception {
		System.out.println("UserController : addUser() 호출");

		System.out.println("/user/addUser : POST");
		// Business Logic
		userService.addUser(user);

		//SchoolRank 추가하기!!!!!!!!!!!!!!!
		
		return "redirect:/user/loginView.jsp";
	}

	// 실명인증여부
	@RequestMapping(value = "addRealname", method = RequestMethod.POST)
	public String addRealname(@ModelAttribute("user") User user) throws Exception {
		System.out.println("UserController : addRealname() 호출");

		System.out.println("/user/addRealname : POST");
		// Business Logic
		userService.addRealname(user);

		return "redirect:/user/loginView.jsp";
	}

	// 회원정보 보기
	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	public String getUser(@RequestParam("userId") String userId, Model model) throws Exception {
		System.out.println("UserController : getUser() GET 호출");

		System.out.println("/user/getUser : GET");
		// Business Logic
		User user = userService.getUser(userId);
		// Model 과 View 연결
		model.addAttribute("user", user);

		return "forward:/user/getUser.jsp";
	}
	
	//수정!!!!!!!!!!!!!!!!!!!!!!!!!!
	// 회원정보 보기
		@RequestMapping(value = "getUser", method = RequestMethod.POST)
		public String getUser(@ModelAttribute("user") User user) throws Exception {
			System.out.println("UserController : getUser() POST 호출");
			
			System.out.println("/user/getUser : POST");
			// Business Logic
//			userService.getUser(userId);

			return "redirect:/user/getUser?userId=" + user.getUserId();
		}
	
	
	// 회원정보 수정
	@RequestMapping(value = "updateUser", method = RequestMethod.GET)
	public String updateUser(@RequestParam("userId") String userId, Model model) throws Exception {
		System.out.println("UserController : updateUser() GET 호출");

		System.out.println("/user/updateUser : GET");
		// Business Logic
		User user = userService.getUser(userId);
		// Model 과 View 연결
		model.addAttribute("user", user);

		return "forward:/user/updateUser.jsp";
	}

	// 회원정보 수정
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, Model model, HttpSession session) throws Exception {
		System.out.println("UserController : updateUser() POST 호출");

		System.out.println("/user/updateUser : POST");
		
		// Business Logic
		userService.updateUser(user);
		
		String sessionId = ((User) session.getAttribute("user")).getUserId();
		if (sessionId.equals(user.getUserId())) {
			session.setAttribute("user", user);
		}
		return "redirect:/user/getUser?userId=" + user.getUserId();
	}

	// 프로필 보기
	@RequestMapping(value = "getProfile", method = RequestMethod.GET)
	public String getProfile(@RequestParam("userId") String userId, Model model) throws Exception {
		System.out.println("UserController : getProfile() 호출");

		System.out.println("/user/getProfile : GET");
		// Business Logic
		User user = userService.getProfile(userId);
		// Model 과 View 연결
		model.addAttribute("user", user);

		return "forward:/user/getProfile.jsp";
	}

	// 프로필 수정
	@RequestMapping(value = "updateProfile", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute("user") User user, Model model, HttpSession session) throws Exception {
		System.out.println("UserController : updateProfile() 호출");

		System.out.println("/user/updateProfile : POST");
		// Business Logic
		userService.updateProfile(user);

		String sessionId = ((User) session.getAttribute("user")).getUserId();
		if (sessionId.equals(user.getUserId())) {
			session.setAttribute("user", user);
		}

		return "redirect:/user/getProfile?userId=" + user.getUserId();
	}

	// 문의사항 등록
	@RequestMapping(value = "addQuestions", method = RequestMethod.POST)
	public String addQuestions(@ModelAttribute("user") User user) throws Exception {
		System.out.println("UserController : addQuestions() 호출");

		System.out.println("/user/addQuestions : POST");
		// Business Logic
		userService.addQuestions(user);

		return "redirect:/user/addQuestions.jsp";
	}

	// 문의사항 수정
	@RequestMapping(value = "updateQuestions", method = RequestMethod.POST)
	public String updateQuestions(@ModelAttribute("user") User user, Model model, HttpSession session)
			throws Exception {
		System.out.println("UserController : updateQuestions() 호출");

		System.out.println("/user/updateQuestions : POST");
		// Business Logic
		userService.updateQuestions(user);

		String sessionId = ((User) session.getAttribute("user")).getUserId();
		if (sessionId.equals(user.getUserId())) {
			session.setAttribute("user", user);
		}

		return "redirect:/user/getUserQuestions?userId=" + user.getUserId();
	}

	// 나의 문의사항 내역
	@RequestMapping(value = "getUserQuestions", method = RequestMethod.GET)
	public String getUserQuestions(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {
		System.out.println("UserController : getUserQuestions() 호출");

		System.out.println("/user/getUserQuestions : GET");
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic 수행
		Map<String, Object> map = userService.getUserQuestions(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/user/getUserQuestions.jsp";
	}

//	// 내가 쓴 게시글
//	@RequestMapping(value = "getUserPostList", method = RequestMethod.GET)
//	public String getUserPostList(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
//			throws Exception {
//		System.out.println("UserController : getUserPostList() 호출");
//
//		System.out.println("/user/getUserPostList : GET");
//
//		if (search.getCurrentPage() == 0) {
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		// Business logic 수행
//		Map<String, Object> map = userService.getUserPostList(search);
//
//		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
//				pageSize);
//		System.out.println(resultPage);
//
//		// Model 과 View 연결
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
//
//		return "forward:/user/getProfile.jsp";
//	}

//	// 내가 쓴 댓글
//	@RequestMapping(value = "getUserCommentList", method = RequestMethod.GET)
//	public String getUserCommentList(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
//			throws Exception {
//		System.out.println("UserController : getUserCommentList() 호출");
//
//		System.out.println("/user/getUserPosgetUserCommentListtList : GET");
//		
//		if (search.getCurrentPage() == 0) {
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		// Business logic 수행
//		Map<String, Object> map = userService.getUserCommentList(search);
//
//		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
//				pageSize);
//		System.out.println(resultPage);
//
//		// Model 과 View 연결
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
//
//		return "forward:/user/getProfile.jsp";
//	}

//	// 내가 좋아요한 글
//	@RequestMapping(value = "getUserLikePostList", method = RequestMethod.GET)
//	public String getUserLikePostList(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
//			throws Exception {
//		System.out.println("UserController : getUserLikePostList() 호출");
//
//		System.out.println("/user/getUserLikePostList : GET");
//		if (search.getCurrentPage() == 0) {
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		// Business logic 수행
//		Map<String, Object> map = userService.getUserLikePostList(search);
//
//		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
//				pageSize);
//		System.out.println(resultPage);
//
//		// Model 과 View 연결
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
//
//		return "forward:/user/getProfile.jsp";
//	}

	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() throws Exception {
		System.out.println("UserController : login() GET 호출");

		System.out.println("/user/logon : GET");

		return "redirect:/user/loginView.jsp";
	}

	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception {
		System.out.println("UserController : login() POST 호출");

		System.out.println("/user/login : POST");
		// Business Logic
		User dbUser = userService.getUser(user.getUserId());

		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
			System.out.println(dbUser);
			System.out.println("session scope 저장");
		}

		return "redirect:/index.jsp";
	}

	// 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		System.out.println("UserController : logout() 호출");

		System.out.println("/user/logout : POST");

		session.invalidate();

		return "redirect:/index.jsp";
	}

	// 아이디 중복체크
	@RequestMapping(value = "checkDuplicationUserId", method = RequestMethod.POST)
	public String checkDuplicationUserId(@RequestParam("userId") String userId, Model model) throws Exception {
		System.out.println("UserController : checkDuplicationUserId() 호출");

		System.out.println("/user/checkDuplicationUserId : POST");
		// Business Logic
		boolean result = userService.checkDuplicationUserId(userId);
		// Model 과 View 연결
		model.addAttribute("result", new Boolean(result));
		model.addAttribute("userId", userId);

		return "forward:/user/checkDuplicationUserId.jsp";
	}

	// 닉네임 중복체크
	@RequestMapping(value = "checkDuplicationNickname", method = RequestMethod.POST)
	public String checkDuplicationNickname(@RequestParam("nickname") String nickname, Model model) throws Exception {
		System.out.println("UserController : checkDuplicationNickname() 호출");

		System.out.println("/user/checkDuplicationNickname : POST");
		// Business Logic
		boolean result = userService.checkDuplicationNickname(nickname);
		// Model 과 View 연결
		model.addAttribute("result", new Boolean(result));
		model.addAttribute("nickname", nickname);

		return "forward:/user/checkDuplicationNickname.jsp";
	}
	
	
	// 메일 중복체크
		@RequestMapping(value = "checkDuplicationMail", method = RequestMethod.POST)
		public String checkDuplicationMail(@RequestParam("mail") String mail, Model model) throws Exception {
			System.out.println("UserController : checkDuplicationMail() 호출");

			System.out.println("/user/checkDuplicationMail : POST");
			// Business Logic
			boolean result = userService.checkDuplicationMail(mail);
			// Model 과 View 연결
			model.addAttribute("result", new Boolean(result));
			model.addAttribute("mail", mail);

			return "forward:/user/checkDuplicationMail.jsp";
		}

//	// 전체 회원목록
//	@RequestMapping(value = "listUser")
//	public String listUser(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
//			throws Exception {
//
//		System.out.println("/user/listUser : GET / POST");
//
//		if (search.getCurrentPage() == 0) {
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		// Business logic 수행
//		Map<String, Object> map = userService.getUserList(search);
//
//		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
//				pageSize);
//		System.out.println(resultPage);
//
//		// Model 과 View 연결
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
//
//		return "forward:/user/listUser.jsp";
//	}

	// 전체 회원목록
	@RequestMapping(value = "listUser", method = RequestMethod.GET)
	public String listUser(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {
		System.out.println("UserController : listUser() 호출");

		System.out.println("/user/listUser : GET / POST");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic 수행
		Map<String, Object> map = userService.getUserList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/user/listUser.jsp";
	}

	// 전체 문의사항 목록
	@RequestMapping(value = "listQuestions")
	public String listQuestions(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {
		System.out.println("UserController : listQuestions() 호출");

		System.out.println("/user/listQuestions : GET / POST");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic 수행
		Map<String, Object> map = userService.getUserQuestionsList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/user/listQuestions.jsp";
	}

//	// 전체 문의사항 목록
//	@RequestMapping(value = "listQuestions", method = RequestMethod.GET)
//	public String listQuestions(@RequestParam("userId") String userId, Model model) throws Exception {
//		System.out.println("UserController : listQuestions() 호출");
//
//		System.out.println("/user/listQuestions : GET");
//		// Business Logic
//		List<User> user = userService.getUserQuestionsList(userId);
//		// Model 과 View 연결
//		model.addAttribute("user", user);
//
//		return "forward:/user/listQuestions.jsp";
//	}

	// 회원탈퇴
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute("user") User user, Model model, HttpSession session) throws Exception {
		System.out.println("UserController : deleteUser() 호출");

		System.out.println("/user/deleteUser : POST");
		// Business Logic
		userService.deleteUser(user);

		String sessionId = ((User) session.getAttribute("user")).getUserId();
		if (sessionId.equals(user.getUserId())) {
			session.setAttribute("user", user);
		}

		return "redirect:/user/getUser?userId=" + user.getUserId();
	}

}
