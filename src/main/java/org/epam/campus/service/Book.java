package org.epam.campus.service;

import java.util.Calendar;
import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    private Date publicationDate;
    private String category;

    public Book(int id, String title, String author, Date publicationDate, String category) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setPublicationDate(publicationDate);
        setCategory(category);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be non-negative.");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
        this.author = author.trim();
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        Date today = Calendar.getInstance().getTime();
        if (publicationDate.after(today)) {
            throw new IllegalArgumentException("Publication date cannot be in the future.");
        }
        this.publicationDate = publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }
        this.category = category.trim();
    }


    @Override
    public String toString() {
        return  id +
                ", " + title +
                ", " + author +
                ", " + publicationDate +
                ", " + category;
    }
}