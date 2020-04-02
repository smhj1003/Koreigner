package com.koreigner.view.inform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;
import com.koreigner.biz.common.page.PagingVO;
import com.koreigner.biz.inform.InfoBoardListVO;
import com.koreigner.biz.inform.InfoBoardService;
import com.koreigner.biz.inform.InfoBoardVO;






@Controller
@SessionAttributes("infoBoard")
public class InfoBoardController {
	
	@Autowired
	private InfoBoardService infoBoardService;


	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		//key: 제목, value: TITLE
		//key: 내용, value: CONTENT
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("제목", "INO_TITLE");
		conditionMap.put("내용", "INO_CONTENT");
		
		return conditionMap;
	}
	
	
	
	@RequestMapping("/getInfoBoardList.do")
	public String getInfoBoardList(InfoBoardVO vo, Model model) { 
		System.out.println(">>> 글 전체 목록 조회 처리-getInfoBoardList()");
		System.out.println("condition : " +vo.getSearchCondition());
		System.out.println("keyword : " + vo.getSearchKeyword());
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("INFO_TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		System.out.println("null처리후 condition : " + vo.getSearchCondition());
		System.out.println("null처리후 keyword : -" + vo.getSearchKeyword() + "-");
		
		List<InfoBoardVO> infoBoardList = infoBoardService.getInfoBoardList(vo);
		model.addAttribute("infoBoardList",infoBoardList);
	
		return "/getInfoBoardList.jsp";
	}
	
	
	@RequestMapping("/getInfoBoard.do")
	public String getInfoBoard (InfoBoardVO vo, Model model) {
		System.out.println(">>> 글 상세 조회 처리 - getInfoBoard()");
		
		InfoBoardVO infoBoard = infoBoardService.getInfoBoard(vo);
		infoBoardService.updateHit(vo.getInfo_idx());
		System.out.println("vo.getInfo_hit"+vo.getInfo_idx());
		model.addAttribute("infoBoard",infoBoard);
		System.out.println("> board : " + infoBoard);
		System.out.println("vo.getInfo_hit"+vo.getInfo_idx());
		
		return "getInfoBoard.jsp";
	}
	
	@RequestMapping("/insertInfoBoard.do")
	public String insertBoard(InfoBoardVO vo) 
			throws IllegalStateException, IOException {
		System.out.println(">>> 글 등록 처리 - insertInfoBoard()"+vo);
		
	
		MultipartFile uploadFile = vo.getUploadFile();
		System.out.println("uploadFile : " + uploadFile);
		
		if (!uploadFile.isEmpty()) {//파일이 있으면(비어있지 않으면)
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("c:/MyStudy/temp/" + fileName));
		}
		
		infoBoardService.insertInfoBoard(vo);
		return "getInfoBoardList.do";
	}
	
	@RequestMapping("/updateInfoBoard.do")
	public String updateBoard(@ModelAttribute("board") InfoBoardVO vo) {
		System.out.println(">>> 글 수정 처리 - updateInfoBoard()");
		System.out.println("> board vo : " + vo);
		
		infoBoardService.updateInfoBoard(vo);
		return "getInfoBoardList.do";
	}
	
	@RequestMapping("/deleteInfoBoard.do")
	public String deleteBoard(InfoBoardVO vo) {
		System.out.println(">>> 글 삭제 처리 - deleteInfoBoard()");
		
		infoBoardService.deleteInfoBoard(vo);
		return "getInfoBoardList.do";
	}
	
	//-------------------------------
	@RequestMapping("/dataTransform.do")
	@ResponseBody //응답객체의 몸체 담아서 전달
	public InfoBoardListVO dataTransform(InfoBoardVO vo) {
		System.out.println(">>> dataTransform() 실행(XML)");
		vo.setSearchCondition("INFO_TITLE");
		vo.setSearchKeyword("");
		
		List<InfoBoardVO> infoBoardList = infoBoardService.getInfoBoardList(vo);
		InfoBoardListVO infoBoardListVO = new InfoBoardListVO();
		infoBoardListVO.setBoardList(infoBoardList);
		
		return infoBoardListVO;
	}
	
	@RequestMapping(value = "fileupload.do", method = RequestMethod.POST)
	   @ResponseBody
	   public String fileUpload(HttpServletRequest req, HttpServletResponse resp, MultipartHttpServletRequest multiFile)
	         throws Exception {
	      JsonObject json = new JsonObject();
	      PrintWriter printWriter = null;
	      OutputStream out = null;
	      MultipartFile file = multiFile.getFile("upload");
	      if (file != null) {
	         if (file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
	            if (file.getContentType().toLowerCase().startsWith("image/")) {
	               try {
	                  String fileName = file.getName();
	                  byte[] bytes = file.getBytes();

	                  // 경로설정
	                  String uploadPath = req.getServletContext().getRealPath("/img");
	                  System.out.println(uploadPath);

	                  // 파일저장
	                  File uploadFile = new File(uploadPath);

	                  // 디렉토리 없다면 생성
	                  if (!uploadFile.exists()) {
	                     uploadFile.mkdirs();
	                  }

	                  // 이름에 유일한 식별자 생성
	                  fileName = UUID.randomUUID().toString();
	                  uploadPath = uploadPath + "/" + fileName;
	                  // 파일 저장
	                  out = new FileOutputStream(new File("C:/MyStudy/70_Spring/BITCAMP_3rd_Project/Koreigners/src/main/webapp/resources/img" + fileName));
	                  out.write(bytes);
	                  out = new FileOutputStream(new File(uploadPath));
	                  out.write(bytes);


	                  printWriter = resp.getWriter();
	                  resp.setContentType("text/html");
	                  String fileUrl = req.getContextPath() + "/img/" + fileName;

	                  System.out.println(req.getContextPath() + "/img/" + fileName);

	                  // json 데이터로 등록
	                  // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
	                  // 이런 형태로 리턴이 나가야함.

	                  json.addProperty("uploaded", 1);
	                  json.addProperty("fileName", fileName);
	                  json.addProperty("url", fileUrl);

	                  System.out.println(fileUrl);
	                  System.out.println(fileName);

	                  printWriter.println(json);
	               } catch (IOException e) {
	                  e.printStackTrace();
	               } finally {
	                  if (out != null) {
	                     out.close();
	                  }
	                  if (printWriter != null) {
	                     printWriter.close();
	                  }

	               }
	            }
	         }
	      }
	      return null;
	   }

	
	
}
