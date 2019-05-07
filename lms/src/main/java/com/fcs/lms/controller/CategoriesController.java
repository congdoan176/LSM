package com.fcs.lms.controller;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.fcs.lms.entity.Category;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@Controller
public class CategoriesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesController.class);
	
	@GetMapping(value = "/c")
	public String listCategory(Model model) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreOptions.getDefaultInstance().getService();
		ApiFuture<QuerySnapshot> queryCategory = db.collection("categories").get();
		ApiFuture<QuerySnapshot> queryCategory1 = null;
		List<Category> categories = queryCategory.get().toObjects(Category.class);
		for (Category category : categories) {
			queryCategory1 = db.collection("categories").whereEqualTo("id", category.getName()).get();
		}
		model.addAttribute("categories", categories);
		return "views/category/index";
	}
	@GetMapping(value = "/{id}")
	public String detailCategory(Model model, @PathVariable ("id") String id) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreOptions.getDefaultInstance().getService();
		ApiFuture<QuerySnapshot> queryCategory = db.collection("categories").whereEqualTo("id", id).get();
		List<QueryDocumentSnapshot> categorySnap = queryCategory.get().getDocuments();
		Category category = null;
		for(DocumentSnapshot category1 :categorySnap ) {
			DocumentReference docRef = db.collection("categories").document(category1.getId());
			
			ApiFuture<DocumentSnapshot> future = docRef.get();
			
			DocumentSnapshot document = future.get();
			
			category = document.toObject(Category.class);
		}
		model.addAttribute("categories", category);
		return "views/category/detail" ;
	}
	
	@GetMapping(value = "/add")
	public String create() {
		return null;
	}
	@PostMapping()
	public String addCategory() {
		return null;
	}
	@PutMapping(value = "/edit/{id}")
	public String updateCategory() {
		return null;
	}
	@PostMapping(value = "/{id}")
	public void delete() {
		
	}
}
