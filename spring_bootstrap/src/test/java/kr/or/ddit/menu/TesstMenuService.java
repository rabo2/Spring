package kr.or.ddit.menu;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.dao.MenuDAO;
import kr.or.ddit.dto.MenuVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/context/root-context.xml")
public class TesstMenuService {
	@Autowired
	private MenuDAO menuDAO;

	@Test
	public void testGetMenuList() throws SQLException{
		List<MenuVO> menuList =null;
		try {
			menuList = menuDAO.selectMainMenu();
		}catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(5, menuList.size());
	}
	
	@Test
	public void testGetSubMenuList() throws SQLException{
		String mCode = "M010100";
		
		List<MenuVO> subMenuList = menuDAO.selectSubMenu(mCode);
		
		for (MenuVO menuVO : subMenuList) {
			System.out.println(menuVO.getMname());
		}
	}
	@Test
	public void testGetSubList() throws SQLException{
		String mCode = "M010100";
		
		MenuVO menu = menuDAO.selectMenuByMcode(mCode);
		
		Assert.assertEquals("회원목록", menu.getMname());
	}
	
} 
