package org.minimalcode.reflect.util;

import org.minimalcode.reflect.Bean;
import org.minimalcode.reflect.Property;

import java.util.Map;

@SuppressWarnings("unused")
public interface TestableBean<T, K> {

    Bean BEAN = Bean.forClass(TestableBean.class);
    Property GENERIC_STRING_PROPERTY = BEAN.getProperty("genericStringProperty");
    Property GENERIC_MAP_PROPERTY = BEAN.getProperty("genericMapProperty");
    Property GENERIC_WILDCARD_ITERABLE_PROPERTY = BEAN.getProperty("genericWildcardIterableProperty");

    T getGenericStringProperty();

    void setGenericStringProperty(T string);

    Map<String, K> getGenericMapProperty();

    Iterable<? extends K> getGenericWildcardIterableProperty();
}
