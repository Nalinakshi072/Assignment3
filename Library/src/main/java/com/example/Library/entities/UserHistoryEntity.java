package com.example.Library.entities;


import javax.persistence.*;

@Entity
@Table(name = "UserHistoryEntity")
public class UserHistoryEntity {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)

     private int id;

    //@ManyToOne(CascadeType)
    private Integer userId;
    private Integer bookId;
    private String startDate;
    private String endDate;

    public UserHistoryEntity(){}


    public UserHistoryEntity(Integer userId, Integer bookId, String startDate, String endDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
