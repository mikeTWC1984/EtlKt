
package sql

import com.microsoft.sqlserver.jdbc.*;
import java.sql.Connection
import java.sql.DriverManager
import java.util.*


class MSSqlClient (val url: String, private val cred: Properties) {

    private val conn = SQLServerDriver().connect(url, cred)

}



