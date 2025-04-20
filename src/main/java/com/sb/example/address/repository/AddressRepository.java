package com.sb.example.address.repository;

import com.sb.example.address.entity.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressRepository {

    @Delete("DELETE FROM TBL_ADDRESS")
    void deleteAll();

    void insertAddress(Address address);

}
