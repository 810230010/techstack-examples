package com.usher.rpc.annotation;

import java.lang.annotation.*;

/**
 * @inherit allows service use @transactional
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RpcService {
    Class<?> value();
}
