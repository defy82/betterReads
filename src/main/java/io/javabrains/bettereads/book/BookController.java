package io.javabrains.bettereads.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.javabrains.bettereads.userbooks.UserBook;
import io.javabrains.bettereads.userbooks.UserBooksPrimaryKey;
import io.javabrains.bettereads.userbooks.UserBooksRepository;

@Controller
public class BookController {
    
    private final String COVER_IMAGE_ROOT = "https://covers.openlibrary.org/b/id/";

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserBooksRepository userBooksRepository;

    @GetMapping(value="/books/{bookId}")
    public String getBook(@AuthenticationPrincipal OAuth2User principal, @PathVariable String bookId, Model model) {
        Optional<Book> optBook = bookRepository.findById(bookId);
        if (optBook.isPresent()) {
            Book book = optBook.get();
            String coverImgUrl = "images/no_image_available.svg.webp";
            if (book.getCoverIds() != null && book.getCoverIds().size() > 0) {
                coverImgUrl = COVER_IMAGE_ROOT + book.getCoverIds().get(0) + "-L.jpg";
            }
            model.addAttribute("coverImage", coverImgUrl);
            model.addAttribute("book", book);
            if (principal != null && principal.getAttribute("login") != null) {
                String userId = principal.getAttribute("login");
                model.addAttribute("loginId", userId);

                UserBooksPrimaryKey key = new UserBooksPrimaryKey(userId, bookId);
                Optional<UserBook> userBook = userBooksRepository.findById(key);

                if (userBook.isPresent()) {
                    model.addAttribute("userBooks", userBook.get());
                }
                else {
                    model.addAttribute("userBooks", new UserBook());
                }
            }

            return "book";
        }
        return "book-not-found";
    }
}
