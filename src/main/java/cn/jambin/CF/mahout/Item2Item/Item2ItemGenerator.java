
package cn.jambin.CF.mahout.Item2Item;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.precompute.MultithreadedBatchItemSimilarities;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.similarity.precompute.BatchItemSimilarities;

import java.io.File;

public final class Item2ItemGenerator {
  private Item2ItemGenerator() {}
  public static void main(String[] args) throws Exception {
    //第一步，加载评分数据集，构建DataModel
    DataModel dataModel = new FileDataModel(new File("/Users/jambin/code/data/rating.csv"));
    //第二步，计算相似度，使用基于最大似然估计计算相似度
    LogLikelihoodSimilarity logLikelihoodSimilarity = new LogLikelihoodSimilarity(dataModel);
    //第三步，构建基于物品的推荐器
    ItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, logLikelihoodSimilarity);
    //第五步，构建用于批处理的推荐器
    BatchItemSimilarities batch = new MultithreadedBatchItemSimilarities(recommender, 10);
    //第六步，执行批处理推荐器任务，任务是把计算结果写进数据库
    batch.computeItemSimilarities(Runtime.getRuntime().availableProcessors(), 1, new Item2ItemWriteToDb());
  }
}