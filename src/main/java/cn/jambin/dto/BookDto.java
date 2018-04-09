package cn.jambin.dto;

import cn.jambin.entity.BookWithBLOBs;

import java.math.BigDecimal;

public class BookDto extends BookWithBLOBs {
    private Double avgRating;
    private Integer total;

    private Integer r1;
    private Integer r2;
    private Integer r3;
    private Integer r4;
    private Integer r5;

    private Double ratio1;
    private Double ratio2;
    private Double ratio3;
    private Double ratio4;
    private Double ratio5;

    public void initData(){
        this.setRatio1(deciMal(r1, total)*100);
        this.setRatio2(deciMal(r2, total)*100);
        this.setRatio3(deciMal(r3, total)*100);
        this.setRatio4(deciMal(r4, total)*100);
        this.setRatio5(deciMal(r5, total)*100);
//        this.setAvgRating(new BigDecimal(avgRating).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());

        this.setCatalog(this.getCatalog().replace("\n", "<br>"));
        this.setAuthorIntro(this.getAuthorIntro().replace("\n", "<br>"));
        this.setSummary(this.getSummary().replace("\n", "<br>"));
    }

    private double deciMal(int top, int below) {
        double result = new BigDecimal((float)top / below).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }



    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = new BigDecimal(avgRating).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getR1() {
        return r1;
    }

    public void setR1(Integer r1) {
        this.r1 = r1;
    }

    public Integer getR2() {
        return r2;
    }

    public void setR2(Integer r2) {
        this.r2 = r2;
    }

    public Integer getR3() {
        return r3;
    }

    public void setR3(Integer r3) {
        this.r3 = r3;
    }

    public Integer getR4() {
        return r4;
    }

    public void setR4(Integer r4) {
        this.r4 = r4;
    }

    public Integer getR5() {
        return r5;
    }

    public void setR5(Integer r5) {
        this.r5 = r5;
    }

    public Double getRatio1() {
        return ratio1;
    }

    public void setRatio1(Double ratio1) {
        this.ratio1 = ratio1;
    }

    public Double getRatio2() {
        return ratio2;
    }

    public void setRatio2(Double ratio2) {
        this.ratio2 = ratio2;
    }

    public Double getRatio3() {
        return ratio3;
    }

    public void setRatio3(Double ratio3) {
        this.ratio3 = ratio3;
    }

    public Double getRatio4() {
        return ratio4;
    }

    public void setRatio4(Double ratio4) {
        this.ratio4 = ratio4;
    }

    public Double getRatio5() {
        return ratio5;
    }

    public void setRatio5(Double ratio5) {
        this.ratio5 = ratio5;
    }
}
