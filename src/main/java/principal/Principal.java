package principal;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import principal.views.PanelCopiaFicheros;

public class Principal extends JFrame {
	JTabbedPane panelTabbed;
	static Principal instance = null;

	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}

	public Principal() {
		super("Gestion de centro Educativo");
		this.setBounds(0, 0, 800, 600);
		panelTabbed = new JTabbedPane();

		PanelCopiaFicheros pcf = new PanelCopiaFicheros();

		panelTabbed.addTab("Copiado de Carpetas", pcf);
		panelTabbed.setSelectedIndex(0);
		this.getContentPane().add(panelTabbed);

	}

	public static void main(String[] args) {
		Principal.getInstance().setVisible(true);

	}

}
