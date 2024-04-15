package com.jackbadawy.postcodeApi.posts;

import org.springframework.context.annotation.Conditional;

import com.jackbadawy.postcodeApi.validation.Unique;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreatePostcodeItemDTO {
	
	@NotBlank(message = "no suburb provided")
	@Unique(message = "This suburb already exists")
	private String suburb;
	
	@NotBlank(message = "no postcode provided")
	@Pattern(regexp = "\\d{4}", message = "Postcode must be 4 digits")
	@Unique(message = "This postcode already exists")
	private String postcode;

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
}
