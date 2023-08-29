package object_stripper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import pdf_document.FontObject;
import pdf_document.TextObject;
import utils.Utils;

public class TextStripper extends PDFTextStripper {
    private List<TextObject> textObjectsListOfPage;
    private PDDocument pdfFile;

    private int totalTextCountOfPage;
    private int totalFullWidthCountOfPage;
    private int totalHalfWidthCountOfPage;
    private List<Float> linePos;

    public TextStripper(PDDocument pdfFile) throws IOException {
        this.pdfFile = pdfFile;
        this.textObjectsListOfPage = new ArrayList<>();
        this.totalTextCountOfPage = 0;
        this.totalFullWidthCountOfPage = 0;
        this.totalHalfWidthCountOfPage = 0;
        this.linePos = new ArrayList<>();
        this.setSortByPosition(true);
    }

    public void clear() {
        this.textObjectsListOfPage.clear();
        this.totalTextCountOfPage = 0;
        this.totalFullWidthCountOfPage = 0;
        this.totalHalfWidthCountOfPage = 0;
    }

    private int linePosMatch(float y) {
        int index = 0;
        if (!this.linePos.contains(y)) {
            for (float pos : this.linePos) {
                if (Math.abs(y - pos) < 1) {
                    index = linePos.indexOf(pos);
                    return index;
                }
            }
            linePos.add(y);
            index = linePos.indexOf(y);
        } else
            index = linePos.indexOf(y);

        return index;
    }

    public void processPage(int index) throws IOException {
        this.clear();
        this.setStartPage(index + 1);
        this.setEndPage(index + 1);
        Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
        this.writeText(this.pdfFile, dummy);
    }

    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
        TextObject textObject = new TextObject();
        // set textObj props
        textObject.setStr(string);
        textObject.setLength(string.length());
        textObject.setHalfWidthCount(Utils.getHalfWidthCount(string));
        textObject.setFullWidthCount(Utils.getFullWidthCount(string));
        this.totalHalfWidthCountOfPage += textObject.getHalfWidthCount();
        this.totalFullWidthCountOfPage += textObject.getFullWidthCount();
        this.totalTextCountOfPage = this.totalFullWidthCountOfPage + this.totalHalfWidthCountOfPage;

        // get fonts
        for (int i = 0, size = textPositions.size(); i < size; i++) {
            TextPosition textPos = textPositions.get(i);
            String fontName = textPos.getFont().getName();

            if (i == 0) { // 一串字的開頭作為該串字的座標
                textObject.setX(textPos.getXDirAdj());
                textObject.setY(textPos.getYDirAdj());
            }

            textObject.addToFontArr(new FontObject(fontName, i, textPos.getFontSizeInPt()));
            textObject.addToHeightArr(textPos.getHeightDir());
            textObject.addToWidthArr(textPos.getWidthDirAdj());
        }
        textObject.setLineIndex(this.linePosMatch(textObject.getY()));
        this.textObjectsListOfPage.add(textObject);
    }

    public PDDocument getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(PDDocument pdfFile) {
        this.pdfFile = pdfFile;
    }

    public int getTotalTextCountOfPage() {
        return totalTextCountOfPage;
    }

    public void setTotalTextCountOfPage(int totalTextCountOfPage) {
        this.totalTextCountOfPage = totalTextCountOfPage;
    }

    public int getTotalHalfWidthCountOfPage() {
        return totalHalfWidthCountOfPage;
    }

    public void setTotalHalfWidthCountOfPage(int totalHalfWidthCount) {
        this.totalHalfWidthCountOfPage = totalHalfWidthCount;
    }

    public int getTotalFullWidthCountOfPage() {
        return totalFullWidthCountOfPage;
    }

    public void setTotalFullWidthCountOfPage(int totalFullWidthCount) {
        this.totalFullWidthCountOfPage = totalFullWidthCount;
    }

    public List<TextObject> getTextObjectsListOfPage() {
        return textObjectsListOfPage;
    }

    public void setTextObjectsListOfPage(List<TextObject> textObjectsList) {
        this.textObjectsListOfPage = textObjectsList;
    }

}
