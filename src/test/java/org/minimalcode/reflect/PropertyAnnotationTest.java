package org.minimalcode.reflect;

import org.junit.Test;
import org.minimalcode.reflect.util.HierarchicalBean;

import java.lang.annotation.Annotation;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class PropertyAnnotationTest {

    @Test
    public void testGetAnnotations() {
        // Ancestor
        assertEquals(0, HierarchicalBean.ANCESTOR_CLASS_PROPERTY.getAnnotations().length);
        assertEquals(1, HierarchicalBean.ANCESTOR_FIRST_DECLARED_PROPERTY.getAnnotations().length);

        // Parent
        assertEquals(0, HierarchicalBean.ANCESTOR_CLASS_PROPERTY.getAnnotations().length);
        assertEquals(1, HierarchicalBean.PARENT_FIRST_PROPERTY.getAnnotations().length);
        assertEquals(1, HierarchicalBean.PARENT_SECOND_PROPERTY.getAnnotations().length);

        // Parent Declared
        assertEquals(1, HierarchicalBean.PARENT_FIRST_DECLARED_PROPERTY.getAnnotations().length);
        assertEquals(1, HierarchicalBean.PARENT_SECOND_DECLARED_PROPERTY.getAnnotations().length);
        assertEquals(0, HierarchicalBean.PARENT_THIRD_DECLARED_PROPERTY.getAnnotations().length);

        // Child
        assertEquals(0, HierarchicalBean.CHILD_CLASS_PROPERTY.getAnnotations().length);
        assertEquals(2, HierarchicalBean.CHILD_FIRST_PROPERTY.getAnnotations().length);
        assertEquals(1, HierarchicalBean.CHILD_SECOND_PROPERTY.getAnnotations().length);
        assertEquals(1, HierarchicalBean.CHILD_THIRD_PROPERTY.getAnnotations().length);

        // Child Declared
        assertEquals(2, HierarchicalBean.CHILD_FIRST_DECLARED_PROPERTY.getAnnotations().length);
        assertEquals(1, HierarchicalBean.CHILD_SECOND_DECLARED_PROPERTY.getAnnotations().length);
        assertEquals(1, HierarchicalBean.CHILD_THIRD_DECLARED_PROPERTY.getAnnotations().length);
    }

    @Test
    public void testGetDeclaredAnnotations() {
        // Ancestor
        assertEquals(0, HierarchicalBean.ANCESTOR_CLASS_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(1, HierarchicalBean.ANCESTOR_FIRST_DECLARED_PROPERTY.getDeclaredAnnotations().length);

        // Parent
        assertEquals(0, HierarchicalBean.PARENT_CLASS_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(0, HierarchicalBean.PARENT_FIRST_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(1, HierarchicalBean.PARENT_SECOND_PROPERTY.getDeclaredAnnotations().length);

        // Parent Declared
        assertEquals(0, HierarchicalBean.PARENT_FIRST_DECLARED_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(1, HierarchicalBean.PARENT_SECOND_DECLARED_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(0, HierarchicalBean.PARENT_THIRD_DECLARED_PROPERTY.getDeclaredAnnotations().length);

        // Child
        assertEquals(0, HierarchicalBean.CHILD_CLASS_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(2, HierarchicalBean.CHILD_FIRST_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(0, HierarchicalBean.CHILD_SECOND_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(1, HierarchicalBean.CHILD_THIRD_PROPERTY.getDeclaredAnnotations().length);

        // Child Declared
        assertEquals(2, HierarchicalBean.CHILD_FIRST_DECLARED_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(0, HierarchicalBean.CHILD_SECOND_DECLARED_PROPERTY.getDeclaredAnnotations().length);
        assertEquals(1, HierarchicalBean.CHILD_THIRD_DECLARED_PROPERTY.getDeclaredAnnotations().length);
    }

    @Test
    public void testGetAnnotationsOverride() throws NoSuchMethodException {
        HierarchicalBean.AnnotationOne annotationOne =
                HierarchicalBean.PARENT_SECOND_PROPERTY.getAnnotation(HierarchicalBean.AnnotationOne.class);

        assertNotNull(annotationOne);
        assertEquals("parent-setter", annotationOne.value());
    }

    @Test
    public void testGetAnnotation() {
        assertNull(HierarchicalBean.ANCESTOR_CLASS_PROPERTY.getAnnotation(HierarchicalBean.AnnotationOne.class));
        assertNotNull(HierarchicalBean.CHILD_FIRST_PROPERTY.getAnnotation(HierarchicalBean.AnnotationOne.class));
    }

    @Test
    public void testGetDeclaredAnnotation() {
        assertNotNull(HierarchicalBean.CHILD_SECOND_PROPERTY.getAnnotation(HierarchicalBean.AnnotationOne.class));
        assertNull(HierarchicalBean.CHILD_SECOND_PROPERTY.getDeclaredAnnotation(HierarchicalBean.AnnotationOne.class));
        assertNotNull(HierarchicalBean.CHILD_THIRD_PROPERTY.getDeclaredAnnotation(HierarchicalBean.AnnotationTwo.class));
    }

    @Test
    public void testIsAnnotationPresent() {
        assertTrue(HierarchicalBean.CHILD_FIRST_PROPERTY.isAnnotationPresent(HierarchicalBean.AnnotationOne.class));
        assertTrue(HierarchicalBean.CHILD_FIRST_PROPERTY.isAnnotationPresent(HierarchicalBean.AnnotationTwo.class));
        assertFalse(HierarchicalBean.CHILD_SECOND_PROPERTY.isAnnotationPresent(HierarchicalBean.AnnotationTwo.class));
    }

    @Test
    public void testGetAnnotationWithNull() {
        try {
            HierarchicalBean.CHILD_FIRST_PROPERTY.getAnnotation(null);
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().contains("null"));
        }
    }

    @Test
    public void testGetDeclaredAnnotationWithNull() {
        try {
            HierarchicalBean.CHILD_FIRST_PROPERTY.getDeclaredAnnotation(null);
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().contains("null"));
        }
    }

    @Test
    public void testIsAnnotationPresentWithNull() {
        try {
            HierarchicalBean.CHILD_FIRST_PROPERTY.isAnnotationPresent(null);
            fail();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().contains("null"));
        }
    }

    @Test
    public void testGetAnnotationsIsAClone() {
        Annotation[] annotations = HierarchicalBean.CHILD_FIRST_PROPERTY.getAnnotations();
        Annotation[] clonedAnnotations = HierarchicalBean.CHILD_FIRST_PROPERTY.getAnnotations();

        clonedAnnotations[0] = null;
        assertNull(clonedAnnotations[0]);
        assertNotNull(annotations[0]);
    }


    @Test
    public void testGetDeclaredAnnotationsIsAClone() {
        Annotation[] annotations = HierarchicalBean.CHILD_FIRST_PROPERTY.getDeclaredAnnotations();
        Annotation[] clonedAnnotations = HierarchicalBean.CHILD_FIRST_PROPERTY.getDeclaredAnnotations();

        clonedAnnotations[0] = null;
        assertNull(clonedAnnotations[0]);
        assertNotNull(annotations[0]);
    }
}