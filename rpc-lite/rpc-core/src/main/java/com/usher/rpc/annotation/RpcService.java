package com.usher.rpc.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface RpcService {
    Class<?> value();
}
