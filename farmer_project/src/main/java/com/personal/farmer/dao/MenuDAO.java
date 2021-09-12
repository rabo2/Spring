package com.personal.farmer.dao;


import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.MenuVO;


public interface MenuDAO {
	
	List<MenuVO> selectMainMenu() throws SQLException;
	
	List<MenuVO> selectSubMenu(String mCode) throws SQLException;
	
	MenuVO selectMenuByMcode(String mCode) throws SQLException;
	
	MenuVO selectMenuByMname(String mName) throws SQLException;
	
	
}
