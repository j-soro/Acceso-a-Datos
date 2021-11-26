import java.sql.*;

public class ejercicioConsultar {

    public static void main(String[] args) {

        try {
            //Abrir la conexion con la Base de Datos
            System.out.println("Conectando con la BD");

            Connection con = DriverManager.getConnection("jdbc:postgresql://172.18.0.2:5432/comercio", "postgres", "bitnami");
            String query = "SELECT * FROM cliente";
            PreparedStatement ps = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("Cliente: "+rs.getString("nombre")+
                " con código " + rs.getString("codigo")+
                " vive en el municipio de "+rs.getString("municipio")+"\n");

            }

            System.out.println("Conexión completada");
            con.close();

        } catch (SQLException e){

            e.printStackTrace();
        }
    }
}
