import java.sql.*;

public class ejercicioModificar {

    public static void main(String[] args) {

        try {
            //Abrir la conexion con la Base de Datos
            System.out.println("Conectando con la BD");

            Connection con = DriverManager.getConnection("jdbc:postgresql://172.18.0.2:5432/pedidos", "postgres", "bitnami");
            String update = "UPDATE cliente SET nombre = ? WHERE codigo = ?";

            PreparedStatement ps = null;

            ps = con.prepareStatement(update);
            ps.setString(1,"Wenceslao");
            ps.setInt(2,4); //cambiará a Estanislao el nombre

            ps.executeUpdate();

            System.out.println("Conexion terminada");
            con.close();

        } catch (SQLException e){

            e.printStackTrace();
        }
    }
}
