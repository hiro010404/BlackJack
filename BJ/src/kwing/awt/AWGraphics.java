package kwing.awt;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * AWTバージョンのグラフィックス
 * 
 * @author Yuuki Miyake
 * 
 * @version 2.0 2007/12/05
 */
public class AWGraphics extends kwing.Graphics {

	/**
	 * グラフィックス
	 */
	private Graphics2D graphics;

	@Override
	public Object getGraphics() {
		return graphics;
	}

	/**
	 * グラフィックスの領域
	 */
	private Image buffer;

	/**
	 * キャンバス
	 */
	private Canvas canvas;

	@Override
	public Object getCanvas() {
		if (canvas == null) {

			return new Canvas() {
				private static final long serialVersionUID = -516525972837306240L;

				/**
				 * 画面のちらつきを抑える
				 * 
				 * @param g
				 */
				@Override
				public void update(Graphics g) {
					paint(g);
				}

				/**
				 * Javaの処理系が自動で実行する描画機能
				 * 
				 * @param g
				 */
				@Override
				public void paint(Graphics g) {
					g.drawImage(buffer, 0, 0, null);
				}
			};
		}
		return canvas;
	}

	/**
	 * AWGraphicsのコンストラクタ
	 * 
	 * @param WIDTH 幅
	 * @param HEIGHT 高さ
	 */
	public AWGraphics(int WIDTH, int HEIGHT) {
		buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		graphics = (Graphics2D) buffer.getGraphics();
		canvas = (Canvas) getCanvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void setColor(String color) {
		this.graphics.setColor(getColor(color));
	}

	/**
	 * 設定された色を返す
	 * 
	 * @param colorType カラータイプ
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

	@Override
	public void setStroke(float size) {
		graphics.setStroke(new BasicStroke(size));
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		graphics.fillRect(x, y, width, height);
	}

	@Override
	public void draw(Object Image, int x, int y) {
		graphics.drawImage((Image) Image, x, y, null);

	}

	@Override
	public void drawText(String text, int x, int y) {
		try {
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Dialog", Font.PLAIN, 19));
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
	public void repaint() {
		canvas.repaint();

	}

}
