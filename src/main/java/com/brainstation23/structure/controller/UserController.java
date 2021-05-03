package com.brainstation23.structure.controller;

import com.brainstation23.structure.api.UserApi;
import com.brainstation23.structure.service.AbstractCRUDApi;
import com.brainstation23.structure.domain.request.UserRequest;
import com.brainstation23.structure.domain.response.UserResponse;
import com.brainstation23.structure.repository.schema.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractController<User, UserRequest, UserResponse> implements UserApi {
    public UserController(AbstractCRUDApi<User, UserRequest, UserResponse> api) {
        super(api);
    }
}
