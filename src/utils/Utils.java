package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
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
            if (info.getTitle() != null)
                metadataObject.setTitle(info.getTitle());
            if (info.getAuthor() != null)
                metadataObject.setAuthor(info.getAuthor());
            if (info.getSubject() != null)
                metadataObject.addToSubjects(info.getSubject());
            if (info.getKeywords() != null)
                metadataObject.setKeywords(info.getKeywords());
            if (info.getCreator() != null)
                metadataObject.addToCreators(info.getCreator());
            if (info.getProducer() != null)
                metadataObject.setProducer(info.getProducer());
            if (info.getCreationDate() != null)
                metadataObject.setCreateDate(dateFormat.format(info.getCreationDate().getTime()));
            if (info.getModificationDate() != null)
                metadataObject.setModifyDate(dateFormat.format(info.getModificationDate().getTime()));
            if (info.getTrapped() != null)
                metadataObject.setTrapped(info.getTrapped());
        }

        if (meta != null) {
            DomXmpParser xmpParser = new DomXmpParser();
            XMPMetadata metadata = xmpParser.parse(meta.toByteArray());
            XMPBasicSchema basic = metadata.getXMPBasicSchema();
            AdobePDFSchema pdf = metadata.getAdobePDFSchema();
            DublinCoreSchema dc = metadata.getDublinCoreSchema();

            if (basic != null) {
                if (basic.getCreateDate() != null)
                    metadataObject.setCreateDate(dateFormat.format(basic.getCreateDate().getTime()));
                if (basic.getModifyDate() != null)
                    metadataObject.setModifyDate(dateFormat.format(basic.getModifyDate().getTime()));
                if (basic.getCreatorTool() != null)
                    metadataObject.setCreatorTool(basic.getCreatorTool());
            }

            if (pdf != null) {
                if (pdf.getKeywords() != null)
                    metadataObject.setKeywords(pdf.getKeywords());
                if (pdf.getPDFVersion() != null)
                    metadataObject.setVersion(pdf.getPDFVersion());
                if (pdf.getProducer() != null)
                    metadataObject.setProducer(pdf.getProducer());
            }

            if (dc != null) {
                if (dc.getTitle() != null)
                    metadataObject.setTitle(dc.getTitle());
                if (dc.getDescription() != null)
                    metadataObject.setDescription(dc.getDescription());
                if (dc.getCreators() != null)
                    metadataObject.setCreators(dc.getCreators());
                if (dc.getDates() != null)
                    metadataObject.setDates(dc.getDates());
                if (dc.getSubjects() != null)
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
        for (int i = 0; i < length; i++) {
            char character = str.charAt(i);
            if (!isHalfWidth(character))
                count++;
        }
        return count;
    }

    public static boolean isHalfWidth(char c) {
        return '\u0000' <= c && c <= '\u00FF'
                || '\uFF61' <= c && c <= '\uFFDC'
                || '\uFFE8' <= c && c <= '\uFFEE';
    }

    public static String getHex(byte[] bytes) throws Exception {
        String hexString = "";
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            hexString += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
        }
        return hexString;
    }

    public static String getMd5(PDImageXObject image) throws Exception {
        BufferedImage buffImg = image.getImage();
        String suffix = image.getSuffix();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (!ImageIO.write(buffImg, suffix, outputStream)) {
            BufferedImage newBufferedImage = new BufferedImage(buffImg.getWidth(), buffImg.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(buffImg, 0, 0, Color.WHITE, null);
            ImageIO.write(newBufferedImage, suffix, outputStream);
        }
        byte[] data = outputStream.toByteArray();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data);
        byte[] hash = md.digest();

        String md5 = getHex(hash);
        return md5;
    }
}