package csv

import DBExporter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.File
import java.io.FileWriter

class CSVExporter() : DBExporter() {

    override val fileName = "report.csv"

    override val tip = "CSV"

    override fun save(data: List<List<String>>) {
        try {
            val writer = FileWriter(File(fileName))
            val csvPrinter = CSVPrinter(writer, CSVFormat.DEFAULT)
            for (row in data) {
                csvPrinter.printRecord(row)
            }
            writer.close()
            csvPrinter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        

    }

    override fun save(data: List<List<String>>, header: List<String>) {
        try {
            val writer = FileWriter(File(fileName))
            val csvPrinter = CSVFormat.DEFAULT.builder()
                    .setHeader(*header.toTypedArray()).build().print(writer)
            for (row in data) {
                csvPrinter.printRecord(row)
            }
            writer.close()
            csvPrinter.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

}