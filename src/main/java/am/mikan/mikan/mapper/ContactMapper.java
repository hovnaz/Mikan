package am.mikan.mikan.mapper;

import am.mikan.mikan.dto.request.ContactRequest;
import am.mikan.mikan.dto.response.ContactResponse;
import am.mikan.mikan.entity.Contact;
import am.mikan.mikan.mapper.base.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactMapper implements BaseMapper<Contact, ContactRequest, ContactResponse> {

    private final ModelMapper modelMapper;

    @Override
    public Contact toEntity(ContactRequest contactRequest) {
        return modelMapper.map(contactRequest, Contact.class);
    }

    @Override
    public ContactResponse toResponse(Contact contact) {
        return modelMapper.map(contact, ContactResponse.class);
    }
}
