package com.jackbadawy.postcodeApi.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCodeItemRepository extends JpaRepository<PostCodeItem, Long> {
	List<PostCodeItem> findByPostCode(String postCode);
    List<PostCodeItem> findBySuburb(String suburb);
	boolean existsByPostCode(String postCode);
    boolean existsBySuburb(String suburb);
    default boolean existsByPostcodeOrSuburb(String postCode, String suburb) {
        return existsByPostCode(postCode) || existsBySuburb(suburb);
    }
}
