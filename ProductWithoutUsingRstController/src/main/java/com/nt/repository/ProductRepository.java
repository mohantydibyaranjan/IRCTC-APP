package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nt.entity.Product;
@RepositoryRestResource(path="product")
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
