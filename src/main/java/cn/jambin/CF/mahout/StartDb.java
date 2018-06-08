package cn.jambin.CF.mahout;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;

import java.util.Date;

public class StartDb {
    public static void main(String[] args) {
        PropKit.use("a_little_config.txt");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        // 所有映射在 MappingKit 中自动化搞定
        // 与 jfinal web 环境唯一的不同是要手动调用一次相关插件的start()方法
        dp.start();
        arp.start();
        test();
    }

    public static void test(){
        Record re = new Record();
        re.set("user_id", 999);
        re.set("book_id", 999);
        re.set("rating", 4);
        re.set("ctime", new Date());
        Db.save("itembased_recommend", re);
    }

}
