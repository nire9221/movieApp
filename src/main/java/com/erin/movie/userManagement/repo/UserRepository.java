package com.erin.movie.userManagement.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.erin.movie.userManagement.entity.User;

//실제로 db에 접근하는 객체 
public interface UserRepository extends JpaRepository <User, Long> {
	
	@Query("SELECT a FROM users a WHERE a.username = :username")
	public User findOneByUsername(@Param("username") String username);
	
//public int insertUserInfo(Map<String, String> user) {
//	String sql = "insert into "
//			+ "users(username, password, role, realname, email) "
//			+ "values(?, ?, ?, 'GUEST', ?, ?)";
//	
//	return jt.update(sql, 
//			user.get("user-id"),
//			user.get("user-password"),
//			user.get("user-real-name"),
//			user.get("user-email")
//		);
//}
}
