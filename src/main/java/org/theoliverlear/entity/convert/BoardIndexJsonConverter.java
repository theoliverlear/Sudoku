package org.theoliverlear.entity.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.theoliverlear.entity.BoardIndex;

@Converter(autoApply = true)
public class BoardIndexJsonConverter implements AttributeConverter<BoardIndex, String> {
    // This class is used to convert a BoardIndex object to a JSON string and
    // vice versa. This is used to store the board in the database using
    // entity attribute.

    // This class implements the AttributeConverter interface, which requires
    // the methods convertToDatabaseColumn() and convertToEntityAttribute().
    // These methods are used to convert the board index to a JSON string and
    // vice versa. They are implemented using the Jackson ObjectMapper class
    // which is a typical JSON parser and converter.
    ObjectMapper objectMapper = new ObjectMapper();
    //============================-Methods-===================================

    //-----------------------Covert-BoardIndex-To-JSON------------------------
    @Override
    public String convertToDatabaseColumn(BoardIndex boardIndex) {
        try {
            // Turn the board index into a JSON string.
            return objectMapper.writeValueAsString(boardIndex);
        } catch (JsonProcessingException ex) {
            // If there is an error in the creation of a JSON, an exception is
            // thrown.
            final String EXCEPTION_MESSAGE = "Error converting BoardIndex to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //----------------------Covert-JSON-To-BoardIndex-------------------------
    @Override
    public BoardIndex convertToEntityAttribute(String boardIndex) {
        try {
            // Turn the JSON string into a BoardIndex object and return it.
            return objectMapper.readValue(boardIndex, BoardIndex.class);
        } catch (JsonProcessingException ex) {
            // If there is an error in the creation of a board index from a JSON
            // string, an exception is thrown.
            final String EXCEPTION_MESSAGE = "Error converting JSON to BoardIndex.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
