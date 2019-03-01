package br.com.iamepp.bookcatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.iamepp.bookcatalog.models.Publisher;
import br.com.iamepp.bookcatalog.repositories.PublisherRepository;
import br.com.iamepp.bookcatalog.services.dtos.PublisherDTO;
import br.com.iamepp.bookcatalog.services.mapper.PublisherMapper;

@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;
    private final PublisherMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);

    @Autowired
    public PublisherServiceImpl(PublisherRepository repository, PublisherMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PublisherDTO save(PublisherDTO publisherDTO) {
        logger.debug("Saving publisher : { }", publisherDTO);
        final var publisher = mapper.toEntity(publisherDTO);
        return mapper.toDto(repository.save(publisher));
    }

    @Override
    public Optional<PublisherDTO> findById(Long id) {
        logger.debug("Searching for publisher with id: { }", id);
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public void delete(PublisherDTO publisherDTO) {
        repository.delete(mapper.toEntity(publisherDTO));
    }

    @Override
    public List<PublisherDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
