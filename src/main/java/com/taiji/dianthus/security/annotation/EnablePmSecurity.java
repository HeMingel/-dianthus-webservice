package com.taiji.dianthus.security.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @InterfaceName EnableTDFSecurity
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(DianthusSecurityConfiguration.class)
public @interface EnablePmSecurity {
}
