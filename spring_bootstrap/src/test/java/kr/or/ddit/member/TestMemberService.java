package kr.or.ddit.member;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.dao.MemberDAO;
import kr.or.ddit.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/context/root-context.xml")
@Repository
public class TestMemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	@Test
	public void testSelectMainMenu() throws SQLException{
		String id = "qeqe";
		MemberVO member = memberDAO.selectMemberById(id);
		Assert.assertEquals("qeqe", member.getId());
		
	}
}
