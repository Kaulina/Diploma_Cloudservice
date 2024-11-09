package ru.netology.diploma_cloudservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diploma_cloudservice.dto.request.ChangeFilenameRequest;
import ru.netology.diploma_cloudservice.dto.response.FileListResponse;
import ru.netology.diploma_cloudservice.entity.StoreFile;
import ru.netology.diploma_cloudservice.entity.User;
import ru.netology.diploma_cloudservice.services.auth.AuthService;
import ru.netology.diploma_cloudservice.services.file.FileServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final AuthService authService;
    private final FileServiceImpl fileService;

    @PostMapping(value = "/file")
    public ResponseEntity<?> uploadFile(
            @RequestHeader("auth-token") String authToken,
            @RequestParam("filename") String filename,
            @RequestParam("file") MultipartFile file) {
        User user = authService.getUserFromToken(authToken);
        fileService.uploadFile(user, filename, file);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) {
        User user = authService.getUserFromToken(authToken);
        fileService.deleteFile(user, filename);
        return ResponseEntity.ok("File deleted successfully");
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> downloadFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) {
        User user = authService.getUserFromToken(authToken);
        byte[] file = fileService.downloadFile(user, filename);
        return ResponseEntity.ok(file);
    }

    @PutMapping("/file")
    public ResponseEntity<?> updateFilename(@RequestHeader("auth-token") String authToken,
                                            @RequestParam("filename") String filename,
                                            @RequestBody ChangeFilenameRequest fileDataApply) {
        User user = authService.getUserFromToken(authToken);
        fileService.updateFilename(user, filename, fileDataApply);
        return ResponseEntity.ok("File name updated successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileListResponse>> getAllFiles(@RequestHeader("auth-token") String authToken, @RequestParam("limit") Integer limit) {
        User user = authService.getUserFromToken(authToken);
        List<StoreFile> storageFiles = fileService.getAllFiles(user, limit);
        List<FileListResponse> fileJsonNames = storageFiles.stream()
                .map(storageFile -> new FileListResponse(storageFile.getFilename(), storageFile.getSize()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(fileJsonNames);
    }
}