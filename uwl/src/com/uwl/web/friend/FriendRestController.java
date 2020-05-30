package com.uwl.web.friend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uwl.service.domain.Friend;
import com.uwl.service.domain.User;
import com.uwl.service.friend.FriendService;

@RestController
@RequestMapping("/friend/*")
public class FriendRestController {

	@Autowired
	private FriendService friendService;

	@RequestMapping(value = "json/requestFriend", method = RequestMethod.POST)
	public Map requestFriend(@RequestBody Friend friend) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		friendService.requestFriend(friend);
		map.put("success", true);
		return map;
	}

	@RequestMapping(value = "json/acceptFriend", method = RequestMethod.POST)
	public Map acceptFriend(@RequestBody Friend friend) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		friendService.acceptFriend(friend);
		map.put("success", true);
		return map;
	}

	@RequestMapping(value = "json/deleteFriend", method = RequestMethod.POST)
	public Map deleteFriend(@RequestBody Friend friend) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		friendService.deleteFriend(friend);
		map.put("success", true);

		return map;
	}

	@RequestMapping(value = "json/getAskedList", method = RequestMethod.POST)
	public Map getAskedList(@RequestBody User user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", friendService.getAskedList(user.getUserId()));

		return map;
	}
}
