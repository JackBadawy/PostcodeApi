package com.jackbadawy.postcodeApi.posts;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class PostCodeItemService {
	
    @Autowired
    private PostCodeItemRepository repo;
    
    @Autowired 
    private ModelMapper mapper;
    
    public PostCodeItem createItem(CreatePostcodeItemDTO data) {
    	
    	
    	PostCodeItem newItem = mapper.map(data, PostCodeItem.class);
    	
    	return this.repo.save(newItem);
    }
    
    public List<PostCodeItem> getAll() {
    	return this.repo.findAll();
    }
    
    public Optional<PostCodeItem> findItemById(Long id) {
    	return this.repo.findById(id);
    }
    
    public Optional<PostCodeItem> updateById(@Valid UpdatePostCodeItemDTO data, Long id) {
    	
    	Optional<PostCodeItem> maybeItem = this.findItemById(id);
    	
    	if (maybeItem.isEmpty()) {
    		return maybeItem;
    	}
    	
    	PostCodeItem foundItem = maybeItem.get();
    	
    	mapper.map(data, foundItem);
    	
    	PostCodeItem updatedItem = this.repo.save(foundItem);
    	
    	return Optional.of(updatedItem);
    }
    
    public boolean deletePostById(Long id) {
    	Optional<PostCodeItem> maybeItem = this.repo.findById(id);
    	if (maybeItem.isEmpty()) return false;
    	this.repo.delete(maybeItem.get());
    	return true;
    }

}
