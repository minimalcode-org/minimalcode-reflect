package org.minimalcode.reflect;

import org.junit.Test;
import org.minimalcode.reflect.util.GenericBean;
import org.minimalcode.reflect.util.InternalReflectionUtil;

import java.util.Date;

import static org.junit.Assert.*;

public class PropertyReflectionTest {

    @Test
    public void testGet() {
        GenericBean genericBean = new GenericBean();
        assertNull(GenericBean.STRING_PROPERTY.get(genericBean));

        final String newValue = "new-value";
        genericBean.setStringProperty(newValue);
        assertEquals(newValue, GenericBean.STRING_PROPERTY.get(genericBean));
    }

    @Test
    public void testGetWithGeneric() {
        final String newValue = "new-value";
        GenericBean genericBean = new GenericBean();
        genericBean.setGenericStringProperty(newValue);
        assertEquals(newValue, GenericBean.GENERIC_STRING_PROPERTY.get(genericBean));
    }

    @Test
    public void tesGetWithoutCglib() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        InternalReflectionUtil.setCglibProxy(false);
        testGet();
        InternalReflectionUtil.setCglibProxy(true);
    }

    @Test
    public void testGetWithWriteOnlyProperty() {
        try {
            GenericBean.SETTER_PROPERTY.get(new GenericBean());
            fail();
        } catch (ReflectionException e) {
            assertTrue(e.getMessage().contains(GenericBean.SETTER_PROPERTY.getName()));
        }
    }

    @Test
    public void testGetWithNullObject() {
        try {
            GenericBean.STRING_PROPERTY.get(null);
            fail();
        } catch (ReflectionException e) {
            assertTrue(e.getMessage().contains(GenericBean.STRING_PROPERTY.getName()));
        }
    }

    @Test
    public void testGetWithIllegalObject() {
        try {
            GenericBean.STRING_PROPERTY.get(new Date());// Not the declaringClass
            fail();
        } catch (ReflectionException e) {
            assertTrue(e.getMessage().contains(GenericBean.STRING_PROPERTY.getName()));
        }
    }

    @Test
    public void testSet() {
        GenericBean genericBean = new GenericBean();

        final String newValue = "new-value";
        GenericBean.STRING_PROPERTY.set(genericBean, newValue);
        assertEquals(newValue, GenericBean.STRING_PROPERTY.get(genericBean));
    }

    @Test
    public void testSetWithGeneric() {
        final String newValue = "new-value";
        GenericBean genericBean = new GenericBean();

        GenericBean.GENERIC_STRING_PROPERTY.set(genericBean, newValue);
        assertEquals(newValue, genericBean.getGenericStringProperty());
    }

    @Test
    public void testSetIllegalType() {
        try {
            GenericBean.STRING_PROPERTY.set(new GenericBean(), 123);// illegal value int to String
        } catch (ReflectionException e) {
            assertTrue(e.getMessage().contains(GenericBean.STRING_PROPERTY.getName()));
        }
    }

    @Test
    public void testSetWithNullObject() {
        try {
            GenericBean.STRING_PROPERTY.set(null, null);
            fail();
        } catch (ReflectionException e) {
            assertTrue(e.getMessage().contains(GenericBean.STRING_PROPERTY.getName()));
        }
    }

    @Test
    public void testSetWithoutCglib() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        InternalReflectionUtil.setCglibProxy(false);
        testSet();
        InternalReflectionUtil.setCglibProxy(true);
    }

    @Test
    public void testSetWithReadOnlyProperty() {
        try {
            GenericBean.GETTER_PROPERTY.set(new GenericBean(), null);
            fail();
        } catch (ReflectionException e) {
            assertTrue(e.getMessage().contains(GenericBean.GETTER_PROPERTY.getName()));
        }
    }
}
