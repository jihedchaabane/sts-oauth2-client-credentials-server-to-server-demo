package com.chj.gr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException ex) {
	    ErrorResponse errorResponse = new ErrorResponse(
	        "http_client_error",
	        "Failed to communicate with endpoint: " + ex.getResponseBodyAsString(),
	        ex.getStatusCode().value()
	    );
	    return new ResponseEntity<>(errorResponse, ex.getStatusCode());
	}
//    @ExceptionHandler(HttpClientErrorException.class)
//    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
//        String errorMessage = "Failed to obtain response: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString();
//        return new ResponseEntity<>(errorMessage, ex.getStatusCode());
//    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> handleRestClientException(RestClientException ex) {
        String errorMessage = "Error communicating with endpoint: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleTokenRetrievalException(CustomException ex) {
        String errorMessage = "Call failed CustomException thrown: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String errorMessage = "Unexpected error: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
