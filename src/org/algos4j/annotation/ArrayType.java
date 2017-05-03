package org.algos4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A program element annotated with this indicates either it consumes, returns or internally uses this particular type of data strucutre.
 * A program element with @ArrayType annotation are used for both array and string types.
 * 
 * @author psajja
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface ArrayType {

}
