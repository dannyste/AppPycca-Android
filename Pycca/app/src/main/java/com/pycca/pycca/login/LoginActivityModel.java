package com.pycca.pycca.login;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private LoginRepository loginRepository;

    public LoginActivityModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void setUser(String email, String password) {
        User user = new User(email, password);
        loginRepository.setUser(user);
    }

    @Override
    public User getUser() {
        return loginRepository.getUser();
    }

}
