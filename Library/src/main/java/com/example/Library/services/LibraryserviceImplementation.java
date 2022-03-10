package com.example.Library.services;

import com.example.Library.dto.Books;
import com.example.Library.dto.User;
import com.example.Library.dto.UserHistory;
import com.example.Library.entities.BooksEntity;
import com.example.Library.entities.UserEntity;
import com.example.Library.entities.UserHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Library.repository.BooksRepository;
import com.example.Library.repository.UserHistoryRepository;
import com.example.Library.repository.UserRepository;

import java.util.*;

@Service
public class LibraryserviceImplementation implements Libraryservice {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    UserHistoryRepository userHistoryRepository;



    @Override
    public void addBook(Books books) {
        booksRepository.save(new BooksEntity(books.getId(), books.getName(), books.getWriter(), books.getSummary(), books.getCategory()));

    }

    @Override
    public void addUser(User user) {
        userRepository.save(new UserEntity(user.getId(), user.getName(), user.getAddress(), user.getPhoneNumber()));

    }

    @Override
    public void issue_Book(UserHistory userHistory) {

        userHistoryRepository.save(new UserHistoryEntity(userHistory.getUserId(), userHistory.getBookId(), userHistory.getStartDate(), userHistory.getEndDate()));
    }


    @Override
    public UserHistory return_Book(UserHistory userHistory) {

        String date = "";
        try {

            Optional<UserHistoryEntity> byUserIdAndBookId = userHistoryRepository.findByUserIdAndBookId(userHistory.getUserId(),
                    userHistory.getBookId());
            if (byUserIdAndBookId.isPresent()) {
                UserHistoryEntity userHistoryEntity = byUserIdAndBookId.get();
                userHistoryEntity.setEndDate(userHistory.getEndDate());
                userHistoryRepository.save(userHistoryEntity);
                return userHistory;
            } else {

                throw new myexception("user or book not found");
            }
        } catch (myexception e) {
            System.out.println(e);
        }

        return null;
    }


    public static <E> E commonObject(List<E> list) {
        Map<E, Integer> map = new HashMap<>();

        for (E t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);

        }

        Map.Entry<E, Integer> maxObject = null;

        for (Map.Entry<E, Integer> e : map.entrySet()) {
            if (maxObject == null || e.getValue() > maxObject.getValue())
                maxObject = e;
        }

        return maxObject.getKey();
    }


    public int bookcount() {
        Iterable<UserHistoryEntity> iterable = userHistoryRepository.findAll();
        List<UserHistory> historylist = new ArrayList<>();
        for (UserHistoryEntity i : iterable) {
            historylist.add(new UserHistory(i.getId(), i.getUserId(), i.getBookId(), i.getStartDate(), i.getEndDate()));
        }
        ArrayList<Integer> bookid = new ArrayList<>();
        for (UserHistory i : historylist) {
            bookid.add(i.getBookId());
        }

        Integer maxbookid = commonObject(bookid);
        return maxbookid;
    }

    public int usercount() {
        Iterable<UserHistoryEntity> iterable = userHistoryRepository.findAll();
        List<UserHistory> userlist = new ArrayList<>();
        for (UserHistoryEntity i : iterable) {
            userlist.add(new UserHistory(i.getId(), i.getUserId(), i.getBookId(), i.getStartDate(), i.getEndDate()));
        }
        ArrayList<Integer> bookid = new ArrayList<>();
        for (UserHistory i : userlist) {
            bookid.add(i.getUserId());
        }

        Integer maxuserid = commonObject(bookid);
        return maxuserid;
    }


    @Override
    public Books getPopularBook() {


        Integer maxbookid = bookcount();


        Iterable<BooksEntity> books = booksRepository.findAll();
        Books popularbook = new Books();
        for (BooksEntity i : books) {
            if (i.getId() == maxbookid) {
                popularbook.setId(i.getId());
                popularbook.setName(i.getName());
                popularbook.setWriter(i.getWriter());
                popularbook.setSummary(i.getSummary());
                popularbook.setCategory(i.getCategory());
            }
        }


        return popularbook;
    }


    @Override
    public User getUserWithMaxBook() {


        Integer maxusercount = usercount();

        Iterable<UserEntity> userEntities = userRepository.findAll();
        User user = new User();
        for (UserEntity i : userEntities) {
            if (i.getId() == maxusercount) {
                user.setId(i.getId());
                user.setName(i.getName());
                user.setAddress(i.getAddress());
                user.setPhoneNumber(i.getPhoneNumber());

            }
        }
        return user;
    }


}


