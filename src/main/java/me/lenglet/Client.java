package me.lenglet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.IOException;

@Entity
@Table(name = "client")
public class Client {

    @Id
    private long id;
    @NaturalId
    private String businessId;
    private ClientProperties properties;

    Client() {
    }

    public Client(String businessId) {
        this.businessId = businessId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public ClientProperties getProperties() {
        return properties;
    }

    public void setProperties(ClientProperties properties) {
        this.properties = properties;
    }

    @Converter(autoApply = true)
    public static class ClientPropertiesToBlobConverter implements AttributeConverter<ClientProperties, byte[]> {

        private static final ObjectMapper mapper;

        static {
            mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
        }

        @Override
        public byte[] convertToDatabaseColumn(ClientProperties attribute) {
            if (attribute == null) {
                return null;
            }
            try {
                return mapper.writeValueAsBytes(attribute);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public ClientProperties convertToEntityAttribute(byte[] dbData) {
            if (dbData == null) {
                return null;
            }
            try {
                return mapper.readValue(dbData, ClientProperties.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class ClientProperties {

        private String name;
        private String firstname;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }
    }
}
