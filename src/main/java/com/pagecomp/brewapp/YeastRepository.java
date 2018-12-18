package com.pagecomp.brewapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YeastRepository extends CrudRepository<Yeast, Integer> {
}