package helpers;

import endpoints.Endpoints;
import endpoints.models.Category;
import endpoints.models.Pet;
import endpoints.models.Tag;
import endpoints.models.enums.PetStatus;
import io.restassured.RestAssured;
import utils.RandomValue;

public class PetHelper{

    BaseMethods baseMethods = new BaseMethods();

    public Long createPetAndGetId(Pet pet) {
        return RestAssured.given().spec(baseMethods.getBaseSpecification())
                .body(createPetJson(pet))
                .post(Endpoints.Pet.ADD_PUT)
                .then()
                    .extract()
                    .path("id");
    }

    public Long generatePetAndGetId() {
        var pet = new Pet()
                .setCategory(new Category())
                .setTags(new Tag[1])
                .setPhotoUrls(new String[2])
                .setName(RandomValue.getString(8))
                .setStatus(PetStatus.AVAILABLE.getStatus());
        return RestAssured.given().spec(baseMethods.getBaseSpecification())
                .body(createPetJson(pet))
                .post(Endpoints.Pet.ADD_PUT)
                .then()
                    .extract()
                    .path("id");
    }

    public Boolean checkPetInfo(Long id, Pet pet) {
        var response = RestAssured.given().spec(baseMethods.getBaseSpecification())
                .get(Endpoints.Pet.FIND_UPDATE_DELETE + id);
        return response.body().print().equals(baseMethods.createJsonFromObject(pet));
    }

    private String createPetJson(Pet pet) {
        return baseMethods.createJsonFromObject(pet);
    }
}
