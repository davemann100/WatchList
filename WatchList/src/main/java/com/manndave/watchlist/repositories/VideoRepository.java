package com.manndave.watchlist.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manndave.watchlist.models.VideoModel;

public interface VideoRepository extends CrudRepository <VideoModel, Long> {
	
	List<VideoModel> findAll();

}