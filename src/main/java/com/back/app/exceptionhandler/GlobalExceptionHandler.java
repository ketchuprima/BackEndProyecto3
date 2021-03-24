package com.back.app.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.back.app.responses.MensajeRespuesta;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
         MensajeRespuesta message = new MensajeRespuesta(exception.getMessage());
         System.out.println("Error inc");
         return new ResponseEntity<Object>(message, HttpStatus.OK);
    }
    
    
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest request){
         MensajeRespuesta message = new MensajeRespuesta(exception.getMessage());
         System.out.println("Error inc");
         return new ResponseEntity<Object>(message, HttpStatus.OK);
    }
    
    
    

}
