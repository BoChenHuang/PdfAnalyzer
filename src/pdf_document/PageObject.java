package pdf_document;
import java.util.List;

public class PageObject {
    private int index;
    private int totalTextCount;
    private int halfWidthCount;
    private int fullWidthCount;
    private float width;
    private float height;
    private List<TextObject> textObjects = null;
    private List<ImageObject> imageObjects = null;

    public PageObject(int index) {
        this.index = index;
        this.totalTextCount = 0;
        this.fullWidthCount = 0;
        this.halfWidthCount = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTotalTextCount() {
        return totalTextCount;
    }

    public int getHalfWidthCount() {
        return halfWidthCount;
    }

    public int getFullWidthCount() {
        return fullWidthCount;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public List<TextObject> getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(List<TextObject> textObjects) {
        this.textObjects = textObjects;
        for(TextObject textObject : this.textObjects) {
            this.fullWidthCount += textObject.getFullWidthCount();
            this.halfWidthCount += textObject.getHalfWidthCount();
            this.totalTextCount = this.fullWidthCount + this.halfWidthCount;
        }
    }

    public void addToTextObjects(TextObject textObject) {
        if (this.textObjects != null) {
            this.textObjects.add(textObject);
            this.fullWidthCount += textObject.getFullWidthCount();
            this.halfWidthCount += textObject.getHalfWidthCount();
            this.totalTextCount = this.fullWidthCount + this.halfWidthCount;
        }
    }

    public List<ImageObject> getImageObjects() {
        return imageObjects;
    }

    public void setImageObjects(List<ImageObject> imageObjects) {
        this.imageObjects = imageObjects;
    }

    public void addToImageObjects(ImageObject imageObject) {
        if (this.imageObjects != null) {
            this.imageObjects.add(imageObject);
        }
    }
}
