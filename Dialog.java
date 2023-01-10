import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Dialog extends Box{
	private static final String [] CHOIX = {"haut", "bas", "gauche", "droite", "milieu"};
	JRadioButton oui = new JRadioButton("oui");
	JRadioButton non = new JRadioButton("non", true);
	JComboBox<String> place = new JComboBox<>(CHOIX);
	
	public Dialog () {
		super(BoxLayout.Y_AXIS);
		JLabel mp = new JLabel("placer un meeple ?");
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(oui);
		bg.add(non);
		Box btn = new Box(BoxLayout.X_AXIS);
		btn.add(mp);
		btn.add(oui);
		btn.add(non);
		this.add(btn);
		this.add(place);
		place.setEnabled(false);
		oui.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				place.setEnabled(true);
				
			}
		});
		
		non.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				place.setEnabled(false);
				
			}
		});
	}
	public Meeple show (JComponent parent) {
		JOptionPane.showMessageDialog(parent, this, "Meeple", JOptionPane.QUESTION_MESSAGE);
		if (oui.isSelected())
			return new Meeple(Jeu.joueur, (String)place.getSelectedItem());
		return null;
		
	}

}
