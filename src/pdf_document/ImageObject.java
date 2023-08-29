package pdf_document;

public class ImageObject {
    private int index;
    private String name;
    private int page;
    private double width;
    private double height;
    private double displayWidth;
    private double displayHeight;
    private double x;
    private double y;
    private String format;

    public ImageObject(int index, String name, int page, double width, double height, double displayWidth,
            double displayHeight, double x, double y, String format) {
        this.index = index;
        this.name = name;
        this.page = page;
        this.width = width;
        this.height = height;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.x = x;
        this.y = y;
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(double displayHeight) {
        this.displayHeight = displayHeight;
    }

    public double getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(double displayWidth) {
        this.displayWidth = displayWidth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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
}
