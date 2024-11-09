package ru.netology.diploma_cloudservice.services.file;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.testng.annotations.Test;
import ru.netology.diploma_cloudservice.entity.StoreFile;
import ru.netology.diploma_cloudservice.entity.User;
import ru.netology.diploma_cloudservice.exception.InputDataException;
import ru.netology.diploma_cloudservice.repository.FileRepository;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;


@SpringBootTest
class FileServiceImplTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileServiceImpl fileServiceImpl;

    @Test
    void uploadFile_Success() throws IOException {
        User user = new User("username", "password");
        MockMultipartFile file = new MockMultipartFile("testFile", "testData".getBytes());

        fileServiceImpl.uploadFile(user, "filename", file);

        verify(fileRepository, times(1)).save(any(StoreFile.class));
    }

    @Test
    void uploadFile_InputDataException() {
        User user = new User("username", "password");
        MockMultipartFile file = new MockMultipartFile("testFile", "testData".getBytes());
        doThrow(new InputDataException("Upload file: Input data exception")).when(fileRepository).save(any(StoreFile.class));

        assertThrows(InputDataException.class, () -> fileServiceImpl.uploadFile(user, "filename", file));
    }

    @Test
    void deleteFile_Success() {
        User user = new User("username", "password");

        assertDoesNotThrow(() -> fileServiceImpl.deleteFile(user, "filename"));

        verify(fileRepository, times(1)).deleteByUserAndFilename(user, "filename");
    }

    @Test
    void deleteFile_InputDataException() {
        User user = new User("username", "password");
        doReturn(new StoreFile("filename", LocalDateTime.now(), 100L, new byte[0], user)).when(fileRepository).findByUserAndFilename(user, "filename");

        assertThrows(InputDataException.class, () -> fileServiceImpl.deleteFile(user, "filename"));
    }

    @Test
    void downloadFile_Success() {
        User user = new User("username", "password");
        byte[] fileContent = "testData".getBytes();
        StoreFile storageFile = new StoreFile("filename", LocalDateTime.now(), 100L, fileContent, user);
        doReturn(storageFile).when(fileRepository).findByUserAndFilename(user, "filename");

        byte[] downloadedContent = fileServiceImpl.downloadFile(user, "filename");

        assertArrayEquals(fileContent, downloadedContent);
    }
}