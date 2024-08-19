package springles.library.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springles.library.repositories.BookRepository;
import springles.library.services.BookService;
@Configuration
public class AppConfig {
   @Bean
   public BookRepository bookRepository(){
    return new BookRepository();
   } 
   @Bean
   public BookService bookService(BookRepository bookRepository){
    return new BookService(new BookRepository());
   }
    
}
