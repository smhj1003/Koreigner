package com.koreigner.biz.job.company;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.koreigner.biz.job.hire.HireVO;

public interface CompanyService {
	List<CompanyVO> getCateList();
	List<CompanyVO> getCateChild(String cate_kor);
	List<CompanyVO> getCateEnChild(String cate_en);
	List<CompanyVO> getAddrDoList();
	List<CompanyVO> getAddrSiList(String si_kor);
	List<CompanyVO> getChildCate(String cate_prnt_en);
	CompanyVO getCateEn(Map<String, String> map);
	CompanyVO companyDetailOne(String mem_id);
	void comJoin(CompanyVO vo, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;
	
	
}
