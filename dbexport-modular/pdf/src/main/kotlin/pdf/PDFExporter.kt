package pdf

import DBExporter
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class PDFExporter() : DBExporter() {

    override val fileName = "report.pdf"

    override val tip = "PDF"

    override fun save(data: List<List<String>>) {
        try {
            val file: OutputStream = FileOutputStream(File(fileName))
            val document: Document = Document()
            PdfWriter.getInstance(document, file)
            document.open()
            for (row in data) {
                document.add(Paragraph(row.joinToString(separator = ",")))
            }
            document.close()
            file.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun save(data: List<List<String>>, header: List<String>) {
        try {
            val file: OutputStream = FileOutputStream(File(fileName))
            val document: Document = Document()
            PdfWriter.getInstance(document, file)
            document.open()
            document.add(Paragraph(header.joinToString(separator = ",")))
            for (row in data) {
                document.add(Paragraph(row.joinToString(separator = ",")))
            }
            document.close()
            file.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}