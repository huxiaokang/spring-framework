/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a constructor, field, setter method or config method as to be autowired
 * by Spring's dependency injection facilities.
 *可以使用 构造器，属性，setter方法或者配置方法进行自动装配
 *
 * <p>Only one constructor (at max) of any given bean class may carry this annotation,
 * indicating the constructor to autowire when used as a Spring bean. Such a
 * constructor does not have to be public.
 * 只能给一个构造器带改注释，构造器不一定是public的
 *
 * <p>Fields are injected right after construction of a bean, before any config
 * methods are invoked. Such a config field does not have to be public.
 * 属性注入在构造器注入之后，在方法注入之前，配置的属性字段不必是公共的
 *
 * <p>Config methods may have an arbitrary name and any number of arguments; each of
 * those arguments will be autowired with a matching bean in the Spring container.
 * Bean property setter methods are effectively just a special case of such a general
 * config method. Such config methods do not have to be public.
 * 配置方法可以有任意的名称和任意数量的参数，每个参数都将使用Spring容器中匹配的bean进行自动装配
 * Bean属性的setter方法实际实际上仅仅是配置方法的一个特例
 *
 * <p>In the case of a multi-arg constructor or method, the 'required' parameter is
 * applicable to all arguments. Individual parameters may be declared as Java-8-style
 * {@link java.util.Optional} or, as of Spring Framework 5.0, also as {@code @Nullable}
 * or a not-null parameter type in Kotlin, overriding the base required semantics.
 * 在多个参数构造器或者方法中， 'required'参数适用于所有参数. 各个参数可以声明为Java-8风格的或者
 * sping5.0 @Nullable 或者Kotlin中的not-null参数类型,覆盖基本所需的语义。
 *
 * <p>In case of a {@link java.util.Collection} or {@link java.util.Map} dependency type,
 * the container autowires all beans matching the declared value type. For such purposes,
 * the map keys must be declared as type String which will be resolved to the corresponding
 * bean names. Such a container-provided collection will be ordered, taking into account
 * {@link org.springframework.core.Ordered}/{@link org.springframework.core.annotation.Order}
 * values of the target components, otherwise following their registration order in the
 * container. Alternatively, a single matching target bean may also be a generally typed
 * {@code Collection} or {@code Map} itself, getting injected as such.
 * 如果是{@link java.util.Collection}或者 {@link java.util.Map}依赖类型
 * 容易自动装配与声明类型的值类型匹配的所有bean.为此目的
 * map的key值必须声明为String类型的，并将其解析为相应bean的名称
 * 容器提供的集合将被排序，考虑目标组件{@link org.springframework.core.Ordered}/{@link org.springframework.core.annotation.Order}的值
 * 否则按照他们在容器中注册的顺序.另外，单个匹配的bean也可以是通用类型{@code Collection} 或者{@code Map}本身
 *
 * <p>Note that actual injection is performed through a
 * {@link org.springframework.beans.factory.config.BeanPostProcessor
 * BeanPostProcessor} which in turn means that you <em>cannot</em>
 * use {@code @Autowired} to inject references into
 * {@link org.springframework.beans.factory.config.BeanPostProcessor
 * BeanPostProcessor} or
 * {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor BeanFactoryPostProcessor}
 * types. Please consult the javadoc for the {@link AutowiredAnnotationBeanPostProcessor}
 * class (which, by default, checks for the presence of this annotation).
 * 注意 实际上是通过 {@link org.springframework.beans.factory.config.BeanPostProcessor
 * BeanPostProcessor}执行的，这反过来意味着无法使用{@code @Autowired}来注入引用到 {@link org.springframework.beans.factory.config.BeanPostProcessor
 * BeanPostProcessor} 或 {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor BeanFactoryPostProcessor}类型
 *
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @since 2.5
 * @see AutowiredAnnotationBeanPostProcessor
 * @see Qualifier
 * @see Value
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

	/**
	 * Declares whether the annotated dependency is required.
	 * <p>Defaults to {@code true}.
	 */
	boolean required() default true;

}
