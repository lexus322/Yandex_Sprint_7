package resources;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;


@JsonIncludeProperties({"id"})
public class CourierId {
    private String id;

    public CourierId(String id) {
        this.id = id;
    }

    public CourierId() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
