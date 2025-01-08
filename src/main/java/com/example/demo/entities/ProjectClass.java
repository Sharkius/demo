package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProjectClass {

    @Id
    @Column(name = "replaceIT")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //cascade = Toutes les opérations sur le projectClass sont récupércuté sur tout les éléments de la liste 
    // ALL pour toutes les opérations
    //OrphanRemoval il supprime ceux qui sont lié à lui
    
    @OneToMany(mappedBy = "trucmuche", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<OneToManyExample> manyEntities;
}
