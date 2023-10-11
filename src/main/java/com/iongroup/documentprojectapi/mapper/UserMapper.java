package com.iongroup.documentprojectapi.mapper;

import com.iongroup.documentprojectapi.dto.RegisterRequest;
import com.iongroup.documentprojectapi.dto.UserDto;
import com.iongroup.documentprojectapi.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toUser(RegisterRequest registerRequest) {
        return mapper.map(registerRequest, User.class);
    }

    public User toUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public UserDto toUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }
}
