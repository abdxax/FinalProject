package com.example.finalproject.service;

import com.example.finalproject.dto.ApiProfile;
import com.example.finalproject.dto.ProfileDTO;
import com.example.finalproject.dto.UpdateProfileDTO;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Profile;
import com.example.finalproject.repestory.AuthRepository;
import com.example.finalproject.repestory.CityRepsotery;
import com.example.finalproject.repestory.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final CityRepsotery cityRepsotery;
    private final AuthRepository authRepository;

     public ApiProfile getprofile(Integer id){
         Profile profile=profileRepository.findByIdEquals(id);
         if(profile==null){
             throw new ApiException("Profile not fount. complete your profile.",404);
         }
         MyUser user=authRepository.findByIdEquals(profile.getId());
         if(user==null){
             throw new ApiException("Profile not fount. complete your profile.",404);
         }
         return new ApiProfile(profile,user);
     }

     public void addProfile(MyUser user,ProfileDTO profileDTO){
         MyUser authUser= authRepository.findByIdEquals(user.getId());
         if(authUser.getProfile()!=null){
             throw new ApiException("You already hava a profile.",400);
         }

         City city=cityRepsotery.findByIdEquals(profileDTO.getCityId());
         if(city==null){
             throw new ApiException("City selected not found",404);
         }
         Profile profile=new Profile(null,profileDTO.getPhone(),authUser,city,null);
         profileRepository.save(profile);
     }

     public void update(MyUser user,ProfileDTO profileDTO){
         Profile oldProfile=profileRepository.findByIdEquals(user.getId());
         if(oldProfile==null){
             throw new ApiException("Profile not found. complete it",404);
         }

         if(profileDTO.getCityId()>0) {
             City city = cityRepsotery.findByIdEquals(profileDTO.getCityId());
             if(city==null){
                 throw new ApiException("City selected not found",404);
             }
             oldProfile.setCity(city);
         }
         if(profileDTO.getPhone()!=null){
             oldProfile.setPhone(profileDTO.getPhone());
         }
         profileRepository.save(oldProfile);
     }

    public Boolean delete(MyUser user,Integer id){
        Profile prof=profileRepository.findByIdEquals(id);
        if(prof==null||prof.getUser().getId()!=user.getId()){
            return false;
        }
        profileRepository.delete(prof);
        return true;
    }

    public List<City> cityList(){
         return cityRepsotery.findAll();
    }

    public Boolean updateInfo(UpdateProfileDTO updateProfileDTO){
         MyUser user=authRepository.findByIdEquals(updateProfileDTO.getUserId());
         Profile profile=profileRepository.findByIdEquals(updateProfileDTO.getUserId());
         City city=cityRepsotery.findByIdEquals(updateProfileDTO.getCityId());
         if(user==null||profile==null||city==null){
             return false;
         }
         user.setName(updateProfileDTO.getName());
         authRepository.save(user);
         profile.setCity(city);
         profile.setPhone(updateProfileDTO.getPhone());
         profileRepository.save(profile);
         return true;
    }
}
