package br.com.iamepp.bookcatalog.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.iamepp.bookcatalog.controllers.util.HeaderUtil;
import br.com.iamepp.bookcatalog.services.PublisherService;
import br.com.iamepp.bookcatalog.services.dtos.PublisherDTO;

@RestController
@RequestMapping(path = "/publisher", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublisherResource {
    private PublisherService service;
    private Logger log = LoggerFactory.getLogger(PublisherResource.class);
    private static final String ENTITY_NAME = "publisher";

    @Autowired
    private PublisherResource(PublisherService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PublisherDTO> get(@PathVariable("id") Long id) {
        log.debug("Seaching for publisher with id : {}", id);
        return ResponseEntity.of(service.findById(id));
    }

    @GetMapping()
    public List<PublisherDTO> getAll() {
        log.debug("Listing all publishers with id");
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<PublisherDTO> add(@RequestBody PublisherDTO publisherDTO)
            throws URISyntaxException {
        log.debug("Adding a new publisher : {}", publisherDTO);
        var result = service.save(publisherDTO);
        return ResponseEntity.created(new URI("/api/tournaments/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
                        result.getId().toString()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<PublisherDTO> update(@RequestBody PublisherDTO dto) {
        return null;
    }
}
