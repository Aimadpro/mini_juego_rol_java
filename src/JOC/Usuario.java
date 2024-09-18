package JOC;

import java.sql.*;

public class Usuario {
    private String name;
    private int puntuacion;
    private int vidas;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion, String nombre, int visas) {
        this.puntuacion = puntuacion;
    }
    public void guardarDadesDb(int vidas, int oro){
        String db_urls = "jdbc:mysql://localhost:3306/java_db";
        String user = "root";
        String passwd = "mysql";
        String guardar = "INSERT INTO resultats (nombre,oro,vida) values (?, ?, ?)";

        try {
            Connection connection = DriverManager.getConnection(db_urls,user,passwd);
            PreparedStatement statement = connection.prepareStatement(guardar);
            statement.setString(1,this.name);
            statement.setInt(2,oro);
            statement.setInt(3,vidas);
            int Add = statement.executeUpdate();
            if (Add > 0){
                System.out.println("Se han guardado");
            }
            statement.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se ha conectado: " + e.getMessage());
        }
    }
}
