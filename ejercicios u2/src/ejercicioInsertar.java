import java.sql.*;

public class ejercicioInsertar {
    
    public static void main(String[] args) {

        insertar_articulo("TEC00001", "Teclado mecánico de aluminio sin teclado numérico", 54.99f, 0.0f, 45, 10);
        
    }

    static void insertar_articulo(String cod, String desc, float precio, float dto, int stock, int stock_min)
    {
        try {

            //Establecer una conexión con la base de datos
            System.out.println("Conectando con la BD");
            Connection con = DriverManager.getConnection("jdbc:postgresql://172.18.0.2:5432/comercio", "postgres", "bitnami");
            String update = "INSERT INTO articulo (codigo, descripcion, precio, dto, stock, stock_min) VALUES (?,?,?,?,?,?)";
            
            //Crear un PreparedStatement para realizar operaciones de manera segura y evitar posible inyección SQL
            PreparedStatement ps = con.prepareStatement(update);
            
            ps.setString(1,cod);
            ps.setString(2,desc);
            ps.setFloat(3, precio);
            ps.setFloat(4, dto);
            ps.setInt(5, stock);
            ps.setInt(6, stock_min);
            
            ps.executeUpdate();

            System.out.println("Conexion hecha");
            con.close();

        } catch (SQLException e) { e.printStackTrace(); }

    }
}
