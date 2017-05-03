package org.algos4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A program element annotated with this indicates either it consumes, returns
 * or internally uses heap (mostly binary heap) data structure. Even though a 
 * heap is also a tree structure treating it is a special case.
 * 
 * @author psajja
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface HeapType {

}
