package cn.jambin.util;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;

import java.util.List;

public class DealTag {

    public static void main(String[] args) {
        PropKit.use("a_little_config.txt");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        // 所有映射在 MappingKit 中自动化搞定
//        _MappingKit.mapping(arp);
        // 与 jfinal web 环境唯一的不同是要手动调用一次相关插件的start()方法
        dp.start();
        arp.start();

        List<Record> list = Db.find("select book_id, tags from book ");

        for (Record record: list){
            try {
                String str = record.getStr("tags");
                if (str==null||str.indexOf(",")<0){
                    System.err.println("书籍标签不存在："+record.get("book_id"));
                    continue;
                }

                for (String tag : str.split(",")){
                    Record tagRecord = Db.findFirst("select id from tag where name=? limit 1", tag);
                    if (tagRecord == null){
                        tagRecord = new Record();
                        tagRecord.set("name", tag);
                        tagRecord.set("title", tag);
                        Db.save("tag", tagRecord);
                        tagRecord = Db.findFirst("select id from tag where name=? limit 1", tag);
                    }
                    Long id = tagRecord.getLong("id");
                    Record bookTag = new Record();
                    bookTag.set("book_id", record.getLong("book_id"));
                    bookTag.set("tag_id", id);
                    Db.save("book_tag", bookTag);
                }
            }catch (Exception e){
                System.err.println("书籍标签出错："+record.get("book_id"));
                continue;
            }

//            System.out.println("书籍完成："+record.get("book_id"));
        }
    }
}
