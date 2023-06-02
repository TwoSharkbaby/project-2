package com.twosharkbaby.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twosharkbaby.www.domain.Movie;
import com.twosharkbaby.www.service.FilmmakerService;
import com.twosharkbaby.www.service.MovieService;
import com.twosharkbaby.www.service.ReviewService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private FilmmakerService filmmakerService;
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("latest", movieService.findRecentMovies());
		model.addAttribute("action", movieService.findActionMovies());
		model.addAttribute("romance", movieService.findRomanceMovies());
		return "/movie/main";
	} 
	
	
	@GetMapping("/admin/movie/saveForm")
	public String saveForm() {
		return "/movie/saveForm";
	} 
	
	@PostMapping("/admin/movie/save")
	public String save(Movie movie, RedirectAttributes rtts) {
		if(movieService.save(movie) != null) {
			rtts.addFlashAttribute("result", "영화등록 성공");
			return "redirect:/";
		} else {
			rtts.addFlashAttribute("result", "영화등록 실패");
			return "redirect:/admin/movie/saveForm";
		}
	} 
	
	@GetMapping("/movie/detail/{id}")
	public String detail(@PathVariable Long id, Model model, @PageableDefault(size = 4,sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("movie", movieService.findById(id));
		model.addAttribute("filmmaker", filmmakerService.findByMovieId(id));
		model.addAttribute("review", reviewService.findByMovieIdWithPage(id, pageable));
		System.out.println(reviewService.findByMovieIdWithPage(id, pageable));
		return "/movie/detail";
	}
	
	@GetMapping("/admin/movie/updateForm/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("movie", movieService.findById(id));
		return "/movie/updateForm";
	}
	
	@PostMapping("/admin/movie/update")
	public String update(Movie movie, RedirectAttributes rtts) {
		if(movieService.save(movie) != null) {
			rtts.addFlashAttribute("result", "영화수정 성공");
			return "redirect:/movie/detail/" + movie.getId();
		} else {
			rtts.addFlashAttribute("result", "영화수정 실패");
			return "redirect:/admin/movie/updateForm/" + movie.getId();
		}
	} 
	
	@GetMapping("/search")
	public String search(String keyword, Model model, @PageableDefault(size = 4,sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("keyword", keyword);
		model.addAttribute("search", movieService.findByTitleContaining(keyword, pageable));
		return "/movie/search";
	}
	
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "test";
	}
	
	
	
	
}
