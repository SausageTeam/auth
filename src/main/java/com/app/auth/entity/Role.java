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
@Table(name = "Role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "MODIFICATION_DATE")
    private String modificationDate;

    @Column(name = "LAST_MODIFICATION_USER")
    private int lastModificationUser;

}
