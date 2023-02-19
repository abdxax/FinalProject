package com.example.finalproject.service;

import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.repestory.CityRepsotery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepsotery cityRepsotery;

    public List<City> getCites(){
        return cityRepsotery.findAll();
    }

    public void addCity(MyUser user,City city){
        if(user.getRole().equalsIgnoreCase("admin")){
            cityRepsotery.save(city);

        }
        throw new ApiException("The user is not admin premmision");
    }

    public Boolean update(MyUser user,Integer id,City city){
     City c=cityRepsotery.findByIdEquals(id);
     if(c==null||!checkRole(user)){
         return false;
     }

     city.setId(c.getId());
     cityRepsotery.save(city);
     return true;
    }
    public Boolean delete(MyUser user,Integer id){
        City c=cityRepsotery.findByIdEquals(id);
        if(c==null||!checkRole(user)){
            return false;
        }

        cityRepsotery.delete(c);
        return true;
    }

    public City getCity(Integer id){
        City c=cityRepsotery.findByIdEquals(id);
        if(c==null){
            return null;
        }
        return c;
    }

    public Boolean checkRole(MyUser user){
        if("admin".equalsIgnoreCase(user.getRole())){
            return true;
        }
        return false;
    }
}
