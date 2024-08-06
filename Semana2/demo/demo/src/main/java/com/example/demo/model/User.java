package com.example.demo.model;
import lombok.Data;
@Data // esta etiqueta permite que se generen los getters y setters de forma automatica
public class User {

    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
}
