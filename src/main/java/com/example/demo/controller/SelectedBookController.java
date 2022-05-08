package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class SelectedBookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/book/{isbn}")
    public String getBookByIsbn(@PathVariable String isbn, @ModelAttribute Book book, Model model) {

        model.addAttribute("book", bookRepository.findById(isbn));
        return "book";
    }
}
