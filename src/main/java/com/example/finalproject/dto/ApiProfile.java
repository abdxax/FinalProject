package com.example.finalproject.dto;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiProfile {
    private Profile profile;
    private MyUser user=null;
}
