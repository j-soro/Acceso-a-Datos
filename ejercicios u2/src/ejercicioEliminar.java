import java.sql.*;

public class ejercicioEliminar {

    public static void main(String[] args) {

        try {
            //Abrir la conexion con la Base de Datos
            System.out.println("Conectando con la BD");
            Connection con = DriverManager.getConnection("jdbc:postgresql://172.19.0.2:5432/pedidos", "postgres", "bitnami");
            String sql = "DELETE FROM ticket WHERE codigo = ?"; //eliminaremos el ticket con fecha 2020-12-18

            PreparedStatement ps = null;

            ps = con.prepareStatement(sql);
            ps.setInt(1,23);
            ps.executeUpdate(); //DDL executeUpdate DML executeQuery

            System.out.println("Conexion terminada");
            con.close();

        } catch (SQLException e){

            e.printStackTrace();
        }

    }
}
