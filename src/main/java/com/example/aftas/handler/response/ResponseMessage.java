package com.example.aftas.handler.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessage {
    public int status;
    public String message;
    public Object data;

    public ResponseMessage() {
    }

    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }


    public ResponseMessage(int status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity ok(String message, Object data) {
        return new ResponseEntity(new ResponseMessage(HttpStatus.OK.value(), message, data), HttpStatus.OK);
    }

    public static ResponseEntity created(String message, Object data){
        return new ResponseEntity(new ResponseMessage(HttpStatus.CREATED.value(), message,data), HttpStatus.CREATED);
    }

    public static ResponseEntity badRequest(String message) {
        return new ResponseEntity(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ResponseMessage> notFound(String message){
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.NOT_FOUND.value(), message), HttpStatus.NOT_FOUND);
    }


}
