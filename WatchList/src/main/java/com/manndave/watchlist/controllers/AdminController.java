package com.manndave.watchlist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	// --- ADMIN PANEL (RENDER) ---
	@GetMapping("/admin")
	public String adminPanel(HttpSession session) {
		// VERIFY USER AUTH -> ON EACH ROUTE!!
		if(session.getAttribute("userId")==null) {
			session.invalidate();
			return "redirect:/";
		}
		return "admin.jsp";
	}
	
}
