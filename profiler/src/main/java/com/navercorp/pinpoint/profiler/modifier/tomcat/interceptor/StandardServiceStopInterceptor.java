package com.nhn.pinpoint.profiler.modifier.tomcat.interceptor;

import com.nhn.pinpoint.bootstrap.interceptor.LifeCycleEventListener;
import com.nhn.pinpoint.bootstrap.interceptor.SimpleAroundInterceptor;
import com.nhn.pinpoint.bootstrap.interceptor.TargetClassLoader;
import com.nhn.pinpoint.bootstrap.logging.PLogger;
import com.nhn.pinpoint.bootstrap.logging.PLoggerFactory;

/**
 * @author emeroad
 */
public class StandardServiceStopInterceptor implements SimpleAroundInterceptor, TargetClassLoader {

    private final PLogger logger = PLoggerFactory.getLogger(this.getClass());
    private final boolean isDebug = logger.isDebugEnabled();

    private LifeCycleEventListener lifeCycleEventListener;

    public StandardServiceStopInterceptor(LifeCycleEventListener lifeCycleEventListener) {
        this.lifeCycleEventListener = lifeCycleEventListener;
    }

    @Override
    public void before(Object target, Object[] args) {

    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        if (isDebug) {
            logger.afterInterceptor(target, args, result, throwable);
        }
        // TODO 시작이 실패했을때 stop이 불러 지는가?
        // if (!InterceptorUtils.isSuccess(result)) {
        // return;
        // }
        lifeCycleEventListener.stop();
    }
}
