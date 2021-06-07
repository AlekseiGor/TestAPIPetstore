package endpoints.models;

public class Category {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public Category setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }
}
