package cn.jambin.CF;

import cn.jambin.CF.mahout.Item2Item.Item2ItemGenerator;
import cn.jambin.CF.mahout.ItemRecommenderDoubanBook;
import cn.jambin.CF.mahout.UserRecommenderDoubanBook;
import cn.jambin.CF.spark.BookALS;

import java.util.Date;

public class CFTask implements Runnable{

    public void run(){
        try {
            System.out.println("定时任务开启");
//            Item2ItemGenerator.main(null);
            ItemRecommenderDoubanBook.main(null);
            System.out.println("itemCF任务完成");
            BookALS.main(null);
            System.out.println("ALS任务完成");
            UserRecommenderDoubanBook.main(null);
            System.out.println("userCF任务完成");
//            System.out.println(new Date());

        } catch (Exception e){

        }


    }
}
