package org.minimalcode.reflect.util;

import org.minimalcode.reflect.Bean;
import org.minimalcode.reflect.Property;

import java.net.URL;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class GenericBean implements TestableBean<String, String> {

    public static final Bean BEAN = Bean.forClass(GenericBean.class);
    public static final Property CLASS_PROPERTY = BEAN.getProperty("class");//from Object
    public static final Property INT_PROPERTY = BEAN.getProperty("intProperty");
    public static final Property STRING_PROPERTY = BEAN.getProperty("stringProperty");
    public static final Property URL_PROPERTY = BEAN.getProperty("URLProperty");
    public static final Property ARRAY_PROPERTY = BEAN.getProperty("arrayProperty");
    public static final Property LIST_PROPERTY = BEAN.getProperty("listProperty");
    public static final Property ITERABLE_PROPERTY = BEAN.getProperty("iterableProperty");
    public static final Property MAP_PROPERTY = BEAN.getProperty("mapProperty");
    public static final Property BEAN_PROPERTY = BEAN.getProperty("beanProperty");

    // Only 1 method
    public static final Property GETTER_PROPERTY = BEAN.getProperty("getterProperty");
    public static final Property ISSER_PROPERTY = BEAN.getProperty("isserProperty");
    public static final Property SETTER_PROPERTY = BEAN.getProperty("setterProperty");

    // From Interface
    public static final Property GENERIC_STRING_PROPERTY = BEAN.getProperty("genericStringProperty");
    public static final Property GENERIC_MAP_PROPERTY = BEAN.getProperty("genericMapProperty");
    public static final Property GENERIC_WILDCARD_ITERABLE_PROPERTY = BEAN.getProperty("genericWildcardIterableProperty");

    public int intProperty;
    private String stringProperty;
    private URL URLProperty;
    private List<String> listProperty;
    private String[] arrayProperty;
    private Iterable<String> iterableProperty;
    private Map<String, String> mapProperty;
    private GenericBean beanProperty;
    private String genericString;

    private List<String> getterProperty;
    private List<String> setterProperty;
    private boolean isserProperty;

    // ignored
    public static String staticField;

    // ignored
    public static String getStaticGetter() {
        return null;
    }

    // ignored
    public static boolean isStaticIsser() {
        return true;
    }

    // ignored
    public static void setStaticSetter(String value) {

    }

    public URL getURLProperty() {
        return URLProperty;
    }

    public void setURLProperty(URL URLProperty) {
        this.URLProperty = URLProperty;
    }

    public List<String> getGetterProperty() {
        return getterProperty;
    }

    public boolean isIsserProperty() {
        return isserProperty;
    }

    public void setSetterProperty(List<String> setterProperty) {
        this.setterProperty = setterProperty;
    }

    public GenericBean getBeanProperty() {
        return beanProperty;
    }

    public void setBeanProperty(GenericBean beanProperty) {
        this.beanProperty = beanProperty;
    }

    public String getStringProperty() {
        return stringProperty;
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public Iterable<String> getIterableProperty() {
        return iterableProperty;
    }

    public void setIterableProperty(Iterable<String> iterableProperty) {
        this.iterableProperty = iterableProperty;
    }

    public String[] getArrayProperty() {
        return arrayProperty;
    }

    public void setArrayProperty(String[] arrayProperty) {
        this.arrayProperty = arrayProperty;
    }

    public List<String> getListProperty() {
        return listProperty;
    }

    public void setListProperty(List<String> listProperty) {
        this.listProperty = listProperty;
    }

    public Map<String, String> getMapProperty() {
        return mapProperty;
    }

    public void setMapProperty(Map<String, String> mapProperty) {
        this.mapProperty = mapProperty;
    }

    public int getIntProperty() {
        return intProperty;
    }

    public void setIntProperty(int intProperty) {
        this.intProperty = intProperty;
    }

    @Override
    public String getGenericStringProperty() {
        return genericString;
    }

    @Override
    public void setGenericStringProperty(String genericString) {
        this.genericString = genericString;
    }

    @Override
    public Map<String, String> getGenericMapProperty() {
        return null;
    }

    @Override
    public Iterable<? extends String> getGenericWildcardIterableProperty() {
        return null;
    }
}
