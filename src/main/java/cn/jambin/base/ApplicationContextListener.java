package cn.jambin.base;
import cn.jambin.base.annotation.BaseService;
import cn.jambin.CF.CFTask;
import cn.jambin.util.PropertiesFileUtil;
import it.sauronsoftware.cron4j.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * spring容器初始化完成事件
 */
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger _log = LoggerFactory.getLogger(ApplicationContextListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            _log.debug(">>>>> spring初始化完毕 <<<<<");
            // spring初始化完毕后，通过反射调用所有使用BaseService注解的initMapper方法
            Map<String, Object> baseServices = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
            for(Object service : baseServices.values()) {
                _log.debug(">>>>> {}.initMapper()", service.getClass().getName());
                try {
                    Method initMapper = service.getClass().getMethod("initMapper");
                    initMapper.invoke(service);
                } catch (Exception e) {
                    _log.error("初始化BaseService的initMapper方法异常", e);
                    e.printStackTrace();
                }
            }

            //任务二，启动定时任务
            String cfTask = new StringBuffer(PropertiesFileUtil.getInstance("jdbc").get("cf.task")).toString();
            if ("true".equals(cfTask)){
                CFTask task = new CFTask();
                Scheduler scheduler = new Scheduler();
                scheduler.schedule("* 3 * * *", task);
                scheduler.start();
            }

        }
    }
}
