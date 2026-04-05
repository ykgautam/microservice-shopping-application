package com.microservices.order.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(value = "inventory", url = "http://localhost:8082")
public interface InventoryClient {

//    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory/is-in-stock")

    @GetExchange("/api/inventory/is-in-stock")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

}
