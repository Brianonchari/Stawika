package com.example.brian.stawika.Model;

import java.util.List;

public class UsersResponse {
    private boolean error;
    public List<Users> users;

    public UsersResponse(boolean error, List<Users> users) {
        this.error = error;
        this.users = users;
    }

    public boolean isError() {
        return error;
    }
    public List<Users> getUsers() {
        return users;
    }





}
