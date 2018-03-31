
// NAME: Gavriel Arbov, ID: 311846208 //

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class HW3_GavrielArbov extends JApplet {
	private final static int FRAME_SIZE = 700;
	private Panel panel = new Panel();

	public HW3_GavrielArbov() {
		add(panel);
	}

	public static void generalSettings(JFrame f) {
		f.setSize(FRAME_SIZE, FRAME_SIZE);
		f.setAlwaysOnTop(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("HW3");
		f.add(new HW3_GavrielArbov());
		generalSettings(f);

	}

	private class Panel extends JPanel {

		private final int THICK_SIZE = 10;
		private final Font FONT = new Font("Serif", Font.BOLD | Font.ITALIC, 18);

		// restoring properties
		private Color rColor;
		private int rThickness;
		private int rSpeed;

		private ArrayList<Point> points = new ArrayList<>();

		private int x;
		private int y;
		private boolean flag;
		private boolean flag2;
		private boolean restoring;
		private boolean drawAgain = true;
		private boolean isPanelClean;

		private Timer timer = new Timer(rSpeed, new ActionListener() {
			// restoring the painting
			@Override
			public void actionPerformed(ActionEvent e) {

				int i = 0;
				timer.setDelay(rSpeed);
				if (i < points.size()) {
					Point p = points.get(i);
					x = (int) p.getX();
					y = (int) p.getY();
					flag2 = true;
					points.remove(i);
					i++;
					repaint();
				} else {
					timer.stop();
					isPanelClean = false;
					drawAgain = true;
				}

			}
		});

		public Panel() {

			setFocusable(true);
			addMouseMotionListener(new MouseAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) {

					if (!isPanelClean && drawAgain) {
						cleanPanel();
						isPanelClean = true;
						restoring = false;
					}

					if (!restoring) {
						points.add(e.getPoint());
						x = e.getX();
						y = e.getY();
						flag = true;
						repaint();
					}

				}
			});

			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent k) {
					int e = k.getKeyCode();
					if (k.isControlDown() && e == KeyEvent.VK_SPACE) {
						new ControlPanel(HW3_GavrielArbov.this);
						timer.stop();
						restoring = true;
						drawAgain = false;
					}
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			// super.paintComponent(g);
			// recording
			if (!restoring) {
				g.setColor(Color.RED);
				g.setFont(FONT);
				g.drawString("RECORDING...", 20, 30);

				if (flag) {
					g.setColor(Color.BLACK);
					g.fillOval(x, y, THICK_SIZE, THICK_SIZE);
					repaint();
				}
				flag = false;
			}

			// restoring
			else {

				timer.start();
				g.setColor(Color.BLACK);
				g.setFont(FONT);
				g.drawString("RESTORING...", 20, 30);

				if (flag2) {
					g.setColor(rColor);
					g.fillOval(x, y, rThickness, rThickness);
				}
				flag2 = false;
			}

			if (timer.isRunning())
				drawAgain = false;

		}

	}

	public void setAction(Color color, int thickness, int speed) {
		cleanPanel();
		panel.rColor = color;
		panel.rThickness = thickness;
		panel.rSpeed = speed;
		panel.restoring = true;

	}

	public void cleanPanel() {
		super.repaint();

	}

}
