 
package ru.otus.elena.bookcatalogue.shell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.elena.bookcatalogue.dao.BookDaoJdbc;
import ru.otus.elena.bookcatalogue.domain.Author;
import ru.otus.elena.bookcatalogue.domain.Book;

@ShellComponent
public class ShellCommands {
    
    private final BookDaoJdbc bookDao;
    
    @Autowired
    public ShellCommands(BookDaoJdbc bookDao) {
        this.bookDao = bookDao;
    }
    
    @ShellMethod("ReadByName")
    public String byname(
            @ShellOption String name
    ) {
        List<Book> bookList = bookDao.getByName(name);
        if (!bookList.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            bookList.forEach(book -> {
                builder.append(book.toString());
            });
            return builder.toString();
        } else {
            return "doesn't exist";
        }
    }

    @ShellMethod("Read by id")
    public String byid(
            @ShellOption int id) {
        Book book = bookDao.getById(id);
        if (book != null) {
            return book.toString();
        } else {
            return "doesn't exist";
        }
    }

    @ShellMethod("Insert")
    public String insert(
            @ShellOption String name,
            @ShellOption String genre,
            @ShellOption String... authors) {
        List<String> list = Arrays.asList(authors);
        ArrayList<Author> authorList = new ArrayList<>();
        list.forEach(author -> {
            authorList.add(new Author(author));
        });
        Book book = new Book(name, genre, authorList);
        long result=bookDao.insert(book);
        return "saved with id: "+ result;
    }

    @ShellMethod("Delete")
    public String delete(@ShellOption long id) {
        int result=bookDao.delete(id);
        return "deleted: "+result+" elements";
    }
    
    @ShellMethod("Read all")
    public String all() {
        List<Book> books = bookDao.getAll();
        List<String> bookList = new ArrayList<>();
        if (!books.isEmpty()) {
            books.forEach(book -> {
                bookList.add(book.toString());
            });
            return bookList.toString();
        } else {
            return "empty";
        }
    }
    
    @ShellMethod("Genre")
    public String genre(@ShellOption String genre) {
        List<Book> books = bookDao.getByGenre(genre);
        List<String> bookList = new ArrayList<>();
        if (!books.isEmpty()) {
            books.forEach(book -> {
                bookList.add(book.toString());
            });
            return bookList.toString();
        } else {
            return "empty";
        }
    }

    @ShellMethod("Count")
    public String count() {
        int result = bookDao.count();
        return String.valueOf(result);
    }

}
