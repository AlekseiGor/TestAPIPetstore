package tests;

import endpoints.Endpoints;
import endpoints.models.Pet;
import endpoints.models.enums.PetStatus;
import helpers.BaseMethods;
import helpers.PetHelper;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import utils.RandomValue;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestPet extends BaseMethods {

    PetHelper petHelper = new PetHelper();
    RandomValue randomValue = new RandomValue();

    @Test
    @DisplayName("Add a new pet")
    public void addPet(){
        final String name = randomValue.getString(8);
        given().spec(getBaseSpecification())
                .body(petHelper.createPetJson(name, PetStatus.AVAILABLE.getStatus()))
                .when()
                    .post(Endpoints.Pet.ADD_PUT)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("name", equalTo(name));
    }

    @Test
    @DisplayName("Get pet by Id")
    public void getPetById() {
        var randomPetId = petHelper.generatePetAndGetId();
        given().spec(getBaseSpecification())
                .when()
                    .get(Endpoints.Pet.FIND_UPDATE_DELETE + randomPetId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", equalTo(randomPetId))
                    .body(equalToObject(Pet.class));//need to check

    }

    @ParameterizedTest
    @DisplayName("Find pets by status")
    @EnumSource(PetStatus.class)
    public void findPetsByStatus(PetStatus petStatus) {
        given().spec(getBaseSpecification())
                .param("status", petStatus.getStatus())
                .when()
                    .get(Endpoints.Pet.FIND_BY_STATUS)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("status", hasItems(petStatus.getStatus()));
    }

    @Test
    @DisplayName("Check invalid status")
    public void checkInvalidStatus() {
        given().spec(getBaseSpecification())
                .param("status", "invalidStatus")
                .when()
                    .get(Endpoints.Pet.FIND_BY_STATUS)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
