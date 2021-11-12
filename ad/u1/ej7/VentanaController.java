package com.dam2.accesodatos;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaController {

    private VentanaModel model;
    private Ventana view;
    private int posicion;

    private ArrayList<Animal> listaAnimales;

    public VentanaController(VentanaModel model, Ventana view) {

        this.model = model;
        this.view = view;
        posicion = 0;
        inicializarEventos();
    }

    private void inicializarEventos() {

        JButton actionButton = view.getUltimoButton();
        actionButton.addActionListener(e -> navegarUltimo());

        actionButton = view.getPrimeroButton();
        actionButton.addActionListener(e -> navegarPrimero());

        actionButton = view.getSiguienteButton();
        actionButton.addActionListener(e -> navegarSiguiente());

        actionButton = view.getAnteriorButton();
        actionButton.addActionListener(e -> navegarAnterior());

        actionButton = view.getGuardarButton();
        actionButton.addActionListener(e -> guardar());

        actionButton = view.getModificarButton();
        actionButton.addActionListener(e -> modificar());

        actionButton = view.getNuevoButton();
        actionButton.addActionListener(e -> nuevo());

        actionButton = view.getEliminarButton();
        actionButton.addActionListener(e -> eliminar());
    }

    // Carga todos los animales de la base de datos en el ArrayList.
	public void cargarTodos() {

        listaAnimales = model.getLista();
	}

    public void cargarVista (int posicion) {

        cargarTodos();
        String nombre;
        String raza;
        String caracteristica;
        String peso;

        if (listaAnimales.size() < 1) {
            nombre = "";
            raza = "";
            caracteristica = "";
            peso = "";
            view.setNombre(nombre);
            view.setRaza(raza);
            view.setCaracteristica(caracteristica);
            view.setPeso(Float.parseFloat(peso));
        
        } else {

            if (posicion > -1 && posicion < listaAnimales.size()) {

                this.posicion = posicion;
                nombre = (listaAnimales.get(posicion).getNombre());
                raza = (listaAnimales.get(posicion).getRaza());
                caracteristica = (listaAnimales.get(posicion).getCaracteristica());
                peso = Float.toString(listaAnimales.get(posicion).getPeso());
                view.setNombre(nombre);
                view.setRaza(raza);
                view.setCaracteristica(caracteristica);
                view.setPeso(Float.parseFloat(peso));
            }
        }
    }

    	// Eventos para los botones de la vista.
	public void navegarSiguiente() {
        view.setModificar(true);
        posicion++;
		cargarVista(posicion);
        model.setPosicion(posicion);
        view.setTfEditable(false);
	}

	public void navegarAnterior() {
        view.setModificar(true);
        posicion--;
		cargarVista(posicion);
        model.setPosicion(posicion);
        view.setTfEditable(false);
	}

	public void navegarPrimero() {
        view.setModificar(true);
        posicion = 0;
		cargarVista(posicion);
        view.setTfEditable(false);
	}

	public void navegarUltimo() {
        view.setModificar(true);
        posicion = listaAnimales.size() -1;
		cargarVista(posicion);
        model.setPosicion(posicion);
        view.setTfEditable(false);
	}

    public void guardar() {

        if (view.isEditable()) {
            modificar();
            cargarVista(posicion);
            view.setTfEditable(false);
            return;
        }
        Animal animal_guardar = new Animal();
        animal_guardar.setNombre(view.getNombre());
        animal_guardar.setRaza(view.getRaza());
        animal_guardar.setCaracteristica(view.getCaracteristica());
        animal_guardar.setPeso(view.getPeso());
        model.guardar(animal_guardar);
        cargarVista(posicion-1);
        view.setTfEditable(false);
        view.setGuardar(false);
	}

    public void modificar() {
        view.setModificar(false);
        view.setTfEditable(true);
        Animal pre_mod = model.getActual();

        pre_mod.setNombre(view.getNombre());
        pre_mod.setRaza(view.getRaza());
        pre_mod.setCaracteristica(view.getCaracteristica());
        pre_mod.setPeso(view.getPeso());
        
        model.modificar(pre_mod);

        cargarVista(posicion);        
	}

    public void nuevo() {
        view.setTfEditable(true);

        view.setNombre("");
        view.setRaza("");
        view.setCaracteristica("");
        view.setPeso(0.0f);
	}

    public void eliminar() {
        model.eliminar();
        
        view.setNombre("");
        view.setRaza("");
        view.setCaracteristica("");
        view.setPeso(0.0f);
	}
}
