package fr.insy2s.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing files.
 */
public interface FilesStorageService {

    /**
     * Initialize the files folder if it hasn't been created.
     */
    public void init();

    /**
     * Save a file.
     *
     * @param file the file to save.
     */
    public void save(MultipartFile file, String type, String id, String fileNumber, String timestamp);

    /**
     * Save a file.
     *
     * @param file the file to save.
     * @param type the type of the file.
     * @param societyName the society who wants to save .
     */
    Path saveFile(MultipartFile file, String type, String societyName);

    /**
     * Get the "name" file.
     *
     * @param filename the name of the file.
     * @return the file.
     */
    public Resource load(String filename);

    /**
     * delete all files.
     */
    public void deleteAll();

    /**
     * Get all the files.
     *
     * @return the list of files.
     */
    public Stream<Path> loadAll();

    /**
     * Get the files root.
     */
    public Path getROOT();
}
