package customer.zdep_jobs.controllers;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;


import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;


import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.zdep.Image_;
import cds.gen.zdep.Image;
@RestController
@RequestMapping("/images")
public class ImageController {
    
    @Autowired
    private PersistenceService db;

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable("imageName") String imageName) {
        
        CqnSelect sel = Select.from(Image_.class).where(e -> e.ID().eq(imageName));
        //查找图片 如果没有返回空
        Optional<Image> result = db.run(sel).first(Image.class);
        if (result.isPresent()) {
            Image image = result.get();
            ByteArrayResource resource = new ByteArrayResource(image.getImageData());
            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
            
        }else{
            return null;
        }

    }
}
