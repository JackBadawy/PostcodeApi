package com.jackbadawy.postcodeApi.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCodeItemRepository extends JpaRepository<PostCodeItem, Long> {
	boolean existsByPostCode(String postCode);
    boolean existsBySuburb(String suburb);
    default boolean existsByPostcodeOrSuburb(String postCode, String suburb) {
        return existsByPostCode(postCode) || existsBySuburb(suburb);
    }
}
