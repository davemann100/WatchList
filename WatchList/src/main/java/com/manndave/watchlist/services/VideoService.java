package com.manndave.watchlist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manndave.watchlist.models.VideoModel;
import com.manndave.watchlist.repositories.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	private VideoRepository videoRepo;
	
	// GET ALL
	public List<VideoModel> getAll(){
		return videoRepo.findAll();
	}
	// CREATE
	public VideoModel create(VideoModel video) {
		return videoRepo.save(video);
	}
	// GET ONE
	public VideoModel getOne(Long id) {
		Optional<VideoModel> optionalVideo = videoRepo.findById(id);
		if(optionalVideo.isPresent()) {
			return optionalVideo.get();
		}
		return null;
	}
	// EDIT ONE
	public VideoModel update(VideoModel video) {
		return videoRepo.save(video);
	}
	// DELETE ONE
	public void delete(Long id) {
		videoRepo.deleteById(id);
	}
	
}
