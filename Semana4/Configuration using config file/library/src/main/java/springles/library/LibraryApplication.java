package springles.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springles.library.configurations.AppConfig;
import springles.library.services.BookService;

import java.util.logging.Logger;

@SpringBootApplication
public class LibraryApplication {
	private static final Logger logger = Logger.getLogger(LibraryApplication.class.getName());


	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
		ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
		BookService bookService=applicationContext.getBean(BookService.class);


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
	}

}
