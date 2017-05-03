package org.algos4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A program element annotated with this indicates that it uses backtracking 
 * approach to solve a programming problem.
 * 
 * @author psajja
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Backtracking {

}
