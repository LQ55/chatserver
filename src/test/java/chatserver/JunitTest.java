package chatserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chat.dao.userDao.UserDao;
import chat.entity.user.User;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类 
@ContextConfiguration("classpath:applicationContext.xml") 
public class JunitTest {
	@Autowired 
	private UserDao userDao;


	@Test
	public void test(){
	    try {
	    	User u = new User();
	    	u.setNo("23423423");
	    	u.setPwd("3rwerws");
	    	userDao.saveUser(u);
	    	System.out.println("保存成功");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }


	}
}
