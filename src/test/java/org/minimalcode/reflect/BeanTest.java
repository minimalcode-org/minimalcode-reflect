package org.minimalcode.reflect;

import org.junit.Ignore;
import org.junit.Test;
import org.minimalcode.reflect.util.GenericBean;
import org.minimalcode.reflect.util.HierarchicalBean;
import org.minimalcode.reflect.util.InternalReflectionUtil;
import org.minimalcode.reflect.util.TestableBean;

import static org.junit.Assert.*;

public class BeanTest {

    @Test
    public void testForClass() {
        assertEquals(Bean.forClass(GenericBean.class), Bean.forClass(GenericBean.class));
        assertNotEquals(Bean.forClass(GenericBean.class), Bean.forClass(HierarchicalBean.ParentBean.class));
        assertNotEquals(Bean.forClass(GenericBean.class), Bean.forClass(HierarchicalBean.ParentBean.class));
    }

    @Test
    public void testForClassWithInterface() {
        assertNotNull(Bean.forClass(TestableBean.class));
    }

    @Test
    public void testForClassWithNull() {
        try {
            Bean.forClass(null);
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().contains("null"));
        }
    }

    @Test
    @Ignore("FlushBeanCache is too time-consuming, better to enable it only manually.")
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public void testForClassSoftReference() {
        Bean.forClass(GenericBean.class);
        InternalReflectionUtil.flushBeanCache();
        assertNotNull(Bean.forClass(GenericBean.class));
    }

    @Test
    public void testGetType() {
        assertEquals(GenericBean.class, Bean.forClass(GenericBean.class).getType());
    }

    @Test
    public void testGetProperty() {
        Bean<GenericBean> genericBean = Bean.forClass(GenericBean.class);
        assertNull(genericBean.getProperty("invalid"));
        assertNotNull(genericBean.getProperty(GenericBean.CLASS_PROPERTY.getName()));
        assertNotNull(genericBean.getProperty(GenericBean.STRING_PROPERTY.getName()));
        assertNotNull(genericBean.getProperty(GenericBean.LIST_PROPERTY.getName()));
    }

    @Test
    public void testGetDeclaredProperty() {
        Bean<GenericBean> genericBean = Bean.forClass(GenericBean.class);
        assertNull(genericBean.getDeclaredProperty("invalid"));
        assertNull(genericBean.getDeclaredProperty(GenericBean.CLASS_PROPERTY.getName()));
        assertNotNull(genericBean.getDeclaredProperty(GenericBean.STRING_PROPERTY.getName()));
    }

    @Test
    public void testGetPropertyWithNull() {
        try {
            Bean.forClass(GenericBean.class).getProperty(null);
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().contains("null"));
        }
    }

    @Test
    public void testGetDeclaredPropertyWithNull() {
        try {
            Bean.forClass(GenericBean.class).getDeclaredProperty(null);
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().contains("null"));
        }
    }

    @Test
    public void testGetProperties() {
        Bean<GenericBean> genericBean = Bean.forClass(GenericBean.class);
        assertEquals(15, genericBean.getProperties().length);
    }

    @Test
    public void testGetDeclaredProperties() {
        Bean<GenericBean> genericBean = Bean.forClass(GenericBean.class);
        assertEquals(14, genericBean.getDeclaredProperties().length);// minus 'class' from Object
    }

    @Test
    public void testGetPropertiesWithStaticFields() throws NoSuchFieldException, NoSuchMethodException {
        assertNotNull(GenericBean.class.getField("staticField"));
        assertNotNull(GenericBean.class.getMethod("getStaticGetter"));
        assertNotNull(GenericBean.class.getMethod("isStaticIsser"));
        assertNotNull(GenericBean.class.getMethod("setStaticSetter", String.class));

        Bean<BeanTest> thisBean = Bean.forClass(BeanTest.class);
        assertNotNull(thisBean.getProperty("class"));
        assertNull(thisBean.getProperty("staticField"));
        assertNull(thisBean.getProperty("staticGetter"));
        assertNull(thisBean.getProperty("staticIsser"));
        assertNull(thisBean.getProperty("staticSetter"));
    }

    @Test
    public void testGetPropertiesWithInterface() {
        assertEquals(3, Bean.forClass(TestableBean.class).getProperties().length);
    }

    @Test
    public void testGetPropertiesIsAClone() {
        Bean<GenericBean> genericBean = Bean.forClass(GenericBean.class);
        Property[] properties = genericBean.getProperties();
        Property[] cloneProperties = genericBean.getProperties();

        properties[0] = null;
        assertNull(properties[0]);
        assertNotNull(cloneProperties[0]);
    }

    @Test
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    public void testEquals() {
        Bean<GenericBean> genericBean = Bean.forClass(GenericBean.class);
        Bean<GenericBean> sameGenericBean = Bean.forClass(GenericBean.class);

        assertEquals(genericBean, sameGenericBean);
        assertTrue(genericBean.equals(sameGenericBean));
        assertFalse(genericBean.equals("not-a-bean"));
    }

    @Test
    public void testHashCode() {
        assertEquals(Bean.forClass(GenericBean.class).hashCode(), GenericBean.class.getName().hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(Bean.forClass(GenericBean.class).toString(), "bean " + GenericBean.class.getName());
    }
}