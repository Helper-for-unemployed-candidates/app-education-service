package com.jobhunter.appeducationservice.shit.utils;

import com.jobhunter.appeducationservice.shit.payload.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public interface ConstantFields {
    String USER = "user";
    String AUTH_HEADER = "Authorization";

    static UserPrincipal currentUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        final HttpServletRequest request = servletRequestAttributes.getRequest();
        final Object user = request.getAttribute(USER);
        if (Objects.nonNull(user) && user instanceof UserPrincipal userDTO)
            return userDTO;
        throw new RuntimeException("USER WAS NOT AUTHORIZED");
    }
}
