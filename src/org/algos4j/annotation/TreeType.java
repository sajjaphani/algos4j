package org.algos4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A program element annotated with this indicates either it consumes, returns
 * or internally uses tree data structure.
 * 
 * @author psajja
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface TreeType {

}
