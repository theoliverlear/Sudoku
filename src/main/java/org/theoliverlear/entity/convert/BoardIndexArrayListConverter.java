package org.theoliverlear.entity.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.theoliverlear.model.sudoku.BoardIndex;

import java.util.ArrayList;

@Converter
public class BoardIndexArrayListConverter  implements AttributeConverter<ArrayList<BoardIndex>, String> {
    // This class is used to convert an ArrayList of BoardIndex objects to a
    // JSON string and vice versa. This is used to store the muted indices in
    // the database using entity attribute.

    // This class implements the AttributeConverter interface, which requires
    // the methods convertToDatabaseColumn() and convertToEntityAttribute().
    // These methods are used to convert the muted indices to a JSON string
    // and vice versa. They are implemented using the Jackson ObjectMapper
    // class which is a typical JSON parser and converter.
    ObjectMapper objectMapper = new ObjectMapper();
    //============================-Methods-===================================

    //------------------------Convert-List-To-JSON----------------------------
    @Override
    public String convertToDatabaseColumn(ArrayList<BoardIndex> mutedIndices) {
        try {
            // Turn the muted indices into a JSON string.
            return objectMapper.writeValueAsString(mutedIndices);
        } catch (JsonProcessingException ex) {
            // If there is an error in the creation of a JSON, an exception is
            // thrown.
            final String EXCEPTION_MESSAGE = "Error converting board indices to JSON.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }
    //-------------------------Convert-JSON-To-List---------------------------
    @Override
    public ArrayList<BoardIndex> convertToEntityAttribute(String mutedIndices) {
        try {
            // Turn the JSON string into a list of board indices and return
            // it. The TypeReference class is used to specify the type of the
            // object that is being converted from JSON. This is used instead
            // of the class itself because the class is generic.
            return objectMapper.readValue(mutedIndices, new TypeReference<ArrayList<BoardIndex>>() {
            });
        } catch (JsonProcessingException ex) {
            // If there is an error in the creation of a list of board indices
            // from a JSON string, an exception is thrown.
            final String EXCEPTION_MESSAGE = "Error converting JSON to board indices.";
            throw new RuntimeException(EXCEPTION_MESSAGE, ex);
        }
    }

}
