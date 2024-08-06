package cholog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cholog.exception.NotFoundException;

@RestController
public class ProductController {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> handleIllegalArgumentException() {
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/products")
	public ResponseEntity<Void> searchProduct(@RequestParam String keyword) {
		if (true) {
			throw new IllegalArgumentException("Invalid keyword: " + keyword);
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Void> getProduct(@PathVariable Long id) {
		if (true) {
			throw new NotFoundException("Product not found: id=" + id);
		}

		return ResponseEntity.ok().build();
	}
}
