package com.venky.wiprotask.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Venkatesh on 03,December,2019
 */


public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
