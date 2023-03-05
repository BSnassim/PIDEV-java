package services;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import model.Produit;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.lang.Process;


public class PDFGenerator {
    
            Produit p = new Produit();

    public void GeneratePdf(String filename, Produit p) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        document.add(new Paragraph("Nom de Produit :"+p.getLibelle()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("type de votre Produit :" + p.getType()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Taille de votre Produit :" + p.getTaille()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Description de produit:" + p.getDescr()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Votre produit :" + p.getImage()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Prix :" + p.getPrix()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Quantite :" + p.getQte()));
        document.add(new Paragraph("                      "));
        

       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                              Welcome to SOUND'ON Market                     "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
       
// ProduitController ps = new ProduitController();
// public static ProduitController ps = new ProduitController();
///*
//    
//    public static void generatePDF() throws SQLException, IOException, DocumentException {
//    // establish database connection
//    /*String url = "jdbc:mysql://localhost:3306/sound'on";
//    String user = "root";
//    String password = "";
//    Connection conn = DriverManager.getConnection(url, user, password);
//
//    // prepare SQL statement
//    String sql = "SELECT libelle, taille, prix FROM `produit`";
//    PreparedStatement stmt = conn.prepareStatement(sql);
//
//    // execute query and fetch result set
//    ResultSet rs = stmt.executeQuery();
//
//    // create new PDF document
//    PDDocument document = new PDDocument();
//    PDPage page = new PDPage();
//    document.addPage(page);
//
//    // add text to the PDF
//    PDPageContentStream contentStream = new PDPageContentStream(document, page);
//    contentStream.beginText();
//    contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
//    contentStream.newLineAtOffset(100, 700);
//    contentStream.showText("My Database Records:");
//    contentStream.newLine();
//    contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
//    while (rs.next()) {
//        contentStream.showText("libelle: " + rs.getString("libelle"));
//        contentStream.newLine();
//        contentStream.showText("taille: " + rs.getString("taille"));
//        contentStream.newLine();
//        contentStream.showText("prix: " + rs.getFloat("prix"));
//        contentStream.newLine();
//    }
//    contentStream.endText();
//    contentStream.close(); // close content stream
//
//    // save PDF document to file
//    File file = new File("../Documents/output.pdf");
//    document.save(file);
//    document.close();
//
//    // close database connection
//    conn.close();
//}*/
//    
//          /*  String file_name = "../Documents/pdroduit.pdf";
//
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(file_name));
//            document.open();
//         String message = null;
//            Paragraph para = new Paragraph(message);
//            document.add(para);
//
//         
//
//           
//            PdfPTable table = new PdfPTable(5);
//            table.setWidthPercentage(60);
//            table.setSpacingBefore(11f);
//
//            Font f = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
//
//            PdfPCell c2 = new PdfPCell(new Paragraph("id"));
//            table.addCell(c2);
//            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
//            
//            PdfPCell c11 = new PdfPCell(new Phrase("libelle"));
//            table.addCell(c11);
//            c11.setHorizontalAlignment(Element.ALIGN_CENTER);
//            PdfPCell c12 = new PdfPCell(new Phrase("prix"));
//            table.addCell(c12);
//            c12.setHorizontalAlignment(Element.ALIGN_CENTER);
//            PdfPCell cl3 = new PdfPCell(new Phrase("type"));
//            table.addCell(cl3);
//            cl3.setHorizontalAlignment(Element.ALIGN_CENTER);
//            PdfPCell cl4 = new PdfPCell(new Phrase("qte"));
//            table.addCell(cl4);
//            cl4.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//            table.setHeaderRows(1);
//            document.add(table);
//
//            int i = 0;
//            for (i = 0; i < ps.afficherProduit().size(); i++) {
//                table.addCell("" + ps.afficherProduit().get(i).getId());
//                table.addCell("" + ps.afficherProduit().get(i).getLibelle());
//                table.addCell("" + ps.afficherProduit().get(i).getTaille());
//                table.addCell("" + ps.afficherProduit().get(i).getQte());
//                table.addCell("" + ps.afficherProduit().get(i).getPrix());
//
//            }
//            document.add(table);
//
//            document.close();
//
//          
//    }*/
}