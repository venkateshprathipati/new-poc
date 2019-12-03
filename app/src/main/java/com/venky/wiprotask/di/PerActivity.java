package com.venky.wiprotask.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Venkatesh on 03,December,2019
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
