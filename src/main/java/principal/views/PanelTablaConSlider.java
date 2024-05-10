package principal.views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.nio.channels.SelectableChannel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import principal.controllers.DatosDeTabla;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PanelTablaConSlider extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCarpetaSeleccionada;
	private JFileChooser jfileChooser;
	public static File carpetaSeleccionada;
	private JTable table;
	Object datosEnTabla[][] = DatosDeTabla.getDatosDeTabla();
	String titulosEnTabla[] = DatosDeTabla.getTitulosColumnas();
	JScrollPane jcp;
	JLabel lblTamanio;
	JSlider slider;
	
	
	private DefaultTableModel dtm = null;
	/**
	 * Create the panel.
	 */
	public PanelTablaConSlider() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Búsqueda de Ficheros");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Carpeta:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfCarpetaSeleccionada = new JTextField();
		jtfCarpetaSeleccionada.setEnabled(false);
		GridBagConstraints gbc_jtfCarpetaSeleccionada = new GridBagConstraints();
		gbc_jtfCarpetaSeleccionada.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCarpetaSeleccionada.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCarpetaSeleccionada.gridx = 1;
		gbc_jtfCarpetaSeleccionada.gridy = 1;
		add(jtfCarpetaSeleccionada, gbc_jtfCarpetaSeleccionada);
		jtfCarpetaSeleccionada.setColumns(10);
		 
		 JLabel lblNewLabel_2 = new JLabel("Tamaño del fichero:");
		 GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		 gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		 gbc_lblNewLabel_2.gridx = 0;
		 gbc_lblNewLabel_2.gridy = 2;
		 add(lblNewLabel_2, gbc_lblNewLabel_2);
		 
		  slider = new JSlider();
		  slider.setPaintLabels(true);
		  slider.setPaintTicks(true);
		  slider.setValue(5);
		  slider.setMaximum(5);
		 slider.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		filtrar();
		 	}
		 });
		 GridBagConstraints gbc_slider = new GridBagConstraints();
		 gbc_slider.insets = new Insets(0, 0, 5, 5);
		 gbc_slider.gridx = 1;
		 gbc_slider.gridy = 2;
		 add(slider, gbc_slider);
		 
		  lblTamanio = new JLabel("Tamaño");
		 GridBagConstraints gbc_lblTamanio = new GridBagConstraints();
		 gbc_lblTamanio.insets = new Insets(0, 0, 5, 0);
		 gbc_lblTamanio.gridx = 2;
		 gbc_lblTamanio.gridy = 2;
		 add(lblTamanio, gbc_lblTamanio);
		
		 jcp = new JScrollPane();
		GridBagConstraints gbc_jcp = new GridBagConstraints();
		gbc_jcp.gridwidth = 3;
		gbc_jcp.fill = GridBagConstraints.BOTH;
		gbc_jcp.gridx = 0;
		gbc_jcp.gridy = 3;
		add(jcp, gbc_jcp);

		
		JButton btnSeleccionaCarpeta = new JButton("Selecciona carpeta");
		btnSeleccionaCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionaCarpeta();
				dtm = getDefaultTableModelNoEditable();
				table = new JTable(dtm);
				jcp.setViewportView(table);
			}
		});
		GridBagConstraints gbc_btnSeleccionaCarpeta = new GridBagConstraints();
		gbc_btnSeleccionaCarpeta.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeleccionaCarpeta.gridx = 2;
		gbc_btnSeleccionaCarpeta.gridy = 1;
		add(btnSeleccionaCarpeta, gbc_btnSeleccionaCarpeta);
		
		
		

		


	}
	
	
	
	private void filtrar() {
	    int valorSlider = slider.getValue();
	    int tamanioLimite = valorSlider * 1024; // Convertir de MB a KB
	    lblTamanio.setText("> " + valorSlider + "MB");

	    Object[][] datosFiltrados = DatosDeTabla.getDatosDeTablaFiltradoporTamanio(tamanioLimite);
	    
	    int cantidadFicheros = datosFiltrados != null ? datosFiltrados.length : 0;
	    if (cantidadFicheros > 0) {
	        lblTamanio.setText("> " + valorSlider + "MB (" + cantidadFicheros + " ficheros)");
	    } else {
	        lblTamanio.setText("> " + valorSlider + "MB (0 ficheros)");
	        dtm.setRowCount(0);
	    }
	    table.setModel(dtm); // Actualiza el modelo de datos en la tabla
	}

	
	
	
	private void seleccionaCarpeta() {
		File fichero = null;
		this.jfileChooser = new JFileChooser();

		// Establecimiento de la carpeta de inicio
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));

		// Tipo de selección que se hace en el diálogo
//		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Sólo selecciona ficheros
		this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sólo selecciona carpetas
//		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Selecciona ficheros y carpetas

		// Filtro del tipo de ficheros que puede abrir
		this.jfileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Carpetas";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return false;
			}
		});

		// Abro el diálogo para la elección del usuario
		int seleccionUsuario = jfileChooser.showOpenDialog(null);

		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			 fichero = this.jfileChooser.getSelectedFile();
			 carpetaSeleccionada = this.jfileChooser.getSelectedFile();

			// Vuelco el nombre del fichero sobre el JTextField
			this.jtfCarpetaSeleccionada.setText(fichero.getAbsolutePath());

		}
	
	}
	
	public static File getCarpeta() {
		return carpetaSeleccionada;
	}
	
	
	private DefaultTableModel getDefaultTableModelNoEditable () {

		datosEnTabla = DatosDeTabla.getDatosDeTabla();

		DefaultTableModel dtm = new DefaultTableModel(datosEnTabla, titulosEnTabla) {
			
			/**
			 * La sobreescritura de este método nos permite controlar qué celdas queremos que sean editables
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column != 1) {
					return false;
				}
				return true;
			} 
		};
		return dtm;
	}

//	public Object[][] getDatosEnTabla() {
//		return datosEnTabla;
//	}
//
//	public void setDatosEnTabla(Object[][] datosEnTabla) {
//		this.datosEnTabla = datosEnTabla;
//	}
	
	

}
