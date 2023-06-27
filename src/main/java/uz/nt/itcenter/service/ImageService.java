package uz.nt.itcenter.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

public interface ImageService {
    String saveFile(MultipartFile image);
    Image getImage(String imageUrl) throws IOException;
}
