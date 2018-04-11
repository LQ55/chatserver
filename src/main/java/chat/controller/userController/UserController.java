package chat.controller.userController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import chat.entity.user.User;
import chat.service.userService.UserService;

@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("/userController")
public class UserController { 
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/saveUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
	public Map<String, Object> saveUser(@RequestBody User user, HttpServletRequest request){
		return userService.saveUser(user,request);
	}
	
	@RequestMapping(value="/findUserByNo",method = RequestMethod.POST)
    @ResponseBody
	public User findUserByNo(@RequestBody String loginNo, HttpServletRequest request){
		return this.userService.findUserByNo(loginNo,request);
	}
	
	@RequestMapping(value="/checkLoginUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
	public Map<String, Object> checkLoginUser(@RequestBody User user, HttpServletRequest request){
		return this.userService.checkLoginUser(user,request);
	}
	
	@RequestMapping(value="/findUserById",method = RequestMethod.POST)
    @ResponseBody
	public User findUserById(@RequestParam("id") Integer id, HttpServletRequest request) {
		return this.userService.findUserById(id,request);
	}
	
	@RequestMapping(value="/updateUser",method = RequestMethod.POST)
    @ResponseBody
	public User updateUser(@RequestBody User user, HttpServletRequest request){
		userService.updateUser(user,request);
		return user;
	}
	
	@RequestMapping(value="/loginOut",method = RequestMethod.POST)
    @ResponseBody
	public void loginOut(HttpServletRequest request){
		this.userService.loginOut(request);
	}
	
	@RequestMapping(value="/changePwd",method = RequestMethod.POST)
    @ResponseBody
	public Map<String,Object> changePwd(@RequestParam("id") Integer id,@RequestParam("oldPwd") String oldPwd,@RequestParam("newPwd") String newPwd, HttpServletRequest request) {
		return this.userService.changePwd(id,oldPwd,newPwd,request);
	}
	
    @RequestMapping(value="/getSearchPageList",method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> getSearchPageList(@RequestParam(value="pageIndex") Integer pageIndex,@RequestParam(value="pageSize") Integer pageSize,@RequestParam(value="searchCondition") String searchCondition, HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("totalItems", this.userService.getSearchTotalItems(searchCondition, request));
    	resultMap.put("pageList", this.userService.getSearchPageList(pageIndex, pageSize, searchCondition, request));
    	return resultMap;
    }
    
    @RequestMapping(value="/getSearchTotalItems",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public int getSearchTotalItems(@RequestParam(value="searchCondition") String searchCondition, HttpServletRequest request){
    	return this.userService.getSearchTotalItems(searchCondition, request);
    }
    
}
