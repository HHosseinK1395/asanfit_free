package Others;

/**
 * Created by N550J on 1/18/2018.
 */

public class Plans {
    String name;
    String description;
    String style;
    String iconUri;
    Long price;
    Long off;
    boolean isActive;
    Long id;

    public Plans(String name, String description, String style, String iconUri, Long price, Long off, boolean isActive, Long id) {
        this.name = name;
        this.description = description;
        this.style = style;
        this.iconUri = iconUri;
        this.price = price;
        this.off = off;
        this.isActive = isActive;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getOff() {
        return off;
    }

    public void setOff(Long off) {
        this.off = off;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
