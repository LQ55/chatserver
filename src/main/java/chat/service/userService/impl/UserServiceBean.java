package chat.service.userService.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import chat.dao.userDao.UserDao;
import chat.entity.user.User;
import chat.service.userService.UserService;
import chat.util.Md5;

@Service("userService")
@SessionAttributes({"loginNo"}) 
public class UserServiceBean implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public Map<String, Object> saveUser(User user, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			user.setEid(System.currentTimeMillis());
			User existUser = this.userDao.findUserByNo(user.getNo());
			if(existUser==null){
				//登陆账号也就是电话
				//user.setTel(user.getLoginNo());
				user.setEid(System.currentTimeMillis());
				userDao.saveUser(user);
				resultMap.put("state", "success");
				resultMap.put("success", "注册成功！");
			}else{
				resultMap.put("state", "error");
				resultMap.put("error", "该登陆账号已经使用！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	/**
	 * 验证用户，用户登陆
	 */
	@Override
	public Map<String, Object> checkLoginUser(User user,HttpServletRequest request){
		Map<String, Object> mesMap = new HashMap<String, Object>();
		User extU = this.findUserByNo(user.getNo(),request);
		if(extU == null){
			mesMap.put("mes", "用户名不存在");
			mesMap.put("state", false);
		}
		else if((Md5.getMd5(extU.getPwd())).equals(Md5.getMd5(user.getPwd()))){
			HttpSession session = request.getSession(true);
			session.setAttribute("user", extU);
			//sesison失效时间30分钟
			session.setMaxInactiveInterval(30*60);
			//处理密码
			extU.setPwd("************");
			//当前用户
			mesMap.put("mes", extU);
			//设置登陆状态
			mesMap.put("state", true);
		}
		else{
			mesMap.put("mes", "密码错误");
			mesMap.put("state", false);
		}
		return mesMap;
	}

	@Override
	public User findUserById(Integer id, HttpServletRequest request) {
		try {
			return this.userDao.findUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	

	@Override
	public User updateUser(User user, HttpServletRequest request) {
		try {
			userDao.updateUser(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteUser(Long id, HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUserById(Long id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByNo(String no, HttpServletRequest request){
		try {
			return this.userDao.findUserByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * TODO:简略描述方法需要做什么
	 * @return void
	 * @author AbnerLi
	 * @time 2017年12月18日
	 */
	@Override
	public void loginOut(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.invalidate();
	}

	/**
	 * TODO:修改密码
	 * @return Map<String,Object>
	 * @author AbnerLi
	 * @time 2018年1月18日
	 */
	@Override
	public Map<String, Object> changePwd(Integer id, String oldPwd,
			String newPwd, HttpServletRequest request) {
		Map<String, Object> mesMap = new HashMap<String, Object>();
		try {
			User user = this.userDao.findUserById(id);
			if(user!=null){
				if(Md5.getMd5(user.getPwd()).equals(oldPwd)){
					user.setPwd(newPwd);
					this.userDao.updateUser(user);
					mesMap.put("state", true);
					mesMap.put("mes", "密码修改成功");
				}
				else{
					mesMap.put("state", false);
					mesMap.put("mes", "原密码输入错误");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mesMap;
	}
	
	@Override
	public List<User> getSearchPageList(Integer pageIndex,
			Integer pageSize, String searchCondition, HttpServletRequest request){
		try {
			return this.userDao.getSearchPageList(pageIndex, pageSize, searchCondition);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int getSearchTotalItems(String searchCondition, HttpServletRequest request){
		try {
			int totalItems = this.userDao.getSearchTotalItems(searchCondition);
			return totalItems;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	
}
