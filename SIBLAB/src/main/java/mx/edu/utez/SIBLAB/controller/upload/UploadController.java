package mx.edu.utez.SIBLAB.controller.upload;

import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.service.machine.MachineService;
import mx.edu.utez.SIBLAB.service.upload.UploadService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api-siblab/image")
public class UploadController {
    @Autowired
    private MachineService service;
    @Autowired
    private UploadService imageService;

    @Validated
    @ExceptionHandler(MultipartException.class)
    @PostMapping( value = "/{id}",produces = "application/json", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public @ResponseBody ResponseEntity<CustomResponse<?>> insert(@RequestParam(name = "image",required = false) MultipartFile file, @PathVariable Long id){

        try {
            File uploadsDir = new File("images");
            if (!uploadsDir.exists()) {
                uploadsDir.mkdir();
            }
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            String imagesDir = System.getProperty("user.dir") + "/images/";
            File imageFile = new File(imagesDir + fileName);
            file.transferTo(imageFile);
            System.out.println(fileName);
            Machine machine = new Machine();
            machine.setId(id);
            machine.setPath_image(fileName);
            return new ResponseEntity<>(this.service.patch(machine), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) throws IOException {
        Resource resource = imageService.getImage(id);
        if (resource != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
