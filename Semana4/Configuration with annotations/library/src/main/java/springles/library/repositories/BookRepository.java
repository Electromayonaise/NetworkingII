package springles.library.repositories;
import springles.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    List <Book> books;
    
    public BookRepository() {
        this.books = new ArrayList<>();
    }
    
    public boolean save(Book book) {
        if (findById(book.getId()) == null) {
            books.add(book);
            return true;
        }
        return false;
    }
    
    public Book findById(Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
    
    
    public boolean deleteById(Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                books.remove(book);
                return true;
            }
        } 
        return false;
    }
    

}
