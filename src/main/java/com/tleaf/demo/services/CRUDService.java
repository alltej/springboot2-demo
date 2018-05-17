package com.tleaf.demo.services;

import java.util.List;

/**
 * @author Allan Tejano
 * 4/20/2018
 */
@Deprecated
public interface CRUDService<T> {
    List<T> listAll();

    T getById( Integer id );

    T saveOrUpdate( T domainObject );

    void delete( Integer id );
}
