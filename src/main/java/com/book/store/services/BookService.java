package com.book.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.modal.Book;
import com.book.store.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void createBook(Book book){
        bookRepository.save(book);
    }

    public List<Book> getBookList() {  
        return bookRepository.findByIsDeletedFalse();  
    }

    public void updateDeleteFlag(int id, boolean isDeleted) throws Exception {
        Optional<Book> existingBookOpt = bookRepository.findById(id);

        if (existingBookOpt.isPresent()) {
            Book existingBook = existingBookOpt.get();
            existingBook.setDeleted(isDeleted);
            bookRepository.save(existingBook);
        } else {
            throw new Exception("Book with ID " + id + " not found.");
        }
    }
}
