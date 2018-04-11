package chat.dao.tpd.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import chat.dao.tpd.TpdDao;
import chat.entity.user.User;

@Repository("tpdDao")
public class TpdDaoBean implements TpdDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	private static final String namespace = "chat.dao.tpd.TpdDao";

	@Override
	public void saveUser(User user) throws Exception {
		String addSql = namespace + ".saveUser";
		sqlSessionTemplate.insert(addSql, user);
	}

}
