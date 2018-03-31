import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class ControlPanel extends JFrame implements ActionListener, KeyListener {
	private final int FRAME_SIZE = 300;

	// thickness levels
	private final int DEFAULT = 10;
	private final int THIN = 5;
	private final int THICK = 15;

	// drawing speeds
	private final int FAST = 1;
	private final int MID = 10;
	private final int SLOW = 30;

	// main frame
	private HW3_GavrielArbov mainFrame;

	// main panel
	private JPanel mainPanel = new JPanel(new FlowLayout(10, 30, 20));

	// first panel
	private JPanel firstPanel = new JPanel(new FlowLayout(0, 0, 0));
	private JRadioButton black = new JRadioButton("Black");
	private JRadioButton red = new JRadioButton("Red");
	private JRadioButton blue = new JRadioButton("Blue");
	private JRadioButton green = new JRadioButton("Green");
	private ButtonGroup bg = new ButtonGroup();

	// second panel
	private JPanel secondPanel = new JPanel(new FlowLayout(0, 0, 3));
	private JComboBox<String> comboBox = new JComboBox<>(
			new String[] { "Default thickness", "Thin thickness", "Thick thickness" });

	// third panel
	private JPanel thirdPanel = new JPanel(new FlowLayout(0, 0, -4));
	private JSlider slider = new JSlider(JSlider.HORIZONTAL);

	// fourth panel
	private JPanel fourthPanel = new JPanel(new FlowLayout(0, 96, 0));
	private JButton okButton = new JButton("OK");

	public ControlPanel(HW3_GavrielArbov mainFrame) {
		super("ControlPanel");
		this.mainFrame = mainFrame;
		setLayout(new GridLayout());

		// adding radio buttons
		black.setMnemonic('B');
		black.addKeyListener(this);
		red.setMnemonic('R');
		red.setSelected(true);
		red.addKeyListener(this);
		blue.setMnemonic('l');
		blue.addKeyListener(this);
		green.setMnemonic('G');
		green.addKeyListener(this);
		bg.add(black);
		bg.add(red);
		bg.add(blue);
		bg.add(green);
		firstPanel.add(black);
		firstPanel.add(red);
		firstPanel.add(blue);
		firstPanel.add(green);
		firstPanel.setBorder(BorderFactory.createTitledBorder("Colors"));

		// adding combo box
		comboBox.setToolTipText("Choose thickness");
		comboBox.addKeyListener(this);
		secondPanel.add(comboBox);

		// adding slider
		slider.setMinimum(1);
		slider.setMaximum(3);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(1);
		slider.addKeyListener(this);
		thirdPanel.setBorder(BorderFactory.createTitledBorder("Speed"));
		thirdPanel.add(slider);

		// adding button
		okButton.addActionListener(this);
		okButton.addKeyListener(this);
		fourthPanel.add(okButton);

		// adding inner panels to main panel
		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		mainPanel.add(thirdPanel);
		mainPanel.add(fourthPanel);

		// adding main panel to frame
		add(mainPanel);

		// creating frame
		generalSettings();
	}

	// button pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		action();

	}

	public void action() {
		dispose();

		// set color
		Color color = null;
		if (black.isSelected())
			color = Color.BLACK;
		if (red.isSelected())
			color = Color.RED;
		if (blue.isSelected())
			color = Color.BLUE;
		if (green.isSelected())
			color = Color.GREEN;

		// set thickness
		int thickness;
		if (comboBox.getSelectedIndex() == 0)
			thickness = DEFAULT;
		else if (comboBox.getSelectedIndex() == 1)
			thickness = THIN;
		else
			thickness = THICK;

		// set speed
		int speed;
		if (slider.getValue() == 1)
			speed = SLOW;
		else if (slider.getValue() == 2)
			speed = MID;
		else
			speed = FAST;

		mainFrame.setAction(color, thickness, speed);

	}

	public void generalSettings() {
		setSize(FRAME_SIZE, FRAME_SIZE);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(mainFrame);
		setVisible(true);
	}

	// enter pressed
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			action();
	}

	// not in use
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
