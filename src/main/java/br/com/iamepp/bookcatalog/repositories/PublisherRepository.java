package br.com.iamepp.bookcatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.iamepp.bookcatalog.models.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}