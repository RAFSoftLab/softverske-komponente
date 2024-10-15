
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement


abstract class DBExporter() {

    abstract val tip:String

    abstract val fileName:String

    abstract fun save(data: List<List<String>>)

    abstract fun save(data: List<List<String>>, header: List<String>)

    @Throws(SQLException::class)
    fun save(rs: ResultSet, useColumnLabelsAsHeaders: Boolean) {
        val all: MutableList<List<String>> = ArrayList()
        val rsmd = rs.metaData
        val columnsNumber = rsmd.columnCount
        while (rs.next()) {
            val row: MutableList<String> = ArrayList()
            for (i in 1..columnsNumber) {
                row.add(rs.getString(i))
            }
            all.add(row)
        }
        if (!useColumnLabelsAsHeaders) save(all)
        else {
            val header: MutableList<String> = ArrayList()
            for (i in 1..columnsNumber) {
                header.add(rsmd.getColumnLabel(i))
            }
            save(all, header)
        }
    }

    @Throws(SQLException::class)
    fun save(rs: ResultSet, header: List<String>) {
        val all: MutableList<List<String>> = ArrayList()
        val rsmd = rs.metaData
        val columnsNumber = rsmd.columnCount
        while (rs.next()) {
            val row: MutableList<String> = ArrayList()
            for (i in 1..columnsNumber) {
                row.add(rs.getString(i))
            }
            all.add(row)
        }
        save(all, header)
    }

    @Throws(SQLException::class)
    fun save(conn: Connection, query: String, useColumnLabelsAsHeaders: Boolean) {
        val stmt: Statement = conn.createStatement()
        val rs: ResultSet = stmt.executeQuery(query)
        save(rs, useColumnLabelsAsHeaders)
    }

    @Throws(SQLException::class)
    fun save(conn: Connection, query: String, header: List<String>) {
        val stmt: Statement = conn.createStatement()
        val rs: ResultSet = stmt.executeQuery(query)
        save(rs, header)
    }

}