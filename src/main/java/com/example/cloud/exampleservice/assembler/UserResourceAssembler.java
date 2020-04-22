package com.example.cloud.exampleservice.assembler;

import com.example.cloud.exampleservice.api.UserController;
import com.example.cloud.exampleservice.model.User;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserResourceAssembler extends RepresentationModelAssemblerSupport<User, UserResource> {
    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toModel(User entity) {
        UserResource userResource = instantiateModel(entity);
        userResource.add(linkTo(
                methodOn(UserController.class)
                        .getUserById(entity.getId()))
                .withSelfRel());
        userResource.setUser_name(entity.getName());
        userResource.setUser_address(entity.getAddress());
        return userResource;
    }
}
