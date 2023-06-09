package org.acme;

import org.jboss.logging.Logger;

import io.smallrye.config.ConfigSourceInterceptor;
import io.smallrye.config.ConfigSourceInterceptorContext;
import io.smallrye.config.ConfigValue;

public class DummyInterceptor implements ConfigSourceInterceptor {

    private static final String THREAD_KEEP_ALIVE = "quarkus.thread-pool.keep-alive-time";

    private static final Logger LOG = Logger.getLogger(DummyInterceptor.class);

    @Override
    public ConfigValue getValue(ConfigSourceInterceptorContext context, String name) {
        String keepAlive = "60s";
        if (name.equals(THREAD_KEEP_ALIVE)) {
            ConfigValue configValue = context.proceed(name);
            if (configValue != null) {
                LOG.infof("keepalive config set to %s", keepAlive);
                configValue = configValue.withValue(keepAlive);
            }
            return configValue;
        }
        return context.proceed(name);
    }

}
