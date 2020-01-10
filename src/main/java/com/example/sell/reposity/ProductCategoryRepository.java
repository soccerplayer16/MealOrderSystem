package com.example.sell.reposity;

import com.example.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/*
repository is like a data table, which contains a bunch of rows of data
DAO is one row of data
repository.save(XX) will save the data to table

 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
