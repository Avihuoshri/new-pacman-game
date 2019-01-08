package JPanels;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ScoreWindow extends JFrame {

	/**
	 * Create the panel.
	 */
	public ScoreWindow() 
	{
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		setSize(400, 400);
		panel.setBounds(0, 0, 384, 361);
		getContentPane().add(panel);
		
		JLabel lblCxcxc = new JLabel("Game data");
		lblCxcxc.setForeground(Color.RED);
		lblCxcxc.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setForeground(Color.RED);
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel imageLable = new JLabel("Batman VS Jocker");
		Image img = new ImageIcon(this.getClass().getResource("C:\\Users\\Avihu\\workspace2\\new pacman game\\Images\\Batman_and_the_joker.png")).getImage() ;
		imageLable.setIcon((new ImageIcon(img)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(150, Short.MAX_VALUE)
					.addComponent(lblCxcxc, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(140))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(280, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(96)
					.addComponent(imageLable, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(92, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCxcxc, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addComponent(imageLable, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		setVisible(true);

	}
	
	
	public static void main(String[] args) 
	{
		ScoreWindow sw = new  ScoreWindow() ;
	}
}
