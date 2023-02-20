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
         City city=cityRepsotery.findByIdEquals(profileDTO.getCityid());
         MyUser user1=authRepstory.findByIdEquals(profileDTO.getIdUser());
         if(city==null){
             throw new ApiException("The city id is not correct ");
         }
       //  Profile profile=new Profile(null,profileDTO.getName(),profileDTO.getPhone(),user,city,null);
           Profile profile=new Profile(null,profileDTO.getName(),profileDTO.getPhone(),user.getId(),city,null);
         profileRepository.save(profile);
     }

     public Boolean update(MyUser user,Integer id,Profile profile){
         Profile prof=profileRepository.findByIdEquals(id);
         if(prof==null||prof.getUserId()!=user.getId()){
             return false;
         }
         profile.setId(prof.getId());
         profile.setUserId(prof.getUserId());
        // profile.setTypes(prof.getTypes());
         profile.setCity(prof.getCity());
         profileRepository.save(profile);
         return true;
     }

    public Boolean delete(MyUser user,Integer id){
        Profile prof=profileRepository.findByIdEquals(id);
        if(prof==null||prof.getUserId()!=user.getId()){
            return false;
        }
        profileRepository.delete(prof);
        return true;
    }
}
