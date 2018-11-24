package ru.otus.elena.bookcatalogue.dao;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.elena.bookcatalogue.domain.Book;

@RunWith(SpringRunner.class)
@ConfigurationProperties("application")
@EnableMongoRepositories
@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void testFindByName() {
        System.out.println("findByName");
        Book book = new Book("masha_v_derevne", "fantasy", new ArrayList<String>() {
            {
                add("vasya");
                add("vova");
            }
        }, true);
        bookRepository.insert(book);
        Book rebook = bookRepository.findByName("masha_v_derevne").get(0);
        assertEquals(book, rebook);

    }

    @Test
    public void testFindByGenre() {
        System.out.println("findByGenre");
        Book book = new Book("masha_v_derevne", "fantasy", new ArrayList<String>() {
            {
                add("vasya");
                add("vova");
            }
        }, true);
        bookRepository.insert(book);
        Book rebook = bookRepository.findByGenre("fantasy").get(0);
        assertEquals(book, rebook);
    }

    @Test
    public void testFindByAuthors() {
        System.out.println("findByAuthors");
        Book book = new Book("masha_v_derevne", "fantasy", new ArrayList<String>() {
            {
                add("vasya");
                add("vova");
            }
        }, true);
        bookRepository.insert(book);
        Book rebook = bookRepository.findByAuthors("vasya").get(0);
        assertEquals(book, rebook);
    }

}