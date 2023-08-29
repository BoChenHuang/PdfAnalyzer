package pdf_document;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MetadataObject {
    private String title;
    private String author;
    private String description;
    private List<String> creators = null;
    private List<Calendar> dates = null;
    private List<String> subjects = null;
    private String keywords;
    private String version;
    private String producer;
    private String createDate;
    private String modifyDate;
    private String creatorTool;
    private String trapped;

    public MetadataObject() {
        this.creators = new ArrayList<>();
        this.dates = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCreators() {
        return creators;
    }

    public void setCreators(List<String> creators) {
        this.creators = creators;
    }

    public void addToCreators(String creator) {
        if (this.creators != null) {
            this.creators.add(creator);
        }
    }

    public List<Calendar> getDates() {
        return dates;
    }

    public void setDates(List<Calendar> dates) {
        this.dates = dates;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void addToSubjects(String subject) {
        if (this.subjects != null) {
            this.subjects.add(subject);
        }
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreatorTool() {
        return creatorTool;
    }

    public void setCreatorTool(String creatorTool) {
        this.creatorTool = creatorTool;
    }

    public String getTrapped() {
        return trapped;
    }

    public void setTrapped(String trapped) {
        this.trapped = trapped;
    }
}
