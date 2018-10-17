package com.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer
{
    private Long id;
    private String name;
    private String source;
    private String industry;
    private String level;
    private String phone;
    private String mobile;
}
