package com.example.corona.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.corona.model.User;
import com.example.corona.repository.LoginUserRepository;

public class LoginViewModel extends ViewModel {

    public void loginUser(User user){
        new LoginUserRepository().createUser(user);
    }


}
