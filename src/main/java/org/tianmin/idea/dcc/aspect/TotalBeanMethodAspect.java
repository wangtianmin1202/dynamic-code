package org.tianmin.idea.dcc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tianmin.idea.dcc.annotation.EnableDcc;
import org.tianmin.idea.dcc.service.DccService;

import java.lang.reflect.Method;

/**
 * @Author wangtianmin
 * @Date 2023/8/23 09:39
 * @Description: 全局bean方法切面拦截
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
@Order
public class TotalBeanMethodAspect {

    @Autowired
    DccService dccService;

    @Pointcut("@annotation(org.tianmin.idea.dcc.annotation.EnableDcc)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundPointCut(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            EnableDcc enableDcc = method.getAnnotation(EnableDcc.class);
            String dccCode = enableDcc.value();
            dccService.proceedBefore(dccCode, args);
            Object returnValue = joinPoint.proceed(args);
            //有后置groovy配置
            Object[] allArgs = new Object[args.length + 1];
            for (int i = 0; i < args.length; i++) {
                allArgs[i] = args[i];
            }
            allArgs[allArgs.length - 1] = returnValue;
            dccService.proceedReturn(dccCode, allArgs);
            return returnValue;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
