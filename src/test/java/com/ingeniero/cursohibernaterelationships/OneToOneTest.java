package com.ingeniero.cursohibernaterelationships;

import com.ingeniero.cursohibernaterelationships.model.Address;
import com.ingeniero.cursohibernaterelationships.model.Author;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OneToOneTest {
    @Test
    void testOneToOne() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

      var author1 =  session.find(Author.class, 1L);
        System.out.println(author1.getAddress());
      var author2 =  session.find(Author.class, 2L);
        System.out.println(author2.getAddress());
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

        session.getTransaction().commit();
    }
}

