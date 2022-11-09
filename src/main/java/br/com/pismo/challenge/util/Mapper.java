package br.com.pismo.challenge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public <D> D mapTo(Object object, Class<D> clazz) {

        return objectMapper.convertValue(object, clazz);
    }

}
