package springles.library.services;

import springles.library.repositories.BookRepository;
import springles.library.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service 
public class BookService {
    
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookService() {

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

    
}
