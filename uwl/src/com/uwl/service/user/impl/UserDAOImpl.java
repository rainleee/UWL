package com.uwl.service.user.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.uwl.common.Search;
import com.uwl.service.domain.User;
import com.uwl.service.user.UserDAO;


@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO {

	/// Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/// Constructor
	public UserDAOImpl() {
		System.out.println(this.getClass());
		System.out.println("UserDAOImpl() 객체 생성");
	}

	/// Method
	@Override
	public void addUser(User user) throws Exception {
		System.out.println("UserDAOImpl : addUser() 호출");
		sqlSession.insert("UserMapper.addUser", user);

	}

	@Override
	public void addQuestions(User user) throws Exception {
		System.out.println("UserDAOImpl : addQuestions() 호출");
		sqlSession.insert("UserMapper.addQuestions", user);

	}

	@Override
	public void addRealname(User user) throws Exception {
		System.out.println("UserDAOImpl : addRealname() 호출");
		sqlSession.update("UserMapper.addRealname", user);

	}

	@Override
	public User getUserByMail(String mail) throws Exception {
		System.out.println("UserDAOImpl : getUserByMail() 호출");
		return sqlSession.selectOne("UserMapper.getUserByMail", mail);
	}
	
	@Override
	public User getUser(String userId) throws Exception {
		System.out.println("UserDAOImpl : getUser() 호출");
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	
	@Override
	public User getUserByNickname(String nickname) throws Exception {
		System.out.println("UserDAOImpl : getUserByNickname() 호출");
		return sqlSession.selectOne("UserMapper.getUserByNickname", nickname);
	}

	@Override
	public User getProfile(String userId) throws Exception {
		System.out.println("UserDAOImpl : getProfile() 호출");
		return sqlSession.selectOne("UserMapper.getProfile", userId);
	}

	@Override
	public List<User> getUserQuestions(Search search) throws Exception {
		System.out.println("UserDAOImpl : getUserQuestions() 호출");
		return sqlSession.selectList("UserMapper.getUserQuestions", search);
	}
	
//	@Override
//	public List<User> getUserPostList(Search search) throws Exception {
//		System.out.println("UserDAOImpl : getUserPostList() 호출");
//		return sqlSession.selectList("UserMapper.getUserPostList", search);
//	}
//	
//	@Override
//	public List<User> getUserCommentList(Search search) throws Exception {
//		System.out.println("UserDAOImpl : getUserCommentList() 호출");
//		return sqlSession.selectList("UserMapper.getUserCommentList", search);
//	}
//	
//	@Override
//	public List<User> getUserLikePostList(Search search) throws Exception {
//		System.out.println("UserDAOImpl : getUserLikePostList() 호출");
//		return sqlSession.selectList("UserMapper.getUserLikePostList", search);
//	}

	@Override
	public List<User> getUserList(Search search) throws Exception {
		System.out.println("UserDAOImpl : getUserList() 호출");
		return sqlSession.selectList("UserMapper.getUserList", search);
	}

	@Override
	public List<User> getUserQuestionsList(Search search) throws Exception {
		System.out.println("UserDAOImpl : getUserQuestionsList() 호출");
		return sqlSession.selectList("UserMapper.getUserQuestionsList", search);
	}

//	@Override
//	public List<User> getActivityList(Search search) throws Exception {
//		return sqlSession.selectList("UserMapper.getActivityList", search);
//	}

	@Override
	public void updateUser(User user) throws Exception {
		System.out.println("UserDAOImpl : updateUser() 호출");
		sqlSession.update("UserMapper.updateUser", user);

	}

	@Override
	public void updateProfile(User user) throws Exception {
		System.out.println("UserDAOImpl : updateProfile() 호출");
		sqlSession.update("UserMapper.updateProfile", user);

	}

	@Override
	public void updateQuestions(User user) throws Exception {
		System.out.println("UserDAOImpl : updateQuestions() 호출");
		sqlSession.update("UserMapper.updateQuestions", user);

	}

	@Override
	public void updateSchoolNo(User user) throws Exception {
		System.out.println("UserDAOImpl : updateSchoolNo() 호출");
		sqlSession.update("UserMapper.updateSchoolNo", user);

	}

	@Override
	public void deleteUser(User user) throws Exception {
		System.out.println("UserDAOImpl : deleteUser() 호출");
		sqlSession.update("UserMapper.deleteUser", user);

	}

	@Override
	public User findId(String mail) throws Exception {
		System.out.println("UserDAOImpl : findId() 호출");
		return sqlSession.selectOne("UserMapper.findId", mail);
	}

	@Override
	public User findPw(String userId) throws Exception {
		System.out.println("UserDAOImpl : findPw() 호출");
		return sqlSession.selectOne("UserMapper.findPw", userId);
	}
	
	@Override
	public int getTotalCount(Search search) throws Exception {
		System.out.println("UserDAOImpl : getTotalCount() 호출");
		return sqlSession.selectOne("UserMapper.getTotalCount", search);
	}

}
