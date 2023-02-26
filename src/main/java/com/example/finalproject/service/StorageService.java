package com.example.finalproject.service;

import com.example.finalproject.config.FileUploadProperties;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Storage;
import com.example.finalproject.model.Work;
import com.example.finalproject.repestory.StorageRepository;
import com.example.finalproject.repestory.WorkRepository;
import com.example.finalproject.uploadingfiles.StorageInterface;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//import static org.hibernate.id.enhanced.HiLoOptimizer.log;

@Service
@RequiredArgsConstructor
public class StorageService implements StorageInterface {

    private final Path dirLocation;
    private final WorkRepository workRepository;

    private StorageRepository storageRepository;
    @Autowired
    public StorageService(FileUploadProperties fileUploadProperties, WorkRepository workRepository, StorageRepository storageRepository) {
        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
                .normalize();
        this.workRepository = workRepository;
        this.storageRepository = storageRepository;
    }

    @Override
    @PostConstruct
    public void init() {
        // TODO Auto-generated method stub
        try {
            Files.createDirectories(this.dirLocation);
        }
        catch (Exception ex) {
            throw new ApiException("Could not create upload dir!",400);
        }
    }

    @Override
    public String saveFile(MultipartFile file, MyUser user) {
        // TODO Auto-generated method stub

        try {
            if(Files.notExists(this.dirLocation.resolve("user-"+user.getId()))){
                Files.createDirectories(this.dirLocation.resolve("user-"+user.getId()));
            }
            String fileName = file.getOriginalFilename();
            Path dfile = this.dirLocation.resolve(("user-"+user.getId())).resolve(fileName);
            Files.copy(file.getInputStream(), dfile,StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (Exception e) {
            throw new ApiException("Could not upload file",400);
        }
    }

    public String saveWorkFile(MultipartFile file, Integer workId, MyUser user) {
        // TODO Auto-generated method stub

        Work work = workRepository.findWorkById(workId);
        if(work==null){
            throw new ApiException("Work not found",404);
        }
        try {
            if(Files.notExists(this.dirLocation.resolve("user-"+user.getId()))){
                Files.createDirectories(this.dirLocation.resolve("user-"+user.getId()));
            }
            if(Files.notExists(this.dirLocation.resolve("user-"+user.getId()).resolve("works"))){
                Files.createDirectories(this.dirLocation.resolve("user-"+user.getId()).resolve("works"));
            }
            if(Files.notExists(this.dirLocation.resolve("user-"+user.getId()).resolve("works").resolve("work-"+work.getId()))){
                Files.createDirectories(this.dirLocation.resolve("user-"+user.getId()).resolve("works").resolve("work-"+work.getId()));
            }

            String fileName = file.getOriginalFilename();
            String path = this.dirLocation+"/user-"+user.getId()+"/works/work-"+work.getId();
            Path dfile = Path.of(path).resolve(System.currentTimeMillis()+("."+file.getContentType().split("/")[1]));
            Path absolute = dfile.toAbsolutePath();
            System.out.println(dfile);


            Files.copy(file.getInputStream(), absolute,StandardCopyOption.REPLACE_EXISTING);

            storageRepository.save(new Storage(null,file.getContentType(),dfile+"",work,null));
            return fileName;

        } catch (Exception e) {
            throw new ApiException("Could not upload file",400);
        }
    }

    @Override
    public Resource loadFile(String path) {
        // TODO Auto-generated method stub
        try {
            Path file = Path.of(path).toAbsolutePath().normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new ApiException("Could not find file",404);
            }
        }
        catch (MalformedURLException e) {
            throw new ApiException("Could not download file",500);
        }
    }

    public void saveMultiFile(MultipartFile[] files, MyUser user) {
        for (int i=0; i<files.length;i++){
            saveFile(files[i],user);
        }
    }
//
//    private Path fileStorageLocation;
//    private final WorkRepository workRepository;
//    private final StorageRepository storageRepository;
//    private final ProjectRepository projectRepository;
//
//    public String storeFile(File file, Integer id, String pathType,MyUser user) {
//
//
//        this.fileStorageLocation = Paths.get("C:\\Storage\\"+pathType ).toAbsolutePath().normalize();
//
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new ApiException("Could not create the directory where the uploaded files will be stored."+
//                    ex,400);
//        }
//
//        String fileName = StringUtils.cleanPath(user.getName() + "-"+id+"-" + file.getName());
//
//        try {
//
//            if (fileName.contains("..")) {
//                throw new ApiException("Sorry! Filename contains invalid path sequence " + fileName,400);
//            }
//
//
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            InputStream targetStream = new FileInputStream(file);
//            Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            updateImagePath(id, pathType, pathType + "/" + fileName);
//
//            return fileName;
//        } catch (IOException ex) {
//            throw new ApiException("Could not store file " + fileName + ". Please try again!", 400);
//        }
//       // return null;
//    }
//
//    private void updateImagePath(Integer id, String pathType, String imagePath) {
//
//        if (pathType.contains("work")) {
//            Work work=workRepository.findWorkById(id);
//            if(work==null){
//                throw new ApiException("dont have work for the id ",400);
//            }
//            Storage storage=new Storage(null,pathType,imagePath,work,null);
//            storageRepository.save(storage);
//
//
//        }
//        else{
//            Project project=projectRepository.findByIdEquals(id);
//            if(project==null){
//                throw new ApiException("dont have project for the id ",400);
//
//            }
//            Storage storage=new Storage(null,pathType,imagePath,null,project);
//            storageRepository.save(storage);
//
//        }
//
//    }
//
//
//    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
//        final File file = new File(multipartFile.getOriginalFilename());
//        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
//            outputStream.write(multipartFile.getBytes());
//        } catch (final IOException ex) {
//
//        }
//
//        return file;
//    }
//
//    public void storeFiles(Work work,MyUser user,MultipartFile [] multipartFiles) {
//        Path fileStorageLocation = Paths.get("Storage/").toAbsolutePath().normalize();
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new ApiException("Could not create the directory where the uploaded files will be stored."+
//                    ex,400);
//        }
//
//        for (MultipartFile f: multipartFiles){
//            f.getContentType();
//        }
//        String fileName = StringUtils.cleanPath("user-"+user.getId()+"/" + file.getName());
//
//        try {
//
//            if (fileName.contains("..")) {
//                throw new ApiException("Sorry! Filename contains invalid path sequence " + fileName,400);
//            }
//
//
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            InputStream targetStream = new FileInputStream(file);
//            Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            updateImagePath(id, pathType, pathType + "/" + fileName);
//
//            return fileName;
//        } catch (IOException ex) {
//            throw new ApiException("Could not store file " + fileName + ". Please try again!", 400);
//        }
//    }
}
