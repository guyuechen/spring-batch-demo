package com.sb.example.address.entity;

import lombok.Data;

@Data
public class Address {

    private String jis;
    private String zipcodeOld;
    private String zipcode;
    private String prefectureKana;
    private String cityKana;
    private String townKana;
    private String prefecture;
    private String city;
    private String town;
    private String duplicateTown;
    private String koaza;
    private String hasTyome;
    private String duplicateZipcode;
    private String update;
    private String changeReason;

}
