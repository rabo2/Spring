package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.dto.MenuVO;

@Repository
public class MenuDAOImpl implements MenuDAO {
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<MenuVO> selectMainMenu() throws SQLException {
		List<MenuVO> menuList = sqlSession.selectList("Menu-Mapper.selectMainMenu");
		return menuList;
	}

	@Override
	public List<MenuVO> selectSubMenu(String mCode) throws SQLException {
		List<MenuVO> menutList = sqlSession.selectList("Menu-Mapper.selectSubMenu", mCode);
		return menutList;
	}

	@Override
	public MenuVO selectMenuByMcode(String mCode) throws SQLException {
		MenuVO menu = sqlSession.selectOne("Menu-Mapper.selectMenuByMcode", mCode);
		return menu;
	}

	@Override
	public MenuVO selectMenuByMname(String mname) throws SQLException {
		MenuVO menu = sqlSession.selectOne("Menu-Mapper.selectMenuByMname", mname);
		return menu;
	}

}
