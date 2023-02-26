package com.example.finalproject.uploadingfiles;

import com.example.finalproject.model.MyUser;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface StorageInterface {


    void init();
    String saveFile(MultipartFile file, MyUser user);
    Resource loadFile(String fileName);


}
