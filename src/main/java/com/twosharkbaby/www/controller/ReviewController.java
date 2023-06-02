package com.twosharkbaby.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twosharkbaby.www.domain.MovieReview;
import com.twosharkbaby.www.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/auth/review/saveForm/{movieId}")
	public String saveForm(@PathVariable Long movieId, Model model) {
		model.addAttribute("movie", movieId);
		return "review/saveForm";
	}
	
	@PostMapping("/auth/review/save")
	public String save(MovieReview movieReview, RedirectAttributes rtts) {
		if(reviewService.save(movieReview) == 1) {
			rtts.addFlashAttribute("result", "리뷰등록 성공");
			return "redirect:/movie/detail/" + movieReview.getMovie().getId();
		} else {
			rtts.addFlashAttribute("result", "이미 리뷰를 등록하셨습니다. 영화당 리뷰는 하나만 등록할 수 있습니다");
			return "redirect:/movie/detail/" + movieReview.getMovie().getId();
		}
	}
	
	@PreAuthorize("principal.user.id == #userId")
	@GetMapping("/auth/review/updateForm/{id}/{userId}")
	public String updateForm(@PathVariable Long id, @PathVariable Long userId, Model model) {
		model.addAttribute("review", reviewService.findById(id));
		return "review/updateForm";
	}
	
	@PreAuthorize("principal.user.id == #movieReview.user.id")
	@PostMapping("/auth/review/update")
	public String update(MovieReview movieReview, RedirectAttributes rtts) {
		if(reviewService.update(movieReview) == 1) {
			rtts.addFlashAttribute("result", "리뷰수정 성공");
			return "redirect:/movie/detail/" + movieReview.getMovie().getId();
		} else {
			rtts.addFlashAttribute("result", "리뷰수정 실패");
			return "redirect:/auth/review/updateForm/" + movieReview.getId();
		}
	}
	

//	@GetMapping("/")
//	public String main(Model model) {
//		model.addAttribute("latest", movieService.findRecentMovies());
//		model.addAttribute("action", movieService.findActionMovies());
//		model.addAttribute("romance", movieService.findRomanceMovies());
//		return "/movie/main";
//	} 
//	
//	
//	@GetMapping("/auth/movie/saveForm")
//	public String saveForm() {
//		return "/movie/saveForm";
//	} 
//	
//	@PostMapping("/auth/movie/save")
//	public String save(Movie movie, RedirectAttributes rtts) {
//		if(movieService.save(movie) != null) {
//			rtts.addFlashAttribute("result", "영화등록 성공");
//			return "redirect:/";
//		} else {
//			rtts.addFlashAttribute("result", "영화등록 실패");
//			return "redirect:/auth/movie/saveForm";
//		}
//	} 
//	
//	@GetMapping("/movie/detail/{id}")
//	public String detail(@PathVariable Long id, Model model) {
//		model.addAttribute("movie", movieService.findById(id));
//		model.addAttribute("filmmaker", filmmakerService.findByMovieId(id));
//		return "/movie/detail";
//	}
//	
//	@GetMapping("/auth/movie/updateForm/{id}")
//	public String updateForm(@PathVariable Long id, Model model) {
//		model.addAttribute("movie", movieService.findById(id));
//		return "/movie/updateForm";
//	}
//	
//	@PostMapping("/auth/movie/update")
//	public String update(Movie movie, RedirectAttributes rtts) {
//		if(movieService.save(movie) != null) {
//			rtts.addFlashAttribute("result", "영화수정 성공");
//			return "redirect:/movie/detail/" + movie.getId();
//		} else {
//			rtts.addFlashAttribute("result", "영화수정 실패");
//			return "redirect:/auth/movie/updateForm/" + movie.getId();
//		}
//	} 
	
	
	
	
	
	
}
