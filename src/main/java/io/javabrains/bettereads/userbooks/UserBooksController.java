package io.javabrains.bettereads.userbooks;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserBooksController {
    
    @Autowired UserBooksRepository userBooksRepository;

    @PostMapping(value = "/addUserBook")
    public ModelAndView addBookForUser(
        @RequestBody MultiValueMap<String, String> formData,
        @AuthenticationPrincipal OAuth2User principal
    ) {
        
        if (principal == null || principal.getAttribute("login") == null)
            return null;

        UserBooksPrimaryKey userBooksPrimaryKey = new UserBooksPrimaryKey();
        userBooksPrimaryKey.setUserId(principal.getAttribute("login"));
        userBooksPrimaryKey.setBookId(formData.getFirst("bookId"));
        
        UserBook userBook = new UserBook(); 
        userBook.setKey(userBooksPrimaryKey);
        userBook.setStartedDate(LocalDate.parse(formData.getFirst("startDate")));
        userBook.setCompletedDate(LocalDate.parse(formData.getFirst("completedDate")));
        userBook.setRating(Integer.parseInt(formData.getFirst("rating")));
        userBook.setStatus(formData.getFirst("status"));

        userBooksRepository.save(userBook);

        return new ModelAndView("redirect:/books/" + formData.getFirst("bookId"));
    }
}
