package net.thetechstack.projectmybookcollection.collections;

import net.thetechstack.projectmybookcollection.books.Book;
import net.thetechstack.projectmybookcollection.books.BookRepository;
import net.thetechstack.projectmybookcollection.readers.Reader;
import net.thetechstack.projectmybookcollection.readers.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class CollectionController {
    @Autowired CollectionRepository collectionRepository;
    @Autowired ReaderRepository readerRepository;
    @Autowired BookRepository bookRepository;
    
    @GetMapping("/{userId}")
    public String collection(@PathVariable("userId") String userId, Model model) {
        
        Optional<Reader> reader = readerRepository.findByUsername(userId);
        if(reader.isEmpty()) return "error";
        
        List books = reader.get().getBookCollection().stream()
                .map(collection -> collection.getBook()).collect(Collectors.toList());
        model.addAttribute("books", books);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", reader.get().getFirstName()
                .concat(" ")
                .concat(reader.get().getLastName())
        );
        return "collection";
    }
    
    @PostMapping("/{userId}/add/{bookId}")
    public RedirectView addBook(@PathVariable("userId") String userId, @PathVariable("bookId") Integer bookId, Model model, Authentication authentication) {

        Optional<Reader> reader = readerRepository.findByUsername(authentication.getName());
        if(!reader.get().getUsername().equals(userId))
            return new RedirectView("/error");

        Optional<Book> book = bookRepository.findById(bookId);
        if(reader.isPresent() && book.isPresent()) {
            Collection collection = new Collection(reader.get(), book.get());
            collectionRepository.save(collection);
        }
        return new RedirectView("/" + userId);
    }

    @PostMapping("/{userId}/remove/{bookId}")
    public RedirectView remove(@PathVariable("userId") String userId, @PathVariable("bookId") Integer bookId, Model model, Authentication authentication) {
        Optional<Reader> reader = readerRepository.findByUsername(authentication.getName());

        if(!reader.get().getUsername().equals(userId))
            return new RedirectView("/error");

        Optional<Book> book = bookRepository.findById(bookId);
        if(reader.isPresent() && book.isPresent()) {
            Collection collection = new Collection(reader.get(), book.get());
            collectionRepository.delete(collection);
        }
        return new RedirectView("/" + userId);
    }
}
