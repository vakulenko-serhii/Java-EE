package com.example.demo.model;

import com.example.demo.model.security.Permission;
import lombok.*;


import javax.persistence.*;


@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
    public class PermissionEntity {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        @Enumerated(EnumType.STRING)
        @Column(name = "permission", unique = true)
        private Permission permission;

}


