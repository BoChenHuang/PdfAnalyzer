package pdf_document;

public class FontObject {
    private String name;
    private int index;
    private float size;

    public FontObject(String name, int index, float size) {
        this.name = name;
        this.index = index;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
