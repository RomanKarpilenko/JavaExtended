package ru.itmo.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @OneToOne
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
