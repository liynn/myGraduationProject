package cn.jambin.util;

import cn.jambin.model._MappingKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

public class JfinalDb {

    public static void start(){
        PropKit.use("a_little_config.txt");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        // 所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        // 与 jfinal web 环境唯一的不同是要手动调用一次相关插件的start()方法
        dp.start();
        arp.start();
    }
}
