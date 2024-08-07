package cholog.controller;

import cholog.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

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

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<>("Bad request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>("Not Found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
//        // 예외 처리 로직
//        return ResponseEntity.status(500).body("An error occurred: " + ex.getMessage());
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<String> handleNotFoundException(NotFoundException ex){
//        // 예외 처리 로직
//        return ResponseEntity.status(500).body("An error occurred: " + ex.getMessage());
//    }


}
