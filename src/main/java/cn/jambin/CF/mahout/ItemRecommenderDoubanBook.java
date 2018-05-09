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
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
public class ItemRecommenderDoubanBook {
    private ItemRecommenderDoubanBook(){
    }

    public static void main(String[] args) throws Exception {

        DataModel dataModel = new FileDataModel(new File("/Users/jambin/code/data/rating.csv"));
        // Build the same recommender for testing that we did last time:
        RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel model) throws TasteException {
                ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
                return new GenericItemBasedRecommender(model, similarity);
            }
        };
        //Evaluate
        RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
        double score = evaluator.evaluate(recommenderBuilder, null, dataModel, 0.9, 0.5);
        System.out.println("基于Pearson相关系数的Item-based CF的RMSE是"+score);
        if (true)
            return;
        Recommender recommender = recommenderBuilder.buildRecommender(dataModel);

        PropKit.use("a_little_config.txt");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        // 所有映射在 MappingKit 中自动化搞定
        // 与 jfinal web 环境唯一的不同是要手动调用一次相关插件的start()方法
        dp.start();
        arp.start();
        LongPrimitiveIterator iterator = dataModel.getUserIDs();
        long userId;
        Date date = new Date();
        while (iterator.hasNext()){
            userId = iterator.nextLong();
            List<RecommendedItem> recommendedItems = recommenderBuilder.buildRecommender(dataModel).recommend(userId, 10);
            for (RecommendedItem recommendedItem: recommendedItems){
                Record re = new Record();
                re.set("user_id", userId);
                re.set("book_id", recommendedItem.getItemID());
                re.set("rating", recommendedItem.getValue());
                re.set("ctime", date);
                Db.save("itembased_recommend", re);
                re=null;
            }
        }
        System.out.println("Recommended for "+dataModel.getNumUsers()+" users ");
    }
}
