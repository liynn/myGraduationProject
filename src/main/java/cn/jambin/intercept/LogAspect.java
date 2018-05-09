package cn.jambin.intercept;

import cn.jambin.entity.Logs;
import cn.jambin.service.LogsService;
import cn.jambin.util.RequestUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 日志记录AOP实现
 */
@Aspect
public class LogAspect {
	@Resource
	LogsService logsService;
	// 开始时间
	private long startTime = 0L;
	// 结束时间
	private long endTime = 0L;
	@Before("execution(* *..controller..*.*(..))")
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		startTime = System.currentTimeMillis();
	}

	@After("execution(* *..controller..*.*(..))")
	public void doAfterInServiceLayer(JoinPoint joinPoint) {
	}

	@Around("execution(* *..controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		// 获取request
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		// 从注解中获取操作名称、获取响应结果
		Object result = pjp.proceed();
		endTime = System.currentTimeMillis();
		Logs logs = new Logs();
		logs.setBasePath(RequestUtil.getBasePath(request));
		logs.setIp(RequestUtil.getIpAddr(request));
		logs.setMethod(request.getMethod());
		if (request.getMethod().equalsIgnoreCase("GET")) {
			logs.setParameter(request.getQueryString());
		} else {
			logs.setParameter(ObjectUtils.toString(request.getParameterMap()));
		}
		logs.setResult(JSON.toJSONString(result));
		logs.setSpendTime((int) (endTime - startTime));
		logs.setStartTime(new Date());
		logs.setUri(request.getRequestURI());
		logs.setUrl(ObjectUtils.toString(request.getRequestURL()));
		logs.setUserAgent(request.getHeader("User-Agent"));
		logs.setUserName(ObjectUtils.toString(request.getUserPrincipal()));
		logsService.insertSelective(logs);
		return result;
	}

}
