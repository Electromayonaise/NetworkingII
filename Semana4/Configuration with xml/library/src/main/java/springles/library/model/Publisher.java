package springles.library.model;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private Long id;
    private String name;
    private String address;
    private List<Book> books;

    public Publisher(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public Publisher(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", books=" + books +
                '}';
    }
}
