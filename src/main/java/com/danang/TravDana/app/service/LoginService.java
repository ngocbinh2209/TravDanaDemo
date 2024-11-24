package com.danang.TravDana.app.service;

import com.danang.TravDana.app.request.UserRequest;
import com.danang.TravDana.app.response.UserResponse;

public interface LoginService {

    UserResponse login(UserRequest userRequest);

}
