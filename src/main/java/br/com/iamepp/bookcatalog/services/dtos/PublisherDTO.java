package br.com.iamepp.bookcatalog.services.dtos;

import java.io.Serializable;
import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PublisherDTO implements Serializable {
    private static final long serialVersionUID = 1967651465024135471L;
    private Long id;
    @NonNull
    private String name;
    private String address;

    public PublisherDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
