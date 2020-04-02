package com.koreigner.biz.job.company;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreigner.biz.job.hire.HireVO;

@Repository
public class CompanyDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	List<CompanyVO> getCateList(){
		List<CompanyVO> list = mybatis.selectList("getCateList");
		return list;
	}
	
	List<CompanyVO> getCateChild(String cate_kor){
		List<CompanyVO> list = mybatis.selectList("getCateChild", cate_kor);
		return list;
	}
	
	List<CompanyVO> getAddrDoList(){
		List<CompanyVO> list = mybatis.selectList("getAddrDoList");
		return list;
	}
	
	List<CompanyVO> getAddrSiList(String si_kor){
		List<CompanyVO> list = mybatis.selectList("getAddrSiList", si_kor);
		return list;
	}
	
	CompanyVO getCateEn(Map<String, String> map) {
		CompanyVO outVO = mybatis.selectOne("getCateEn", map);
		return outVO;
	}
	
	void comJoin(CompanyVO vo) {
		mybatis.insert("comJoin", vo);
	}
	
	String getComName(String mem_id) {
		String companyName = mybatis.selectOne("getComName", mem_id);
		return companyName;
	}
	
	CompanyVO companyDetailOne(String mem_id) {
		CompanyVO companyVO = mybatis.selectOne("companyDetailOne", mem_id);
		return companyVO;
	}
	
	List<CompanyVO> getChildCate(String cate_prnt_en) {
		List<CompanyVO> companyVO = mybatis.selectList("getChildCate", cate_prnt_en);
		return companyVO;
	}

	public List<CompanyVO> getCateEnChild(String cate_en) {
		List<CompanyVO> list = mybatis.selectList("getCateEnChild", cate_en);
		return list;
	}
}
