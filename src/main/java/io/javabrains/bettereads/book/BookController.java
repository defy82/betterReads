package io.javabrains.bettereads.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {
    
    private final String COVER_IMAGE_ROOT = "https://covers.openlibrary.org/b/id/";

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value="/books/{bookId}")
    public String getBook(@PathVariable String bookId, Model model) {
        Optional<Book> optBook = bookRepository.findById(bookId);
        if (optBook.isPresent()) {
            Book book = optBook.get();
            String coverImgUrl = "images/no_image_available.svg.webp";
            if (book.getCoverIds() != null && book.getCoverIds().size() > 0) {
                coverImgUrl = COVER_IMAGE_ROOT + book.getCoverIds().get(0) + "-L.jpg";
            }
            model.addAttribute("coverImage", coverImgUrl);
            model.addAttribute("book", book);
            return "book";
        }
        return "book-not-found";
    }
}
