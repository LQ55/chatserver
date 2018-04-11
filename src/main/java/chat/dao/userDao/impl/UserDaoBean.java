package chat.dao.userDao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import chat.dao.userDao.UserDao;
import chat.entity.user.User;

@Repository("userDao")
public class UserDaoBean implements UserDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	private static final String namespace = "chat.dao.userDao.UserDao";

	@Override
	public User saveUser(User user) throws Exception {
		String addSql = namespace + ".saveUser";
		sqlSessionTemplate.insert(addSql, user);
		return null;
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserByEid(Long eid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByNo(String no) throws Exception {
		Map paramMap= new HashMap<String, Object>();
		paramMap.put("no",no);
		String selectSql = namespace + ".findUserByNo";
		User user = this.sqlSessionTemplate.selectOne(selectSql,paramMap);
		return user;
	}

	@Override
	public User findUserById(Integer id) throws Exception {
		Map paramMap= new HashMap<String, Object>();
		paramMap.put("id", id);
		String selectSql = namespace + ".findUserById";
		User user = this.sqlSessionTemplate.selectOne(selectSql,paramMap);
		return user;
	}

	@Override
	public User updateUser(User user) throws Exception {
		String addSql = namespace + ".updateUser";
		sqlSessionTemplate.insert(addSql, user);
		return null;
	}

	@Override
	public int getSearchTotalItems(String searchCondition) throws Exception {
		String selectSql = namespace + ".getSearchTotalItems";
		Map paramMap= new HashMap<String, Object>();
		paramMap.put("searchCondition", "%" + searchCondition + "%");
		return this.sqlSessionTemplate.selectOne(selectSql,paramMap);
	}
	
	@Override
	public List<User> getSearchPageList(Integer pageIndex, Integer pageSize,
			String searchCondition) throws Exception {
		String selectSql = namespace + ".getSearchPageList";
		Map paramMap= new HashMap<String, Object>();
		int startIndex = pageSize * (pageIndex - 1);
		paramMap.put("startIndex", startIndex);
		paramMap.put("pageSize", pageSize);
		paramMap.put("searchCondition", "%" + searchCondition + "%");
		return this.sqlSessionTemplate.selectList(selectSql, paramMap);
	}
}
