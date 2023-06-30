package com.manndave.watchlist.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
//import com.manndave.watchlist.controllers.Keys;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class VideoController {
	
	private String key = "test123";
//	private Keys key;

	
	@Autowired
	private VideoService videoService;

	
	// --- DASHBOARD (RENDER) ---
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		// VERIFY USER AUTH -> ON EACH ROUTE!!
		if (session.getAttribute("userId") == null) {
			session.invalidate();
			return "redirect:/";
		} else {
			
//			System.out.println(key.getKey());
			
			List<VideoModel> allVideos = videoService.getAll();
			model.addAttribute("videoList", allVideos);
			return "dashboard.jsp";
		}
	}

	// --- CREATE COURSE (RENDER) ---
	@GetMapping("/create")
	public String create(HttpSession session, @ModelAttribute("video") VideoModel video) {
		// VERIFY USER AUTH -> ON EACH ROUTE!!
		if (session.getAttribute("userId") == null) {
			session.invalidate();
			return "redirect:/";
		}
		return "add.jsp";
	}

	// --- CREATE COURSE (ACTION) ---
	@PostMapping("/create")
	public String processCreateForm(@Valid @ModelAttribute("video") VideoModel video, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
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
			
			// STRING MANIPULATION TO ADD PLUS INSTEAD OF SPACES FOR API QUERY
			String videoTitle = oneVideo.getTitle().replace(" ", "+");
			
			// TMDB API CALL WITH VIDEO TITLE
			try {
				URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + key + "&query=" + videoTitle);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				// var to store response code such ass 404 500 etc
				int responseCode = connection.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
	                // Create a BufferedReader to read the response
	                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String line;
	                StringBuilder response = new StringBuilder();
	                // Read the response line by line
	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }
	                reader.close();
	                // Print the response to console
	                System.out.println(response.toString());
	                // DATA STORED IN RESPONSE, SEND THIS TO JSP VIA MODEL MODEL
	                model.addAttribute("apiResponse", response);
	                System.out.println(response);
	                
	                // CREATE AN EXTRA MODEL AND SERVICE TO INSTANTIATE MOVIES FROM API
	                // SEND THAT OBJ BACK TO JSP
	                // 
	                
	            } else {
	                System.out.println("API call failed with response code: " + responseCode);
	            }

	            // Close the connection
	            connection.disconnect();
	        	} catch (Exception e) {
	        		e.printStackTrace();
		        }
				return "showOne.jsp";
				// Use title of video and send to newAPIservice to handle api fetch
				// Send results to JSP
		}

	}

	// --- EDIT ONE (RENDER) ---
	@GetMapping("/edit/{id}")
	public String editOneVideo(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			session.invalidate();
			return "redirect:/";
		}
		model.addAttribute("video", videoService.getOne(id));
		return "edit.jsp";
	}

	// --- EDIT ONE (ACTION) ---
	@PutMapping("/edit/{id}")
	public String processEditOne(@Valid @ModelAttribute("video") VideoModel video, BindingResult bindingResult,
			Model model, @PathVariable("id") Long id) {
		if (bindingResult.hasErrors()) {
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
