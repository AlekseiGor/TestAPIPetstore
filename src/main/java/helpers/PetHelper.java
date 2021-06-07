package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import endpoints.models.Category;
import endpoints.models.Pet;

public class PetHelper {

    ObjectMapper objectMapper = new ObjectMapper();

    public String createPetJson(String name, String status) {
        var result = "";
        var pet = new Pet()
                .setCategory(new Category())
                .setPhotoUrls(new String[2])
                .setName(name)
                .setStatus(status);
        try {
            result = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            System.out.println("Error when generating JSON from object " + e.getMessage());
        }
        return result;
    }
}
