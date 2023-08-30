package pdf_document;
import java.util.ArrayList;
import java.util.List;

public class DocumentObject {
    private String name;
    private MetadataObject metadata;
    private List<PageObject> pages;
    private Integer numberOfPages;

    public DocumentObject() {
        this.metadata = new MetadataObject();
        this.pages = new ArrayList<>();
        this.numberOfPages = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetadataObject getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataObject metadata) {
        this.metadata = metadata;
    }

    public void setPaggs(List<PageObject> pages) {
        this.pages = pages;
    }

    public List<PageObject> getPages() {
        return pages;
    }

    public void addPage(PageObject page) {
        this.pages.add(page);
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
