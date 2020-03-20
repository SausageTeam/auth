package com.app.auth.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "PERMISSION_NAME")
    private String permissionName;

    @Column(name = "PERMISSION_DESCRIPTION")
    private String permissionDescription;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "MODIFICATION_DATE")
    private String modificationDate;

    @Column(name = "LAST_MODIFICATION_USER")
    private int lastModificationUser;
}
