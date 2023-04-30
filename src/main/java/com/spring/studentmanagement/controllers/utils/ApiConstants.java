package com.spring.studentmanagement.controllers.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Created at 4/30/2023 by Darius
 **/
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstants {

    public static final String REDIRECT_ERROR_401 = "redirect:/errors/error-401";
    public static final String REDIRECT_ERROR_500 = "redirect:/errors/error-500";

    public static final String ATTR_ERROR_MESSAGE = "errorMessage";
}
