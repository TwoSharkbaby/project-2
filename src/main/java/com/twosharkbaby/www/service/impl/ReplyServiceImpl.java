package com.twosharkbaby.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twosharkbaby.www.domain.Reply;
import com.twosharkbaby.www.domain.ReplyUpDown;
import com.twosharkbaby.www.repository.ReplyRepository;
import com.twosharkbaby.www.repository.ReplyUpDownRepository;
import com.twosharkbaby.www.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private ReplyUpDownRepository replyUpDownRepository;
	
	@Transactional(readOnly = true)
	public Long findByCount(Long id) {
		return replyRepository.findByCount(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Reply> findByBoardIdWithPage(Long id, Pageable pageable) {
		return replyRepository.findByBoardId(id, pageable);
	}
	
	@Transactional
	public Reply save(Reply reply) {
		Long replyId = reply.getId();
		if(replyId != null) { // 답글
			Reply origin = replyRepository.findById(reply.getId());
			Long seq = replyRepository.getAvailableSeq(origin); // 위치 찾기
			if(seq == null) { // 위치를 못찾으면 해당 그룹의 마지막에 추가하기 위해 카운트
				seq = replyRepository.countByGrp(origin.getGrp());
			} else { // 위치를 찾으면 해당 위치 이상의 값들을 증가
				replyRepository.increaseSequence(Reply.builder().grp(origin.getGrp()).seq(seq).build());
			}
			reply.setId(null); // 번호 초기화
			reply.setGrp(origin.getGrp()); // 그룹 유지
			reply.setSeq(seq); // 계산된 seq
			reply.setDep(origin.getDep()+1); // 차수 증가
		} 
		
		Reply result = replyRepository.save(reply);
		
		if(replyId == null) { // 새글
			result.setGrp(result.getId());
			replyRepository.save(result);
		}
		
		return result;
	}
	
	@Transactional
	public Integer update(Reply reply) {
		return replyRepository.update(reply);
	}
	
	@Transactional
	public Long deleteById(Long id) {
		replyUpDownRepository.deleteByReplyId(id);
		return replyRepository.deleteById(id);
	}
	
	@Transactional  
	public Reply up(ReplyUpDown replyUpDown) { // 좋아요 1 싫어요2
		ReplyUpDown tmp = replyUpDownRepository.findByReplyIdAndUserId(replyUpDown.getReply().getId(), replyUpDown.getUser().getId());
		replyUpDown.setUpDown(1L);
		if(tmp == null) {
			replyRepository.upGood(replyUpDown.getReply().getId()); 
			replyUpDownRepository.save(replyUpDown);
		} else if (tmp.getUpDown() == 1L) {
			replyRepository.downGood(replyUpDown.getReply().getId());
			replyUpDownRepository.deleteById(tmp.getId());
		} else if (tmp.getUpDown() == 2L) {
			replyRepository.downBad(replyUpDown.getReply().getId());
			replyRepository.upGood(replyUpDown.getReply().getId()); 
			replyUpDownRepository.deleteById(tmp.getId());
			replyUpDownRepository.save(replyUpDown);
		}
		return replyRepository.findById(replyUpDown.getReply().getId());
	}
	
	@Transactional  
	public Reply down(ReplyUpDown replyUpDown) { // 좋아요 1 싫어요2
		ReplyUpDown tmp = replyUpDownRepository.findByReplyIdAndUserId(replyUpDown.getReply().getId(), replyUpDown.getUser().getId());
		replyUpDown.setUpDown(2L);
		if(tmp == null) {
			replyRepository.upBad(replyUpDown.getReply().getId());
			replyUpDownRepository.save(replyUpDown);
		} else if (tmp.getUpDown() == 2L) {
			replyRepository.downBad(replyUpDown.getReply().getId());
			replyUpDownRepository.deleteById(tmp.getId());
		} else if (tmp.getUpDown() == 1L) {
			replyRepository.downGood(replyUpDown.getReply().getId());
			replyRepository.upBad(replyUpDown.getReply().getId());
			replyUpDownRepository.deleteById(tmp.getId());
			replyUpDownRepository.save(replyUpDown);
		}
		return replyRepository.findById(replyUpDown.getReply().getId());
	}
	
}
