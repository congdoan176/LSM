//package com.fcs.lms.controller;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.CollectionFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//
//import com.google.api.core.ApiFuture;
//import com.google.cloud.firestore.CollectionReference;
//import com.google.cloud.firestore.DocumentReference;
//import com.google.cloud.firestore.DocumentSnapshot;
//import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.FirestoreOptions;
//import com.google.cloud.firestore.Query;
//import com.google.cloud.firestore.QueryDocumentSnapshot;
//import com.google.cloud.firestore.QuerySnapshot;
//import com.google.cloud.firestore.WriteResult;
//
//@Controller
//public class CategoriesController {
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesController.class);
//	
//	@GetMapping(value = "/c")
//	public String listCategory(Model model) throws InterruptedException, ExecutionException {
//		Firestore db = FirestoreOptions.getDefaultInstance().getService();
//		ApiFuture<QuerySnapshot> queryCategory = db.collection("categories").get();
//		ApiFuture<QuerySnapshot> queryCategory1 = null;
//		List<Category> categories = queryCategory.get().toObjects(Category.class);
//		for (Category category : categories) {
//			queryCategory1 = db.collection("categories").whereEqualTo("id", category.getName()).get();
//		}
//		model.addAttribute("categories", categories);
//		return "views/category/index";
//	}
//	@GetMapping(value = "/cate/{id}")
//	public String detailCategory(Model model, @PathVariable ("id") String id) throws InterruptedException, ExecutionException {
//		Firestore db = FirestoreOptions.getDefaultInstance().getService();
//		ApiFuture<QuerySnapshot> future =
//			    db.collection("categories").whereEqualTo("id", id).get();
//			// future.get() blocks on response
//			List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//			Category category = new Category();
//			for (DocumentSnapshot document : documents) {
//			  System.out.println(document.getId());
//			  if (document.exists()) {
//				  // convert document to POJO
//				  category = document.toObject(Category.class);
//				  System.out.println(category);
//				} else {
//				  System.out.println("No such document!");
//				}
//			}
//			model.addAttribute("category", category);
//		return "views/category/detail";
//	}
//	
//	@PostMapping(value = "/add")
//	public String create(Model model, String id, String name, int total,String img ) {
//		Firestore db = FirestoreOptions.getDefaultInstance().getService();
//		Map<String, Object> category = new HashMap<>();
//		category.put("id", id);
//		category.put("name", name);
//		category.put("img", img);
//		category.put("total", total);
//		ApiFuture<DocumentReference> addedDocRef = db.collection("categories").add(category);
// 		return "views/category/index";
//	}
//	@GetMapping(value = "/form")
//	public String addCategory() {
//		return "views/category/create";
//	}
//	@PutMapping(value = "/category/{id}")
//	public String updateCategory() {
//		Firestore db = FirestoreOptions.getDefaultInstance().getService();
//		return null;
//	}
//	@DeleteMapping(value = "/category/{id}")
//	public void delete() {
//		
//	}
//}
