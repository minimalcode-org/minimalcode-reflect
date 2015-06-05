# MinimalCode Bean Reflect

[![Build Status](https://travis-ci.org/minimalcode-org/minimalcode-reflect.svg?branch=master)](https://travis-ci.org/minimalcode-org/minimalcode-reflect)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.minimalcode/minimalcode-reflect/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.minimalcode/minimalcode-reflect/)
[![Dependency Status](https://www.versioneye.com/user/projects/556daccd393462001f120000/badge.svg?style=flat)](https://www.versioneye.com/user/projects/556daccd393462001f120000)

MinimalCode Reflect is a **minimalistic** Java framework built with the purpose of providing high-performance API for dealing with **JavaBean** properties. 

The API are **designed upon the standard java.lang.reflect** framework, implementing its interfaces and following all its well established conventions, in a familiar way. The source code consists in only **2 classes** and it is extremely compact, robust and well-tested, with **100% test coverage**. 

The library is also standalone **without any dependency** and universal (**jdk 1.6+, works on Android too**). In addition to this, it is very fast and garbage-collector friendly, using bytecode proxy generation to provide **performance better than plain java reflection**.

### Installation

The library requires only **jdk 1.6+**. 

If [cglib](https://github.com/cglib/cglib) is found in the classpath then the bytecode proxy generation will be automatically enabled to provide better performance, but this dependency is totally **optional** and can be ignored without side effects.

- Maven:

  ```xml
	<dependencies>
		<dependency>
			<groupId>org.minimalcode</groupId>
			<artifactId>minimalcode-reflect</artifactId>
			<version>0.5.1</version><!-- or last version -->
		</dependency>
	
		<!-- Optional Cglib Dependency (avoid it on Android systems) -->
		<dependency>
			<groupId>cglib</groupId>
			<artifactId>cglib</artifactId>
			<version>3.1</version>
		</dependency>
	</dependencies>
  ```
  
### Property Accessors

Accessor methods are auto-discovered following the standard JavaBean patterns.

- **Read Method**:    T **get**Field() || boolean **is**Field()
- **Write Method**:   void **set**Field(T field)

```java
public class Book {
	// Note: Getter, Isser and Setter methods are here hidden for readability
	public boolean valid;// isValid() and setValid(boolean valid)
	private String title;// getTitle() and setTitle(String title)
	protected List<String> pages;// getPages() and setPages(List<String> pages)
}
```

- For **normal properties**, accessor methods must be **public and can be inherited** from superclasses\interfaces.

- For **declared properties** instead, accessor methods can have **any valid modifier**
(public, protected, private, package-private...), but must be declared in the target class or interface,
as any **inheritance is ignored**.

-  Only one accessor is required: properties can be **read-only or write-only**.
  
```java
Bean<Book> bean = Bean.forClass(Book.class);// works with interfaces too

// Get Single Property
Property title = bean.getProperty("title");
Property pages = bean.getDeclaredProperty("pages");
Property iNull = bean.getDeclaredProperty("not-valid");// null, if not found

// All Properties
for(Property property : bean.getProperties()) {
	System.out.println("Property: " + property.getName());
}

// All Declared Properties
for(Property property : bean.getDeclaredProperties()) {
	System.out.println("Property: " + property.getName() + " (declared)");
}
```

### Property Reflection

*Property::get* and *Property::set* will invoke the getter\isser\setter methods. The **bytecode proxy** (if enabled) is automatically used to provide fast property access. The **"raw" accessor methods** are still available for additional flexibility. Furthermore, if a **matching accessible field** with the same name and type of the property is present, it will also be provided.

```java
Object obj = new Book();
Bean<?> bean = Bean.forClass(object.getClass());
Property title = bean.getProperty("title");

// if property exists
if (title != null) {

	// Write
	if (title.isWritable()) {
		title.set(obj, "new-title");
	}
	
	// Read
	if (title.isReadable()) {
		System.out.println(title + " has value " + (String) title.get(obj) + " in " + obj);
	}
	
	// Raw accessors and field
	Method readMethod = title.getReadMethod();
	Method writeMethod = title.getWriteMethod();
	Field field = title.getField();
}

```

### Property Types

In addition to the **plain type** and the **generic type** (if any), a convenient actual type is provided. The **actual type** is the plain type for simple properties, the resolved element type for List, Iterable, Collection and array properties, or the Map value type for maps properties.

```java

class Book {
	// Note: Getter and Setter methods are here hidden for readability
	private List<String> pages;// getPages() and setPages(List<String> pages)
}

Property pages = Bean.forClass(Book.class).getProperty("pages");

// if property exists
if (pages != null) {

	Class<?> type = pages.getType();// List
	Type genericType = pages.getGenericType();// ParameterizedType
	Class<?> actualType = pages.getActualType();// Resolved element Type: String
	
	if(List.class.isAssignableFrom(type)) {
		System.out.println(pages.getName() + " is a " + type + " of " + actualType);// "pages is a ...List of ...String"
	}
}

```

### Property Annotations

Annotations are statically collected from field, read method and write method accessors. Inheritance of annotations from superclasses is also supported.

```java

class Book {
	@MyAnnotationOne
	private List<String> pages;
	
	public List<String> getPages() { return pages; }
	
	@MyAnnotationTwo
	public void setPages(List<String> pages) { this.pages = pages; }
}

// Annotations
for(Property property : Bean.forClass(Book.class).getProperties()) {
	if(property.isAnnotationPresent(MyAnnotationOne.class)) {
		MyAnnotationOne annotation = property.getAnnotation(MyAnnotationOne.class);
		System.out.println(property.getName() + " has @MyAnnotationOne with value " + annotation.value());
	}
    	
	// All the AnnotatedElement methods are also supported
	... = property.getAnnotations();
	... = property.getAnnotation(MyAnnotationOne.class);
	... = property.getAnnotationsByType(MyAnnotationOne.class);// only Jdk 1.8
	... = property.getDeclaredAnnotations();
	... = property.getDeclaredAnnotation(MyAnnotationTwo.class);
}

```

That's all... Less is more.

### Links

Minimalcode Reflect is a low-level API. For a more developer-friendly API, Spring's BeanWrapper-alike and Apache BeanUtils-alike, see the MinimalCode Beans project:
* [https://github.com/minimalcode-org/minimalcode-beans](https://github.com/minimalcode-org/minimalcode-beans)
