package object_stripper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.state.Concatenate;
import org.apache.pdfbox.contentstream.operator.state.Restore;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.apache.pdfbox.contentstream.operator.state.SetGraphicsStateParameters;
import org.apache.pdfbox.contentstream.operator.state.SetMatrix;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.contentstream.operator.DrawObject;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;

import pdf_document.ImageObject;
import utils.Utils;

public class ImageStripper extends PDFStreamEngine {
    private List<ImageObject> imageObjectsOfPage;
    private List<String> md5Map;
    private int pageIndex;
    private int imageIndex;
    private PDDocument pdfFile;

    public ImageStripper(PDDocument pdfFile) {
        this.imageObjectsOfPage = new ArrayList<>();
        this.md5Map = new ArrayList<>();
        this.pageIndex = 0;
        this.imageIndex = 0;
        this.pdfFile = pdfFile;

        this.addOperator(new Concatenate());
        this.addOperator(new DrawObject());
        this.addOperator(new SetGraphicsStateParameters());
        this.addOperator(new Save());
        this.addOperator(new Restore());
        this.addOperator(new SetMatrix());
    }

    public void processPage(int index) throws IOException {
        this.clear();
        PDPage page = this.pdfFile.getPage(index);
        this.setPageIndex(index + 1);
        super.processPage(page);
    }

    public void clear() {
        this.imageObjectsOfPage.clear();
        this.pageIndex = 0;
    }

    @Override
    protected void processOperator(Operator operator, List<COSBase> operands) throws IOException {
        String operation = operator.getName();
        if (OperatorName.DRAW_OBJECT.equals(operation)) {
            COSName objectName = (COSName) operands.get(0);
            PDXObject xobject = getResources().getXObject(objectName);
            if (xobject instanceof PDImageXObject) {
                String name = objectName.getName();
                Matrix ctmNew = getGraphicsState().getCurrentTransformationMatrix();
                PDImageXObject image = (PDImageXObject) xobject;
                this.imageIndex++;
                String imgName = "Im" + (getMd5MapIndex(image) + 1);
                double width = image.getWidth();
                double height = image.getHeight();
                double displayWidth = ctmNew.getScalingFactorX();
                double displayHeight = ctmNew.getScalingFactorY();
                double x = ctmNew.getTranslateX();
                double y = ctmNew.getTranslateY();
                String suffix = image.getSuffix();
                ImageObject imageObject = new ImageObject(imageIndex, imgName, this.pageIndex, width, height,
                        displayWidth, displayHeight, x, y, suffix);
                this.imageObjectsOfPage.add(imageObject);
            } else if (xobject instanceof PDFormXObject) {
                PDFormXObject form = (PDFormXObject) xobject;
                showForm(form);
            }
        } else {
            super.processOperator(operator, operands);
        }
    }

    private int getMd5MapIndex(PDImageXObject image) {
        String md5 = "";
        try {
            md5 = Utils.getMd5(image);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (this.md5Map.indexOf(md5) == -1)
            this.md5Map.add(md5);

        return this.md5Map.indexOf(md5);
    }

    public PDDocument getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(PDDocument pdfFile) {
        this.pdfFile = pdfFile;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int paegIndex) {
        this.pageIndex = paegIndex;
    }

    public List<ImageObject> getImageObjectsOfPage() {
        return imageObjectsOfPage;
    }

    public void setImageObjectsOfPage(List<ImageObject> imageObjectsOfPage) {
        this.imageObjectsOfPage = imageObjectsOfPage;
    }
}
