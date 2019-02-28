package br.com.iamepp.bookcatalog.services;

import java.util.List;
import java.util.Optional;

import br.com.iamepp.bookcatalog.services.dtos.PublisherDTO;

public interface PublisherService {
    public PublisherDTO save(PublisherDTO publisherDTO);

    public Optional<PublisherDTO> findById(Long id);

    public void delete(PublisherDTO publisherDTO);

    public List<PublisherDTO> findAll();
}