package com.twosharkbaby.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twosharkbaby.www.domain.Filmmaker;
import com.twosharkbaby.www.service.FilmmakerService;

@Controller
public class FilmmakerController {
	
	@Autowired
	private FilmmakerService filmmakerService;

	@GetMapping("/admin/filmmaker/saveForm/{movieId}")
	public String saveForm(@PathVariable Long movieId, Model model) {
		model.addAttribute("movie", movieId);
		return "filmmaker/saveForm";
	}
	
	@PostMapping("/admin/filmmaker/saveForm")
	public String save(Filmmaker filmmaker, RedirectAttributes rtts) {
		if(filmmakerService.save(filmmaker) != null) {
			rtts.addFlashAttribute("result", "배우등록 성공");
			return "redirect:/movie/detail/" + filmmaker.getMovie().getId();
		} else {
			rtts.addFlashAttribute("result", "배우등록 실패");
			return "redirect:/admin/filmmaker/saveForm/" + filmmaker.getMovie().getId();
		}
	}

	@GetMapping("/admin/filmmaker/updateForm/{filmmakerId}/{userId}")
	public String updateForm(@PathVariable Long filmmakerId, @PathVariable Long userId, Model model) {
		model.addAttribute("filmmaker", filmmakerService.findById(filmmakerId));
		return "filmmaker/updateForm";
	}
	
	@PostMapping("/admin/filmmaker/update")
	public String update(Filmmaker filmmaker, RedirectAttributes rtts) {
		if(filmmakerService.save(filmmaker) != null) {
			rtts.addFlashAttribute("result", "배우수정 성공");
			return "redirect:/movie/detail/" + filmmaker.getMovie().getId();
		} else {
			rtts.addFlashAttribute("result", "배우수정 실패");
			return "redirect:/admin/filmmaker/updateForm/" + filmmaker.getMovie().getId() + "/" + filmmaker.getUser().getId();
		}
	}
	
}
