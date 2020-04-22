package com.example.cloud.exampleservice.assembler;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UserResource extends RepresentationModel<UserResource> {
    private String user_name;
    private String user_address;
}
