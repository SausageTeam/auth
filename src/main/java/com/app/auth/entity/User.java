package com.app.auth.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Column(name = "CREATED_DATE_TIME")
    private String createdDateTime;

    @Column(name = "MODIFICATION_DATE_TIME")
    private String modificationDateTime;

}
