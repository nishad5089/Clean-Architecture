package com.brainstation23.structure.mapper;

import com.brainstation23.structure.domain.request.UserRequest;
import com.brainstation23.structure.domain.response.UserResponse;
import com.brainstation23.structure.repository.schema.User;
import org.springframework.stereotype.Component;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Component
public class UserMapper extends AbstractMapper<User, UserRequest, UserResponse> {
    @Override
    public UserResponse convertToResponse(User entity) {
        final UserResponse response = new UserResponse();
        super.convert(entity, response);
        response.setUsername(entity.getUsername());
        return response;
    }

    public User convertToEntity(User user,UserRequest userRequest) {
        user.setUsername(userRequest.getUsername());
        return user;
    }
}
