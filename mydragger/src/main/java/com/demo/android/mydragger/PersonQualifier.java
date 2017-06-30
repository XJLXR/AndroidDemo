package com.demo.android.mydragger;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;


/**
 * Created by XiongRun on 2017/5/20.
 */

//先自定义一个限定符@PersonQualifier：
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonQualifier {
}
