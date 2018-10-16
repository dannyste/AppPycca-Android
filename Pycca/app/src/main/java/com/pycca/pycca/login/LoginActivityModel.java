package com.pycca.pycca.login;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private LoginRepository loginRepository;

    public LoginActivityModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

}
