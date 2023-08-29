import java.io.File;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

import object_stripper.TextStripper;
import pdf_document.DocumentObject;
import pdf_document.MetadataObject;
import pdf_document.PageObject;
import utils.Utils;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // 輸出路徑與頁範圍選項參數
            Option optionOutput = new Option("output", true, "output the json file to path");
            Option optionPageRange = new Option("range", true, "parse pdf with page range");
            Options opts = new Options();
            opts.addOption(optionOutput);
            opts.addOption(optionPageRange);

            CommandLineParser cmdParser = new DefaultParser();
            CommandLine cmd = cmdParser.parse(opts, args);

            String outputPath = cmd.getOptionValue("output");
            Boolean hasOutputPath = outputPath == null ? true : false;

            // Pdf file loading
            File input = new File(args[0]);
            PDDocument pdfFile = PDDocument.load(input);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfFile);
            DocumentObject doc = new DocumentObject();
            doc.setName(input.getName());
            int pageNum = pdfFile.getNumberOfPages();
            doc.setNumberOfPages(pageNum);
            MetadataObject metadataObj = Utils.createMetadataObject(
                    pdfFile.getDocumentInformation(),
                    pdfFile.getDocumentCatalog().getMetadata());
            doc.setMetadata(metadataObj);

            // set page range
            String pageRange = cmd.getOptionValue("range");
            ArrayList<Integer> pageRangeArr = Utils.getPageRange(pageRange, pageNum);
            TextStripper textStripper = new TextStripper(pdfFile);

            //parse page
            for(int i : pageRangeArr) {
                PDPage page = pdfFile.getPage(i);
                PageObject pageObject = new PageObject(i + 1);

                pageObject.setWidth(page.getMediaBox().getWidth());
                pageObject.setHeight(page.getMediaBox().getHeight());

                textStripper.processPage(i);
                pageObject.setTextObjects(textStripper.getTextObjectsListOfPage());
                //TODO stripe image
            }

            //TODO out json file
        } catch (Exception e) {
        }
    }
}
