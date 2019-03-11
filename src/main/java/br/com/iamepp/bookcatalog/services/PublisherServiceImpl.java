package br.com.iamepp.bookcatalog.services;

import br.com.iamepp.bookcatalog.repositories.PublisherRepository;
import br.com.iamepp.bookcatalog.services.dtos.PublisherDTO;
import br.com.iamepp.bookcatalog.services.mapper.PublisherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;
    private final PublisherMapper mapper;
    private final Logger LOGGER = LoggerFactory.getLogger(PublisherServiceImpl.class);

    @Autowired
    public PublisherServiceImpl(PublisherRepository repository, PublisherMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PublisherDTO save(PublisherDTO dto) {
        LOGGER.debug("Saving publisher : { }", dto);
        final var publisher = mapper.toEntity(dto);
        final var saved = repository.save(publisher);
        return mapper.toDto(saved);
    }

    @Override
    public Optional<PublisherDTO> findById(Long id) {
        LOGGER.debug("Searching for publisher with id: { }", id);
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public void delete(PublisherDTO dto) {
        LOGGER.debug("Deleting a publisher : {}", dto);
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public List<PublisherDTO> findAll() {
        LOGGER.debug("Listing all publishers");
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
