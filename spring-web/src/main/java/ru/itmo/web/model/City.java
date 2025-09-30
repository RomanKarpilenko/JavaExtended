package ru.itmo.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_translation_key_id")
    private TranslationKey nameTranslationKey;

    @Column(name = "code")
    private String code;

    @Column(name = "population")
    private Long population;

    public City(TranslationKey nameTranslationKey, String code, Long population) {
        this.nameTranslationKey = nameTranslationKey;
        this.code = code;
        this.population = population;
    }
}
