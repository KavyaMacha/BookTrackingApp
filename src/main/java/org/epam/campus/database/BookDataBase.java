package org.epam.campus.database;

import org.epam.campus.service.Book;

import java.util.*;

public class BookDataBase {
    private static Map<Integer, Book> books;

    public BookDataBase() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book) {
        // Just add the book if it's not already in the map
        if (book != null && !books.containsKey(book.getId())) {
            books.put(book.getId(), book);
        } else {
            System.out.println("Book already exists or provided book is null");
        }
    }

    public Book getBook(int id) {
        return books.get(id);
    }

    public void updateBook(int id, Book book) {
        // Check if the book exists before updating
        if (books.containsKey(id)) {
            books.put(id, book);
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public void removeBook(int id) {
        if (books.containsKey(id)) {
            books.remove(id);
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public static List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
}
}
