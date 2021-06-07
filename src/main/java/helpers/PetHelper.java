package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import endpoints.Endpoints;
import endpoints.models.Category;
import endpoints.models.Pet;
import endpoints.models.Tag;
import endpoints.models.enums.PetStatus;
import io.restassured.RestAssured;
import utils.RandomValue;

import java.lang.reflect.Array;

public class PetHelper{

    BaseMethods baseMethods = new BaseMethods();
    RandomValue randomValue = new RandomValue();

    public String generatePetAndGetId() {
        return RestAssured.given().spec(baseMethods.getBaseSpecification())
                .body(createPetJson(randomValue.getString(8), PetStatus.AVAILABLE.getStatus()))
                .post(Endpoints.Pet.ADD_PUT)
                .then()
                .extract()
                .path("id")
                .toString();
    }

    public String createPetJson(String name, String status) {
        var result = "";
        var pet = new Pet()
                .setCategory(new Category())
                .setTags(new Tag[1])
                .setPhotoUrls(new String[2])
                .setName(name)
                .setStatus(status);
        try {
            result = new ObjectMapper().writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            System.out.println("Error when generating JSON from object " + e.getMessage());
        }
        return result;
    }
}
