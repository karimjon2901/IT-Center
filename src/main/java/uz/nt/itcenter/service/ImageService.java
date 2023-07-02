package uz.nt.itcenter.service;

import org.springframework.web.multipart.MultipartFile;


public interface ImageService {
    String saveFile(MultipartFile image);
}
