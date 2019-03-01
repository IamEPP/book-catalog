package br.com.iamepp.bookcatalog.services.mapper;

import org.mapstruct.*;

import br.com.iamepp.bookcatalog.models.Publisher;
import br.com.iamepp.bookcatalog.services.dtos.PublisherDTO;

@Mapper(componentModel = "spring", uses = {})
public interface PublisherMapper extends EntityMapper<PublisherDTO, Publisher> {

}