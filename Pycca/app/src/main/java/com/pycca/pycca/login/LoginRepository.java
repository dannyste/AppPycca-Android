package com.pycca.pycca.login;

import com.pycca.pycca.pojo.User;

public interface LoginRepository {

    void setUser(User user);

    User getUser();

}
