package cn.jambin.dto;

import cn.jambin.entity.User;

public class UserRatingVo extends User {

    private int rating;

    private long bookId;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
