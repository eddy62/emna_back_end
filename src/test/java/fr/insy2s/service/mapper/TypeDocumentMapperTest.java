package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeDocumentMapperTest {

    private TypeDocumentMapper typeDocumentMapper;

    @BeforeEach
    public void setUp() {
        typeDocumentMapper = new TypeDocumentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeDocumentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeDocumentMapper.fromId(null)).isNull();
    }
}
