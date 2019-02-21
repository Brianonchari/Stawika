package com.example.brian.stawika.model;

import java.util.List;

public class UsersResponse {

    public List<Users> users;
    private boolean error;

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
