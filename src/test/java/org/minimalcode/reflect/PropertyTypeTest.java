package org.minimalcode.reflect;

import org.junit.Test;
import org.minimalcode.reflect.util.GenericBean;
import org.minimalcode.reflect.util.TestableBean;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PropertyTypeTest {

    @Test
    public void testGetType() {
        assertEquals(int.class, GenericBean.INT_PROPERTY.getType());
        assertEquals(String.class, GenericBean.STRING_PROPERTY.getType());
        assertEquals(String[].class, GenericBean.ARRAY_PROPERTY.getType());
        assertEquals(List.class, GenericBean.LIST_PROPERTY.getType());
        assertEquals(Iterable.class, GenericBean.ITERABLE_PROPERTY.getType());
        assertEquals(List.class, GenericBean.GETTER_PROPERTY.getType());
        assertEquals(boolean.class, GenericBean.ISSER_PROPERTY.getType());
        assertEquals(List.class, GenericBean.SETTER_PROPERTY.getType());
        assertEquals(String.class, GenericBean.GENERIC_STRING_PROPERTY.getType());
        assertEquals(Map.class, GenericBean.GENERIC_MAP_PROPERTY.getType());
        assertEquals(Iterable.class, GenericBean.GENERIC_WILDCARD_ITERABLE_PROPERTY.getType());
    }

    @Test
    public void testGetActualType() {
        assertEquals(int.class, GenericBean.INT_PROPERTY.getActualType());
        assertEquals(String.class, GenericBean.STRING_PROPERTY.getActualType());
        assertEquals(String.class, GenericBean.ARRAY_PROPERTY.getActualType());
        assertEquals(String.class, GenericBean.LIST_PROPERTY.getActualType());
        assertEquals(String.class, GenericBean.ITERABLE_PROPERTY.getActualType());
        assertEquals(String.class, GenericBean.GETTER_PROPERTY.getActualType());
        assertEquals(boolean.class, GenericBean.ISSER_PROPERTY.getActualType());
        assertEquals(String.class, GenericBean.SETTER_PROPERTY.getActualType());
        assertEquals(String.class, GenericBean.GENERIC_STRING_PROPERTY.getActualType());
        assertEquals(String.class, GenericBean.GENERIC_MAP_PROPERTY.getActualType());
        assertEquals(Object.class, TestableBean.GENERIC_STRING_PROPERTY.getActualType());

        // Unresolved generic
        assertEquals(null, GenericBean.GENERIC_WILDCARD_ITERABLE_PROPERTY.getActualType());// can be resolved
        assertEquals(null, TestableBean.GENERIC_MAP_PROPERTY.getActualType());
        assertEquals(null, TestableBean.GENERIC_WILDCARD_ITERABLE_PROPERTY.getActualType());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testGetGenericType() {
        assertEquals(GenericBean.GETTER_PROPERTY.getReadMethod().getGenericReturnType(), GenericBean.GETTER_PROPERTY.getGenericType());
        assertEquals(GenericBean.SETTER_PROPERTY.getWriteMethod().getGenericParameterTypes()[0], GenericBean.SETTER_PROPERTY.getGenericType());
    }
}
