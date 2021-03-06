package fr.insy2s.web.rest;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.service.ContratService;
import fr.insy2s.service.DocumentService;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.service.dto.DocumentDTO;
import fr.insy2s.utils.TypeDocumentConstants;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import fr.insy2s.service.FilesStorageService;

import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FilesController {

    @Autowired
    FilesStorageService storageService;
    @Autowired
    DocumentService documentService;
    @Autowired
    ContratService contratService;


    /**
     * {@code POST  /upload} : Upload a new file.
     *
     * @param file      the file to upload.
     * @param absenceId the ID linked to Absence.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body a personalized message,
     * or with status {@code 400 (Bad Request)} if the Document cannot be created,
     * or with status {@code 417 (Expectation Failed) if an error occured during upload}
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("absenceId") Long absenceId,
                                             @RequestParam("noteDeFraisId") Long noteDeFraisId, @RequestParam("autresVariableId") Long autresVariableId,
                                             @RequestParam("fileNumber") String fileNumber, @RequestParam("timestamp") String timestamp) {
        String message = "";
        boolean isAbsence = false;
        boolean isNoteDeFrais = false;
        boolean isAutre = false;
        String type = "";
        String id = "";
        try {
            if (absenceId != -1) {
                isAbsence = true;
                type = "Absence";
                id = absenceId.toString();
            } else if (noteDeFraisId != -1) {
                isNoteDeFrais = true;
                type = "NoteDeFrais";
                id = noteDeFraisId.toString();
            } else if (autresVariableId != -1) {
                isAutre = true;
                type = "Autre";
                id = autresVariableId.toString();
            }
            storageService.save(file, type, id, fileNumber, timestamp);
            try {
                /* Crée l'entité Document liée */
                DocumentDTO documentDTO = new DocumentDTO();
                if (isAbsence) {
                    documentDTO.setAbsenceId(absenceId);
                    documentDTO.setTypeDocumentId(TypeDocumentConstants.ABSENCE);
                } else if (isNoteDeFrais) {
                    documentDTO.setNoteDeFraisId(noteDeFraisId);
                    documentDTO.setTypeDocumentId(TypeDocumentConstants.NOTE_DE_FRAIS);
                } else if (isAutre) {
                    documentDTO.setAutresVariableId(autresVariableId);
                    documentDTO.setTypeDocumentId(TypeDocumentConstants.AUTRES_VARIABLE);
                }
                String extension[] = file.getContentType().split("/");
                documentDTO.setCheminFichier("./" + storageService.getROOT().toString() + "/" + id + "_" + type + "_" + fileNumber + "_" + timestamp + "." + extension[1]);
                documentDTO.setNom(id + "_" + type + "_" + fileNumber + "_" + timestamp + "." + extension[1]);
                documentService.save(documentDTO);
            } catch (Exception e) {
                message = "Error: could not create the entity Document linked to: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
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

    /**
     * {@code POST  /upload/contracts/{id}} : Upload a new file.
     *
     * @param file  the file to upload.
     * @param id    the ID of the Contrat.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body a personalized message,
     * or with status {@code 400 (Bad Request)} if the Document cannot be created,
     * or with status {@code 417 (Expectation Failed) if an error occured during upload}
     */
    @Secured({AuthoritiesConstants.SOCIETY, AuthoritiesConstants.ADMIN}) // comptable ??
    @PostMapping("/upload/contracts/{id}")
    public ResponseEntity<String> uploadSignedContract(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws BadRequestAlertException {
        String message = "";
        Optional<ContratDTO> optionalContratDTO = contratService.findOne(id);
        if (!optionalContratDTO.isPresent())
            throw new BadRequestAlertException("The contract does not exist", "contrat", "contractNonExistant");
        ContratDTO contrat = optionalContratDTO.get();
        if ( contrat.isSigne())
            throw new BadRequestAlertException("The contract is already signed", "contrat", "alreadySigned");

        try {
            Path documentPath = storageService.saveFile(file,"contrat",  id + "");
            try {
                DocumentDTO documentDTO = new DocumentDTO();
                documentDTO.setCheminFichier(documentPath.toString().replace("\\\"", "\""));
                documentDTO.setNom(documentPath.getFileName().toString());
                documentDTO.setContratId(id);
                documentDTO.setTypeDocumentId(TypeDocumentConstants.CONTRAT);
                documentService.save(documentDTO);

                // Make the contract signed
                contratService.signeContract(id);
                message = "File uploaded with success: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Error: could not create the entity Document linked to: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
