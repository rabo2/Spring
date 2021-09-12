package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;

import com.personal.farmer.dto.CalenderVO;

public interface CalenderService {
	
	List<CalenderVO> selectCalenderAllById(String mem_id) throws SQLException;

	List<CalenderVO> selectCalenderByDateAndMemId(CalenderVO vo) throws SQLException;
	
	void insertCalender(CalenderVO vo) throws SQLException;

	void updateCalender(CalenderVO vo) throws SQLException;
	
	void deleteCalender(CalenderVO vo) throws SQLException;
}
