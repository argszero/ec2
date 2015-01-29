package org.argszero.ec2.controller;

import org.argszero.ec2.common.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shaoaq on 10/30/14.
 */
@ControllerAdvice
public class SecExceptionController {

    @ExceptionHandler(SecException.class)
    @ResponseBody
    public Response handleSecException(SecException ex) {
        return new Response(false,"sssssssssssss");
    }
    public static class SecException extends Exception{

    }
}
