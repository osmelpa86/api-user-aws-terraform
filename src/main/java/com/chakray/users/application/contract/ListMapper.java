package com.chakray.users.application.contract;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ListMapper<T, V> {

    private final Mapper<T, V> mapper;

    public List<V> map(List<T> input) {
        return input.stream()
                .map(this.mapper::map)
                .collect(Collectors.toList());
    }

}