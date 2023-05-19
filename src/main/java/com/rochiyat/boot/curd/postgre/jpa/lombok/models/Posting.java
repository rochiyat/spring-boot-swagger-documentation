package com.rochiyat.boot.curd.postgre.jpa.lombok.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "posting")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    public Posting(String tittle, String description, boolean published) {
        this.tittle = tittle;
        this.description = description;
        this.published = published;
    }
}
