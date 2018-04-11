package chat.service.userService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import chat.entity.user.User;

public interface UserService {
	public Map<String, Object>  saveUser(User user, HttpServletRequest request);
	
	public void deleteUser(Long id, HttpServletRequest request);
	
	public User findUserById(Long id, HttpServletRequest request);

	public User findUserByNo(String no, HttpServletRequest request);

	public Map<String, Object> checkLoginUser(User user,HttpServletRequest request);

	public User findUserById(Integer id, HttpServletRequest request);

	public User updateUser(User user, HttpServletRequest request);

	public void loginOut(HttpServletRequest request);

	public Map<String, Object> changePwd(Integer id, String oldPwd,
			String newPwd, HttpServletRequest request);

	public List<User> getSearchPageList(Integer pageIndex, Integer pageSize,
			String searchCondition, HttpServletRequest request);

	public int getSearchTotalItems(String searchCondition, HttpServletRequest request);

}
