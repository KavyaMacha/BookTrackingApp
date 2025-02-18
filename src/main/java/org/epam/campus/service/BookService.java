package org.epam.campus.service;

import org.epam.campus.database.*;
import java.util.*;
import java.util.Date;

public class BookService {
    private final BookDataBase bookDatabase;

    public BookService(BookDataBase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    public void addBook(Book book) {
        bookDatabase.addBook(book);
    }

    public void editBookField(int id, String fieldName, Object newValue) {
        Book book = findBookById(id);
        if (book != null) {
            switch (fieldName) {
                case "title" -> book.setTitle((String) newValue);
                case "author" -> book.setAuthor((String) newValue);
                case "publicationDate" -> book.setPublicationDate((Date) newValue);
                case "category" -> book.setCategory((String) newValue);
                default -> throw new IllegalArgumentException("Unexpected field: " + fieldName);
            }
        } else {
            System.out.println("Book not found with ID: " + id);
        }
    }

    public Book findBookById(int id) {
        List<Book> bookList=BookDataBase.getAllBooks();
        Optional<Book> book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        return book.orElse(null);
    }

    public void deleteBook(int id) {
        bookDatabase.removeBook(id);
    }

    public void displayBooks() {
        List<Book> books = bookDatabase.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void displaySummary() {
        Collection<Book> books = bookDatabase.getAllBooks();
        Map<String, Integer> summary = new HashMap<>();
        for (Book book : books) {
            String category = book.getCategory();
            summary.put(category, summary.getOrDefault(category, 0) + 1);
        }

        if (summary.isEmpty()) {
            System.out.println("No books available to summarize.");
        } else {
            System.out.println("Summary of books by category:");
            for (Map.Entry<String, Integer> entry : summary.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
