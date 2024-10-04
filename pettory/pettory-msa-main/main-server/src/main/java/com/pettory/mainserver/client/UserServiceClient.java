package com.pettory.mainserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-server", url ="http://localhost:8080")
public interface UserServiceClient {
    @GetMapping("/users/email")
    UserInfoResponse getUserInfoByEmail();

}
