package com.uwl.web.post;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uwl.common.Page;
import com.uwl.common.Search;
import com.uwl.service.domain.Post;
import com.uwl.service.post.PostService;

@Controller
@RequestMapping("/post/*")
public class PostController {
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;
	
	public PostController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="getBoard", method=RequestMethod.GET)	//------------------------------------------------테스트 종료
	public String getBoard(@RequestParam("postNo") int postNo, Model model) throws Exception{
		System.out.println("getBoard.GET");
		Post post = postService.getBoard(postNo);
		model.addAttribute(post);
		return "forward:/post/getBoard.jsp";
	}
	
	@RequestMapping(value ="addBoard", method=RequestMethod.GET)	//------------------------------------------------테스트 종료
	public String addBoard(@RequestParam("gatherCategoryNo") String gatherCategoryNo, Model model) throws Exception {
		System.out.println("addBoard.GET");
		model.addAttribute("gatherCategoryNo", gatherCategoryNo);
		return "forward:/post/addBoard.jsp";
	}
	
	@RequestMapping(value="addBoard", method=RequestMethod.POST)	//------------------------------------------------테스트 종료
	public String addBoard(@ModelAttribute("post") Post post,
					HttpServletRequest request, Model model, @RequestParam("fileName") MultipartFile file) throws Exception {
		System.out.println("addBoard.POST");
		String path = "C:\\Users\\User\\Desktop\\fileUploadTest\\"; //썸네일 저장할 경로
		String name="";
//		userid hidden
		if(!file.getOriginalFilename().isEmpty()) {	//썸네일을 올렸을 때
			file.transferTo(new File(path, file.getOriginalFilename()));
			name = file.getOriginalFilename();
			post.setUploadFileName(name);
			postService.addBoard(post);
			model.addAttribute("post", post);
			return "forward:/post/getBoard.jsp";
		}else { 	//썸네일을 안올렸을 때
			post.setUploadFileName("empty.jpg");
			postService.addBoard(post);
			model.addAttribute("post", post);
			return "forward:/post/getBoard.jsp";
		}
	}
	
	@RequestMapping(value="updateBoard", method=RequestMethod.GET)	//------------------------------------------------테스트 종료
	public String updateBoard(@RequestParam("postNo") int postNo, Model model) throws Exception {
		System.out.println("updateBoard.GET");
		Post post = postService.getBoard(postNo);
		model.addAttribute("post", post);
		return "forward:/post/updateBoard.jsp";
	}
	
	@RequestMapping(value="updateBoard", method=RequestMethod.POST)	//------------------------------------------------테스트 종료
	public String updateBoard(@ModelAttribute("post") Post post, @RequestParam("fileName") MultipartFile file
								, Model model) throws Exception {
		System.out.println("updateBoard.POST");
		String path = "C:\\Users\\User\\Desktop\\fileUploadTest\\"; //썸네일 저장할 경로
		String name="";
		if(!file.getOriginalFilename().isEmpty()) {	//썸네일을 올렸을 때
			file.transferTo(new File(path, file.getOriginalFilename()));
			name = file.getOriginalFilename();
			post.setUploadFileName(name);
			postService.updateBoard(post);
			model.addAttribute("post", post);
			return "forward:/post/getBoard.jsp";
		}else { 	//썸네일을 안올렸을 때
			post.setUploadFileName("empty.jpg");
			postService.updateBoard(post);
			model.addAttribute("post", post);
			return "forward:/post/getBoard.jsp";
			}
		}

	@RequestMapping(value="deleteBoard", method=RequestMethod.GET) //--------------------------------테스트 종료
	public String deleteBoard(@RequestParam("postNo") int postNo, @RequestParam("gatherCategoryNo") String gatherCategoryNo) throws Exception{
		System.out.println("deleteBoard.POST");
		postService.deleteBoard(postNo);
		return "forward:/post/listBoard";
	}
	
	@RequestMapping(value="listBoard")	//----------------------------테스트 종료
	public String getBoardList(@ModelAttribute("search") Search search, @RequestParam("gatherCategoryNo") String gatherCategoryNo,
								Model model) throws Exception{
		System.out.println("getBoardList.POST or GET");
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(5);		//몇개의 게시글을 노출시킬 것?
		
		Map<String, Object> map = postService.getBoardList(search, gatherCategoryNo);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), 3, 5);
		
		model.addAttribute("list", map.get("list"));
		System.out.println(map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("gatherCategoryNo", gatherCategoryNo);
		return "forward:/post/listBoard.jsp";
	}
	
	@RequestMapping(value="getNotice", method=RequestMethod.GET)
	public String getNotice(@RequestParam("postNo") int postNo, Model model) throws Exception {
		System.out.println("getNotice.GET");
		Post post = postService.getNotice(postNo);
		model.addAttribute("post", post);
		return "forward:/post/getNotice.jsp";
	}
	
//	@RequestMapping(value="addBoard", method=RequestMethod.GET)
//	public String addNotice(@RequestParam("gatherCategory") String gatherCategoryNo, Model model) throws Exception{
//		
//		return null;
//	}
}