package com.example.testdrawbitmapmesh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PolylineView2 extends View {
	private static final float LEFT = 1 / 16F, TOP = 1 / 16F, RIGHT = 15 / 16F, BOTTOM = 7 / 8F;// �����������λ��
	private static final float TIME_X = 3 / 32F, TIME_Y = 1 / 16F, MONEY_X = 31 / 32F, MONEY_Y = 15 / 16F;// �����������λ��
	private static final float TEXT_SIGN = 1 / 32F;// ������Դ�С
	private static final float THICK_LINE_WIDTH = 1 / 128F, THIN_LINE_WIDTH = 1 / 512F;// ���ߺ�ϸ����Դ�С

	private TextPaint mTextPaint;// ���ֻ���
	private Paint linePaint, pointPaint;// �������ʺ͵㻭��
	private Path mPath;// ·������
	private Bitmap mBitmap;// �������ߵ�Btimap����
	private Canvas mCanvas;// װ��mBitmap��Canvas����

	private List<PointF> pointFs;// �����б�
	private float[] rulerX, rulerY;// xy����̶�

	private String signX, signY;// ����X��Y����ֱ��ʾʲô������
	private float textY_X, textY_Y, textX_X, textX_Y;// ��������
	private float textSignSzie;// xy�����ʶ�ı������С
	private float thickLineWidth, thinLineWidth;// ���ߺ�ϸ�߿��
	private float left, top, right, bottom;// ������������������������
	private int viewSize;// �ؼ��ߴ�
	private float maxX, maxY;// �����������̶�
	private float spaceX, spaceY;// �̶ȼ��

	public PolylineView2(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ʵ�����ı����ʲ����ò���
		mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
		mTextPaint.setColor(Color.WHITE);

		// ʵ�����������ʲ����ò���
		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		linePaint.setStyle(Paint.Style.STROKE);
		linePaint.setColor(Color.WHITE);

		// ʵ�����㻭�ʲ����ò���
		pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		pointPaint.setStyle(Paint.Style.FILL);
		pointPaint.setColor(Color.WHITE);

		// ʵ����Path����
		mPath = new Path();

		// ʵ����Canvas����
		mCanvas = new Canvas();

		// ��ʼ������
		initData();
	}

	/**
	 * ��ʼ������֧��
	 * View��ʼ��ʱ���Կ��Ǹ���һ��ģ������
	 * ��Ȼ���ǿ���ͨ��setData���������Լ�������
	 */
	private void initData() {
		Random random = new Random();
		pointFs = new ArrayList<PointF>();
		for (int i = 0; i < 20; i++) {
			PointF pointF = new PointF();

			pointF.x = (float) (random.nextInt(100) * i);
			pointF.y = (float) (random.nextInt(100) * i);

			pointFs.add(pointF);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// ������ûѧϰ�����ؼ�֮ǰǿ�ƿ��һ��
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// ��ȡ�ؼ��ߴ�
		viewSize = w;

		// ���������ʶ�ı�����
		textY_X = viewSize * TIME_X;
		textY_Y = viewSize * TIME_Y;

		// ��������ʶ�ı�����
		textX_X = viewSize * MONEY_X;
		textX_Y = viewSize * MONEY_Y;

		// ����xy���ʶ�ı���С
		textSignSzie = viewSize * TEXT_SIGN;

		// ������������������������
		left = viewSize * LEFT;
		top = viewSize * TOP;
		right = viewSize * RIGHT;
		bottom = viewSize * BOTTOM;

		// ������߿��
		thickLineWidth = viewSize * THICK_LINE_WIDTH;

		// ����ϸ�߿��
		thinLineWidth = viewSize * THIN_LINE_WIDTH;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// ��䱳��
		canvas.drawColor(0xFF9596C4);

		// ���Ʊ�ʶԪ��
		drawSign(canvas);

		// ��������
		drawGrid(canvas);

		// ��������
		drawPolyline(canvas);
	}

	/**
	 * ��������
	 * ������ʹ��һ���µ�Bitmap�������µ�Canvas��������������
	 * ��Ȼ�����ֱ����ԭ����canvas��onDraw�������Ǹ�����ֱ�ӻ�������㻹û��������εĻ�����
	 * 
	 * @param canvas
	 *            ����
	 * 
	 */
	private void drawPolyline(Canvas canvas) {
		// ����һ��Bitmap�����С�����ǵ������Сһ��
		mBitmap = Bitmap.createBitmap((int) (viewSize * (RIGHT - LEFT) - spaceX), (int) (viewSize * (BOTTOM - TOP) - spaceY), Bitmap.Config.ARGB_8888);

		// ��Bitmapע��Canvas
		mCanvas.setBitmap(mBitmap);

		// Ϊ�������һ����͸���ĺ�ɫ
		mCanvas.drawARGB(75, 255, 0, 0);

		// ��������
		mPath.reset();

		/*
		 * ����Path�ͻ���Point
		 */
		for (int i = 0; i < pointFs.size(); i++) {
			// ����x����
			float x = mCanvas.getWidth() / maxX * pointFs.get(i).x;

			// ����y����
			float y = mCanvas.getHeight() / maxY * pointFs.get(i).y;
			y = mCanvas.getHeight() - y;

			// ����С���
			mCanvas.drawCircle(x, y, thickLineWidth, pointPaint);

			/*
			 * ����ǵ�һ������������ΪPath�����
			 */
			if (i == 0) {
				mPath.moveTo(x, y);
			}

			// ���Ӹ���
			mPath.lineTo(x, y);
		}

		// ����PathEffect
		linePaint.setPathEffect(new CornerPathEffect(10));

		// �����������
		linePaint.setStrokeWidth(thickLineWidth);

		// ��Path���Ƶ������Զ���Canvas��
		mCanvas.drawPath(mPath, linePaint);

		// ��mBitmap���Ƶ�ԭ����canvas
		canvas.drawBitmap(mBitmap, left, top + spaceY, null);
	}

	/**
	 * ��������
	 * 
	 * @param canvas
	 *            ����
	 */
	private void drawGrid(Canvas canvas) {
		// ��������
		canvas.save();

		// �����������ʿ��
		linePaint.setStrokeWidth(thickLineWidth);

		// ����xy��Path
		mPath.moveTo(left, top);
		mPath.lineTo(left, bottom);
		mPath.lineTo(right, bottom);

		// ����xy��
		canvas.drawPath(mPath, linePaint);

		// ��������
		drawLines(canvas);

		// �ͷŻ���
		canvas.restore();
	}

	/**
	 * ��������
	 * 
	 * @param canvas
	 *            ����
	 */
	private void drawLines(Canvas canvas) {
		// ����̶����ֳߴ�
		float textRulerSize = textSignSzie / 2F;

		// �������ֻ������ֳߴ�
		mTextPaint.setTextSize(textRulerSize);

		// ��������������߿��
		linePaint.setStrokeWidth(thinLineWidth);

		// ��ȡ���ݳ���
		int count = pointFs.size();

		// ���������ֵΪ���ݳ��ȼ�һ
		int divisor = count - 1;

		// ��������������ֵ
		maxX = 0;
		for (int i = 0; i < count; i++) {
			if (maxX < pointFs.get(i).x) {
				maxX = pointFs.get(i).x;
			}
		}

		// �������������ܱ�count������ֵ
		int remainderX = ((int) maxX) % divisor;
		maxX = remainderX == 0 ? ((int) maxX) : divisor - remainderX + ((int) maxX);

		// ���������������ֵ
		maxY = 0;
		for (int i = 0; i < count; i++) {
			if (maxY < pointFs.get(i).y) {
				maxY = pointFs.get(i).y;
			}
		}

		// ��������������ܱ�count������ֵ
		int remainderY = ((int) maxY) % divisor;
		maxY = remainderY == 0 ? ((int) maxY) : divisor - remainderY + ((int) maxY);

		// ���ɺ���̶�ֵ
		rulerX = new float[count];
		for (int i = 0; i < count; i++) {
			rulerX[i] = maxX / divisor * i;
		}

		// ��������̶�ֵ
		rulerY = new float[count];
		for (int i = 0; i < count; i++) {
			rulerY[i] = maxY / divisor * i;
		}

		// �����������̶ȼ��
		spaceY = viewSize * (BOTTOM - TOP) / count;
		spaceX = viewSize * (RIGHT - LEFT) / count;

		// �������������û���͸����Ϊ75%
		int sc = canvas.saveLayerAlpha(0, 0, canvas.getWidth(), canvas.getHeight(), 75, Canvas.ALL_SAVE_FLAG);

		// ���ƺ����߶�
		for (float y = viewSize * BOTTOM - spaceY; y > viewSize * TOP; y -= spaceY) {
			for (float x = viewSize * LEFT; x < viewSize * RIGHT; x += spaceX) {
				/*
				 * ���������߶�
				 */
				if (y == viewSize * TOP + spaceY) {
					canvas.drawLine(x, y, x, y + spaceY * (count - 1), linePaint);
				}

				/*
				 * ���ƺ����߶�
				 */
				if (x == viewSize * RIGHT - spaceX) {
					canvas.drawLine(x, y, x - spaceX * (count - 1), y, linePaint);
				}
			}
		}

		// ��ԭ����
		canvas.restoreToCount(sc);

		// ���ƺ�������̶�ֵ
		int index_x = 0, index_y = 1;
		for (float y = viewSize * BOTTOM - spaceY; y > viewSize * TOP; y -= spaceY) {
			for (float x = viewSize * LEFT; x < viewSize * RIGHT; x += spaceX) {
				/*
				 * ���ƺ���̶���ֵ
				 */
				if (y == viewSize * BOTTOM - spaceY) {
					canvas.drawText(String.valueOf(rulerX[index_x]), x, y + textSignSzie + spaceY, mTextPaint);
				}

				/*
				 * ��������̶���ֵ
				 */
				if (x == viewSize * LEFT) {
					canvas.drawText(String.valueOf(rulerY[index_y]), x - thickLineWidth, y + textRulerSize, mTextPaint);
				}

				index_x++;
			}
			index_y++;
		}
	}

	/**
	 * ���Ʊ�ʶԪ��
	 * 
	 * @param canvas
	 *            ����
	 */
	private void drawSign(Canvas canvas) {
		// ��������
		canvas.save();

		// �����ı��������ֳߴ�
		mTextPaint.setTextSize(textSignSzie);

		// ���������ʶ����
		mTextPaint.setTextAlign(Paint.Align.LEFT);
		canvas.drawText(null == signY ? "y" : signY, textY_X, textY_Y, mTextPaint);

		// ���ƺ����ʶ����
		mTextPaint.setTextAlign(Paint.Align.RIGHT);
		canvas.drawText(null == signX ? "x" : signX, textX_X, textX_Y, mTextPaint);

		// �ͷŻ���
		canvas.restore();
	}

	/**
	 * ��������
	 * 
	 * @param pointFs
	 *            �㼯��
	 */
	public synchronized void setData(List<PointF> pointFs, String signX, String signY) {
		/*
		 * ����Ϊ��ֱ��GG
		 */
		if (null == pointFs || pointFs.size() == 0)
			throw new IllegalArgumentException("No data to display !");

		/*
		 * �������ݳ��Ȳ�����10��
		 * ��������ͼ��˵����̫���û��Ҫ������ͼ��ʾ�˶���ʹ��ɢ��ͼ
		 */
		if (pointFs.size() > 10)
			throw new IllegalArgumentException("The data is too long to display !");

		// �������ݲ��ػ���ͼ
		this.pointFs = pointFs;
		this.signX = signX;
		this.signY = signY;
		invalidate();
	}
}
