package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundIDException;
import kr.or.ddit.request.Criteria;
import kr.or.ddit.request.SearchCriteria;

public interface MemberService {
   
   //로그인
   void login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException;
   
   //회원 정보 조회
   MemberVO getMember(String id) throws SQLException;
   
   //회원 리스트 조회
   List<MemberVO> getMemberList() throws SQLException;
   List<MemberVO> getMemberList(Criteria cri) throws SQLException;
   Map<String, Object> getMemberList(SearchCriteria cri) throws SQLException;
   
   int getSearchMemberListCount(SearchCriteria cri) throws SQLException;
   
   //회원 등록
   public void regist(MemberVO member) throws SQLException;
   
   //회원 수정
   public void modify(MemberVO member) throws SQLException;
   
   //회원 삭제
   public void remove(String id) throws SQLException;
   
   //회원 정지
   public void disabled(String id) throws SQLException;
   
   //회원 활성
   public void enabled(String id) throws SQLException;
}