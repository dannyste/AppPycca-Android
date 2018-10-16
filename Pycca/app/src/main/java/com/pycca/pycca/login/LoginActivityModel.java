package com.pycca.pycca.login;

import com.pycca.pycca.pojo.User;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private LoginRepository loginRepository;

    public LoginActivityModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

}
