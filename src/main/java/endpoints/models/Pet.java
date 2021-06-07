package endpoints.models;

public class Pet {

    private Integer id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private String status;


    public Integer getId() {
        return id;
    }

    public Pet setId(Integer id) {
        this.id = id;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Pet setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public Pet setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public Tag[] getTags() {
        return tags;
    }

    public Pet setTags(Tag[] tags) {
        this.tags = tags;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Pet setStatus(String status) {
        this.status = status;
        return this;
    }
}
