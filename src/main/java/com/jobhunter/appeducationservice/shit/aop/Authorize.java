package com.jobhunter.appeducationservice.shit.aop;


import com.jobhunter.appeducationservice.shit.enums.RoleEnum;


import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorize {
    RoleEnum[] permissions() default {};
}
