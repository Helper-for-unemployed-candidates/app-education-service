package com.jobhunter.appeducationservice.shit.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private String region;
    private String city;
    private String fullAddress;
}
