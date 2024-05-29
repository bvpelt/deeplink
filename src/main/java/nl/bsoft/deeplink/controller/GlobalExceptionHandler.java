package nl.bsoft.deeplink.controller;

import nl.bsoft.deeplink.exception.NotFoundException;
import nl.bsoft.deeplink.generated.model.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Problem> handleNotFoundException(NotFoundException e) {
        Problem problem = new Problem();
        problem.setStatus(404);
        problem.setDetail(e.getMessage());
        problem.setDetail(e.toString());
        return new ResponseEntity<>(problem, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handleException(Exception e) {
        Problem problem = new Problem();
        problem.setStatus(500);
        problem.setTitle("Internal Server Error. An unexpected error occurred.");
        problem.setDetail(e.getMessage());
        problem.setDetail(e.toString());

        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}