package com.book.store.controller;

import org.springframework.web.bind.annotation.RestController;

import com.book.store.modal.Book;
import com.book.store.services.BookService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Slf4j
@RestController
public class apiController {
    @Autowired
    private BookService bookService;
    @PostMapping("/addBook")
    public String postMethodName(@RequestBody Book book) throws Exception {
        System.out.println(book);
        bookService.createBook(book);
        log.info("created successfully");
        return "Book created Successfully";
    }

    @GetMapping("/getBookList")
    public List<Book> getBookList() throws Exception {
        return bookService.getBookList();  // Using corrected service method
    }
    
    @PutMapping("/updateDeleteFlag/{id}")
    public ResponseEntity<Void> updateDeleteFlag(@PathVariable int id) {
        try {
            Boolean isDeleted = true;
            bookService.updateDeleteFlag(id, isDeleted);
            log.info("Delete flag for book with ID " + id + " updated to " + isDeleted);
            return ResponseEntity.ok().build(); // Return 200 with no content
        } catch (Exception e) {
            log.error("Error updating delete flag for book with ID " + id, e);
            return ResponseEntity.status(404).build(); // Return 404 if not found
        }
    }
}
