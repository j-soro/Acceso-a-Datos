package com.jsc_ad.ejercicio_u3.view;

import com.jsc_ad.ejercicio_u3.Employee;
import com.jsc_ad.ejercicio_u3.controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame{
    private JPanel mainPanel;
    private JTable empTable;
    private JButton btnNewEmp;
    private JButton btnFindEmp;
    private JButton btnEditEmp;
    private JButton btnDeleteEmp;
    private JLabel empTableHeader;
    private JLabel projTableHeader;
    private JTable projTable;
    private JButton btnNewProj;
    private JButton btnDeleteProj;
    private JButton btnEditProj;
    private JButton btnFindProj;
    private JList listProyectos;


    public MainView(String title){
        super(title);

        createUIComponents();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        btnNewEmp.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            JDialog jdNewEmp = new JDialog(frame);
            jdNewEmp.setTitle("Nuevo empleado");
            jdNewEmp.setResizable(false);

            jdNewEmp.setLayout(new FlowLayout(FlowLayout.LEFT));
            jdNewEmp.setBounds(500, 300, 360, 200);
            jdNewEmp.setLocationRelativeTo(null);

            JLabel lbName = new JLabel("Nombre: ");
            lbName.setPreferredSize( new Dimension(80, 24));

            JTextField txtName = new JTextField();
            txtName.setPreferredSize( new Dimension( 200, 24 ) );

            JLabel lbLastName = new JLabel("Apellidos: ");
            lbLastName.setPreferredSize( new Dimension(80, 24));

            JTextField txtLastName = new JTextField();
            txtLastName.setPreferredSize( new Dimension( 200, 24 ) );

            JLabel lbSalary = new JLabel("Salario :");
            lbSalary.setPreferredSize( new Dimension(80, 24));

            JTextField txtSalary = new JTextField();
            txtSalary.setPreferredSize( new Dimension( 100, 24 ) );

            JButton btnAddProject = new JButton("Añadir proyecto...");
            JButton btnExit = new JButton("Cancelar");
            JButton btnSave = new JButton("Guardar");

            btnAddProject.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //todo add project to employee
                }
            });
            btnExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jdNewEmp.dispose();
                }
            });

            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Employee x = new Employee(  txtName.getText(),
                                txtLastName.getText(),
                                Float.valueOf(txtSalary.getText()));
                        Controller.saveEmployee(x);
                        updateEmployeeTableData();
                        txtName.setText("");
                        txtLastName.setText("");
                        txtSalary.setText("");

                        JOptionPane.showMessageDialog(frame,
                                "El empleado con id "+x.getEmployee_id()+" ha sido CREADO.\n"+x.toString(),
                                "Empleado creado",
                                JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(frame,
                                "Introduce valores válidos",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            JPanel row1 = new JPanel(); row1.setLayout(new FlowLayout(FlowLayout.CENTER));
            row1.add(lbName); row1.add(txtName);

            JPanel row2 = new JPanel(); row2.setLayout(new FlowLayout(FlowLayout.CENTER));
            row2.add(lbLastName); row2.add(txtLastName);

            JPanel row3 = new JPanel(); row3.setLayout(new FlowLayout(FlowLayout.CENTER));
            row3.add(lbSalary); row3.add(txtSalary);

            JPanel row4 = new JPanel(); row4.setLayout(new FlowLayout(FlowLayout.RIGHT));
            row4.add(btnSave); row4.add(btnAddProject); row4.add(btnExit);

            jdNewEmp.add(row1);
            jdNewEmp.add(row2);
            jdNewEmp.add(row3);
            jdNewEmp.add(row4);

            jdNewEmp.setVisible(true);

        });
        btnDeleteEmp.addActionListener(e -> {

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            JDialog jdDeleteEmp = new JDialog(frame);

            try{

                int id = (int) empTable.getValueAt(empTable.getSelectedRow(),0);
                Employee x = Controller.findEmployeeById(id);
                Controller.deleteEmployee(x);

                JOptionPane.showMessageDialog(frame,
                        "El empleado con id "+id+" ha sido eliminado.\n"+x.toString(),
                        "Empleado eliminado",
                        JOptionPane.OK_OPTION);

                updateEmployeeTableData();


            } catch (Exception exception) {
                JOptionPane.showMessageDialog(frame,
                        "",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }

        });
        btnEditEmp.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            JDialog jdNewEmp = new JDialog(frame);
            jdNewEmp.setTitle("Modificar empleado");
            jdNewEmp.setResizable(false);

            jdNewEmp.setLayout(new FlowLayout(FlowLayout.LEFT));
            jdNewEmp.setBounds(500, 300, 360, 200);
            jdNewEmp.setLocationRelativeTo(null);

            int id = (int) empTable.getValueAt(empTable.getSelectedRow(),0);
            Employee x = Controller.findEmployeeById(id);

            JLabel lbName = new JLabel(x.getFirstName()+": > ");
            lbName.setPreferredSize( new Dimension(80, 24));

            JTextField txtName = new JTextField();
            txtName.setPreferredSize( new Dimension( 200, 24 ) );

            JLabel lbLastName = new JLabel(x.getLastName()+": > ");
            lbLastName.setPreferredSize( new Dimension(80, 24));

            JTextField txtLastName = new JTextField();
            txtLastName.setPreferredSize( new Dimension( 200, 24 ) );

            JLabel lbSalary = new JLabel(x.getSalary()+": > ");
            lbSalary.setPreferredSize( new Dimension(80, 24));

            JTextField txtSalary = new JTextField();
            txtSalary.setPreferredSize( new Dimension( 100, 24 ) );

            JButton btnExit = new JButton("Cancelar");
            JButton btnSave = new JButton("Guardar");

            btnExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jdNewEmp.dispose();
                }
            });

            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        x.setFirstName(txtName.getText());
                        x.setLastName(txtLastName.getText());
                        x.setSalary(Float.parseFloat(txtSalary.getText()));

                        Controller.saveEmployee(x);
                        updateEmployeeTableData();
                        txtName.setText("");
                        txtLastName.setText("");
                        txtSalary.setText("");

                        JOptionPane.showMessageDialog(frame,
                                "El empleado con id "+x.getEmployee_id()+" ha sido MODIFICADO.\n"+x.toString(),
                                "Empleado modificado",
                                JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(frame,
                                "Introduce valores válidos",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            JPanel row1 = new JPanel(); row1.setLayout(new FlowLayout(FlowLayout.CENTER));
            row1.add(lbName); row1.add(txtName);

            JPanel row2 = new JPanel(); row2.setLayout(new FlowLayout(FlowLayout.CENTER));
            row2.add(lbLastName); row2.add(txtLastName);

            JPanel row3 = new JPanel(); row3.setLayout(new FlowLayout(FlowLayout.CENTER));
            row3.add(lbSalary); row3.add(txtSalary);

            JPanel row4 = new JPanel(); row4.setLayout(new FlowLayout(FlowLayout.RIGHT));
            row4.add(btnSave); row4.add(btnExit);

            jdNewEmp.add(row1);
            jdNewEmp.add(row2);
            jdNewEmp.add(row3);
            jdNewEmp.add(row4);

            jdNewEmp.setVisible(true);


        });
        btnFindEmp.addActionListener(e -> {

        });
        empTable.addComponentListener(new ComponentAdapter() {
        });
    }

    public static void main(String[] args) {

        JFrame frame = new MainView("Gestor de empleados y proyectos");
        frame.setVisible(true);
    }


    private void createUIComponents() {

        empTableHeader.setFont(new Font("Arial",Font.BOLD,22));
        projTableHeader.setFont(new Font("Arial",Font.BOLD,22));

        initializeTable(empTable, "Lista de empleados");
        initializeTable(projTable, "Lista de proyectos");

        updateEmployeeTableData();
        updateProjectTableData();

        formatTable(empTable);
        formatTable(projTable);

        addMouseListener(empTable);
        addMouseListener(projTable);

    }

    private void initializeTable(JTable table, String tableName) {

        table.setFont(new Font("Arial",Font.PLAIN,14));
        table.setFillsViewportHeight(true);
        table.setName(tableName);
        table.setRowHeight(45);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);

    }

    private void formatTable(JTable table){
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(5);

        DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRendererLeft = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRendererRight = new DefaultTableCellRenderer();

        cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
        cellRendererLeft.setHorizontalAlignment(JLabel.LEFT);
        cellRendererRight.setHorizontalAlignment((JLabel.RIGHT));

        table.setDefaultRenderer(Integer.class, cellRendererCenter);
        table.setDefaultRenderer(String.class, cellRendererCenter);

        table.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);
        table.getColumnModel().getColumn(4).setCellRenderer(cellRendererCenter);
    }

    private void updateTableRows(JTable table) {

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int columnIndexToSort = 0;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    private void updateEmployeeTableData(){

        Object[][] data = Controller.getEmployeeData();
        String[] columnNames = {"Id", "Nombre", "Apellidos", "Salario", "Proyectos"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {

            final Class[] types = { Integer.class, String.class, String.class, Float.class, Integer.class };
            boolean[] canEdit = new boolean [] {
                    false, true, true, true, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        empTable.setModel(model);
        updateTableRows(empTable);
    }

    private void updateProjectTableData() {

        Object[][] data = Controller.getProjectData();
        String[] columnNames = {"Id", "Título", "Descripción", "Fecha entrega", "Empleados"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {

            Class[] types = { Integer.class, String.class, String.class, Integer.class, Integer.class };
            boolean[] canEdit = new boolean [] {
                    false, true, true, true, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        projTable.setModel(model);
        updateTableRows(projTable);
    }

    private void addMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.equals(e.getSource())){
                    int col = table.columnAtPoint(e.getPoint());
                    int row = table.rowAtPoint(e.getPoint());

                    System.out.println(table.getName()+" Row: " + table.getValueAt(row,col));
                }
            }
        });
    }
}
