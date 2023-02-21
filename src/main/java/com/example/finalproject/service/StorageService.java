package com.example.finalproject.service;

import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Project;
import com.example.finalproject.model.Storage;
import com.example.finalproject.model.Work;
import com.example.finalproject.repestory.ProjectRepository;
import com.example.finalproject.repestory.StorageRepository;
import com.example.finalproject.repestory.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//import static org.hibernate.id.enhanced.HiLoOptimizer.log;

@Service
@RequiredArgsConstructor
public class StorageService {
    private Path fileStorageLocation;
    private final WorkRepository workRepository;
    private final StorageRepository storageRepository;
    private final ProjectRepository projectRepository;

    public String storeFile(File file, Integer id, String pathType,MyUser user) {


        this.fileStorageLocation = Paths.get("C:\\Storage\\"+pathType ).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new ApiException("Could not create the directory where the uploaded files will be stored."+
                    ex,400);
        }

        String fileName = StringUtils.cleanPath(user.getName() + "-"+id+"-" + file.getName());

        try {

            if (fileName.contains("..")) {
                throw new ApiException("Sorry! Filename contains invalid path sequence " + fileName,400);
            }


            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            InputStream targetStream = new FileInputStream(file);
            Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);

            updateImagePath(id, pathType, pathType + "/" + fileName);

            return fileName;
        } catch (IOException ex) {
            throw new ApiException("Could not store file " + fileName + ". Please try again!", 400);
        }
       // return null;
    }

    private void updateImagePath(Integer id, String pathType, String imagePath) {

        if (pathType.contains("work")) {
            Work work=workRepository.findWorkById(id);
            if(work==null){
                throw new ApiException("dont have work for the id ",400);
            }
            Storage storage=new Storage(null,pathType,imagePath,work,null);
            storageRepository.save(storage);


        }
        else{
            Project project=projectRepository.findByIdEquals(id);
            if(project==null){
                throw new ApiException("dont have project for the id ",400);

            }
            Storage storage=new Storage(null,pathType,imagePath,null,project);
            storageRepository.save(storage);

        }

    }


    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            
        }

        return file;
    }
}
