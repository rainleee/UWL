package com.uwl.service.report.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.uwl.common.Search;
import com.uwl.service.domain.Report;
import com.uwl.service.report.ReportDAO;

@Repository("reportDAOImpl")
public class ReportDAOImpl implements ReportDAO{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ReportDAOImpl() {
		super();
	}

	@Override
	public Report getReport(int reportNo) throws Exception {
		return sqlSession.selectOne("ReportMapper.getReport", reportNo);
	}

	@Override
	public void addPostReport(Report report) throws Exception {
		sqlSession.insert("ReportMapper.addPostReport", report);
	}
	
	@Override
	public void addCommentReport(Report report) throws Exception {
		sqlSession.insert("ReportMapper.addCommentReport", report);
	}

	@Override
	public void updateReport(Report report) throws Exception {
		sqlSession.update("ReportMapper.updateReport", report);
	}

	@Override
	public List<Report> getReportList(Search search, Report report) throws Exception {
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("search", search);
		map.put("report", report);
		return sqlSession.selectList("ReportMapper.getReportList",map);
	}

	
	
	
	
	
	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ReportMapper.getTotalCount",search);
	}
	
}
