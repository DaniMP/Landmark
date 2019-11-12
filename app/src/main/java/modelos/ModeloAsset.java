package modelos;

public class ModeloAsset {
    public String id;
    public String url;
    public String description;
    public String title;
    public int category;

    public ModeloAsset(String id, String url, String title, String description, int category) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.title = title;
        this.category = category;
    }
}
