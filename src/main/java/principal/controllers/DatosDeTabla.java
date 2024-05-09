package principal.controllers;

import java.io.File;
import java.util.List;

import principal.views.PanelTablaYFicheros;

public class DatosDeTabla {

	private static Object[][] datos = null;
 	
	
	public static String[] getTitulosColumnas() {
		return new String[] {"Nombre", "Tamaño", "Ultima modificación"};
	}
	
	
	public static Object[][] getDatosDeTabla() {
		if (datos == null) {
			File[] estudiantes  = PanelTablaYFicheros.getCarpeta().listFiles();
			datos = new Object[estudiantes.length][3];
			for (int i = 0; i < estudiantes.length; i++) {
				File e = estudiantes[i];
				datos[i][0] = e.getName();
				datos[i][1] = (e.getTotalSpace() / 1024 + " KB");
				datos[i][2] = e.lastModified();

			}
		}
		
		return datos;
	}

	
	
}
