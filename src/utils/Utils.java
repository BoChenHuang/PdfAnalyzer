package utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.xml.DomXmpParser;

import pdf_document.MetadataObject;

public class Utils {
    public static MetadataObject createMetadataObject(PDDocumentInformation info, PDMetadata meta) throws Exception {
        MetadataObject metadataObject = new MetadataObject();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (info != null) {
            metadataObject.setTitle(info.getTitle());
            metadataObject.setAuthor(info.getAuthor());
            metadataObject.addToSubjects(info.getSubject());
            metadataObject.setKeywords(info.getKeywords());
            metadataObject.addToCreators(info.getCreator());
            metadataObject.setProducer(info.getProducer());
            metadataObject.setCreateDate(dateFormat.format(info.getCreationDate().getTime()));
            metadataObject.setModifyDate(dateFormat.format(info.getModificationDate().getTime()));
            metadataObject.setTrapped(info.getTrapped());
        }

        if (meta != null) {
            DomXmpParser xmpParser = new DomXmpParser();
            XMPMetadata metadata = xmpParser.parse(meta.toByteArray());
            XMPBasicSchema basic = metadata.getXMPBasicSchema();
            AdobePDFSchema pdf = metadata.getAdobePDFSchema();
            DublinCoreSchema dc = metadata.getDublinCoreSchema();

            if (basic != null) {
                metadataObject.setCreateDate(dateFormat.format(basic.getCreateDate().getTime()));
                metadataObject.setModifyDate(dateFormat.format(basic.getModifyDate().getTime()));
                metadataObject.setCreatorTool(basic.getCreatorTool());
            }

            if (pdf != null) {
                metadataObject.setKeywords(pdf.getKeywords());
                metadataObject.setVersion(pdf.getPDFVersion());
                metadataObject.setProducer(pdf.getProducer());
            }

            if (dc != null) {
                metadataObject.setTitle(dc.getTitle());
                metadataObject.setDescription(dc.getDescription());
                metadataObject.setCreators(dc.getCreators());
                metadataObject.setDates(dc.getDates());
                metadataObject.setSubjects(dc.getSubjects());
            }
        }
        return metadataObject;
    }

    public static ArrayList<Integer> getPageRange(String pageRange, int pageNum) {
        ArrayList<Integer> pageRangeArr = new ArrayList<>();
        if (pageRange != null) {
            int start, end;
            for (String item : pageRange.split(",")) {
                start = Integer.parseInt(item.split("-")[0]);
                end = Integer.parseInt(item.split("-")[1]);
                for (int i = start - 1; i < end; i++) {
                    pageRangeArr.add(i);
                }
            }
        } else {
            for (int i = 0; i < pageNum; i++) {
                pageRangeArr.add(i);
            }
        }
        return pageRangeArr;
    }

    public static int getHalfWidthCount(String value) {
        int count = 0;
        String str = (value == null) ? "" : value;
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (isHalfWidth(character))
                count++;
        }
        return count;
    }

    public static int getFullWidthCount(String value) {
        int count = 0;
        String str = (value == null) ? "" : value;
        int length = str.length();
        for(int i = 0; i < length; i++) {
            char character = str.charAt(i);
            if(!isHalfWidth(character))
                count++;
        }
        return count;
    }

    public static boolean isHalfWidth(char c) {
        return '\u0000' <= c && c <= '\u00FF'
                || '\uFF61' <= c && c <= '\uFFDC'
                || '\uFFE8' <= c && c <= '\uFFEE';
    }
}