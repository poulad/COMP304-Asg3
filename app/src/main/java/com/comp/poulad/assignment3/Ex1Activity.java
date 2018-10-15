package com.comp.poulad.assignment3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Ex1Activity extends AppCompatActivity {

    private Spinner _spThickness;
    private RadioGroup _rgColor;
    private TextView _tvY;
    private ImageView _ivBitmap;

    private Canvas _canvas;
    private Paint _paint;
    private int _currentX;
    private int _currentY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        //creating a bitmap as content view for the image
        Point windowSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(windowSize);
        Bitmap bitmap = Bitmap.createBitmap(windowSize.x, windowSize.y, Bitmap.Config.ARGB_8888);
        _canvas = new Canvas(bitmap);
        _canvas.drawColor(Color.LTGRAY); //background

        _ivBitmap = findViewById(R.id.ImageViewForDrawing);
        _ivBitmap.setImageBitmap(bitmap);

        _tvY = findViewById(R.id.tvY);

        _canvas.drawColor(Color.LTGRAY);

        String[] thicknessValues = {"10", "15", "20", "25", "30"};
        _spThickness = findViewById(R.id.spThickness);
        _spThickness.setAdapter(new ArrayAdapter<>(
                Ex1Activity.this, android.R.layout.simple_spinner_item, thicknessValues
        ));
        _spThickness.setSelection(1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(Ex1Activity.this, MainActivity.class));
            return false;
        }
        return move(keyCode);
    }

    public void onClearButtonClicked(View v) {
        restartCanvas();
    }

    private void init() {
        _spThickness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                createPaint();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        _rgColor = findViewById(R.id.rgColor);
        _rgColor.setOnCheckedChangeListener((group, checkedId) -> createPaint());

        findViewById(R.id.ibUp).setOnClickListener(v -> move(KeyEvent.KEYCODE_DPAD_UP));
        findViewById(R.id.ibDown).setOnClickListener(v -> move(KeyEvent.KEYCODE_DPAD_DOWN));
        findViewById(R.id.ibRight).setOnClickListener(v -> move(KeyEvent.KEYCODE_DPAD_RIGHT));
        findViewById(R.id.ibLeft).setOnClickListener(v -> move(KeyEvent.KEYCODE_DPAD_LEFT));

        restartCanvas();
    }

    private boolean move(int keyCode) {
        boolean handled = true;
        final int magnitude = 8;

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                drawLine(0, -magnitude);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                drawLine(0, magnitude);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                drawLine(-magnitude, 0);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                drawLine(magnitude, 0);
                break;
            default:
                handled = false;
        }

        if (handled) {
            _ivBitmap.requestFocus();
        }

        return handled;
    }

    private void drawLine(int changesX, int changesY) {
        if (_paint == null) {
            init();
        }

        int newX = _currentX + changesX;
        int newY = _currentY + changesY;
        _canvas.drawLine(_currentX, _currentY, newX, newY, _paint);

        _currentX = newX;
        _currentY = newY;

        if (changesY != 0) {
            _tvY.setText(getString(R.string.y, _currentY));
        }
    }

    private void restartCanvas() {
        _canvas.drawColor(Color.LTGRAY);

        createPaint();

        _currentX = _ivBitmap.getWidth() / 2;
        _currentY = _ivBitmap.getHeight() / 2;
        _canvas.drawPoint(_currentX, _currentY, _paint);
        _tvY.setText(getString(R.string.y, _currentY));
    }

    private void createPaint() {
        Paint paint = new Paint();

        int color;
        int selectedColorId = _rgColor.getCheckedRadioButtonId();
        if (selectedColorId == R.id.rbRed) {
            color = Color.RED;
        } else if (selectedColorId == R.id.rbYellow) {
            color = Color.YELLOW;
        } else {
            color = Color.CYAN;
        }

        int thickness = Integer.parseInt(_spThickness.getSelectedItem().toString());

        paint.setColor(color);
        paint.setStrokeWidth(thickness);
        _paint = paint;
    }
}
