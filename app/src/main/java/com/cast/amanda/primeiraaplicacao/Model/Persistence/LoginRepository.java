package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Login;

public interface LoginRepository {

    Login verifyLogin(Login login);

}