package com.rainbow.demo.common

import org.aopalliance.intercept.Joinpoint
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.boot.actuate.metrics.GaugeService
import org.springframework.stereotype.Component

@Aspect
@Component
class ServiceMonitor {

    @Autowired
    private lateinit var counterService: CounterService

    @Autowired
    private lateinit var gaugeService: GaugeService

    //使用Aop统计接口调用次数
    @Before("execution(* com.rainbow.controller.*.*(..))")
    fun countServiceInvoke(joinPoint: JoinPoint) {
        counterService.increment("${joinPoint.signature}")
    }


    //计算接口调用延迟时间
    @Around("execution(* com.rainbow.controller.*.*(..))")
    fun latencyService(pjp: ProceedingJoinPoint) {
        val start = System.currentTimeMillis()
        pjp.proceed()
        val end = System.currentTimeMillis()
        gaugeService.submit(pjp.signature.toString(), (end - start).toDouble())
    }
}