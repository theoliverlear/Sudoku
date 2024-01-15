package org.theoliverlear.entity.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.theoliverlear.entity.Board;

@Converter
public class BoardJsonConverter implements AttributeConverter<Board, String> {
    // This class is used to convert a Board object to a JSON string and vice
    // versa. This is used to store the board in the database using entity
    // attribute.

    // This class implements the AttributeConverter interface, which requires
    // the methods convertToDatabaseColumn() and convertToEntityAttribute().
    // These methods are used to convert the board to a JSON string and vice
    // versa. They are implemented using the Jackson ObjectMapper class which
    // is a typical JSON parser and converter.
    ObjectMapper objectMapper = new ObjectMapper();

    //============================-Methods-===================================

    //------------------------Covert-Board-To-JSON----------------------------
    @Override
    public String convertToDatabaseColumn(Board board) {
        try {
            // Turn the board into a JSON string.
            return objectMapper.writeValueAsString(board);
        } catch (Exception ex) {
            // If there is an error in the creation of a JSON, an exception is
            // thrown.
            final String EXCEPTION_MESSAGE = "Error converting Board to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //----------------------Covert-JSON-To-Board------------------------------
    @Override
    public Board convertToEntityAttribute(String board) {
        try {
            // Turn the JSON string into a Board object and return it.
            return objectMapper.readValue(board, Board.class);
        } catch (Exception ex) {
            // If there is an error in the creation of a board from a JSON
            // string, an exception is thrown.
            final String EXCEPTION_MESSAGE = "Error converting JSON to Board.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
