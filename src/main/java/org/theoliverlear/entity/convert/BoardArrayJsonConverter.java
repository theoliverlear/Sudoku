package org.theoliverlear.entity.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BoardArrayJsonConverter implements AttributeConverter<int[][], String> {
    // This class is used to convert a 2D array of integers to a JSON string
    // and vice versa. This is used to store the board in the database using
    // entity attribute.

    // This class implements the AttributeConverter interface, which requires
    // the methods convertToDatabaseColumn() and convertToEntityAttribute().
    // These methods are used to convert the board to a JSON string and vice
    // versa. They are implemented using the Jackson ObjectMapper class which
    // is a typical JSON parser and converter.
    ObjectMapper objectMapper = new ObjectMapper();

    //============================-Methods-===================================

    //----------------------Covert-Board-To-JSON------------------------------
    @Override
    public String convertToDatabaseColumn(int[][] board) {
        try {
            // Turn the board into a JSON string.
            return objectMapper.writeValueAsString(board);
        } catch (JsonProcessingException ex) {
            // If there is an error in the creation of a JSON, an exception is
            // thrown.
            final String EXCEPTION_MESSAGE = "Error converting board to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //------------------------Covert-JSON-To-Board----------------------------
    @Override
    public int[][] convertToEntityAttribute(String board) {
        try {
            // Turn the JSON string into a 2d array of integers board and
            // return it.
            return objectMapper.readValue(board, int[][].class);
        } catch (JsonProcessingException ex) {
            // If there is an error in the creation of a board from a JSON
            // string, an exception is thrown.
            final String EXCEPTION_MESSAGE = "Error converting JSON to board.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
}
