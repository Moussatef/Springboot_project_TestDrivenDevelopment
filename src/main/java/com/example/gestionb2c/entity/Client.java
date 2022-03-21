package com.example.gestionb2c.entity;

import com.example.gestionb2c.enums.Gander;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(unique = true,nullable = false)
    private String  phone ;

    @Column(nullable = false)
    private String fullName;

    @Column()
    @Enumerated
    private Gander gander;

    @Column()
    private boolean isActive = true;


    private Integer old;





}
