package main

import DBExporter
import java.sql.DriverManager
import java.util.ServiceLoader


fun main() {
    val jdbcUrl = "jdbc:mysql://localhost:3306/skdemo?autoReconnect=true&useSSL=false";
    val conn = DriverManager
        .getConnection(jdbcUrl, "root", "Root.2021")
    println(conn.isValid(0))

    val stmt = conn.createStatement()


    // svi studenti sa podatkom o studijskom programu
    val rs = stmt.executeQuery(
        "SELECT ime, prezime, godinaUpisa, skraceniNaziv  FROM student s, studProgram sp "
                + "where s.idstudprogram = sp.idstudProgram"
    )



    val serviceLoader = ServiceLoader.load(DBExporter::class.java)


    val exporterServices = mutableMapOf<String,DBExporter> ()

    serviceLoader.forEach{
            service -> exporterServices.put(service.tip, service)
    }

    println(exporterServices.keys)

    //exporterServices.get("PDF")!!.save(rs,true)
}