package org.acme;

import io.smallrye.config.ConfigSourceInterceptor;
import io.smallrye.config.ConfigSourceInterceptorContext;
import io.smallrye.config.ConfigValue;

public class DummyInterceptor implements ConfigSourceInterceptor {

    private static final String THREAD_KEEP_ALIVE = "quarkus.thread-pool.keep-alive-time";

    @Override
    public ConfigValue getValue(ConfigSourceInterceptorContext context, String name) {
        String keepAlive = "60s";
        if (name.equals(THREAD_KEEP_ALIVE)) {
            return context.proceed(name).withValue(keepAlive);
        }
        return context.proceed(name);
    }

}
