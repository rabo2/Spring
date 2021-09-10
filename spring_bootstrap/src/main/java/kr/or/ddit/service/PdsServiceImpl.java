package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.dao.AttachDAO;
import kr.or.ddit.dao.PdsDAO;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.request.PageMaker;
import kr.or.ddit.request.SearchCriteria;

public class PdsServiceImpl implements PdsService {

	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private PdsDAO pdsDAO;

	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}

	private AttachDAO attachDAO;

	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}

	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			List<PdsVO> pdsList = pdsDAO.selectPdsCriteria(session, cri);

			if (pdsList != null) for (PdsVO pds : pdsList) {
					addAttachList(session, pds);
			}

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(pdsDAO.selectPdsCriteriaTotalCount(session, cri));

			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("pdsList", pdsList);
			dataMap.put("pageMaker", pageMaker);

			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public PdsVO getPds(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			PdsVO pds = pdsDAO.selectPdsByPno(session, pno);
			addAttachList(session, pds);

			return pds;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(PdsVO pds) throws SQLException { // pds에는 AttachList가 들어가 있음 / pno가 없음
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int pno = pdsDAO.getSeqNextValue(session);
			// pno를 심어주는 작업을 해야함

			pds.setPno(pno);
			pdsDAO.insertPds(session, pds);

			if (pds.getAttachList() != null)
				for (AttachVO attach : pds.getAttachList()) {
					attach.setPno(pno);
					attach.setAttacher(pds.getWriter());
					attachDAO.insertAttach(session, attach);
				}

		} finally {
			session.close();
		}
	}

	@Override
	public void modify(PdsVO pds) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
	
		try {
			pdsDAO.updatePds(session, pds);
			// attachDAO.deleteAllAttach(pds.getPno());
			
			if (pds.getAttachList() != null)
				for (AttachVO attach : pds.getAttachList()) {
					attach.setPno(pds.getPno());
					attach.setAttacher(pds.getWriter());
					attachDAO.insertAttach(session, attach);
				}
			
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
	
		try {
			pdsDAO.deletePds(session, pno);
			attachDAO.deleteAllAttach(session, pno);
		} finally {
			session.close();
		}
	}

	@Override
	public PdsVO read(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			PdsVO pds = pdsDAO.selectPdsByPno(session, pno);
			pdsDAO.increaseViewCnt(session, pno);

			addAttachList(session, pds);

			return pds;
		} finally {
			session.close();
		}
	}

	@Override
	public AttachVO getAttachByAno(int ano) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			AttachVO attach = attachDAO.selectAttachByAno(session, ano);
			return attach;
		}finally {
			session.close();
		}
	}

	@Override
	public void removeAttachByAno(int ano) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			attachDAO.deleteAttach(session, ano);
		}finally {
			session.close();
		}
	}

	private void addAttachList(SqlSession session, PdsVO pds) throws SQLException {
		// 이게 계속 쓰이기때문에 private으로 하나 만들어놨다.
		if (pds == null) return; // 쿼리문으로 짜면 innerView

		int pno = pds.getPno();
		List<AttachVO> attachList = attachDAO.selectAttachesByPno(session, pno);

		pds.setAttachList(attachList);

	}

}