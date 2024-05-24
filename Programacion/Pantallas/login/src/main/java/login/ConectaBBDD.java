package login;

import java.sql.*;

public class ConectaBBDD {

    private Connection conn = null;
    private Statement sentenciaSQL = null;
    private ResultSet rs = null;

    public ConectaBBDD(){}

    public void conecta() {

        try {// Establece la conexión
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/empresa", "root",
                    "");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void crearSentencia() {
        try {
            // Crear una sentencia para enviar consultas a la base de datos
            sentenciaSQL = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("\nSentencia creada con éxito.");
        } catch (SQLException ex) {
            System.out.println("\nERROR: NO se ha creado el objeto Statement.");
        }

    }

    public void cerrarConexion() {
        // se cerrará la conexión a la BBDD.
        try {
            if (rs != null) {
                rs.close();
            }
            if (sentenciaSQL != null) {
                sentenciaSQL.close();
            }
            if (conn != null) {
                conn.close();
            }
            System.out.println("\nConexión cerrada con éxito.");
        } catch (SQLException ex) {
            System.out.println("\nERROR: NO se ha PODIDO CERRAR la conexión.");
        }

    }

    public void ejecutaSQL(String sql) {
        try {
            // realiza la consulta y devuelve resultados
            rs = sentenciaSQL.executeQuery(sql);
            System.out.println("SQL ejecuta correctamente");
        } catch (SQLException ex) {
            System.out.println("ERROR: No se ha podido ejecutar la SQL: " + sql);
        }
    }

    public int updateSQL(String sql) {
        // actualiza la BBDD
        int upd = -1;
        try {
            upd = sentenciaSQL.executeUpdate(sql);
        } catch (SQLException e) {
            upd = -1;
        }
        return upd;
    }

    public static Connection getConn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConn'");
    }
}
