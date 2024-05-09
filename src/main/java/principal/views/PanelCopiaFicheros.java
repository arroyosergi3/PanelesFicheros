package principal.views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.awt.event.ActionEvent;

public class PanelCopiaFicheros extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCarpetaOrigen;
	private JTextField jtfCarpetaDestino;
	private JFileChooser jfileChooser;
	JProgressBar progressBar;
	static File carpetaOrigen = null;
	static File carpetaDestino = null;
			

	/**
	 * Create the panel.
	 */
	public PanelCopiaFicheros() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Copiado de contenido de carpetas");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Carpeta Origen");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		jtfCarpetaOrigen = new JTextField();
		jtfCarpetaOrigen.setEnabled(false);
		GridBagConstraints gbc_jtfCarpetaOrigen = new GridBagConstraints();
		gbc_jtfCarpetaOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCarpetaOrigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCarpetaOrigen.gridx = 1;
		gbc_jtfCarpetaOrigen.gridy = 1;
		add(jtfCarpetaOrigen, gbc_jtfCarpetaOrigen);
		jtfCarpetaOrigen.setColumns(10);

		JButton btnSeleccionaOrigen = new JButton("Selecciona Carpeta Origen");
		btnSeleccionaOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carpetaOrigen =  seleccionaFichero(jtfCarpetaOrigen);
			}
		});
		GridBagConstraints gbc_btnSeleccionaOrigen = new GridBagConstraints();
		gbc_btnSeleccionaOrigen.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeleccionaOrigen.gridx = 2;
		gbc_btnSeleccionaOrigen.gridy = 1;
		add(btnSeleccionaOrigen, gbc_btnSeleccionaOrigen);

		JLabel lblNewLabel_2 = new JLabel("Carpeta Destino");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		jtfCarpetaDestino = new JTextField();
		jtfCarpetaDestino.setEnabled(false);
		GridBagConstraints gbc_jtfCarpetaDestino = new GridBagConstraints();
		gbc_jtfCarpetaDestino.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCarpetaDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCarpetaDestino.gridx = 1;
		gbc_jtfCarpetaDestino.gridy = 2;
		add(jtfCarpetaDestino, gbc_jtfCarpetaDestino);
		jtfCarpetaDestino.setColumns(10);

		JButton btnSeleccionaDestino = new JButton("Selecciona Carpeta Destino");
		btnSeleccionaDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carpetaDestino =  seleccionaFichero(jtfCarpetaDestino);
			}
		});
		GridBagConstraints gbc_btnSeleccionaDestino = new GridBagConstraints();
		gbc_btnSeleccionaDestino.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeleccionaDestino.gridx = 2;
		gbc_btnSeleccionaDestino.gridy = 2;
		add(btnSeleccionaDestino, gbc_btnSeleccionaDestino);

		JButton btnCopiar = new JButton("Copiar Ficheros");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					copiar();
					JOptionPane.showMessageDialog(null, "Ficheros copiados con éxito mi rey");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnCopiar = new GridBagConstraints();
		gbc_btnCopiar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCopiar.gridx = 1;
		gbc_btnCopiar.gridy = 3;
		add(btnCopiar, gbc_btnCopiar);

		 progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 3;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 4;
		add(progressBar, gbc_progressBar);

	}
	
    private void copiar() throws IOException {
    	
        File[] archivos = carpetaOrigen.listFiles();
        int total = archivos.length;
        int copiados = 0;
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    File destinoArchivo = new File(carpetaDestino, archivo.getName());
                    copiarArchivo(archivo, destinoArchivo);
                    copiados++;
                    progressBar.setValue(copiados * 100 / total);
                } 
            }
        }
    }

    public void copiarArchivo(File origen, File destino) throws IOException {
    	
        Path origenPath = origen.toPath();
        Path destinoPath = destino.toPath();
        Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
    }
	

	private File seleccionaFichero(JTextField jtf) {
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

			// Vuelco el nombre del fichero sobre el JTextField
			jtf.setText(fichero.getAbsolutePath());

		}
		return fichero;
	}
	
	

}
