package com.example.cloud.exampleservice.api;

import com.example.cloud.exampleservice.assembler.UserResource;
import com.example.cloud.exampleservice.assembler.UserResourceAssembler;
import com.example.cloud.exampleservice.dto.UserDTO;
import com.example.cloud.exampleservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "This Api is used for operation performed on User")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adds new user", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResource addUser(@RequestBody UserDTO userDTO){
        Assert.notNull(userDTO, "The input DTO cannot be null");
        return userResourceAssembler.toModel(userService.addUser(userDTO));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/get-by-id/{userId}")
    @ApiOperation(value = "Adds new user", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResource getUserById(@PathVariable(value = "userId") String userId){
        Assert.notNull(userId, "The input id cannot be null");
        return userResourceAssembler.toModel(userService.getUserById(userId));
    }
}
