package com.brainstation23.structure.service;

import com.brainstation23.structure.domain.request.UserRequest;
import com.brainstation23.structure.domain.response.UserResponse;
import com.brainstation23.structure.mapper.UserMapper;
import com.brainstation23.structure.repository.jpa.UserRepository;
import com.brainstation23.structure.repository.schema.User;
import org.springframework.stereotype.Service;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Service
public class UserService extends AbstractService<User, UserRequest, UserResponse> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(final UserRepository userRepository, final UserMapper userMapper) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    protected User mapToEntity(User user,UserRequest request) {
        return userMapper.convertToEntity(user,request);
    }

}
