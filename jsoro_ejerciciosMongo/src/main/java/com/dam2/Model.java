package com.dam2;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;

// Esta clase contiene el acceso a la BBDD
public class Model {
    // Instancia de la conexión a MongoDB
    final private MongoDatabase mgDb;
    static MongoCollection<User> users = null;

    public Model() {
        this.mgDb = Connection.getInstance();
        updateUserCollection();
    }

    private void updateUserCollection() {
        users = mgDb.getCollection("users", User.class);
    }

    //CRUD para usuarios
    // Crear un nuevo usuario
    public void createUser(User user) {

        User newUser = new User().setUsername(user.getUsername()).
                setPassword(user.getPassword()).setFirstname(user.
                        getFirstname()).setLastname(user.getLastname());

        users.insertOne(newUser);
        //updateUserCollection();
    }

    // Buscar usuarios por username
    public static User findUserByUserName(String username){
        return users.find(eq("username", username)).first();
    }

    // Buscar usuarios por nombre
    public static User findUserByFirstName(String str){
        return users.find(eq("firstname", str)).first();
    }

    // Buscar usuario por apellido
    public static User findUserByLastName(String str){
        User user = users.find(eq("lastname", str)).first();
        return user;
    }

    // Actualizar usuario con username equivalente al parámetro
    public static void updateUser(User user){

        users.updateOne(eq("username", user.getUsername()),
                combine(set("username", user.getUsername()),
                        set("firstname", user.getFirstname()),
                        set("lastname", user.getLastname())
                )
        );
    }

    // Eliminar usuario
    public static void deleteUser(String username)
    {
        users.deleteOne(eq("username", username));
    }

    public static void deleteAllUsers(){
        DeleteResult deleteResult = users.deleteMany(new Document());
        System.out.println(deleteResult.getDeletedCount());
    }

    public static boolean userLogin(String username, String password)
    {
        try{
            User user = findUserByUserName(username);
            if( user.getPassword().equals(password))
            {
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    public void getAll() {

        for (User user : users.find()) System.out.println(user);
    }

    // Generar 7 usuarios de prueba
    public void generateUsers(){

        List<User> usersList = asList(
            new User().setUsername("jsoro").setPassword("1234").setFirstname("Joaquín").setLastname("Soro"),
            new User().setUsername("umi5").setPassword("1234").setFirstname("Manuel").setLastname("Busquier"),
            new User().setUsername("jrodri").setPassword("1234").setFirstname("José").setLastname("Rodríguez"),
            new User().setUsername("blas7").setPassword("1234").setFirstname("Blas").setLastname("Diez"),
            new User().setUsername("fernan23").setPassword("1234").setFirstname("Fernando").setLastname("Paños"),
            new User().setUsername("therefti").setPassword("1234").setFirstname("Emilio").setLastname("Senabre"),
            new User().setUsername("thecharles").setPassword("1234").setFirstname("Carlos").setLastname("Martínez")
        );

        users.insertMany(usersList);









    }
}
