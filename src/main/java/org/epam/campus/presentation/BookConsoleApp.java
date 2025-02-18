package org.epam.campus.presentation;

import org.epam.campus.database.BookDataBase;
import org.epam.campus.service.Book;
import org.epam.campus.service.BookService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.*;

public class BookConsoleApp {
    public static void main(String[] args) {
        BookService bookService = new BookService(new BookDataBase());
        Scanner sc = new Scanner(in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        while (true) {
            out.println("\n=== Book Management System ===");
            out.println("1. Add Book");
            out.println("2. Display All Books");
            out.println("3. Edit Book");
            out.println("4. Delete Book");
            out.println("5. Display Summary");
            out.println("6. Exit");
            out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline left by nextInt()
            switch (choice) {
                case 1 -> {
                    out.println("Enter Book ID:");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    out.println("Enter Title:");
                    String title = sc.nextLine();
                    out.println("Enter Author:");
                    String author = sc.nextLine();
                    out.println("Enter Publication Date (dd-MM-yyyy):");
                    Date publicationDate = null;
                    try {
                        publicationDate = dateFormat.parse(sc.nextLine());
                    } catch (Exception e) {
                        out.println("Invalid date format.");
                        break;
                    }
                    out.println("Enter Category:");
                    String category = sc.nextLine();
                    Book book = new Book(id, title, author, publicationDate, category);
                    bookService.addBook(book);
                    out.println("Book added successfully.");
                }
                case 2 -> bookService.displayBooks();
                case 3 -> {
                    out.println("Enter the Book ID of the book you want to edit:");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    if (bookService.findBookById(id) == null) {
                        out.println("Book ID does not exist.");
                    } else {
                        boolean continueEditing = true;
                        while (continueEditing) {
                            out.println("Select field to edit:");
                            out.println("1. Title");
                            out.println("2. Author");
                            out.println("3. Publication Date");
                            out.println("4. Category");
                            out.println("5. Done Editing");
                            out.print("Enter your choice: ");
                            int editChoice = sc.nextInt();
                            sc.nextLine(); // Consume newline
                            switch (editChoice) {
                                case 1 -> {
                                    out.println("Enter new Title:");
                                    String title = sc.nextLine();
                                    bookService.editBookField(id, "title", title);
                                }
                                case 2 -> {
                                    out.println("Enter new Author:");
                                    String author = sc.nextLine();
                                    bookService.editBookField(id, "author", author);
                                }
                                case 3 -> {
                                    out.println("Enter new Publication Date (dd-MM-yyyy):");
                                    Date publicationDate = null;
                                    try {
                                        publicationDate = dateFormat.parse(sc.nextLine());
                                        bookService.editBookField(id, "publicationDate", publicationDate);
                                    } catch (Exception e) {
                                        out.println("Invalid date format.");
                                    }
                                }
                                case 4 -> {
                                    out.println("Enter new Category:");
                                    String category = sc.nextLine();
                                    bookService.editBookField(id, "category", category);
                                }
                                case 5 -> continueEditing = false;
                                default -> out.println("Invalid choice. Try again.");
                            }
                        }
                        out.println("Book updated successfully.");
                    }
                }
                case 4 -> {
                    out.println("Enter the Book ID to delete:");
                    int id = sc.nextInt();
                    bookService.deleteBook(id);
                    out.println("Book deleted successfully.");
                }
                case 5 -> bookService.displaySummary();
                case 6 -> {
                    out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> out.println("Invalid choice. Try again.");
            }
        }
    }
}
