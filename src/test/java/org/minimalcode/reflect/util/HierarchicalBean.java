package org.minimalcode.reflect.util;

import org.minimalcode.reflect.Bean;
import org.minimalcode.reflect.Property;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings("unused")
public abstract class HierarchicalBean {

    // Ancestor
    public static final Bean ANCESTOR_BEAN = Bean.forClass(AncestorBean.class);
    public static final Property ANCESTOR_CLASS_PROPERTY = ANCESTOR_BEAN.getProperty("class");
    public static final Property ANCESTOR_FIRST_PROPERTY_NULL = ANCESTOR_BEAN.getProperty("firstProperty");
    public static final Property ANCESTOR_SECOND_PROPERTY_NULL = ANCESTOR_BEAN.getProperty("secondProperty");
    public static final Property ANCESTOR_THIRD_PROPERTY_NULL = ANCESTOR_BEAN.getProperty("thirdProperty");
    public static final Property ANCESTOR_CLASS_DECLARED_PROPERTY_NULL = ANCESTOR_BEAN.getDeclaredProperty("class");
    public static final Property ANCESTOR_FIRST_DECLARED_PROPERTY = ANCESTOR_BEAN.getDeclaredProperty("firstProperty");
    public static final Property ANCESTOR_SECOND_DECLARED_PROPERTY_NULL = ANCESTOR_BEAN.getDeclaredProperty("secondProperty");
    public static final Property ANCESTOR_THIRD_DECLARED_PROPERTY_NULL = ANCESTOR_BEAN.getDeclaredProperty("thirdProperty");

    // Parent
    public static final Bean PARENT_BEAN = Bean.forClass(ParentBean.class);
    public static final Property PARENT_CLASS_PROPERTY = PARENT_BEAN.getProperty("class");
    public static final Property PARENT_FIRST_PROPERTY = PARENT_BEAN.getProperty("firstProperty");
    public static final Property PARENT_SECOND_PROPERTY = PARENT_BEAN.getProperty("secondProperty");
    public static final Property PARENT_THIRD_PROPERTY_NULL = PARENT_BEAN.getProperty("thirdProperty");
    public static final Property PARENT_CLASS_DECLARED_PROPERTY_NULL = PARENT_BEAN.getDeclaredProperty("class");
    public static final Property PARENT_FIRST_DECLARED_PROPERTY = PARENT_BEAN.getDeclaredProperty("firstProperty");
    public static final Property PARENT_SECOND_DECLARED_PROPERTY = PARENT_BEAN.getDeclaredProperty("secondProperty");
    public static final Property PARENT_THIRD_DECLARED_PROPERTY = PARENT_BEAN.getDeclaredProperty("thirdProperty");

    // Child
    public static final Bean CHILD_BEAN = Bean.forClass(ChildBean.class);
    public static final Property CHILD_CLASS_PROPERTY = CHILD_BEAN.getProperty("class");
    public static final Property CHILD_FIRST_PROPERTY = CHILD_BEAN.getProperty("firstProperty");
    public static final Property CHILD_SECOND_PROPERTY = CHILD_BEAN.getProperty("secondProperty");
    public static final Property CHILD_THIRD_PROPERTY = CHILD_BEAN.getProperty("thirdProperty");
    public static final Property CHILD_CLASS_DECLARED_PROPERTY_NULL = CHILD_BEAN.getDeclaredProperty("class");
    public static final Property CHILD_FIRST_DECLARED_PROPERTY = CHILD_BEAN.getDeclaredProperty("firstProperty");
    public static final Property CHILD_SECOND_DECLARED_PROPERTY = CHILD_BEAN.getDeclaredProperty("secondProperty");
    public static final Property CHILD_THIRD_DECLARED_PROPERTY = CHILD_BEAN.getDeclaredProperty("thirdProperty");

    @Retention(RetentionPolicy.RUNTIME)
    public @interface AnnotationOne {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface AnnotationTwo {
        String value() default "";
    }

    public static class AncestorBean /* extends Object */ {
        // property
        // public Class<?> getClass();

        // not a property
        @AnnotationOne("ancestor-field")
        protected String firstProperty;

        // not a property
        private String getFirstProperty() {
            return firstProperty;
        }

        // not a property
        private void setFirstProperty(String property) {
            this.firstProperty = property;
        }
    }

    public static class ParentBean extends AncestorBean {

        @AnnotationOne("parent-field")
        public String secondProperty;

        // property
        public String getFirstProperty() {
            return firstProperty;
        }

        // property
        @AnnotationOne("parent-setter")
        public void setSecondProperty(String secondProperty) {
            this.secondProperty = secondProperty;
        }

        // not a property
        private void setThirdProperty(String thirdProperty) {
        }
    }

    public static class ChildBean extends ParentBean {

        // property
        @AnnotationOne("child-setter")
        @AnnotationTwo("child-setter")
        public void setFirstProperty(String property) {
            this.firstProperty = property;
        }

        // property
        public String getSecondProperty() {
            return secondProperty;
        }

        // property
        @AnnotationTwo("child-setter")
        public void setThirdProperty(String thirdProperty) {
        }
    }
}
