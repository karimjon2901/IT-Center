package uz.nt.itcenter.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.nt.itcenter.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
@Component
public class ImageServiceImpl implements ImageService {
    public String filePath(String folder, String ext) {
        LocalDate localDate = LocalDate.now();
        String path = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        File file = new File(folder + "/" + path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        return file.getPath() + "\\" + uuid + ext;
    }

    @Override
    public String saveFile(MultipartFile image) {
        String ext = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        try {
            String filePath;
            Files.copy(image.getInputStream(), Path.of(filePath = filePath("upload", ext)));
            return filePath;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Image getImage(String imageUrl) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(imageUrl));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = ImageIO.read(bis);
        return bImage2;
    }
}
