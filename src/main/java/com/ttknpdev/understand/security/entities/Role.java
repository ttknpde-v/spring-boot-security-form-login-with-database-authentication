package com.ttknpdev.understand.security.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "role")
public class Role {
    @Id // mark PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mark auto increment
    private Long rid;
    private String role;

}
