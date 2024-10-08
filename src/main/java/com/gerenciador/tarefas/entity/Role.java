package com.gerenciador.tarefas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gerenciador.tarefas.permissions.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@Getter
@Setter
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<Usu> users;
}
