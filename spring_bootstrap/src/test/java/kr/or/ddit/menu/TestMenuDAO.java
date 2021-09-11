package kr.or.ddit.menu;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.MenuVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/context/root-context.xml")
@Repository
public class TestMenuDAO {
	@Autowired 
	SqlSession session;

	@Test
	public void testSqlSession() throws SQLException{
	Collection<String> mapNames = session.getConfiguration().getMappedStatementNames();
		
		Assert.assertTrue(mapNames.contains("Member-Mapper.selectMemberById"));
	}
	
	@Test
	public void testGetMember() throws SQLException{
		String id = "qeqe";
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById",id);
		Assert.assertEquals("qeqe", member.getId());
	}
}
