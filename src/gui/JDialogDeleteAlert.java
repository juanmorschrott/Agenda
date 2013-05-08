/** @proyecto: Desarrollo de una agenda simple, con conexion a base de datos MySQL
 *  @prototipo: Prototipo 1.0.1
 *  @since: Prototipo 1
 *  @fuente: JDialogDelete.java - 1.0 - 02/03/2013
 *  @descripcion: Dialogo para confirmar que queremos proceder a borrar informacion
 *  de la base de datos.
 *  @author: Juan Andres Moreno Schrott
 */

package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDialogDeleteAlert extends JDialog {
	
	private boolean decision = false;

	/**
	 * Create the dialog.
	 */
	public JDialogDeleteAlert() {
		setBounds(100, 100, 252, 170);
		getContentPane().setLayout(null);
		
		JLabel lblestaSeguroDe = new JLabel("Confirme que desea borrar los datos de:");
		lblestaSeguroDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblestaSeguroDe.setVerticalTextPosition(SwingConstants.TOP);
		lblestaSeguroDe.setVerticalAlignment(SwingConstants.TOP);
		lblestaSeguroDe.setBounds(10, 11, 216, 40);
		getContentPane().add(lblestaSeguroDe);
		
		JButton btnNewButton = new JButton("Borrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				decision = true;
			}
		});
		btnNewButton.setBounds(10, 97, 100, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decision = false;
			}
		});
		btnNewButton_1.setBounds(120, 97, 100, 23);
		getContentPane().add(btnNewButton_1);
	}

	public boolean getDecision() {
		return decision;
	}

	public void setDecision(boolean decision) {
		this.decision = decision;
	}
}
