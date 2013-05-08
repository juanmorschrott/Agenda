/** @proyecto: Desarrollo de una agenda simple, con conexion a base de datos MySQL
 *  @prototipo: Prototipo 1.0.1
 *  @since: Prototipo 1
 *  @fuente: MainWindow.java - 1.0 - 02/03/2013
 *  @descripcion: Ventana printipal de la aplicacion.
 *  @author: Juan Andres Moreno Schrott
 */

package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import data.GestionDatos;

import model.Contacto;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

public class MainWindow extends JFrame {

	private JFrame frmAgenda;
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private JTextField tfTelefono;
	private JTextField tfBuscar;
	private JTable table;
	private Image icon;

	private Contacto contact = null;
	private JTextField tfBorrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Accedemos a la base de datos
					GestionDatos.accesoDatos();

					// Iniciamos ventana
					MainWindow window = new MainWindow();
					window.frmAgenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		icon = Toolkit.getDefaultToolkit().getImage("data/agenda.png");
		this.setIconImage(icon);

		contact = new Contacto();

		frmAgenda = new JFrame();
		frmAgenda.getContentPane().setBackground(
				UIManager.getColor("Button.background"));
		frmAgenda.setResizable(false);
		frmAgenda.setTitle("Agenda");
		frmAgenda.setBounds(100, 100, 766, 451);
		frmAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgenda.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Insertar",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 32, 366, 155);
		frmAgenda.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 40, 70, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Direccion:");
		lblNewLabel_1.setBounds(10, 65, 70, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Telefono:");
		lblNewLabel_2.setBounds(10, 90, 70, 14);
		panel.add(lblNewLabel_2);

		tfNombre = new JTextField();
		tfNombre.setBounds(90, 37, 258, 20);
		panel.add(tfNombre);
		tfNombre.setColumns(10);

		tfDireccion = new JTextField();
		tfDireccion.setBounds(90, 62, 258, 20);
		panel.add(tfDireccion);
		tfDireccion.setColumns(10);

		tfTelefono = new JTextField();
		tfTelefono.setBounds(90, 87, 258, 20);
		panel.add(tfTelefono);
		tfTelefono.setColumns(10);

		JButton bInsertar = new JButton("Insertar");
		bInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				contact.setNombre(tfNombre.getText());
				contact.setDireccion(tfDireccion.getText());
				contact.setTelefono(tfTelefono.getText());

				GestionDatos.accesoDatos();
				GestionDatos.insertData(contact);
			}
		});
		bInsertar.setBounds(259, 118, 89, 23);
		panel.add(bInsertar);

		JPanel panel_1 = new JPanel();
		panel_1.setName("");
		panel_1.setBorder(new TitledBorder(null, "Buscar",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 198, 366, 101);
		frmAgenda.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		tfBuscar = new JTextField();
		tfBuscar.addMouseListener(new MouseAdapter() {
			
			/**
			 * Al hacer click sobre el TextField, borraremos el texto de cualquier
			 * otra caja de texto.
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfNombre.setText(" ");
				tfDireccion.setText(" ");
				tfTelefono.setText(" ");
			}
		});
		tfBuscar.setBounds(10, 36, 342, 20);
		panel_1.add(tfBuscar);
		tfBuscar.setColumns(10);

		JButton bBuscar = new JButton("Buscar");
		bBuscar.addActionListener(new ActionListener() {
			
			/**
			 * Al pulsar el boton buscar, obtenemos los atributos del objeto
			 * Contacto, y los colocamos en la JTable
			 */
			public void actionPerformed(ActionEvent e) {

				contact.setNombre(tfBuscar.getText());

				GestionDatos.accesoDatos();
				GestionDatos.readData(contact);

				table.setModel(new DefaultTableModel(new Object[][] { {
						contact.getNombre(), contact.getDireccion(),
						contact.getTelefono() }, },

				new String[] { "Nombre", "Direccion", "Telefono" }));
			}
		});
		bBuscar.setBounds(263, 67, 89, 23);
		panel_1.add(bBuscar);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Contacto",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 310, 738, 101);
		frmAgenda.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 718, 43);
		panel_2.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { {
				contact.getNombre(), contact.getDireccion(),
				contact.getTelefono() }, },

		new String[] { "Nombre", "Direccion", "Telefono" }));
		scrollPane.setViewportView(table);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 760, 21);
		frmAgenda.getContentPane().add(menuBar);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About...");
		mnHelp.add(mntmAbout);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Borrar",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(382, 198, 366, 101);
		frmAgenda.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		tfBorrar = new JTextField();
		tfBorrar.setBounds(10, 35, 342, 20);
		panel_3.add(tfBorrar);
		tfBorrar.setColumns(10);

		JButton bBorrar = new JButton("Borrar");
		bBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean response;

				contact.setNombre(tfBorrar.getText());

				JDialogDeleteAlert alert = new JDialogDeleteAlert();
				alert.setVisible(true);

				/*
				response = alert.getDecision();

				if (response == true) {
					GestionDatos.accesoDatos();
					GestionDatos.deleteData(contact);
					alert.setVisible(false);
				} else {
					alert.setVisible(false);
				}
*/
			}
		});
		bBorrar.setBounds(263, 67, 89, 23);
		panel_3.add(bBorrar);
	}
}
