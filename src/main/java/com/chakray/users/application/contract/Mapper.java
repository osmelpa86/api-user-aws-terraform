package com.chakray.users.application.contract;

public interface Mapper<T, V> {
    V map(T input);
}
