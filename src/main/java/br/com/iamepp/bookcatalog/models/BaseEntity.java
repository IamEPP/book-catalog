package br.com.iamepp.bookcatalog.models;

import static java.util.Objects.nonNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public boolean isNell() {
        return nonNull(id);
    }
}