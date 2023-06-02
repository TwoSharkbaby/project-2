package com.twosharkbaby.www.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twosharkbaby.www.domain.Board;
import com.twosharkbaby.www.domain.BoardType;
import com.twosharkbaby.www.domain.BoardUpDown;
import com.twosharkbaby.www.domain.RoleType;
import com.twosharkbaby.www.repository.BoardRepository;
import com.twosharkbaby.www.repository.BoardUpDownRepository;
import com.twosharkbaby.www.repository.ReplyRepository;
import com.twosharkbaby.www.repository.ReplyUpDownRepository;
import com.twosharkbaby.www.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardUpDownRepository boardUpDownRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private ReplyUpDownRepository replyUpDownRepository;
	
	@Transactional(readOnly = true)
	public List<Board> notice() {
		return boardRepository.notice();
	}
	
	@Transactional(readOnly = true)
	public Page<Board> findAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional
	public Board save(Board board) {
		if (board.getRole() == RoleType.ROLE_ADMIN) {
			board.setType(BoardType.공지);
			return boardRepository.save(board);
		} else {
			board.setType(BoardType.일반);
			return boardRepository.save(board);
		}
	}
	
	@Transactional
	public Board findByIdWithCounting(Long id) {
		boardRepository.upCount(id); 
		return boardRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Board findById(Long id) {
		return boardRepository.findById(id);
	}
	
	@Transactional
	public Board update(Board board) {
		Board tmp = boardRepository.findById(board.getId());
		tmp.setTitle(board.getTitle());
		tmp.setContent(board.getContent());
		return boardRepository.save(tmp);
	}
	
	@Transactional
	public Long deleteById(Long id) {
		replyRepository.findByBoardId(id).forEach(reply -> {
			replyUpDownRepository.deleteByReplyId(reply.getId());
		});
		replyRepository.deleteByBoardId(id);
		boardUpDownRepository.deleteByBoardId(id);
		return boardRepository.deleteById(id);
	}
	
	@Transactional  
	public Board up(BoardUpDown boardUpDown) { // 좋아요 1 싫어요2
		BoardUpDown tmp = boardUpDownRepository.findByBoardIdAndUserId(boardUpDown.getBoard().getId(), boardUpDown.getUser().getId());
		boardUpDown.setUpDown(1L);
		if(tmp == null) {
			boardRepository.upGood(boardUpDown.getBoard().getId()); 
			boardUpDownRepository.save(boardUpDown);
		} else if (tmp.getUpDown() == 1L) {
			boardRepository.downGood(boardUpDown.getBoard().getId());
			boardUpDownRepository.deleteById(tmp.getId());
		} else if (tmp.getUpDown() == 2L) {
			boardRepository.downBad(boardUpDown.getBoard().getId());
			boardRepository.upGood(boardUpDown.getBoard().getId()); 
			boardUpDownRepository.deleteById(tmp.getId());
			boardUpDownRepository.save(boardUpDown);
		}
		return boardRepository.findById(boardUpDown.getBoard().getId());
	}
	
	@Transactional  
	public Board down(BoardUpDown boardUpDown) { // 좋아요 1 싫어요2
		BoardUpDown tmp = boardUpDownRepository.findByBoardIdAndUserId(boardUpDown.getBoard().getId(), boardUpDown.getUser().getId());
		boardUpDown.setUpDown(2L);
		if(tmp == null) {
			boardRepository.upBad(boardUpDown.getBoard().getId());
			boardUpDownRepository.save(boardUpDown);
		} else if (tmp.getUpDown() == 2L) {
			boardRepository.downBad(boardUpDown.getBoard().getId());
			boardUpDownRepository.deleteById(tmp.getId());
		} else if (tmp.getUpDown() == 1L) {
			boardRepository.downGood(boardUpDown.getBoard().getId());
			boardRepository.upBad(boardUpDown.getBoard().getId());
			boardUpDownRepository.deleteById(tmp.getId());
			boardUpDownRepository.save(boardUpDown);
		}
		return boardRepository.findById(boardUpDown.getBoard().getId());
	}
}
