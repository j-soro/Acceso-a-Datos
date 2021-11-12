package com.dam2.accesodatos;
import java.util.ArrayList;

import com.dam2.accesodatos.Animal;

public class VentanaModel {

    private ArrayList<Animal> listaAnimales;
    private int posicion;

    public VentanaModel() {

        this.listaAnimales = retrieveAnimalesFromDatabase();
        posicion = 0;

    }

    public ArrayList<Animal> retrieveAnimalesFromDatabase() {

        ArrayList<Animal> animales = new ArrayList<>();

        Animal pollo = new Animal();
        pollo.setNombre("Pollo");
        pollo.setCaracteristica("Corral");
        pollo.setPeso(1.5f);
        pollo.setRaza("Español");
        animales.add(pollo);

        Animal perro = new Animal();
        perro.setNombre("Perro");
        perro.setCaracteristica("Mascota");
        perro.setPeso(12.0f);
        perro.setRaza("Labrador");
        animales.add(perro);

        Animal vaca = new Animal();
        vaca.setNombre("Vaca");
        vaca.setCaracteristica("Lechera");
        vaca.setPeso(200.0f);
        vaca.setRaza("Gorda");
        animales.add(vaca);

        Animal hamster = new Animal();
        hamster.setNombre("Hamster");
        hamster.setCaracteristica("Rabioso");
        hamster.setPeso(0.2f);
        hamster.setRaza("Ruso");
        animales.add(hamster);

        Animal caballo = new Animal();
        caballo.setNombre("Caballo");
        caballo.setCaracteristica("Carreras");
        caballo.setPeso(220.0f);
        caballo.setRaza("Albardón");
        animales.add(caballo);

        return animales;
    }

    public void guardar(Animal animal) {

        listaAnimales.add(animal);
        posicion++;
    }

    public void modificar(Animal animalModificado) {

        Animal animal = listaAnimales.get(posicion);
        animal.setNombre(animalModificado.getNombre());
        animal.setCaracteristica(animalModificado.getCaracteristica());
        animal.setRaza(animalModificado.getRaza());
        animal.setPeso(animalModificado.getPeso());
    }

    public void setPosicion(int i) {

        posicion = i;
    }

    public void eliminar() {

        listaAnimales.remove(posicion);
    }

    public Animal getActual() {

        return listaAnimales.get(posicion);
    }

    public ArrayList<Animal> getLista() {

        return listaAnimales;
    }

    public Animal buscar(String nombre) {
        for (Animal animal : listaAnimales) {
            if (animal.getNombre().equals(nombre)) {
                return animal;
            }
        }

        return null;
    }

    public Animal getPrimero() {

        posicion = 0;
        return listaAnimales.get(posicion);
    }

    public Animal getAnterior() {

        if (posicion == 0)
            return null;

        posicion--;
        return listaAnimales.get(posicion);
    }

    public Animal getSiguiente() {

        if (posicion == listaAnimales.size() -1)
            return null;

        posicion++;
        return listaAnimales.get(posicion);
    }

    public Animal getUltimo() {

        posicion = listaAnimales.size() -1;
        return listaAnimales.get(posicion);
    }
}
