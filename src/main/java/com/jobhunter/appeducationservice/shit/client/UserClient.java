package com.jobhunter.appeducationservice.shit.client;

import com.jobhunter.appeducationservice.shit.payload.Response;
import com.jobhunter.appeducationservice.shit.payload.UserPrincipal;
import com.jobhunter.appeducationservice.shit.utils.ConstantFields;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserClient {
    @GetExchange("/api/v1/u/account")
    Response<UserPrincipal> get(@RequestHeader(ConstantFields.AUTH_HEADER) String token);
}
