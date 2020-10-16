package fr.insy2s.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.stream.Stream;

import fr.insy2s.service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path ROOT = Paths.get("fichiers/social/variablesdepaie");
    private final String DEFAULT_PATH = "fichiers";

    @Override
    public void init() {
        try {
            Files.createDirectory(ROOT);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, String type, String id, String fileNumber, String timestamp) {
        try {
            String[] extension = file.getContentType().split("/");
            Files.copy(file.getInputStream(), this.ROOT.resolve(id + "_" + type + "_" + fileNumber + "_" + timestamp + "." + extension[1]));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Path saveFile(MultipartFile file, String type, String societyName) {
        try {
            String[] extension  = Objects.requireNonNull(file.getContentType()).split("/");
            Long timestamp      = new Timestamp(System.currentTimeMillis()).getTime();
            Path defPath        = Paths.get(String.format("%s/%s/%s", DEFAULT_PATH, type, societyName));
            Files.createDirectories(defPath);
            Path path = defPath.resolve(
                String.format("%s_%s.%s",
                    type,
                    timestamp,
                    extension[1]
                )
            );
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = ROOT.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(ROOT.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.ROOT, 1).filter(path -> !path.equals(this.ROOT)).map(this.ROOT::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    public Path getROOT() {
        return ROOT;
    }

    public String getDEFAULT_PATH() {
        return DEFAULT_PATH;
    }
}
