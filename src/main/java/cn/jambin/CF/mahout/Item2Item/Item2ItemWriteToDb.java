package cn.jambin.CF.mahout.Item2Item;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import org.apache.mahout.cf.taste.similarity.precompute.SimilarItem;
import org.apache.mahout.cf.taste.similarity.precompute.SimilarItems;
import org.apache.mahout.cf.taste.similarity.precompute.SimilarItemsWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Item2ItemWriteToDb implements SimilarItemsWriter {
  @Override
  public void open() {
    //加载数据库连接信息
    PropKit.use("a_little_config.txt");
    DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
    dp.start();
    arp.start();
  }
  @Override
  public void add(SimilarItems similarItems) throws IOException {
    int counter = 0;
    List<Record> list = new ArrayList<>();
    Date date = new Date();
    for (SimilarItem item: similarItems.getSimilarItems()) {
      Record record = new Record();
      record.set("id", null);
      record.set("book_id", similarItems.getItemID());
      record.set("similarity_book_id", item.getItemID());
      record.set("similarity", item.getSimilarity());
      record.set("ctime", date);
      list.add(record);
      counter++;
      if (counter>=10)
        break;
    }
    Db.batchSave("book_similarity", list, 10);
  }
  @Override
  public void close() throws IOException {
  }
}

