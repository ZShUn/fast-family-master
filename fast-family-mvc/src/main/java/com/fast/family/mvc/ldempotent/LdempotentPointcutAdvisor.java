package com.fast.family.mvc.ldempotent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LdempotentPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {


    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        LdempotentAnnotation annotation = method.getAnnotation(LdempotentAnnotation.class);
        if (annotation != null) {
            return true;
        }
        return false;
    }

}
