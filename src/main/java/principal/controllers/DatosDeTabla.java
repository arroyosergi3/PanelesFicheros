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
	
	
	public static Object[][] getDatosDeTablaFiltradoporTamanio(int tamanio, String carpeta) {
		File f = new File(carpeta);
	    if (f != null) {
	    	System.out.println("La carpeta no es nula");
	        File[] archivos = f.listFiles();
	        // Crear una nueva matriz para almacenar los datos filtrados
	        Object[][] datosFiltrados = new Object[archivos.length][3];
	        System.out.println(archivos.length);
	        int contador = 0;
	        for (int i = 0; i < archivos.length; i++) {
//	        	System.out.println("La i está subiendo: " + i);
	            File archivo = archivos[i];
	            if (archivo.length() <= tamanio * 1024) { // Convertir tamaño a bytes
//		        	System.out.println("El archivo pesa " + archivo.length() );

	                datosFiltrados[contador][0] = archivo.getName();
//	                System.out.println("Archivo añadido a tabla: " + archivo.getName());
	                datosFiltrados[contador][1] = (archivo.length() / 1024f) + " KB"; // Tamaño en KB
	                Date d = new Date(archivo.lastModified());
	                SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
	                datosFiltrados[contador][2] = dft.format(d);
	                contador++;
	            }
	        }
	        // Redimensionar la matriz para eliminar las filas vacías
	        Object[][] datosFiltradosFinal = new Object[contador][3];
	        for (int i = 0; i < contador; i++) {
	            datosFiltradosFinal[i] = datosFiltrados[i];
	        }
	        return datosFiltradosFinal;
	    }
	    return null; // Retornar null si no hay carpeta seleccionada o no hay archivos
	}



	public static Object[][] getDatosDeTablaFiltrado(String str) {
	    if (PanelTablaYFicheros.getCarpeta() != null) {
	        File[] estudiantes = PanelTablaYFicheros.getCarpeta().listFiles();
	        datos = new Object[estudiantes.length][3];
	        int contador = 0;
	        for (int i = 0; i < estudiantes.length; i++) {
	            String nombre = estudiantes[i].getName();
	            if (nombre.contains(str)) {
	                datos[contador][0] = nombre;
	                datos[contador][1] = (estudiantes[i].length() / 1024f + " KB");
	                Date d = new Date(estudiantes[i].lastModified());
	                SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
	                datos[contador][2] = dft.format(d);
	                contador++;
	            }
	        }
	        // Redimensionar la matriz para eliminar las filas vacías
	        Object[][] datosFiltrados = new Object[contador][3];
	        for (int i = 0; i < contador; i++) {
	            datosFiltrados[i] = datos[i];
	        }
	        return datosFiltrados;
	    }
	    return datos;
	}


	public static Object[][] getDatosDeTabla() {
	    if (PanelTablaYFicheros.getCarpeta() != null) {
	        File[] estudiantes = PanelTablaYFicheros.getCarpeta().listFiles();
	        datos = new Object[estudiantes.length][3];
	        for (int i = 0; i < estudiantes.length; i++) {
	            File e = estudiantes[i];
	            datos[i][0] = e.getName();
	            datos[i][1] = (e.length() / 1024f + " KB");
	            Date d = new Date(e.lastModified());
	            SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy");
	            datos[i][2] = dft.format(d);
	        }
	    }
	    return datos;
	}


}
