package com.fast.family.mvc.ldempotent;

import java.lang.annotation.*;

/**
 * @author 张顺
 * @version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LdempotentAnnotation {
}