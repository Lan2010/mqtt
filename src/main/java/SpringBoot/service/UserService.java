package SpringBoot.service;

import java.util.List;

import SpringBoot.bean.User;

public interface UserService {
	User getUserById(Integer id);

	public List<User> getUserList();

	public int add(User user);

	public int update(Integer id, User user);

	public int delete(Integer id);

}
