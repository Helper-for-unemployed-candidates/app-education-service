package com.jobhunter.appeducationservice.shit.aop;

import com.jobhunter.appeducationservice.shit.client.UserClient;
import com.jobhunter.appeducationservice.shit.enums.RoleEnum;
import com.jobhunter.appeducationservice.shit.payload.Response;
import com.jobhunter.appeducationservice.shit.payload.UserPrincipal;
import com.jobhunter.appeducationservice.shit.utils.ConstantFields;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.jobhunter.appeducationservice.shit.utils.ConstantFields.AUTH_HEADER;



@Aspect
@RequiredArgsConstructor
public class AuthorizeExecutor {
    private final UserClient userClient;

    @Before(value = "@annotation(authorize)")
    public void authorizeExecutor(Authorize authorize) {
        authorizeUser(authorize);
    }


    private void authorizeUser(Authorize authorize) {
        HttpServletRequest request = currentRequest();
        String token = request.getHeader(AUTH_HEADER);

        if (Objects.isNull(token))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        try {
            Response<UserPrincipal> authorized = getAuthorized(token);
            if (!authorized.isSuccess())
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, authorized.getErrors().get(0).getErrorMsg());
            if (hasPermissions(List.of(authorized.getData().getRole()), authorize.permissions()))
                request.setAttribute(ConstantFields.USER, authorized.getData());
            else throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @Cacheable(value = "authorizedUser")
    public Response<UserPrincipal> getAuthorized(String token) {
        return userClient.get(token);
    }

    boolean hasPermissions(List<RoleEnum> actual, RoleEnum[] expected) {
        if (expected.length == 0)
            return true;
        return Objects.nonNull(actual) && Arrays.stream(expected).anyMatch(actual::contains);
    }

    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }
}