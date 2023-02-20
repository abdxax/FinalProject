package com.example.finalproject;

import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.repestory.AuthRepstory;
import com.example.finalproject.repestory.CityRepsotery;
import com.example.finalproject.repestory.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @Autowired
    CityRepsotery cityRepsotery;
    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Autowired
    AuthRepstory authRepstory;
    @Bean
    public void addCities(){
        if(cityRepsotery.findAll().size()==0){
            ArrayList<City> cities = new ArrayList<>();
            cities.add(new City("جدة"));
            cities.add(new City("الرياض"));
            cities.add(new City("الدمام"));
            cities.add(new City("حائل"));
            cities.add(new City("تبوك"));
            cities.add(new City("جيزان"));
            cities.add(new City("مكة"));
            cities.add(new City("المدينة"));
            cityRepsotery.saveAll(cities);
        }
    }

    @Bean
    public void addServiceTypes(){
        if(serviceTypeRepository.findAll().size()==0){
            ArrayList<ServiceType> serviceTypes = new ArrayList<>();
            serviceTypes.add(new ServiceType("تسويق"));
            serviceTypes.add(new ServiceType("التصوير الفوتوقرافي"));
            serviceTypes.add(new ServiceType("البرمجة"));
            serviceTypes.add(new ServiceType("الطبخ"));
            serviceTypes.add(new ServiceType("كتابة محتوى"));
            serviceTypes.add(new ServiceType("تصميم"));
            serviceTypeRepository.saveAll(serviceTypes);
        }
    }

    @Bean
    public void addDefaultUsers(){
        if(authRepstory.findAll().size()==0){
            MyUser myUser1 = new MyUser("Abdullah","abodi.imz@gmail.com",new BCryptPasswordEncoder().encode("Asd@12345") ,"ADMIN");
            MyUser myUser2 = new MyUser("Abdulrahman","jarallah@gmail.com",new BCryptPasswordEncoder().encode("Asd@12345") ,"ADMIN");
            MyUser myUser3 = new MyUser("Freelancer","freelancer@gmail.com",new BCryptPasswordEncoder().encode("Asd@12345") ,"FREELANCER");
            MyUser myUser4 = new MyUser("user","user@gmail.com",new BCryptPasswordEncoder().encode("Asd@12345") ,"USER");
            authRepstory.save(myUser1);
            authRepstory.save(myUser2);
            authRepstory.save(myUser3);
            authRepstory.save(myUser4);
        }
    }

}
