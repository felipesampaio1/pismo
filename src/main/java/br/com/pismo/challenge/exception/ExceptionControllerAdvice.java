package br.com.pismo.challenge.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, NativeWebRequest request) {

        Problem problem = Problem.builder()
                .error(ErrorEnum.ENTITY_NOT_FOUND)
                .message(ErrorEnum.ENTITY_NOT_FOUND.getTitle())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }



    @Builder
    @Data
    public static class Problem {

        private ErrorEnum error;
        private String message;
        private HttpStatus status;
    }
}
