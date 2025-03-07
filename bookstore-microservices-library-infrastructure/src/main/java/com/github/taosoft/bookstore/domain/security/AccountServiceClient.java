package com.github.taosoft.bookstore.domain.security;

import com.github.taosoft.bookstore.domain.account.Account;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * 用户信息的远程服务客户端
 * 各个工程都可能涉及取当前用户之类的操作，将此客户端放到基础包以便通用
 **/
@FeignClient(name = "account")
public interface AccountServiceClient {

    @GET
    @Path("/restful/accounts/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    Account findByUsername(@PathParam("username") String username);
}
