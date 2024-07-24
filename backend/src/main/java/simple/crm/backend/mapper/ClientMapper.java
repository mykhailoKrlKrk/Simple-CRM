package simple.crm.backend.mapper;

import org.mapstruct.Mapper;
import simple.crm.backend.config.MapperConfig;
import simple.crm.backend.dto.client.ClientRequestDto;
import simple.crm.backend.dto.client.ClientResponseDto;
import simple.crm.backend.model.Client;

@Mapper(config = MapperConfig.class)
public interface ClientMapper {
    ClientResponseDto toDto(Client service);

    Client toModel(ClientRequestDto requestDto);
}
