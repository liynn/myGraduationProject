package cn.jambin.CF.spark;

import cn.jambin.model.AlsRating;
import cn.jambin.util.JfinalDb;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.Date;

public class BookALS {

    public static class Rating implements Serializable {

        private static final long serialVersionUID = 1L;
        private int userId;
        private int bookId;
        private float rating;

        public Rating() {
        }

        public Rating(int userId, int bookId, float rating) {
            this.userId = userId;
            this.bookId = bookId;
            this.rating = rating;
        }

        public int getUserId() {
            return userId;
        }

        public int getBookId() {
            return bookId;
        }

        public float getRating() {
            return rating;
        }


        public static Rating parseRating(String str) {
            String[] fields = str.split(",");
            if (fields.length != 3) {
                throw new IllegalArgumentException("Each line must contain 3 fields");
            }
            int userId = Integer.parseInt(fields[0]);
            int bookId = Integer.parseInt(fields[1]);
            float rating = Float.parseFloat(fields[2]);
            return new Rating(userId, bookId, rating);
        }
    }

    public static void main(String[] args) throws Exception{
        // 使用本地所有可用线程local[*]模式
        SparkSession spark = SparkSession.builder().master("local[*]").appName("JavaALSExample").getOrCreate();
        //加载评分数据集并转化为JavaRDD
        JavaRDD<Rating> ratingsRDD = spark
                .read().textFile("/Users/jambin/code/data/rating.csv").javaRDD()
                .map(Rating::parseRating);
        Dataset<Row> ratings = spark.createDataFrame(ratingsRDD, Rating.class);
        // 拆分数据，80%的训练集和20%的测试集
        Dataset<Row>[] splits = ratings.randomSplit(new double[] { 0.8, 0.2 },2);
        Dataset<Row> trainingData = splits[0];
        Dataset<Row> testingData = splits[1];
        // 对训练数据集使用ALS算法构建建议模型
        ALS als = new ALS().setRank(10).setMaxIter(10).setRegParam(0.2)
                .setUserCol("userId").setItemCol("bookId").setRatingCol("rating")
                .setImplicitPrefs(true).setAlpha(1.5);
        ALSModel model = als.fit(trainingData);
        model.setColdStartStrategy("drop");
        // 通过计算均方根误差RMSE(Root Mean Squared Error)来评估模型
        Dataset<Row> predictions = model.transform(testingData);

        RegressionEvaluator evaluator = new RegressionEvaluator().setMetricName("rmse")
                .setLabelCol("rating").setPredictionCol("prediction");
        double rmse = evaluator.evaluate(predictions);
        // 打印均方根误差
        System.out.println("ALS均方根误差RMSE = " + rmse);
//        if(true)
//            return;
        //为每一个用户推荐十本书籍
        Dataset<Row> userRecs = model.recommendForAllUsers(10);
//        predictUser(userRecs, 3);
        //保存推荐结果到数据库
        saveToDb(userRecs);
    }
// 注意下面使用冷启动策略drop，确保不会有NaN评估指标
//        model.setColdStartStrategy("drop");

    public static void saveToDb(Dataset<Row> userRecs){
        Date date = new Date();
        try {
            JfinalDb.start();
            userRecs.javaRDD().foreach(
                    row->
                            row.getList(1).forEach(
                                    arr->{
                                        String str = arr.toString();
                                        str = str.substring(1,str.length()-1);
                                        int userId = row.getInt(0);
                                        System.out.println(userId+","+str);
                                        try {
                                            AlsRating alsRating = new AlsRating();
                                            alsRating.setUserId(userId);
                                            alsRating.setBookId(Integer.parseInt(str.split(",")[0]));
                                            alsRating.setRating(Double.parseDouble(str.split(",")[1]));
                                            alsRating.setCtime(date);
                                            alsRating.save();
                                        }catch (Exception x){

                                        }


                                    })
            );
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
    }

    public static void predictUser(Dataset<Row> userRecs, int userId) {
        userRecs.javaRDD().filter(row -> row.getInt(0) == userId).foreach(
                row -> row.getList(1).forEach(
                        line -> {
                            String str = line.toString();
                            str = str.substring(1,str.length()-1);
                            System.out.println(userId+","+str);
                            try {
                                AlsRating alsRating = new AlsRating();
                                alsRating.setUserId(userId);
                                alsRating.setBookId(Integer.parseInt(str.split(",")[0]));
                                alsRating.setRating(Double.parseDouble(str.split(",")[1]));
                                alsRating.save();
                            }catch (Exception x){

                            }
                        }

                )
        );
    }
}
