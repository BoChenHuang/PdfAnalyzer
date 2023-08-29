package pdf_document;
import java.util.ArrayList;
import java.util.List;

public class TextObject {
    private List<Float> widthArr;
    private List<Float> heightArr;
    private List<FontObject> fontArr;

    public TextObject() {
        this.widthArr = new ArrayList<>();
        this.heightArr = new ArrayList<>();
    }

    public List<Float> getWidthArr() {
        return widthArr;
    }

    public void setWidthArr(List<Float> width) {
        this.widthArr = width;
    }

    public void addToWidthArr(float width) {
        if ( this.widthArr != null) {
            this.widthArr.add(width);
        }
    }

    public List<Float> getHeightArr() {
        return heightArr;
    }

    public void setHeightArr(List<Float> height) {
        this.heightArr = height;
    }

    public void addToHeight(float height) {
        if ( this.heightArr != null) {
            this.heightArr.add(height);
        }
    }

    public List<FontObject> getFontArr() {
        return fontArr;
    }

    public void setFontArr(List<FontObject> fontArr) {
        this.fontArr = fontArr;
    }

    public void addToFontArr(FontObject fontObject) {
        if (this.fontArr != null) {
            this.fontArr.add(fontObject);
        }
    }
}
