package br.com.iamepp.bookcatalog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Publisher extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}
