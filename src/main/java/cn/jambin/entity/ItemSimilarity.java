package cn.jambin.entity;

public class ItemSimilarity {
    private Integer id;

    private Integer itemId;

    private Integer similarityItemId;

    private Double similarity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSimilarityItemId() {
        return similarityItemId;
    }

    public void setSimilarityItemId(Integer similarityItemId) {
        this.similarityItemId = similarityItemId;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }
}