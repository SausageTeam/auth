package com.app.auth.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RolePermission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne
    @JoinColumn(name = "PERMISSION_ID")
    private Permission permission;

    @Column(name = "ACTIVE_FLAG")
    private int activeFlag;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "MODIFICATION_DATE")
    private String modificationDate;

    @Column(name = "LAST_MODIFICATION_USER")
    private int lastModificationUser;

}
