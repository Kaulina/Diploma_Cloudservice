package ru.netology.diploma_cloudservice.services.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diploma_cloudservice.dto.request.ChangeFilenameRequest;
import ru.netology.diploma_cloudservice.entity.StoreFile;
import ru.netology.diploma_cloudservice.entity.User;
import ru.netology.diploma_cloudservice.exception.InputDataException;
import ru.netology.diploma_cloudservice.repository.FileRepository;
import ru.netology.diploma_cloudservice.repository.LoginRepository;
import ru.netology.diploma_cloudservice.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class FileServiceImpl {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Transactional
    public void uploadFile(User user, String fileName, MultipartFile file) {
        try {
            fileRepository.save(new StoreFile(fileName, LocalDateTime.now(), file.getSize(), file.getBytes(), user));
            log.info("Successfully uploaded file. User: {}", user.getUsername());
        } catch (IOException e) {
            log.error("Upload file: Input data exception");
            throw new InputDataException("Upload file: Input data exception");
        }
    }

    @Transactional
    public void deleteFile(User user, String filename) {
        fileRepository.deleteByUserAndFilename(user, filename);
        StoreFile storageFile = fileRepository.findByUserAndFilename(user, filename);
        if (storageFile != null) {
            log.error("Delete file: Input data exception");
            throw new InputDataException("Input data exception");
        }
        log.info("Successfully deleted file. User: {}", user.getUsername());
    }

    @Transactional
    public byte[] downloadFile(User user, String filename) {
        StoreFile storageFile = fileRepository.findByUserAndFilename(user, filename);
        if (storageFile == null) {
            log.error("Download file: Input data exception");
            throw new InputDataException("Download file: Input data exception");
        }
        log.info("Downloaded file. User: {}", user.getUsername());
        return storageFile.getFileContent();
    }

    @Transactional
    public void updateFilename(User user, String fileName, ChangeFilenameRequest fileDataApply) {
        fileRepository.updateFilenameByUser(user, fileName, fileDataApply.getFilename());
        StoreFile storageFile = fileRepository.findByUserAndFilename(user, fileName);
        if (storageFile != null) {
            log.error("ERROR: Input data exception");
            throw new InputDataException("ERROR: Input data exception");
        }
        log.info("Successfully updated file name. User: {}", user.getUsername());
    }

    @Transactional
    public List<StoreFile> getAllFiles(User user, Integer limit) {
        log.info("Successfully fetched all files. User: {}", user.getUsername());
        return fileRepository.findAllByUser(user);
    }
}
