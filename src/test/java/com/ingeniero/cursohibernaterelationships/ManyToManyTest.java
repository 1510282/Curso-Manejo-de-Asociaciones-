package com.ingeniero.cursohibernaterelationships;

import com.ingeniero.cursohibernaterelationships.model.Address;
import com.ingeniero.cursohibernaterelationships.model.Author;
import com.ingeniero.cursohibernaterelationships.model.Book;
import com.ingeniero.cursohibernaterelationships.model.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ManyToManyTest {
    @Test
    void testManyToMany() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var book1 = session.find(Book.class, 1L);
        System.out.println("Categorias Libro 1 "+book1.getCategories());

        var book2 = session.find(Book.class, 2L);
        System.out.println("Categorias Libro 2 "+book2.getCategories());
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

        var cat1 = new Category("Ficcion",12);
        var cat2 = new Category("No ficcion",12);
        var cat3 = new Category("Terror",12);
        var cat4 = new Category("Comedia",12);

        session.persist(cat1);
        session.persist(cat2);
        session.persist(cat3);
        session.persist(cat4);


        book1.getCategories().add(cat1);
        book1.getCategories().add(cat2);

        book2.getCategories().add(cat1);
        book2.getCategories().add(cat3);

        book3.getCategories().add(cat1);
        book3.getCategories().add(cat4);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);


        session.getTransaction().commit();
    }
}

