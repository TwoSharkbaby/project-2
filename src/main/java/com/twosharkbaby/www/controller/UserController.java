package com.twosharkbaby.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twosharkbaby.www.dto.joinUserDto;
import com.twosharkbaby.www.dto.updateUserDto;
import com.twosharkbaby.www.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
//	@PostMapping("/join")
//	public String join(joinUserDto dto, RedirectAttributes rtts) {
//		if(userService.join(dto) != null) { 
//			rtts.addFlashAttribute("result", "회원가입 성공");
//			return "redirect:/loginForm";
//		} else {
//			rtts.addFlashAttribute("result", "회원가입 실패");
//			return "redirect:/joinForm";
//		}
//	}
	
	//validation check	
	@PostMapping("/join")
	public String join(@Valid joinUserDto dto, RedirectAttributes rtts) {
		if(userService.join(dto) != null) { 
			rtts.addFlashAttribute("result", "회원가입 성공");
			return "redirect:/loginForm";
		} else {
			rtts.addFlashAttribute("result", "회원가입 실패");
			return "redirect:/joinForm";
		}
	}
	
	@PreAuthorize("principal.user.id == #id")
	@GetMapping("/updateForm/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.findUser(id));
		return "user/updateForm";
	}
	
//	@PreAuthorize("principal.user.id == #dto.id")
//	@PostMapping("/update")
//	public String update(updateUserDto dto, RedirectAttributes rtts, HttpServletRequest request) {
//		if(userService.update(dto) != null) { 
//			rtts.addFlashAttribute("result", "회원수정 성공");
//			new SecurityContextLogoutHandler().logout(request, null, null);
//	        return "redirect:/loginForm";
//		} else {
//			rtts.addFlashAttribute("result", "회원수정 실패");
//			return "redirect:/updateForm/" + dto.getId();
//		}
//	}
	
//	validation check		
	@PreAuthorize("principal.user.id == #dto.id")
	@PostMapping("/update")
	public String update(@Valid updateUserDto dto, RedirectAttributes rtts, HttpServletRequest request) {
		if(userService.update(dto) != null) { 
			rtts.addFlashAttribute("result", "회원수정 성공");
			new SecurityContextLogoutHandler().logout(request, null, null);
	        return "redirect:/loginForm";
		} else {
			rtts.addFlashAttribute("result", "회원수정 실패");
			return "redirect:/updateForm/" + dto.getId();
		}
	}
	
}
