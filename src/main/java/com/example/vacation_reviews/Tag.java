package com.example.vacation_reviews;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "tags")
	private Set <Review> reviews;
	
	public Tag(){};
	
	public Tag(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getId() {
		return id;
	}
	
	public Set<Review> getReviews(){
		return reviews;
	}
	
	
	

}
