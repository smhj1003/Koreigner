package com.koreigner.biz.member.resume;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.koreigner.common.member.FileUtils;

@Service
public class ResumeServiceImpl implements ResumeService {
	
	@Autowired
	private ResumeDAO resumeDAO;
	
	@Autowired
	private FileUtils fileUtils;
	
	//이력서 입력
	@Override
	public void insertResume(Map<String, Object> map, HttpServletRequest request) throws Exception {
		
		String workday = "";
		if(map.get("WORK_TIME_WEEK") instanceof String[]) {
			String[] work_time_week = (String[])map.get("WORK_TIME_WEEK");
			for (int i = 0; i < work_time_week.length; i++) {
				workday += work_time_week[i] + "/";
			}
			
			System.out.println("workday : " + workday);
			map.put("WORK_TIME_WEEK", workday);
		}
		
		resumeDAO.insertResume(map);
		
		if(map.get("JOIN_YEAR") instanceof String[]) {
			String[] join_year = (String[])map.get("JOIN_YEAR");
			String[] join_month = (String[])map.get("JOIN_MONTH");
			String[] resign_year = (String[])map.get("RESIGN_YEAR");
			String[] resign_month = (String[])map.get("RESIGN_MONTH");
			String[] region = (String[])map.get("REGION");
			String[] company = (String[])map.get("COMPANY");
			String[] task = (String[])map.get("TASK");
			
			for(int i=0; i<join_year.length; i++) {
				map.put("JOIN_YEAR", join_year[i]);
				map.put("JOIN_MONTH", join_month[i]);
				map.put("RESIGN_YEAR", resign_year[i]);
				map.put("RESIGN_MONTH", resign_month[i]);
				map.put("REGION", region[i]);
				map.put("COMPANY", company[i]);
				map.put("TASK", task[i]);
				resumeDAO.insertCareer(map);
			}
		} else {
			resumeDAO.insertCareer(map);
		}
		
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size; i++) {
			System.out.println("///////파일 : " + list.get(i));
			resumeDAO.insertFile(list.get(i));
		}
		
	}
	
	//이력서 가져오기
	@Override
	public Map<String, Object> selectResume(String mem_id) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = resumeDAO.selectResume(mem_id);
		System.out.println("====== selectResume : " + tempMap);
		resultMap.put("map", tempMap);
		
		if(tempMap != null) {
			List<Map<String, Object>> careerList = resumeDAO.selectCareerList(tempMap);
			List<Map<String, Object>> fileList = resumeDAO.selectFileList(tempMap);
			System.out.println("====== selectCareer : " + careerList);			
			System.out.println("====== selectFile : " + fileList);			
			
			resultMap.put("careerList", careerList);
			resultMap.put("fileList", fileList);
		}
		return resultMap;
	}
	
	
	//이력서 파일 다운받기위한 정보가져오기
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return resumeDAO.selectFileInfo(map);
	}

	
	//이력서 수정하기
	@Override
	public void updateResume(Map<String, Object> map, HttpServletRequest request) throws Exception {

		String workday = "";
		if(map.get("WORK_TIME_WEEK") instanceof String[]) {
			String[] work_time_week = (String[])map.get("WORK_TIME_WEEK");
			for (int i = 0; i < work_time_week.length; i++) {
				workday += work_time_week[i] + "/";
			}
			
			System.out.println("workday : " + workday);
			map.put("WORK_TIME_WEEK", workday);
		}
		
		resumeDAO.updateResume(map);
		
		resumeDAO.deleteCareer(map);
		
		if(map.get("JOIN_YEAR") instanceof String[]) {
			String[] join_year = (String[])map.get("JOIN_YEAR");
			String[] join_month = (String[])map.get("JOIN_MONTH");
			String[] resign_year = (String[])map.get("RESIGN_YEAR");
			String[] resign_month = (String[])map.get("RESIGN_MONTH");
			String[] region = (String[])map.get("REGION");
			String[] company = (String[])map.get("COMPANY");
			String[] task = (String[])map.get("TASK");
			
			for(int i=0; i<join_year.length; i++) {
				map.put("JOIN_YEAR", join_year[i]);
				map.put("JOIN_MONTH", join_month[i]);
				map.put("RESIGN_YEAR", resign_year[i]);
				map.put("RESIGN_MONTH", resign_month[i]);
				map.put("REGION", region[i]);
				map.put("COMPANY", company[i]);
				map.put("TASK", task[i]);
				resumeDAO.insertCareer(map);
			}
		} else {
			resumeDAO.insertCareer(map);
		}
		
		resumeDAO.deleteFileList(map);
		
		List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(map,request);
		Map<String,Object> tempMap = null;
		for(int i=0; i<list.size(); i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				resumeDAO.insertFile(tempMap);
			} else {
				resumeDAO.updateFile(tempMap);
			}
		}
		
		System.out.println("update file: " + tempMap);
		
		
	}


	

}