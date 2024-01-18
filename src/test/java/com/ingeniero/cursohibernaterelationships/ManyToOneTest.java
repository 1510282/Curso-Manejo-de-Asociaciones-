package com.ingeniero.cursohibernaterelationships;

import com.ingeniero.cursohibernaterelationships.model.Address;
import com.ingeniero.cursohibernaterelationships.model.Author;
import com.ingeniero.cursohibernaterelationships.model.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ManyToOneTest {
    @Test
    void testManyToOne() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var author1 = session.find(Author.class, 1L);
        System.out.println("Libros author 1 "+author1.getBooks());

        var author = session.find(Author.class, 2L);
        System.out.println("Libros author 2 "+author.getBooks());



    }

    void insertData() {
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Author author = new Author("Fernando Alonso", "nano@email.com",
                LocalDate.of(1981, 7, 29));

        Author author2 = new Author("Kimi Raikkonen", "cold@email.com",
                LocalDate.of(1979, 10, 17));



        Address address = new Address("Calle 1", "Oviedo", "España");
        Address address2 = new Address("Calle 2", "Helsinki", "Finlandia");

        author2.setAddress(address2);
        author.setAddress(address);

        session.persist(address);
        session.persist(address2);

        session.persist(author);
        session.persist(author2);

        var book1 = new Book("Fernando pasandolos a todos", 33.0, 200, true, author);
        var book2 = new Book("Kimi pasandolos a todos", 33.0, 200, true, author2);
        var book3 = new Book("Fernando, la mision", 33.0, 200, true, author);
        var book4 = new Book("Kimi al fresco", 33.0, 200, true, author2);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);


        session.getTransaction().commit();
    }
}

