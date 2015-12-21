package com.cellexperts.dao;

import com.cellexperts.beans.User;

public interface UserDao {
	User findByUserName(String username);


}
