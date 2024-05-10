package principal.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import principal.views.PanelTablaYFicheros;

public class DatosDeTabla {

	private static Object[][] datos = null;

	public static String[] getTitulosColumnas() {
		return new String[] { "Nombre", "Tamaño", "Ultima modificación" };
	}

	public static Object[][] getDatosDeTablaFiltrado(String str) {
	    if (datos == null) {
	        if (PanelTablaYFicheros.getCarpeta() != null) {
	            File[] estudiantes = PanelTablaYFicheros.getCarpeta().listFiles();
	            datos = new Object[estudiantes.length][3];
	            int contador = 0; // Variable para controlar la posición en la matriz de datos
	            for (int i = 0; i < estudiantes.length; i++) {
	                String nombre = estudiantes[i].getName();
	                if (nombre.toLowerCase().contains(str)) {
	                    // Si el nombre coincide con el filtro, añadir datos a la matriz
	                    datos[contador][0] = nombre;
	                    datos[contador][1] = (estudiantes[i].length() / 1024f + " KB");
	                    Date d = new Date(estudiantes[i].lastModified());
	                    SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
	                    datos[contador][2] = dft.format(d);
	                    contador++; // Incrementar el contador de posición
	                }
	            }
	            // Redimensionar la matriz para eliminar las filas vacías
	            Object[][] datosFiltrados = new Object[contador][3];
	            for (int i = 0; i < contador; i++) {
	                datosFiltrados[i] = datos[i];
	            }
	            return datosFiltrados;
	        }
	    }
	    return datos;
	}

	public static Object[][] getDatosDeTabla() {
		if (datos == null) {
			if (PanelTablaYFicheros.getCarpeta() != null) {
				File[] estudiantes = PanelTablaYFicheros.getCarpeta().listFiles();
				datos = new Object[estudiantes.length][3];
				for (int i = 0; i < estudiantes.length; i++) {
					File e = estudiantes[i];
					datos[i][0] = e.getName();
					datos[i][1] = (e.length() / 1024f + " KB");
					Date d = new Date(e.lastModified());
					SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
					;
					datos[i][2] = dft.format(d);

				}
			}

		}

		return datos;
	}

}
