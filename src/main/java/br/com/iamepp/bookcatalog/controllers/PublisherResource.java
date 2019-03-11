package br.com.iamepp.bookcatalog.controllers;

import br.com.iamepp.bookcatalog.services.PublisherService;
import br.com.iamepp.bookcatalog.services.dtos.PublisherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static br.com.iamepp.bookcatalog.controllers.util.HeaderUtil.createEntityCreationAlert;

@RestController
@RequestMapping(path = "/publisher", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublisherResource {
    private PublisherService service;
    private final Logger LOGGER = LoggerFactory.getLogger(PublisherResource.class);
    private static final String ENTITY_NAME = "publisher";

    @Autowired
    private PublisherResource(PublisherService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PublisherDTO> get(@PathVariable("id") Long id) {
        LOGGER.debug("Request to search for publisher with id : {}", id);
        return ResponseEntity
                .of(service.findById(id));
    }

    @GetMapping()
    public List<PublisherDTO> getAll() {
        LOGGER.debug("Request to list all publishers");
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<PublisherDTO> add(@RequestBody PublisherDTO publisherDTO)
            throws URISyntaxException {
        LOGGER.debug("Request to add a new publisher : {}", publisherDTO);
        var result = service.save(publisherDTO);
        return ResponseEntity.created(new URI("/api/tournaments/" + result.getId()))
                .headers(createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<PublisherDTO> update(@RequestBody PublisherDTO dto) {
        return null;
    }
}
