package com.ryw.jetpackexample;


//import com.google.gson.annotations.SerializedName;

import com.ryw.mapo.anotations.SerializedName;

public class GreetBean extends BaseBean{

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
