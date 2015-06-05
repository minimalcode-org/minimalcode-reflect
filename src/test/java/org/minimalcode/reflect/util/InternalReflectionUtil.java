package org.minimalcode.reflect.util;

import org.minimalcode.reflect.Bean;
import org.minimalcode.reflect.Property;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class InternalReflectionUtil {

    /**
     * Flushes the {@link Bean#beansCache}.
     */
    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "InfiniteLoopStatement"})
    public static void flushBeanCache() {
        try {
            List<float[]> memhog = new LinkedList<float[]>();
            while (true) {
                memhog.add(new float[102400]);
            }
        } catch (OutOfMemoryError e) {
            /* At this point all the unreferenced SoftReferences have been released */
        }
    }

    /**
     * Changes the status of the {@link Property.ProxyMethod#isCglibPresent}.
     *
     * @param status the new flag status
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setCglibProxy(boolean status) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> proxyMethod = Class.forName("org.minimalcode.reflect.Property$ProxyMethod");
        Field isCglibPresent = proxyMethod.getDeclaredField("isCglibPresent");// static field
        isCglibPresent.setAccessible(true);
        isCglibPresent.set(null, status);
    }
}
