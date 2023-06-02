package com.twosharkbaby.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twosharkbaby.www.domain.Board;
import com.twosharkbaby.www.service.BoardService;
import com.twosharkbaby.www.service.ReplyService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private ReplyService replyService;

	@GetMapping("/board")
	public String board(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("notice", boardService.notice());
		model.addAttribute("board", boardService.findAll(pageable));
		return "/board/board";
	}

	@GetMapping("/auth/board/saveForm")
	public String saveForm() {
		return "/board/saveForm";
	}

	@PostMapping("/auth/board/save")
	public String save(Board board, RedirectAttributes rtts) {
		if (boardService.save(board) != null) {
			rtts.addFlashAttribute("result", "게시글 등록 성공");
			return "redirect:/board";
		} else {
			rtts.addFlashAttribute("result", "게시글 등록 실패");
			return "redirect:/auth/board/saveForm";
		}
	}

	@GetMapping("/board/detail/{id}")
	public String detail(@PathVariable Long id, Model model,
			@PageableDefault(page = 0, size = 10) @SortDefaults({
					@SortDefault(sort = "grp", direction = Sort.Direction.DESC),
					@SortDefault(sort = "seq", direction = Sort.Direction.ASC) }) Pageable pageable) {
		model.addAttribute("board", boardService.findByIdWithCounting(id));
		model.addAttribute("count", replyService.findByCount(id));
		model.addAttribute("reply", replyService.findByBoardIdWithPage(id, pageable));
		return "/board/detail";
	}

	@PreAuthorize("principal.user.id == #userId")
	@GetMapping("/auth/board/updateForm/{id}/{userId}")
	public String updateForm(@PathVariable Long id, @PathVariable Long userId, Model model) {
		model.addAttribute("board", boardService.findById(id));
		return "/board/updateForm";
	}

	@PreAuthorize("principal.user.id == #board.user.id")
	@PostMapping("/auth/board/update")
	public String update(Board board, RedirectAttributes rtts) {
		if (boardService.update(board) != null) {
			rtts.addFlashAttribute("result", "게시글 수정 성공");
			return "redirect:/board/detail/" + board.getId();
		} else {
			rtts.addFlashAttribute("result", "게시글 수정 실패");
			return "redirect:/auth/board/updateForm/" + board.getId();
		}
	}

}
