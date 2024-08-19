package springles.library.services;

import springles.library.repositories.BookRepository;

import java.util.List;

import springles.library.model.Book;

import springles.library.model.Publisher;


public class BookService {
    
    BookRepository bookRepository;

    public BookService() {

    }

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean save(Long id, String name, String author, String publisher) {
        return bookRepository.save(new Book(id, name, author, publisher));
    }

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        return bookRepository.deleteById(id);
    }

     public void addBooksToEditorial(Publisher publisher, List<Book> books) {
        for (Book book : books) {
            publisher.addBook(book);
        }
    }

    public void listPublishersWithBooks(List<Publisher> publishers) {
        for (Publisher publisher : publishers) {
            System.out.println(publisher);
        }
    }

    public void listBooksInPublisher(Publisher publisher) {
        System.out.println("Books in " + publisher.getName() + ":");
        for (Book book : publisher.getBooks()) {
            System.out.println(book);
        }
    }

    
}
