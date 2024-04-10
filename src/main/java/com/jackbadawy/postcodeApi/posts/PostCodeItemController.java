package com.jackbadawy.postcodeApi.posts;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class PostCodeItemController {
	
	@Autowired
	private PostCodeItemService postCodeItemService;
	
	@PostMapping
	public ResponseEntity<PostCodeItem> createPost(@Valid @RequestBody CreatePostcodeItemDTO data) {
		PostCodeItem createdItem = this.postCodeItemService.createItem(data);
		return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PostCodeItem>> getAllPosts() {
		List<PostCodeItem> allItems = this.postCodeItemService.getAll();
		return new ResponseEntity<>(allItems, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostCodeItem> getItemById(@PathVariable Long id) {
		Optional<PostCodeItem> maybeItem = this.postCodeItemService.findItemById(id);
		
		if (maybeItem.isPresent()) {
            PostCodeItem foundItem = maybeItem.get();
            return new ResponseEntity<>(foundItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<PostCodeItem> updateItemById(@Valid @RequestBody UpdatePostCodeItemDTO data, @PathVariable Long id) {
		
		Optional<PostCodeItem> maybeUpdatedItem = this.postCodeItemService.updateById(data, id);
		
		if (maybeUpdatedItem.isPresent()) {
            PostCodeItem foundItem = maybeUpdatedItem.get();
            return new ResponseEntity<>(foundItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PostCodeItem> deletePostById(@PathVariable Long id) {
		boolean deleted = this.postCodeItemService.deletePostById(id);
		
		if (!deleted) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
}}
