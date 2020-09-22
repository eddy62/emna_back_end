package fr.insy2s.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import fr.insy2s.service.DocumentService;
import fr.insy2s.service.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.insy2s.service.FilesStorageService;

@RestController
@RequestMapping("/api")
public class FilesController {

    @Autowired
    FilesStorageService storageService;
    @Autowired
    DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("absenceId") Long absenceId) {
        String message = "";
        try {
            storageService.save(file);
            try {
                /* Crée l'entité Document liée */
                DocumentDTO documentDTO = new DocumentDTO();
                documentDTO.setAbsenceId(absenceId);
                documentDTO.setCheminFichier(storageService.getRoot().toString());
                documentDTO.setNom(file.getOriginalFilename());
                DocumentDTO result = documentService.save(documentDTO);
                System.out.println(result);
            } catch (Exception e) {
                message = "Error: could not create the entity Document linked to: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
            message = "File uploaded with success: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Error: could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    /* TODO DEFAULT FUNCTION, A ADAPTER */
    /*@GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }*/

    /* TODO DEFAULT FUNCTION, A ADAPTER */
    /*@GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }*/
}
