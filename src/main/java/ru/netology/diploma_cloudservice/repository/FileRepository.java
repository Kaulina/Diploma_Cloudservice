package ru.netology.diploma_cloudservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.diploma_cloudservice.entity.StoreFile;
import ru.netology.diploma_cloudservice.entity.User;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<StoreFile, String> {
    void deleteByUserAndFilename(User user, String filename);

    StoreFile findByUserAndFilename(User user, String filename);

    @Modifying
    @Query("update StoreFile f set f.filename = :newName " +
            "where f.filename = :filename " +
            "and f.user = :user")
    void updateFilenameByUser(@Param("user") User user,
                              @Param("filename") String filename,
                              @Param("newName") String newName);

    List<StoreFile> findAllByUser(User user);

}