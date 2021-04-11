package net.thetechstack.projectmybookcollection.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired BookRepository bookRepository;
    @GetMapping
    public String books(@ModelAttribute("text") String text, Model model) {
        if(Objects.nonNull(text) && !text.isEmpty()) {
            List<Book> books = bookRepository.findByOriginalTitleContainingIgnoreCase(text);
            model.addAttribute("books", books);
        }
        return "books";
    }
    
}
