package cn.jambin.CF.mahout;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.Date;
import java.util.List;

public class UserRecommenderDoubanBook {
    private UserRecommenderDoubanBook(){
    }

    public static void main(String[] args) throws Exception {

        DataModel dataModel = new FileDataModel(new File("/Users/jambin/code/data/rating.csv"));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, dataModel);
//        Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
        RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, dataModel);
                return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
            }
        };
        Recommender recommender = recommenderBuilder.buildRecommender(dataModel);
        //Evaluate
        RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
        double score = evaluator.evaluate(recommenderBuilder, null, dataModel, 0.9, 0.5);
        System.out.println("基于Pearson相关系数的User-based CF的RMSE是"+score);

        PropKit.use("a_little_config.txt");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        dp.start();
        arp.start();
        LongPrimitiveIterator iterator = dataModel.getUserIDs();
        long userId;
        Date date = new Date();
        while (iterator.hasNext()){
            userId = iterator.nextLong();
            List<RecommendedItem> recommendedItems = recommender.recommend(userId, 10);
            for (RecommendedItem recommendedItem: recommendedItems){
                Record re = new Record();
                re.set("userId", userId);
                re.set("bookId", recommendedItem.getItemID());
                re.set("rating", recommendedItem.getValue());
                re.set("ctime", date);
                Db.save("userbased_recommend", re);
            }
        }
        System.out.println("Recommended for "+dataModel.getNumUsers()+" users ");
    }
}
