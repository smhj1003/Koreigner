package com.koreigner.biz.inform;

import java.util.List;





public interface InfoBoardService {
  
	void insertInfoBoard(InfoBoardVO vo);
	void updateInfoBoard(InfoBoardVO vo);
	void deleteInfoBoard(InfoBoardVO vo);
	InfoBoardVO getInfoBoard(InfoBoardVO vo);
	List<InfoBoardVO> getInfoBoardList(InfoBoardVO vo);
	void updateHit(int info_idx);

	List<InfoBoardVO> getInfoBoardList();
	int getBoardListCnt();
		
	

}
