package kwing.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Swingバージョンのグラフィックス
 * 
 * @author Yuuki Miyake
 * 
 * @version 2.0 2007/12/05
 */
public class SWGraphics extends kwing.Graphics {

	/**
	 * グラフィックス
	 */
	private Graphics2D graphics;

	/**
	 * グラフィックスの領域
	 */
	private Image buffer;

	/**
	 * キャンバス
	 */
	private JPanel canvas;

	@Override
	public Object getGraphics() {

		return graphics;
	}

	@Override
	public Object getCanvas() {

		if (canvas == null)
			return new JPanel() {
				private static final long serialVersionUID = -516525972837306240L;

				/**
				 * Javaの処理系が自動で実行する描画機能
				 * 
				 * @param g
				 */
				@Override
				protected void paintComponent(Graphics g) {
					g.drawImage(buffer, 0, 0, null);
				}
			};
		return canvas;
	}

	public SWGraphics(int WIDTH, int HEIGHT) {
		buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		graphics = (Graphics2D) buffer.getGraphics();
		canvas = (JPanel) getCanvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void setColor(String color) {
		graphics.setColor(getColor(color));
	}

	@Override
	public void setStroke(float size) {
		graphics.setStroke(new BasicStroke(size));
	}

	@Override
	public void draw(Object image, int x, int y) {

		graphics.drawImage((Image) image, x, y, null);
	}

	@Override
	public void drawText(String text, int x, int y) {
		try {
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Dialog", Font.ITALIC, 19));
			graphics.drawString(text, x, y);
		} catch (NullPointerException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle,
			int drawAngle) {
		graphics.drawArc(x, y, width, height, startAngle, drawAngle);

	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height) {
		graphics.drawRoundRect(x, y, width, height, 9, 9);
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		graphics.fillRect(x, y, width, height);
	}

	@Override
	public void repaint() {
		canvas.repaint();

	}

	/**
	 * 設定された色を返す
	 * 
	 * @param colorType　カラータイプ
	 * @return カラー
	 */
	private Color getColor(String colorType) {
		Color color;

		if (colorType.equals("white")) {
			color = Color.WHITE;
		} else if (colorType.equals("green")) {
			color = new Color(46, 128, 46);
		} else {
			color = Color.BLACK;
		}
		return color;

	}

}
