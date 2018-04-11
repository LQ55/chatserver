package chat.dao.tpd;

import chat.entity.user.User;

public interface TpdDao {
	public void saveUser(User user) throws Exception;
}
