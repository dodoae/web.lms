package service;

import java.util.List;

import entity.BoardEntity;
import vo.BoardVO;

public class BoardService {
	private BoardEntity be;
	private BoardVO vo;
	
	public BoardService() {
		be = new BoardEntity();
	}
	public void insertBoard(BoardVO vo) {
		be.insertBoard(vo);
	}
	public int getArticleCount() {
		return be.getArticleCount();
	}
	public List getArticles(int startRow, int endRow) throws Exception {
		return be.getArticles(startRow, endRow);
	}
	public BoardVO getBoard(BoardVO vo) throws Exception {
		return be.getBoard(vo);
	}
	public BoardVO updateGetBoard(BoardVO vo) throws Exception {
		return be.updateGetBoard(vo);
	}
	public void updateBoard(BoardVO vo) throws Exception {
		be.updateBoard(vo);
	}
	public void deleteBoard(BoardVO vo) {
		be.deleteBoard(vo);
	}
}
