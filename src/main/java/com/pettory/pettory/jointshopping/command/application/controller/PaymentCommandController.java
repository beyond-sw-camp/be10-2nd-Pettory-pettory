package com.pettory.pettory.jointshopping.command.application.controller;

import com.siot.IamportRestClient.IamportClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
@CrossOrigin("*")
public class PaymentCommandController {
    
    @PostMapping("/payment")
    @ResponseBody
    public void bookPay(int amount,String imp_uid, String merchant_uid) throws Exception{

        System.out.println("결제 성공");
        System.out.println("결제 금액 : " + amount);
        System.out.println("imp_uid : " + imp_uid);
        System.out.println("merchant_uid : " + merchant_uid);
    }

}
