package com.sb.demo.reader.readdb;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private int age;
}
