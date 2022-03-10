package com.example.Library.controller;


import com.example.Library.dto.Books;
import com.example.Library.dto.User;
import com.example.Library.dto.UserHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Library.services.LibraryserviceImplementation;

import javax.validation.Valid;

@RestController
@RequestMapping("Library")

public class LibrayControl {


    @Autowired
    LibraryserviceImplementation serviceApiImplementation;


    @PostMapping(value = "/BooksEntity",consumes = "application/json")
    public void addBooks(@RequestBody @Valid Books books) {
        serviceApiImplementation.addBook(books);
    }

    @PostMapping(value = "/UserEntity", consumes = "application/json")
    public void addUser(@RequestBody @Valid User user) {
        serviceApiImplementation.addUser(user);
    }

   @PostMapping(value="/issue" , consumes = "application/json")
     public void isuue_Book(@RequestBody @Valid UserHistory userHistory){
      serviceApiImplementation.issue_Book(userHistory);
    }

    @PutMapping(value="/Return" , consumes = "application/json")
   public UserHistory returnBook(@RequestBody @Valid UserHistory userBookHistory){
    return serviceApiImplementation.return_Book(userBookHistory);
    }

 @GetMapping(value = "/getfamousbook")
    public Books getAllImformationBook() {
        return serviceApiImplementation.getPopularBook();
    }

    @GetMapping(value = "/getuser")
    public User getImformationUserMaxBooks(){
        return serviceApiImplementation.getUserWithMaxBook();
    }


}
