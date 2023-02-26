package com.example.finalproject;

import com.example.finalproject.config.FileUploadProperties;
import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.repestory.AuthRepository;
import com.example.finalproject.repestory.CityRepsotery;
import com.example.finalproject.repestory.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadProperties.class
})
public class FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @Autowired
    CityRepsotery cityRepsotery;
    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Autowired
    AuthRepository authRepository;
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
        if(authRepository.findAll().size()==0){
            MyUser myUser1 = new MyUser("Abdullah","abodi.imz@gmail.com",new BCryptPasswordEncoder().encode("Asd@12345") ,"ADMIN");
            MyUser myUser2 = new MyUser("Abdulrahman","jarallah@gmail.com",new BCryptPasswordEncoder().encode("Asd@12345") ,"ADMIN");
            MyUser myUser3 = new MyUser("Freelancer","freelancer@gmail.com",new BCryptPasswordEncoder().encode("Asd@12345") ,"FREELANCER");
            MyUser myUser4 = new MyUser("user","user@gmail.com",new BCryptPasswordEncoder().encode("Asd@12345") ,"USER");
            authRepository.save(myUser1);
            authRepository.save(myUser2);
            authRepository.save(myUser3);
            authRepository.save(myUser4);
        }
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
