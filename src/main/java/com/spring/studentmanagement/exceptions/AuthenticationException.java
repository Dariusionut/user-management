package com.spring.studentmanagement.exceptions;

import lombok.extern.slf4j.Slf4j;

import java.io.Serial;

/**
 * Created at 4/25/2023 by Darius
 **/
@Slf4j
public class AuthenticationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 578783969488844889L;

    public AuthenticationException(String message) {
        super(message);
        log.error(message);
    }
}
