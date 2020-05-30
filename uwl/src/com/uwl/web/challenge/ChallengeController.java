package com.uwl.web.challenge;

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
import com.uwl.service.challenge.ChallengeService;
import com.uwl.service.domain.Challenge;

@Controller
@RequestMapping("/challenge/*")
public class ChallengeController {
	
	//Field ==> 더 필요하면 추가시킬것.
	@Autowired
	@Qualifier("challengeServiceImpl")
	private ChallengeService challService;
	
	//Constructor
	public ChallengeController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value = "addChallenge", method = RequestMethod.GET)
	public String addChallenge() throws Exception{
		
		System.out.println("/challenge/addChallenge : GET");
		
		return "redirect:/challenge/addChallengeView.jsp";
		
	}
	
	@RequestMapping(value = "addChallenge", method = RequestMethod.POST)
	public String addChallenge(@ModelAttribute("challenge")Challenge challenge, Model model) throws Exception{
		
		System.out.println("/challenge/addChallenge : POST");
		
		//addChallenge Logic 실행
		challService.addChallenge(challenge);
		model.addAttribute("challenge", challenge);
		
		System.out.println("challenege : " + challenge);
		
		return "forward:/challenge/addChallenge.jsp";
	}
	
	@RequestMapping(value = "updateChallenge", method = RequestMethod.GET)
	public String updateChallenge(@RequestParam("challNo") int challNo, Model model) throws Exception{
		
		System.out.println("/challenge/updateChallenge : GET");
		
		Challenge challenge = challService.getChallengeAdmin(challNo);
		
		model.addAttribute("challenge", challenge);
		
		return "forward:/challenge/updateChallenge.jsp";
		
	}
	
	@RequestMapping(value = "updateChallenge", method = RequestMethod.POST)
	public String updateChallenge(@ModelAttribute("challenge") Challenge challenge, Model model) throws Exception{
		
		System.out.println("/challenge/updateChallenge : POST");
		
		challService.updateChallenge(challenge);
		
		model.addAttribute("challenge", challenge);
		System.out.println("challenge update : " + challenge);
		
		//role이 어드민일때만 가게끔 로직을 구성해야된다. 나중에 할 떄 참고
		return "redirect:/challenge/getchallenge?challNo=" + challenge.getChallNo();
	}
	
	//GET과 POST를 동시에
	@RequestMapping(value = "getAdminChallengeList")
	public String getAdminChallengeList(@ModelAttribute("search") Search search, Model model, HttpServletRequest request) throws Exception{
		
		System.out.println("/challenge/getAdminChallengeList");
		
		//가져온 현재페이지가 0이면 1페이지로 navagation
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		search.setPageSize(pageSize);
		
		Map<String, Object> map = challService.getAdminChallengeList(search);
		System.out.println("getAdminChallengeList의 map : " + map);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("list"));
		System.out.println("list : " + map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/challenge/getAdminChallengeList.jsp";
		
	}
	
	@RequestMapping(value = "getChallenge", method = RequestMethod.GET)
	public String getChallengeAdmin(@RequestParam("challNo") int challNo, Model model) throws Exception{
		
		System.out.println("getChallenge의 challNo : " + challNo);
		System.out.println("/challenge/getChallenge : GET ");
		
		Challenge challenge = challService.getChallengeAdmin(challNo);
		
		model.addAttribute("challenge", challenge);
		
		return "forward:/challenge/updateChallengetView.jsp";
	}
		
	
}
