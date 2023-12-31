package com.itoxi.petnuri.domain.delivery.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryListRes {

    private Long id;
    private String name;
    private String phone;
    private String roadAddress;
    private String address;
    private String zipcode;
    private Boolean isBased;
}
