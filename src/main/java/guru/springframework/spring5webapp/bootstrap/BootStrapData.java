package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven design", "123123");
        Publisher macgraw = new Publisher("Tata macgraw hills", "1 main st", "Newyork", "NY", "83052");

        publisherRepository.save(macgraw);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(macgraw);

        macgraw.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(macgraw);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2ee Development without EJB", "39392343985");
        Publisher talon = new Publisher("Talon Books", "1 hurontario st", "Mississauga", "ON", "L4Z0A5");

        publisherRepository.save(talon);

        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);
        talon.getBooks().add(noEjb);
        noEjb.setPublisher(talon);

        authorRepository.save(rod);
        bookRepository.save(noEjb);
        publisherRepository.save(talon);

        System.out.println("Started boot strap-----");
        System.out.println("Number of books:"+ bookRepository.count());
        System.out.println("Number of publisher:"+ publisherRepository.count());


    }
}
