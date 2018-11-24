package com.br.doglove.backgroundCommunication

import android.content.Context

import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit


abstract class BackgroundCommunicationAction {

    protected fun schedule(context: Context,
                           backgroundCommunication: BackgroundCommunication,
                           initialDelay: Long,
                           delayBetweenExections: Long,
                           timeUnit: TimeUnit): ScheduledThreadPoolExecutor {

        val executor = ScheduledThreadPoolExecutor(1)
        executor.scheduleWithFixedDelay(PeriodicExecution(context, backgroundCommunication), initialDelay, delayBetweenExections, timeUnit)
        return executor
    }
}
