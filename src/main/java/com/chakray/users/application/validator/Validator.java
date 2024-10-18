package com.chakray.users.application.validator;

public interface Validator<T> {

    boolean isValid(T parameter);
}
