package springles.library;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springles.library.model.Book;
import springles.library.services.BookService;
import springles.library.model.Publisher;

@SpringBootApplication
public class LibraryApplication {
	private static final Logger logger = Logger.getLogger(LibraryApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BookService bookService=context.getBean(BookService.class);

		logger.info("////////////////////////////////PARTE 1////////////////////////////////");
		if (bookService.save( (long)1, "libropro1", "pro", "probooks")){
			logger.info("Se ha anadido el libro "+bookService.findById((long)1).getName());
		}else{
			logger.info("No se anadio");
		}

		if (bookService.save( (long)2, "libropro2", "pro", "probooks")){
			logger.info("Se ha anadido el libro "+bookService.findById((long)2).getName());
		}else{
			logger.info("No se anadio");
		}

		if (bookService.save( (long)3, "libropro3", "pro", "probooks")){
			logger.info("Se ha anadido el libro "+bookService.findById((long)3).getName());
		}else{
			logger.info("No se anadio");
		}

		logger.info("La informacion del libro con id 2 es "+bookService.findById((long)2).toString());

		if (bookService.deleteById( ((long)1) )){
			logger.info("El libro con id 1 se ha eliminado correctamente");
		}else{
			logger.info("El lbro con id 1 no se ha eliminado");
		}
		

		bookService.deleteById((long)10);

		logger.info("////////////////////////////////PARTE 2////////////////////////////////");

		Publisher publisher1 = context.getBean("Editorial pro 1", Publisher.class);
        Publisher publisher2 = context.getBean("Editorial pro 2", Publisher.class);
        Publisher publisher3 = context.getBean("Editorial pro 3", Publisher.class);

        publisher1.addBook(new Book((long) 4, "Libro 4", "Autor 4", publisher1.getName()));
        publisher1.addBook(new Book((long) 5, "Libro 5", "Autor 5", publisher1.getName()));
        publisher1.addBook(new Book((long) 6, "Libro 6", "Autor 6", publisher1.getName()));
        publisher1.addBook(new Book((long) 7, "Libro 7", "Autor 7", publisher1.getName()));
        publisher1.addBook(new Book((long) 8, "Libro 8", "Autor 8", publisher1.getName()));

        publisher2.addBook(new Book((long) 9, "Libro 9", "Autor 9", publisher2.getName()));
        publisher2.addBook(new Book((long) 10, "Libro 10", "Autor 10", publisher2.getName()));
        publisher2.addBook(new Book((long) 11, "Libro 11", "Autor 11", publisher2.getName()));
        publisher2.addBook(new Book((long) 12, "Libro 12", "Autor 12", publisher2.getName()));
        publisher2.addBook(new Book((long) 13, "Libro 13", "Autor 13", publisher2.getName()));

        publisher3.addBook(new Book((long) 14, "Libro 14", "Autor 14", publisher3.getName()));
        publisher3.addBook(new Book((long) 15, "Libro 15", "Autor 15", publisher3.getName()));
        publisher3.addBook(new Book((long) 16, "Libro 16", "Autor 16", publisher3.getName()));
        publisher3.addBook(new Book((long) 17, "Libro 17", "Autor 17", publisher3.getName()));
        publisher3.addBook(new Book((long) 18, "Libro 18", "Autor 18", publisher3.getName()));

        logger.info(publisher1.getName() + " tiene " + publisher1.getBooks().size() + " libros.");
        logger.info(publisher2.getName() + " tiene " + publisher2.getBooks().size() + " libros.");
        logger.info(publisher3.getName() + " tiene " + publisher3.getBooks().size() + " libros.");

        logger.info("Libros de " + publisher1.getName() + ": " + publisher1.getBooks());
        logger.info("Libros de " + publisher2.getName() + ": " + publisher2.getBooks());
        logger.info("Libros de " + publisher3.getName() + ": " + publisher3.getBooks());
	}

}
