
package com.example.vacation_reviews;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	TagRepository tagRepo;

	@RequestMapping("/reviews")
	public String getAllRentals(Model model) {
		model.addAttribute("reviewsAsCollection", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/review")
	public String getOneRental(@RequestParam(value = "id") Long id, Model model) {
		model.addAttribute("review", reviewRepo.findOne(id));
		return "onereview";
	}

	@RequestMapping("/home")
	public String fetchReviews(Model model) {
		model.addAttribute("reviewsAsCollection", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/categories")
	public String getAllCategories(Model model) {
		model.addAttribute("categoriesAsCollection", categoryRepo.findAll());
		return "categories";
	}

	@RequestMapping("/category")
	public String getOneCategory(@RequestParam(value = "id") Long categoryId, Model model) {
		model.addAttribute("oneCategory", categoryRepo.findOne(categoryId));
		return "category";
	}

	@RequestMapping("/add-tag")
	public String addTag(@RequestParam(value = "id") Long id, String name) {
		Tag newTag = tagRepo.findByName(name);
		if (newTag == null) {
			newTag = new Tag(name);
			tagRepo.save(newTag);
		}
		Review review = reviewRepo.findOne(id);
		Tag existing = tagRepo.findByName(name);
		if (!review.getTags().contains(existing)) {
			review.addTag(newTag);
			reviewRepo.save(review);
		}
		return "redirect:/review?id=" + id;
	}

	@RequestMapping("/remove-tag")
	public String removeTag(@RequestParam Long id, String name) {
		Tag toRemove = tagRepo.findByName(name);
		Review review = reviewRepo.findOne(id);
//		Tag existing = tagRepo.findByName(name);
//		if (review.getTags().contains(existing)) {
			review.removeTag(toRemove);
			reviewRepo.save(review);
	//	}
		return "redirect:/review?id=" + id;
	}

	@RequestMapping("/remove-tag-button")
	public String removeTagButton(@RequestParam Long tagId, @RequestParam Long reviewId) {
		Tag toRemove = tagRepo.findOne(tagId);
		Review review = reviewRepo.findOne(reviewId);
		review.removeTag(toRemove);
		reviewRepo.save(review);
		return "redirect:/review?id=" + reviewId;
	}

}
