package com.example.Library.services;

import com.example.Library.dto.Books;
import com.example.Library.dto.User;
import com.example.Library.dto.UserHistory;
import org.springframework.stereotype.Service;

@Service
public interface Libraryservice {

    public void addBook(Books books);
    public void addUser(User user);
    public void issue_Book(UserHistory userHistory);
public UserHistory return_Book(UserHistory userHistory );
    public Books getPopularBook();
    public User getUserWithMaxBook();
}
