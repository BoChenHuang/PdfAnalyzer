package pdf_document;
import java.util.ArrayList;
import java.util.List;

public class TextObject {
    private String str;
    private float x;
    private float y;
    private int length;
    private int fullWidthCount;
    private int halfWidthCount;
    private List<Float> widthArr;
    private List<Float> heightArr;
    private List<FontObject> fontArr;
    private int lineIndex;


    public TextObject() {
        this.widthArr = new ArrayList<>();
        this.heightArr = new ArrayList<>();
        this.fontArr = new ArrayList<>();
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public int getHalfWidthCount() {
        return halfWidthCount;
    }

    public void setHalfWidthCount(int halfWidthCount) {
        this.halfWidthCount = halfWidthCount;
    }

    public int getFullWidthCount() {
        return fullWidthCount;
    }

    public void setFullWidthCount(int fullWidthCount) {
        this.fullWidthCount = fullWidthCount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
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

    public void addToHeightArr(float height) {
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
