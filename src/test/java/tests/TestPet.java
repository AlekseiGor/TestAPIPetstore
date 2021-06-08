package tests;

import endpoints.Endpoints;
import endpoints.models.Category;
import endpoints.models.Pet;
import endpoints.models.Tag;
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

    @Test
    @DisplayName("Add a new pet")
    public void addPet(){
        var name = RandomValue.getString(8);
        var pet = new Pet()
                .setCategory(new Category())
                .setTags(new Tag[1])
                .setPhotoUrls(new String[2])
                .setName(name)
                .setStatus(PetStatus.AVAILABLE.getStatus());
        var petId = petHelper.createPetAndGetId(pet);
        given().spec(getBaseSpecification())
                .when()
                    .get(Endpoints.Pet.FIND_UPDATE_DELETE + petId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", equalTo(petId))
                    .body("name", equalTo(name))
                    .body("status", equalTo(PetStatus.AVAILABLE.getStatus()));

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
                    .body("id", equalTo(randomPetId));

    }

    @Test
    @DisplayName("Get pet by wrong Id")
    public void getPetByWrongId() {
        given().spec(getBaseSpecification())
                .when()
                    .get(Endpoints.Pet.FIND_UPDATE_DELETE + 0)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    @DisplayName("Get pet by invalid Id")
    public void getPetByInvalidId() {
        var invalidId = RandomValue.getString(8);
        given().spec(getBaseSpecification())
                .when()
                    .get(Endpoints.Pet.FIND_UPDATE_DELETE + invalidId)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    @DisplayName("Delete pet by Id")
    public void deletePetById() {
        var randomPetId = petHelper.generatePetAndGetId();
        given().spec(getBaseSpecification())
                .when()
                    .delete(Endpoints.Pet.FIND_UPDATE_DELETE + randomPetId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("message", equalTo(randomPetId.toString()));
    }

    @Test
    @DisplayName("Delete pet by wrong Id")
    public void deletePetByWrongId() {
        given().spec(getBaseSpecification())
                .when()
                    .delete(Endpoints.Pet.FIND_UPDATE_DELETE + 0)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Delete pet by invalid Id")
    public void deletePetByInvalidId() {
        var invalidId = RandomValue.getString(8);
        given().spec(getBaseSpecification())
                .when()
                    .delete(Endpoints.Pet.FIND_UPDATE_DELETE + invalidId)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);

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
                    .body("status", hasItem(petStatus.getStatus()));
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

    @Test
    @DisplayName("Update pet info with form")
    public void updatePetWithForm() {
        var randomPetId = petHelper.generatePetAndGetId();
        var name = RandomValue.getString(8);
        given().spec(getBaseSpecification())
                .contentType("application/x-www-form-urlencoded")
                .param("name", name)
                .param("status", PetStatus.SOLD.getStatus())
                .when()
                    .post(Endpoints.Pet.FIND_UPDATE_DELETE + randomPetId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("message", equalTo(randomPetId.toString()));
        given().spec(getBaseSpecification())
                .when()
                    .get(Endpoints.Pet.FIND_UPDATE_DELETE + randomPetId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", equalTo(randomPetId))
                    .body("name", equalTo(name))
                    .body("status", equalTo(PetStatus.SOLD.getStatus()));
    }
}
