package tests;

import endpoints.Endpoints;
import endpoints.models.enums.PetStatus;
import helpers.BaseMethods;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class TestPet extends BaseMethods {

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
