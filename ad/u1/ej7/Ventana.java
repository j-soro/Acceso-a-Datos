package com.dam2.accesodatos;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class Ventana {
    private boolean editable = false;
    private JFrame mainFrame;
    private JPanel jpMain;
    private JPanel jpNombre;
    private JPanel jpRaza;
    private JPanel jpCaracteristicas;
    private JPanel jpPeso;
    private JPanel jpBotonesNav;
    private JPanel jpBotonesAccion;


    private JLabel lbNombre;
    private JLabel lbRaza;
    private JLabel lbCaracteristicas;
    private JLabel lbPeso;

    private JTextField tfNombre;
    private JTextField tfRaza;
    private JTextField tfCaracteristica;
    private JTextField tfPeso;

    private JButton btGuardar;
    private JButton btNuevo;
    private JButton btModificar;
    private JButton btEliminar;

    private JButton btAnterior;
    private JButton btSiguiente;
    private JButton btPrimero;
    private JButton btUltimo;

    //JTextField tfBusqueda;
    //private JButton btBuscar;

    public Ventana() {
        inicializar();
    }

    private void inicializar() {

        Dimension dText = new Dimension(300, 30);
        Dimension djpMain = new Dimension(420, 280);
        Dimension dBtnSize = new Dimension (95, 30);

        FlowLayout lyFields = new FlowLayout(FlowLayout.RIGHT);

        mainFrame = new JFrame();
        mainFrame.setSize(djpMain);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        jpMain = new JPanel();
        BoxLayout lyMain = new BoxLayout(jpMain, BoxLayout.Y_AXIS);
        jpMain.setLayout(lyMain);
        jpMain.setSize(djpMain);
        
        // Nombre
        jpNombre = new JPanel();
        jpNombre.setLayout(lyFields);

        lbNombre = new JLabel("Nombre");
        tfNombre = new JTextField();

        jpNombre.add(lbNombre); jpNombre.add(tfNombre);       
        
        // Raza
        jpRaza = new JPanel();
        jpRaza.setLayout(lyFields);

        lbRaza = new JLabel("Raza");
        tfRaza = new JTextField();

        jpRaza.add(lbRaza); jpRaza.add(tfRaza);

        // Características
        jpCaracteristicas = new JPanel();
        jpCaracteristicas.setLayout(lyFields);

        lbCaracteristicas = new JLabel("Caracteristicas");
        tfCaracteristica = new JTextField();

        jpCaracteristicas.add(lbCaracteristicas); jpCaracteristicas.add(tfCaracteristica);

        // Peso
        jpPeso = new JPanel();
        jpPeso.setLayout(lyFields);

        lbPeso = new JLabel("Peso");
        tfPeso =  new JTextField();

        jpPeso.add(lbPeso); jpPeso.add(tfPeso);

        tfNombre.setEditable(false); tfNombre.setPreferredSize(dText); 
        tfRaza.setEditable(false); tfRaza.setPreferredSize(dText); 
        tfCaracteristica.setEditable(false); tfCaracteristica.setPreferredSize(dText); 
        tfPeso.setEditable(false); tfPeso.setPreferredSize(dText);

        //Botones (Navegación)
        jpBotonesNav = new JPanel();

        btPrimero = new JButton("|<");
        btAnterior = new JButton("<");
        btSiguiente = new JButton(">");
        btUltimo = new JButton(">|");

        jpBotonesNav.add(btPrimero); btPrimero.setPreferredSize(dBtnSize);
        jpBotonesNav.add(btAnterior); btAnterior.setPreferredSize(dBtnSize);
        jpBotonesNav.add(btSiguiente); btSiguiente.setPreferredSize(dBtnSize);
        jpBotonesNav.add(btUltimo); btUltimo.setPreferredSize(dBtnSize);

        //Botones (Acciones)
        jpBotonesAccion = new JPanel();
        
        btGuardar = new JButton("Guardar");
        btModificar = new JButton("Modificar");
        btNuevo = new JButton("Nuevo");
        btEliminar = new JButton("Eliminar");

        jpBotonesAccion.add(btGuardar); btGuardar.setPreferredSize(dBtnSize);
        jpBotonesAccion.add(btModificar); btModificar.setPreferredSize(dBtnSize);
        jpBotonesAccion.add(btNuevo); btNuevo.setPreferredSize(dBtnSize);
        jpBotonesAccion.add(btEliminar); btEliminar.setPreferredSize(dBtnSize);

        // Construcción
        jpMain.add(jpNombre);
        jpMain.add(jpRaza);
        jpMain.add(jpCaracteristicas);
        jpMain.add(jpPeso);
        jpMain.add(jpBotonesNav);
        jpMain.add(jpBotonesAccion);

        mainFrame.add(jpMain);
        mainFrame.setVisible(true);
    }

    public void setModificar(boolean b) {

        this.btModificar.setEnabled(b);

    }

    public void setGuardar(boolean b) {

        this.btGuardar.setEnabled(b);

    }

    public void setTfEditable(boolean b) {

        tfNombre.setEditable(b);
        tfRaza.setEditable(b);
        tfCaracteristica.setEditable(b);
        tfPeso.setEditable(b);
        this.editable = b;
    }


    public boolean isEditable() {
        return this.editable;
    }

    public void setNombre(String n) {

        tfNombre.setText(n);
    }

    public String getNombre() {

        return tfNombre.getText();
    }

    public void setRaza(String s) {

        tfRaza.setText(s);
    }

    public String getRaza() {

        return tfRaza.getText();
    }

    public void setCaracteristica(String s) {

        tfCaracteristica.setText(s);
    }

    public String getCaracteristica() {

        return tfCaracteristica.getText();
    }

    public void setPeso(float f) {

        tfPeso.setText(String.valueOf(f));
    }

    public float getPeso() {

        return Float.parseFloat(tfPeso.getText());
    }

    public JButton getPrimeroButton() {
        return btPrimero;
    }

    public JButton getAnteriorButton() {
        return btAnterior;
    }

    public JButton getSiguienteButton() {
        return btSiguiente;
    }

    public JButton getUltimoButton() {
        return btUltimo;
    }

    public JButton getGuardarButton() {
        return btGuardar;
    }

    public JButton getModificarButton() {
        return btModificar;
    }

    public JButton getNuevoButton() {
        return btNuevo;
    }

    public JButton getEliminarButton() {
        return btEliminar;
    }

}
