package simple.crm.backend.mapper;

import org.mapstruct.Mapper;
import simple.crm.backend.config.MapperConfig;
import simple.crm.backend.dto.contact.ContactRequestDto;
import simple.crm.backend.dto.contact.ContactResponseDto;
import simple.crm.backend.model.Contact;

@Mapper(config = MapperConfig.class)
public interface ContactMapper {
    ContactResponseDto toDto(Contact service);

    Contact toModel(ContactRequestDto requestDto);
}
