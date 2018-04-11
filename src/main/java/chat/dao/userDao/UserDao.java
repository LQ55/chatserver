package chat.dao.userDao;

import java.util.List;

import chat.entity.user.User;

public interface UserDao {
	public User saveUser(User user) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
	
	public User findUserByEid(Long eid) throws Exception;

	public User findUserByNo(String no) throws Exception;

	public User findUserById(Integer id) throws Exception;

	public User updateUser(User user) throws Exception;

	public int getSearchTotalItems(String searchCondition) throws Exception;

	public List<User> getSearchPageList(Integer pageIndex, Integer pageSize,
			String searchCondition) throws Exception;
}
