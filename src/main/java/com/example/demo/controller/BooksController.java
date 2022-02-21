package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BooksController {

    List<Book> books = new ArrayList<>();

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String saveBook(){
        return "books-get";
    }

    @RequestMapping(value = "/add-books", method = RequestMethod.POST)
    public String listBooks(@ModelAttribute Book book){
        books.add(book);
        return "redirect:/books-list";
    }

    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model) {
        model.addAttribute("books", books);
        return "books-list";
    }
}