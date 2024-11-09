package ru.netology.diploma_cloudservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.diploma_cloudservice.dto.response.AuthResponse;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AuthResponse> handleBadCredentials(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(e.getMessage(), 400));
    }

    @ExceptionHandler(FileNotExistException.class)
    public ResponseEntity<AuthResponse> handleDeleteFile(FileNotExistException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponse(e.getMessage(), 500));
    }

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<AuthResponse> handleInputData(InputDataException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(e.getMessage(), 400));
    }

    @ExceptionHandler(AuthicatedException.class)
    public ResponseEntity<AuthResponse> handleBadCredentials(AuthicatedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(e.getMessage(), 401));
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<AuthResponse> handleBadCredentials(FileException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponse(e.getMessage(), 500));
    }

    @ExceptionHandler(MissingValueException.class)
    public ResponseEntity<AuthResponse> handleBadCredentials(MissingValueException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponse(e.getMessage(), 500));
    }

}
