package com.manndave.watchlist.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="videos")
public class VideoModel {

	// AUTO INCREMENT ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Title is required")
    @Size(min=2, max=50, message="Title must be between 2 & 50 characters")
    private String title;
    
    @NotEmpty(message="Type is required")
    @Size(min=2, max=15, message="Select a type")
    private String type;
    
    @NotNull(message="Recommended by is optional")
    private String recommended;

	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    // MANY TO ONE RELATIONSHIP
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user_id;
    
    // DEFAULT CONSTRUCTOR
    public VideoModel() {
    	
    }
    
    // POPULATED CONSTRUCTOR
    
    
    // GETTERS & SETTERS
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public String getTitle() {
    	return title;
    }
    public void setTitle(String title) {
    	this.title = title;
    }
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    public String getRecommended() {
    	return recommended;
    }
    public void setRecommended(String recommended) {
    	this.recommended = recommended;
    }
    public Date getCreatedAt() {
    	return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
    	this.createdAt = createdAt;
    }
    public User getUser_id() {
    	return user_id;
    }
    public void setUser_id(User user_id) {
    	this.user_id = user_id;
    }
    
    
	// RUN UPON CREATION OF NEW INSTANCE
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

	// RUN UPON UPDATE OF NEW INSTANCE 
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
