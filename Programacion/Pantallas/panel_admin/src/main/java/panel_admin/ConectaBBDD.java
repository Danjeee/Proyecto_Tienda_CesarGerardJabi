
package com.example.basedatosempresafx;

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

    public boolean irFinal() {
        boolean ok = false;
        try {
            ok = rs.last();
        } catch (SQLException ex) {
            System.out.println("\nERROR: No se ha podido ir al último");
        }
        return ok;
    }

    public boolean irSiguiente() {
        boolean ok = false;
        try {
            ok = rs.next();
        } catch (SQLException ex) {
            System.out.println("\nERROR: No se ha podido ir al siguiente");
        }
        return ok;
    }

    public boolean irAnterior() {
        boolean ok = false;
        try {
            ok = rs.previous();
        } catch (SQLException ex) {
            System.out.println("\nERROR: No se ha podido ir al anterior");
        }
        return ok;
    }

    public boolean irPrimero() {
        boolean ok = false;
        try {
            ok = rs.first();
        } catch (SQLException ex) {
            System.out.println("\nERROR: No se ha podido ir al primero");
            ex.printStackTrace();
        }
        return ok;
    }

    public int tamanyo() throws SQLException {
        int tam = 0, origen = 0;
        origen = rs.getRow();
        rs.last();
        tam = rs.getRow();
        rs.absolute(origen);
        return tam;
    }

    public Articulo devRegistroActual() {
        Articulo art = null;
        try {
            art = new Articulo(String.valueOf(rs.getInt("id")), rs.getString("nombre"),
                     rs.getFloat("precio"), rs.getString("codigo"), rs.getInt("grupo"));
        } catch (SQLException e) {
            System.out.println("\nERROR: No se ha podido obtener el Artículo");
        }
        return (art);
    }

    public int grabaRegistro(Articulo art) throws SQLException {
        int ok = -1;
        String sql = "INSERT INTO articulos(nombre,precio,codigo,grupo) VALUES "
                + "('" + art.getNombre() + "'," + art.getPrecio() + ",'" + art.getCodigo() + "'," + art.getGrupo() + ")";
        ok = this.updateSQL(sql);

        return (ok);
    }

    public int modiRegistro(Articulo art) throws SQLException {
        int ok = -1;

        String sql = "UPDATE articulos SET nombre = '" + art.getNombre() + "', precio =" + art.getPrecio()
                + ", codigo ='" + art.getCodigo() + "', grupo = " + art.getGrupo() + " WHERE id = " + art.getId();

        ok = this.updateSQL(sql);
        return (ok);
    }

    public boolean isPrimero() throws SQLException {
        return rs.isFirst();
    }

    public boolean isUltimo() throws SQLException {
        return rs.isLast();
    }
    
    public int posActual() throws SQLException{
        return rs.getRow();
    }

}
