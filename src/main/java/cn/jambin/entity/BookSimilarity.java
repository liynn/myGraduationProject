package cn.jambin.entity;

public class BookSimilarity {
    private Integer id;

    private Integer bookId;

    private Integer similarityBookId;

    private Double similarity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getSimilarityBookId() {
        return similarityBookId;
    }

    public void setSimilarityBookId(Integer similarityBookId) {
        this.similarityBookId = similarityBookId;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }
}