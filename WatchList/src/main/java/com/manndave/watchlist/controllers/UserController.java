package com.manndave.watchlist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.manndave.watchlist.models.LoginUser;
import com.manndave.watchlist.models.User;
import com.manndave.watchlist.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

		@Autowired
		private UserService userService;
		
		// --- LOG/REG (RENDER) ---	
		@GetMapping("/")
		public String logreg(Model model) {
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			return "logreg.jsp";
		}
		
		// -- REGISTER -- (ACTION)
		@PostMapping("/register")
		public String processRegister(@Valid @ModelAttribute("newUser") User newUser, 
				BindingResult result, Model model, HttpSession session) {
	        // 1. get the registeredUser by calling register in service and make use of the binding result
			User registeredUser = userService.register(newUser, result);
	        // 2. check for result errors
	        // 2.1 if there are errors, put the missing model in and return jsp
			if(result.hasErrors()) {
				model.addAttribute("newLogin", new LoginUser());
				return "logreg.jsp";
				// 2.2 if no errors, set userId in session and redirect
			} else {
				session.setAttribute("userId", registeredUser.getId());
				session.setAttribute("userName", registeredUser.getUserName());
				return "redirect:/dashboard";
			}	
			
		}
		
		// -- LOGIN -- (ACTION)
		@PostMapping("/login")
		public String processLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
				BindingResult result, Model model, HttpSession session) {
	         // 1. get the user by calling login in service and make use of the binding result
			User loginUser = userService.login(newLogin, result); 
	        // 2. check for result errors
	        // 2.1 if there are errors, put the missing model in and return jsp
			if(result.hasErrors()) {
				model.addAttribute("newUser", new User());
				return "logreg.jsp";
				// 2.2 if no errors, get the user info from user and set userId in the session
			} else {
				session.setAttribute("userId", loginUser.getId());
				session.setAttribute("userName", loginUser.getUserName());
				return "redirect:/dashboard";
			}
		}
		
		// --- LOGOUT (ACTION) ---
		@GetMapping("/logout")
		public String logout(HttpSession session) {
	        // clear session using session.invalidate()
			session.invalidate();
			return "redirect:/";
		}
		
		
		
		// --- DASHBOARD (RENDER) ---
		@GetMapping("/dashboard")
		public String dashboard(HttpSession session) {
			// VERIFY USER AUTH -> ON EACH ROUTE!!
			if(session.getAttribute("userId")==null) {
				session.invalidate();
				return "redirect:/";
			}
			return "dashboard.jsp";
		}
		
		
		
}
