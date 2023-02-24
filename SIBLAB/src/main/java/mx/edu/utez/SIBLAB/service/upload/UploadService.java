package mx.edu.utez.SIBLAB.service.upload;

import mx.edu.utez.SIBLAB.models.machine.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
@Transactional
public class UploadService {
    @Autowired
    private MachineRepository repository;
    private String imageDir = System.getProperty("user.dir") + "/images/";
    @Transactional(readOnly = true)
    public Resource getImage(Long id) throws IOException {
        String imageName = this.repository.findById(id).get().getPath_image();
        Path imagePath = Paths.get(imageDir + "/" + imageName);
        Resource resource = new UrlResource(imagePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new FileNotFoundException("La imagen no existe o no es legible");
        }
    }
}
