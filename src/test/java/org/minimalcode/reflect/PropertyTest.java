package org.minimalcode.reflect;

import org.junit.Test;
import org.minimalcode.reflect.util.GenericBean;
import org.minimalcode.reflect.util.HierarchicalBean;

import static org.junit.Assert.*;

public class PropertyTest {

    @Test
    public void testGetName() {
        assertEquals("stringProperty", GenericBean.STRING_PROPERTY.getName());
        assertEquals("getterProperty", GenericBean.GETTER_PROPERTY.getName());
        assertEquals("setterProperty", GenericBean.SETTER_PROPERTY.getName());
        assertEquals("isserProperty", GenericBean.ISSER_PROPERTY.getName());
        assertEquals("URLProperty", GenericBean.URL_PROPERTY.getName());// capitalized
    }

    @Test
    public void testGetDeclaringBean() {
        assertEquals(Bean.forClass(GenericBean.STRING_PROPERTY.getDeclaringBean().getType()),
                GenericBean.STRING_PROPERTY.getDeclaringBean());
    }

    @Test
    public void testIsReadable() {
        assertTrue(GenericBean.STRING_PROPERTY.isReadable());
        assertTrue(GenericBean.GETTER_PROPERTY.isReadable());
        assertTrue(GenericBean.ISSER_PROPERTY.isReadable());
        assertFalse(GenericBean.SETTER_PROPERTY.isReadable());
    }

    @Test
    public void testIsWritable() {
        assertTrue(GenericBean.STRING_PROPERTY.isWritable());
        assertFalse(GenericBean.GETTER_PROPERTY.isWritable());
        assertFalse(GenericBean.ISSER_PROPERTY.isWritable());
        assertTrue(GenericBean.SETTER_PROPERTY.isWritable());
    }

    @Test
    @SuppressWarnings({"EqualsBetweenInconvertibleTypes", "EqualsWithItself"})
    public void testEquals() {
        assertTrue(GenericBean.STRING_PROPERTY.equals(GenericBean.STRING_PROPERTY));
        assertFalse(GenericBean.STRING_PROPERTY.equals("not-a-property"));
        assertFalse(GenericBean.STRING_PROPERTY.equals(GenericBean.ARRAY_PROPERTY));
    }

    @Test
    public void testEqualsSamePropertyAndDeclaredProperty() {
        // Ancestor
        assertNotEquals(HierarchicalBean.ANCESTOR_CLASS_PROPERTY, HierarchicalBean.ANCESTOR_CLASS_DECLARED_PROPERTY_NULL);
        assertNotEquals(HierarchicalBean.ANCESTOR_FIRST_PROPERTY_NULL, HierarchicalBean.ANCESTOR_FIRST_DECLARED_PROPERTY);
        //assertNotEquals(HierarchicalBean.ANCESTOR_SECOND_PROPERTY_NULL, HierarchicalBean.ANCESTOR_SECOND_DECLARED_PROPERTY_NULL);// both null
        //assertNotEquals(HierarchicalBean.ANCESTOR_THIRD_PROPERTY_NULL, HierarchicalBean.ANCESTOR_THIRD_DECLARED_PROPERTY_NULL);// both null

        // Parent
        assertNotEquals(HierarchicalBean.PARENT_CLASS_PROPERTY, HierarchicalBean.PARENT_CLASS_DECLARED_PROPERTY_NULL);
        assertEquals(HierarchicalBean.PARENT_FIRST_PROPERTY, HierarchicalBean.PARENT_FIRST_DECLARED_PROPERTY);
        assertEquals(HierarchicalBean.PARENT_SECOND_PROPERTY, HierarchicalBean.PARENT_SECOND_DECLARED_PROPERTY);
        assertNotEquals(HierarchicalBean.PARENT_THIRD_PROPERTY_NULL, HierarchicalBean.PARENT_THIRD_DECLARED_PROPERTY);

        // Child
        assertNotEquals(HierarchicalBean.CHILD_CLASS_PROPERTY, HierarchicalBean.CHILD_CLASS_DECLARED_PROPERTY_NULL);
        assertNotEquals(HierarchicalBean.CHILD_FIRST_PROPERTY, HierarchicalBean.CHILD_FIRST_DECLARED_PROPERTY);
        assertNotEquals(HierarchicalBean.CHILD_SECOND_PROPERTY, HierarchicalBean.CHILD_SECOND_DECLARED_PROPERTY);
        assertEquals(HierarchicalBean.CHILD_THIRD_PROPERTY, HierarchicalBean.CHILD_THIRD_DECLARED_PROPERTY);
    }

    @Test
    public void testHashCode() {
        int hashCode = GenericBean.STRING_PROPERTY.getDeclaringBean().getType().getName().hashCode()
                ^ GenericBean.STRING_PROPERTY.getName().hashCode();

        assertEquals(hashCode, GenericBean.STRING_PROPERTY.hashCode());
    }

    @Test
    public void testToString() {
        String toString = "property " + GenericBean.STRING_PROPERTY.getDeclaringBean().getType().getName()
                + "." + GenericBean.STRING_PROPERTY.getName();

        assertEquals(toString, GenericBean.STRING_PROPERTY.toString());
    }
}