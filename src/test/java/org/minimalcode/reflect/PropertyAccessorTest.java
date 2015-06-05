package org.minimalcode.reflect;

import org.junit.Test;
import org.minimalcode.reflect.util.GenericBean;
import org.minimalcode.reflect.util.HierarchicalBean;

import java.util.Locale;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class PropertyAccessorTest {

    @Test
    public void testAccessorHierarchy() {
        // Ancestor
        assertEquals(1, HierarchicalBean.ANCESTOR_BEAN.getProperties().length);// class
        assertNotNull(HierarchicalBean.ANCESTOR_CLASS_PROPERTY);
        assertNull(HierarchicalBean.ANCESTOR_FIRST_PROPERTY_NULL);
        assertNull(HierarchicalBean.ANCESTOR_SECOND_PROPERTY_NULL);
        assertNull(HierarchicalBean.ANCESTOR_THIRD_PROPERTY_NULL);

        // Ancestor Declared
        assertEquals(1, HierarchicalBean.ANCESTOR_BEAN.getDeclaredProperties().length);// first
        assertNull(HierarchicalBean.ANCESTOR_CLASS_DECLARED_PROPERTY_NULL);
        assertNotNull(HierarchicalBean.ANCESTOR_FIRST_DECLARED_PROPERTY);
        assertNull(HierarchicalBean.ANCESTOR_SECOND_DECLARED_PROPERTY_NULL);
        assertNull(HierarchicalBean.ANCESTOR_THIRD_DECLARED_PROPERTY_NULL);

        // Parent
        assertEquals(3, HierarchicalBean.PARENT_BEAN.getProperties().length);// class, first, second
        assertNotNull(HierarchicalBean.PARENT_CLASS_PROPERTY);
        assertNotNull(HierarchicalBean.PARENT_FIRST_PROPERTY);
        assertNotNull(HierarchicalBean.PARENT_SECOND_PROPERTY);
        assertNull(HierarchicalBean.PARENT_THIRD_PROPERTY_NULL);

        // Parent Declared
        assertEquals(3, HierarchicalBean.PARENT_BEAN.getDeclaredProperties().length);// first, second, third
        assertNull(HierarchicalBean.PARENT_CLASS_DECLARED_PROPERTY_NULL);
        assertNotNull(HierarchicalBean.PARENT_FIRST_DECLARED_PROPERTY);
        assertNotNull(HierarchicalBean.PARENT_SECOND_DECLARED_PROPERTY);
        assertNotNull(HierarchicalBean.PARENT_THIRD_DECLARED_PROPERTY);

        // Child
        assertEquals(4, HierarchicalBean.CHILD_BEAN.getProperties().length);// class, first, second, third
        assertNotNull(HierarchicalBean.CHILD_CLASS_PROPERTY);
        assertNotNull(HierarchicalBean.CHILD_FIRST_PROPERTY);
        assertNotNull(HierarchicalBean.CHILD_SECOND_PROPERTY);
        assertNotNull(HierarchicalBean.CHILD_THIRD_PROPERTY);

        // Child Declared
        assertEquals(3, HierarchicalBean.CHILD_BEAN.getDeclaredProperties().length);// first, second, third
        assertNull(HierarchicalBean.CHILD_CLASS_DECLARED_PROPERTY_NULL);
        assertNotNull(HierarchicalBean.CHILD_FIRST_DECLARED_PROPERTY);
        assertNotNull(HierarchicalBean.CHILD_SECOND_DECLARED_PROPERTY);
        assertNotNull(HierarchicalBean.CHILD_THIRD_DECLARED_PROPERTY);
    }

    @Test
    public void testAccessorsOverrideHierarchy() {
        // Ancestor
        assertNull(HierarchicalBean.ANCESTOR_CLASS_PROPERTY.getField());
        assertNotNull(HierarchicalBean.ANCESTOR_CLASS_PROPERTY.getReadMethod());
        assertNull(HierarchicalBean.ANCESTOR_CLASS_PROPERTY.getWriteMethod());

        // Ancestor Declared
        assertNotNull(HierarchicalBean.ANCESTOR_FIRST_DECLARED_PROPERTY.getField());
        assertNotNull(HierarchicalBean.ANCESTOR_FIRST_DECLARED_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.ANCESTOR_FIRST_DECLARED_PROPERTY.getWriteMethod());

        // Parent
        assertNull(HierarchicalBean.PARENT_CLASS_PROPERTY.getField());
        assertNotNull(HierarchicalBean.PARENT_CLASS_PROPERTY.getReadMethod());
        assertNull(HierarchicalBean.PARENT_CLASS_PROPERTY.getWriteMethod());

        assertNotNull(HierarchicalBean.PARENT_FIRST_PROPERTY.getField());
        assertNotNull(HierarchicalBean.PARENT_FIRST_PROPERTY.getReadMethod());
        assertNull(HierarchicalBean.PARENT_FIRST_PROPERTY.getWriteMethod());

        assertNotNull(HierarchicalBean.PARENT_SECOND_PROPERTY.getField());
        assertNull(HierarchicalBean.PARENT_SECOND_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.PARENT_SECOND_PROPERTY.getWriteMethod());

        // Parent Declared
        assertNotNull(HierarchicalBean.PARENT_FIRST_DECLARED_PROPERTY.getField());
        assertNotNull(HierarchicalBean.PARENT_FIRST_DECLARED_PROPERTY.getReadMethod());
        assertNull(HierarchicalBean.PARENT_FIRST_DECLARED_PROPERTY.getWriteMethod());

        assertNotNull(HierarchicalBean.PARENT_SECOND_DECLARED_PROPERTY.getField());
        assertNull(HierarchicalBean.PARENT_SECOND_DECLARED_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.PARENT_SECOND_DECLARED_PROPERTY.getWriteMethod());

        assertNull(HierarchicalBean.PARENT_THIRD_DECLARED_PROPERTY.getField());
        assertNull(HierarchicalBean.PARENT_THIRD_DECLARED_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.PARENT_THIRD_DECLARED_PROPERTY.getWriteMethod());

        // Child
        assertNull(HierarchicalBean.CHILD_CLASS_PROPERTY.getField());
        assertNotNull(HierarchicalBean.CHILD_CLASS_PROPERTY.getReadMethod());
        assertNull(HierarchicalBean.CHILD_CLASS_PROPERTY.getWriteMethod());

        assertNotNull(HierarchicalBean.CHILD_FIRST_PROPERTY.getField());
        assertNotNull(HierarchicalBean.CHILD_FIRST_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.CHILD_FIRST_PROPERTY.getWriteMethod());

        assertNotNull(HierarchicalBean.CHILD_SECOND_PROPERTY.getField());
        assertNotNull(HierarchicalBean.CHILD_SECOND_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.CHILD_SECOND_PROPERTY.getWriteMethod());

        assertNull(HierarchicalBean.CHILD_THIRD_PROPERTY.getField());
        assertNull(HierarchicalBean.CHILD_THIRD_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.CHILD_THIRD_PROPERTY.getWriteMethod());

        // Child Declared
        assertNotNull(HierarchicalBean.CHILD_FIRST_DECLARED_PROPERTY.getField());
        assertNull(HierarchicalBean.CHILD_FIRST_DECLARED_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.CHILD_FIRST_DECLARED_PROPERTY.getWriteMethod());

        assertNotNull(HierarchicalBean.CHILD_SECOND_DECLARED_PROPERTY.getField());
        assertNotNull(HierarchicalBean.CHILD_SECOND_DECLARED_PROPERTY.getReadMethod());
        assertNull(HierarchicalBean.CHILD_SECOND_DECLARED_PROPERTY.getWriteMethod());

        assertNull(HierarchicalBean.CHILD_THIRD_DECLARED_PROPERTY.getField());
        assertNull(HierarchicalBean.CHILD_THIRD_DECLARED_PROPERTY.getReadMethod());
        assertNotNull(HierarchicalBean.CHILD_THIRD_DECLARED_PROPERTY.getWriteMethod());
    }

    @Test
    public void testAccessorsWithGeneric() {
        assertNotNull(GenericBean.GENERIC_STRING_PROPERTY);
        assertNull(GenericBean.GENERIC_STRING_PROPERTY.getField());
        assertNotNull(GenericBean.GENERIC_STRING_PROPERTY.getReadMethod());
        assertNotNull(GenericBean.GENERIC_STRING_PROPERTY.getWriteMethod());
        assertTrue(GenericBean.GENERIC_STRING_PROPERTY.isWritable());
        assertTrue(GenericBean.GENERIC_STRING_PROPERTY.isReadable());
    }

    @Test
    public void testGetPropertyWithIsserAndBooleanObject() {
        class IsserBean {
            public Boolean isProperty() {
                return true;
            }
        }

        assertNull(Bean.forClass(IsserBean.class).getProperty("property"));
    }

    @Test
    public void testAccessorsWithGetter() {
        assertNotNull(GenericBean.GETTER_PROPERTY);
        assertNotNull(GenericBean.GETTER_PROPERTY.getField());
        assertNotNull(GenericBean.GETTER_PROPERTY.getReadMethod());
        assertNull(GenericBean.GETTER_PROPERTY.getWriteMethod());
        assertFalse(GenericBean.GETTER_PROPERTY.isWritable());
        assertTrue(GenericBean.GETTER_PROPERTY.isReadable());
    }

    @Test
    public void testAccessorsWithIsser() {
        assertNotNull(GenericBean.ISSER_PROPERTY);
        assertNotNull(GenericBean.ISSER_PROPERTY.getField());
        assertNotNull(GenericBean.ISSER_PROPERTY.getReadMethod());
        assertNull(GenericBean.ISSER_PROPERTY.getWriteMethod());
        assertFalse(GenericBean.ISSER_PROPERTY.isWritable());
        assertTrue(GenericBean.ISSER_PROPERTY.isReadable());
    }

    @Test
    public void testAccessorsWithSetter() {
        assertNotNull(GenericBean.SETTER_PROPERTY);
        assertNotNull(GenericBean.SETTER_PROPERTY.getField());
        assertNull(GenericBean.SETTER_PROPERTY.getReadMethod());
        assertNotNull(GenericBean.SETTER_PROPERTY.getWriteMethod());
        assertTrue(GenericBean.SETTER_PROPERTY.isWritable());
        assertFalse(GenericBean.SETTER_PROPERTY.isReadable());
    }

    @Test
    public void testAccessorsWithPrivateFieldSetterGetter() {
        assertNotNull(GenericBean.STRING_PROPERTY);
        assertNotNull(GenericBean.STRING_PROPERTY.getField());
        assertNotNull(GenericBean.STRING_PROPERTY.getReadMethod());
        assertNotNull(GenericBean.STRING_PROPERTY.getWriteMethod());
        assertTrue(GenericBean.STRING_PROPERTY.isWritable());
        assertTrue(GenericBean.STRING_PROPERTY.isReadable());
    }

    @Test
    public void testGetReadMethodWithBothGetterAndIsser() throws NoSuchMethodException {
        class GenericBean {
            public boolean getSpecial() {
                return true;
            }

            public boolean isSpecial() {
                return true;
            }
        }

        Property special = Bean.forClass(GenericBean.class).getProperty("special");

        assertNotNull(special);
        assertEquals("special", special.getName());
        assertEquals(GenericBean.class.getMethod("isSpecial"), special.getReadMethod());
    }

    @Test
    public void testGetPropertyWithNoName() {
        @SuppressWarnings("unused")
        class NoNameBean {
            public String get() {
                return null;
            }

            public void set(String value) {
            }

            public boolean is() {
                return false;
            }
        }

        Bean<NoNameBean> noNameBean = Bean.forClass(NoNameBean.class);
        assertNotNull(noNameBean.getProperty("class"));// from Object
        assertEquals(1, noNameBean.getProperties().length);// class
        assertEquals(0, noNameBean.getDeclaredProperties().length);
    }

    @Test
    public void testFindAccessorFieldWithWrongType() {
        class GenericBean {
            private Locale property;// same name but wrong type

            public String getProperty() {
                return null;
            }

            public void setProperty(String property) {
            }
        }

        assertNull(Bean.forClass(GenericBean.class).getProperty("property").getField());
    }
}