package com.manndave.watchlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.manndave.watchlist.models.VideoModel;
import com.manndave.watchlist.services.VideoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	
	// --- DASHBOARD (RENDER) ---
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		// VERIFY USER AUTH -> ON EACH ROUTE!!
		if(session.getAttribute("userId")==null) {
			session.invalidate();
			return "redirect:/";
		}else {
			List<VideoModel> allVideos = videoService.getAll();
			model.addAttribute("videoList", allVideos);
			return "dashboard.jsp";
		}
	}
	
	// --- CREATE COURSE (RENDER) ---
	@GetMapping("/create")
	public String create(HttpSession session, @ModelAttribute("video") VideoModel video) {
		// VERIFY USER AUTH -> ON EACH ROUTE!!
		if(session.getAttribute("userId")==null) {
			session.invalidate();
			return "redirect:/";
		}
		return "add.jsp";
	}
	
	// --- CREATE COURSE (ACTION) ---
	@PostMapping("/create")
	public String processCreateForm(@Valid @ModelAttribute("video") VideoModel video, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "add.jsp";			
		} else {
			videoService.create(video);
			return "redirect:/dashboard";
		}
	}
	
	// --- VIEW ONE COURSE (RENDER) ---
	@GetMapping("/video/{id}")
	public String viewOneVideo(HttpSession session, @ModelAttribute("video") VideoModel video, @PathVariable("id") Long id, Model model) {
		// VERIFY USER AUTH -> ON EACH ROUTE!!
		if(session.getAttribute("userId")==null) {
			session.invalidate();
			return "redirect:/";
		} else {
			VideoModel oneVideo = videoService.getOne(id);
			model.addAttribute("oneVideo", oneVideo);
			return "showOne.jsp";
		}
	}
	
	// --- EDIT ONE (RENDER) ---
	@GetMapping("/edit/{id}")
	public String editOneVideo(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId")==null) {
			session.invalidate();
			return "redirect:/";
		}
		model.addAttribute("video", videoService.getOne(id));
		return "edit.jsp";
	}
	
	// --- EDIT ONE (ACTION) ---
	@PutMapping("/edit/{id}")
	public String processEditOne(@Valid @ModelAttribute("video") VideoModel video, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
		if(bindingResult.hasErrors()) {
			return "edit.jsp";			
		}
		videoService.update(video);
		return "redirect:/dashboard";
	}
	
	// --- DELETE ONE (ACTION) ---
	@DeleteMapping("/delete/{id}")
	public String deleteVideo(@PathVariable("id") Long id) {
		videoService.delete(id);
		return "redirect:/dashboard";
	}
	
	
	
	
	
	
	
	
}
