package com.example.finalproject.service;

import com.example.finalproject.dto.ProfileDTO;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.City;
import com.example.finalproject.model.Freelancer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Profile;
import com.example.finalproject.repestory.AuthRepstory;
import com.example.finalproject.repestory.CityRepsotery;
import com.example.finalproject.repestory.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final CityRepsotery cityRepsotery;
    private final AuthRepstory authRepstory;

     public Profile getprofile(Integer id){
         Profile profile=profileRepository.findByIdEquals(id);
         if(profile==null){
             return null;
         }
         return profile;
     }

     public void addProfile(MyUser user,ProfileDTO profileDTO){
         City city=cityRepsotery.findByIdEquals(profileDTO.getCityId());
         MyUser user1=authRepstory.findByIdEquals(user.getId());
         if(city==null){
             throw new ApiException("The city id is not correct ",404);
         }
//         Profile profile=new Profile(null,profileDTO.getName(),profileDTO.getPhone(),user,city,null);
           Profile profile=new Profile(null,profileDTO.getPhone(),user1,city,null);
         profileRepository.save(profile);
     }

     public Boolean update(MyUser user,Profile profile){
         Profile prof=profileRepository.findByIdEquals(user.getId());
         if(prof==null){
             return false;
         }
         prof.setCity(profile.getCity());
         prof.setPhone(profile.getPhone());
         profileRepository.save(prof);
         return true;
     }

    public Boolean delete(MyUser user,Integer id){
        Profile prof=profileRepository.findByIdEquals(id);
        if(prof==null||prof.getUser().getId()!=user.getId()){
            return false;
        }
        profileRepository.delete(prof);
        return true;
    }
}
