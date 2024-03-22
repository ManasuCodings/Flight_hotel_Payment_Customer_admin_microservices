package com.userService.service;

import com.userService.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createOneUser(UserDto userDto);

    List<UserDto> getAllUser();

    UserDto getOne_User(long userId);

    void deleteUser_byId(long userId);

    UserDto findbyEmail(String email);
}
