package com.server.onlineup.service.implementation;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    T save(T t);

    void remove(String id);
}