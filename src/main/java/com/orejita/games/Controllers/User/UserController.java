package com.orejita.games.Controllers.User;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.DTO.User.UserDto;
import com.orejita.games.Entities.User.User;
import com.orejita.games.Services.Interfaces.IUserService;


@Controller
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<UserDto> getAllUsers() {
        List<User> users = service.getAllUsers();
        return users.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserDto getUserById(@PathVariable("id") long id) {
        User user = service.getOneUser(id);
        return convertToDto(user);
    }

    @GetMapping("/{username:.+}")
    @ResponseBody
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        User user = service.getOneUser(username);
        return convertToDto(user);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Validated(OnCreate.class) @RequestBody UserDto user) {
        User userEntity = convertToEntity(user);
        User _user = service.createUser(userEntity);
        return convertToDto(_user);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public UserDto updateUser(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody UserDto user) {
        User userEntity = convertToEntity(user);
        User _user = service.updateUser(id, userEntity);
        return convertToDto(_user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") long id) {
        service.deleteUser(id);
    }


    private UserDto convertToDto(User user) {
        UserDto dto = this.modelMapper.map(user, UserDto.class);
        return dto;
    }

    private User convertToEntity(UserDto dto) {
        User model = this.modelMapper.map(dto, User.class);
        return model;
    }
    
}
