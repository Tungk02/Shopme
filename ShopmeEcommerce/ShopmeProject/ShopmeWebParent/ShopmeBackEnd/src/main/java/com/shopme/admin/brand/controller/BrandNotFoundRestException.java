package com.shopme.admin.brand.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shopme.common.entity.Product;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Brand not found")
public class BrandNotFoundRestException extends Exception {
	private static final long serialVersionUID = 1L;

	@PostMapping("/products/save")
	public String saveProduct(Product product) {
		System.out.println("Product Name: " + product.getName());
		System.out.println("Brand ID: " + product.getBrand().getId());
		System.out.println("Category ID: " + product.getCategory().getId());
		return "redirect:/products";
	}
}
