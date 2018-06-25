package SpringBoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot.bean.User;
import SpringBoot.mapper.UserMapper;
import SpringBoot.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	public List<User> getUserList() {
		return userMapper.getUserList();
	}

	public int add(User user) {
		return userMapper.add(user);
	}

	public int update(Integer id, User user) {
		return userMapper.update(id, user);
	}

	public int delete(Integer id) {
		return userMapper.delete(id);
	}
	
	
}
