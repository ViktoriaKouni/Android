package com.example.corona.repository;

import com.example.corona.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginUserRepository {


    public CollectionReference getCollectionUser(){
        return FirebaseFirestore.getInstance().collection("User");
    }

    public void createUser(User user){
        getCollectionUser().document(user.getEmail()).set(user);
    }
}
