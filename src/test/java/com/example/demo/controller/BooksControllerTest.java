//package com.example.demo.controller;
//
//import com.example.demo.model.Book;
//import com.example.demo.service.BookService;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.mockito.Mockito.*;
//
//import java.util.List;
//import java.util.Objects;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(BooksController.class)
//class BooksControllerTest{
//
//    @MockBean
//    private BookService booksService;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @SneakyThrows
//    void saveBook() {
//
//        byte[] book = Objects.requireNonNull(BooksControllerTest.class.getResourceAsStream("/request.json")).readAllBytes();
//        Book bookObj = new Book("1984", "554732", "George Orwell");
//
//        when(booksService.getAll()).thenReturn(List.of(bookObj));
//
//        mockMvc.perform(
//                        post("/save")
//                                .content(book)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(new String(Objects.requireNonNull(BooksControllerTest.class.getResourceAsStream("/response.json")).readAllBytes())));
//
//        verify(booksService).add(any());
//        verify(booksService).getAll();
//    }
//
//    @Test
//    @SneakyThrows
//    void findBook() {
//
//        byte[] book = Objects.requireNonNull(BooksControllerTest.class.getResourceAsStream("/request.json")).readAllBytes();
//        Book bookObj = new Book("1984", "554732", "George Orwell");
//
//        when(booksService.findByNameIsbn(any(),any(),any())).thenReturn(List.of(bookObj));
//
//        mockMvc.perform(
//                        post("/find")
//                                .content(book)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(new String(Objects.requireNonNull(BooksControllerTest.class.getResourceAsStream("/response.json")).readAllBytes())));
//
//        verify(booksService).findByNameIsbn("1984", "554732", "George Orwell");
//    }
//
//    @Test
//    @SneakyThrows
//    void getAllBook() {
//
//        Book bookObj = new Book("1984", "554732", "George Orwell");
//        when(booksService.getAll()).thenReturn(List.of(bookObj));
//
//        mockMvc.perform(
//                        get("/findAll")
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(new String(Objects.requireNonNull(BooksControllerTest.class.getResourceAsStream("/response.json")).readAllBytes())));
//
//        verify(booksService).getAll();
//    }
//}