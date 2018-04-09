package cn.jambin.dto;

import java.math.BigDecimal;

public class UserBookDto {

    private long bookId;

    private String title;

    private String author;

    private String image;

    private double rating;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = new BigDecimal(rating).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
