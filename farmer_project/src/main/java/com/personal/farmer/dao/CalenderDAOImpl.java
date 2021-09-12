package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.CalenderVO;

public class CalenderDAOImpl implements CalenderDAO{
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<CalenderVO> selectCalenderAllById(String mem_id) throws SQLException {
		List<CalenderVO> calList = sqlSession.selectList("Calender-Mapper.selectCalenderAllById", mem_id);
		return calList;
	}
	
	@Override
	public List<CalenderVO> selectCalenderByDateAndMemId(CalenderVO vo) throws SQLException {
		List<CalenderVO> calList = sqlSession.selectList("Calender-Mapper.selectCalenderByDateAndMemId", vo);
		return calList;
	}

	@Override
	public void insertCalender(CalenderVO vo) throws SQLException {
		sqlSession.insert("Calender-Mapper.insertCalender", vo);
	}

	@Override
	public void updateCalender(CalenderVO vo) throws SQLException {
		sqlSession.update("Calender-Mapper.updateCalender", vo);
	}

	@Override
	public void deleteCalender(CalenderVO vo) throws SQLException {
		sqlSession.delete("Calender-Mapper.deleteCalender", vo);
	}



}
