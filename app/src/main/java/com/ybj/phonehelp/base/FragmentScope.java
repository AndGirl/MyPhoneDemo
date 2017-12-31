package com.ybj.phonehelp.base;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by 杨阳洋 on 2018/1/1.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
