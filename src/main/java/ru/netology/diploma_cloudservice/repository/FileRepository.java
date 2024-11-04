package ru.netology.diploma_cloudservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.diploma_cloudservice.entity.File;
import ru.netology.diploma_cloudservice.entity.User;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File findFileByFilenameAndUser(String filename, User user);

    Boolean existsAllByFilenameAndUser(String filename, User user);
}