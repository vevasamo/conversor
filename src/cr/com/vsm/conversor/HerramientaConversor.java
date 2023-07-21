package cr.com.vsm.conversor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HerramientaConversor extends JFrame implements ActionListener {

	private JButton btnMonedas;
	private JButton btnMedidas;
	private JButton btnSalir;
	private JLabel lbimg, titulo;

	/* Constructor */
	public HerramientaConversor() {
		setLayout(null); // Layout absoluto
		setBounds(350, 150, 600, 400); // Tamaño de la ventana
		
		ImageIcon img = new ImageIcon("./imagenes/VS.png");
		setIconImage(img.getImage());
		
		setTitle("Herramienta de Conversión:-)");
		setResizable(false); // No redimensionable

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//setDefaultCloseOperation(EXIT_ON_CLOSE); // Cerrar proceso al cerrar la ventana
		
		lbimg = new JLabel("");
		lbimg.setIcon(new ImageIcon("./imagenes/VS Desarrollos.png")); 
		//lbimg.setBounds(400,100, 300, 200); 
		lbimg.setBounds(410,160, 225, 225); 
		add(lbimg);
		

		titulo = new JLabel("HERRAMIENTA DE CONVERSIÓN");
		titulo.setBounds(200, 20, 250, 30);
		add(titulo);

		btnMedidas = new JButton("Conversor de Longitud");
		btnMedidas.setBounds(80, 110, 200, 40);
		add(btnMedidas);
		btnMedidas.addActionListener(this); /* Inicializo escuchador del botón */

		btnMonedas = new JButton("Conversor de Monedas");
		btnMonedas.setBounds(310, 110, 200, 40);
		add(btnMonedas);
		btnMonedas.addActionListener(this); /* Inicializo escuchador del botón */

		btnSalir = new JButton("Terminar");
		btnSalir.setBounds(80, 180, 200, 40);
		add(btnSalir);
		btnSalir.addActionListener(this); /* Inicializo escuchador del botón */

		setVisible(true); // Muestro JFrame (lo último para que lo pinte todo correctamanete)

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMedidas) {
			ConversorLongitud medidasLong = new ConversorLongitud(); 
			medidasLong.setVisible(true);
			
		} else if (e.getSource() == btnMonedas) {
			ConversorMonedas cambioMonedas = new ConversorMonedas(); 
	//		ConversorMonedas cambioMonedasGeneral = new ConversorMonedasGeneral(); 
			cambioMonedas.setVisible(true);
		} else { // Salir
			JOptionPane.showMessageDialog(null,
					":-( Hasta la próxima! " + "Regresa siempre que necesites más conversiones! ;-)");
			System.exit(0);
		}

	}

	public static void main(String[] args) {

		new HerramientaConversor();

	}
}