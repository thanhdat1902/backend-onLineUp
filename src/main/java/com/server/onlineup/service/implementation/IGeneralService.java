package com.server.onlineup.service.implementation;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    T save(T t);

    void remove(int id);
}