package com.Johana.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;



public class Lienzo extends View {

    //Path que se usa para ir pintando las lineas
    private Path drawPath;
    //Paint de dibujar y Paint de Canvas (es como un pincel)
    private static Paint drawPaint;
    private Paint canvasPaint;
    //Color del pincel
    private static int paintColor = 0xFF000000;
    //canvas (el lienzo)
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;


    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {

        //Configuración del area sobre la que pintar

        drawPath = new Path();
        drawPaint = new Paint();

        //Se manda a llamar el color del pincel
        drawPaint.setColor(paintColor);
        //Hace que los trazos no sean lineas rectas
        drawPaint.setAntiAlias(true);
        //EL ancho del pincel
        drawPaint.setStrokeWidth(30);
        //Tipo de estilo, es para pintar solo trazos
        drawPaint.setStyle(Paint.Style.STROKE);
        //Que sea pintura redondeada
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        //Objeto con el que se va a pintar, ANTI_ALIAS_FLAG permite pintar un poco mas difuminado
        canvasPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    //TOCAR SOBRE ÉL (MANEJAS)
    //Registra los touch de usuario
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            //pulsa con el dedo
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            //sigue el movimiento del dedo
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
                //Identifica cuento se levanta el dedo
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        //repintar
        invalidate();
        return true;

    }


    //Y CUANDO TOCO SOBRE ÉL, REPINTA SOBRE ÉL
    //Pinta la vista. Será llamado desde el OnTouchEvent
    @Override
    protected void onDraw(Canvas canvas) {
        //Crea un mapa de bits, para poder ir haciendo los trazos
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);

        canvas.drawPath(drawPath, drawPaint);
    }

    //PINTA SOBRE EL (CONSTRUYES)
    //Tamaño asignado a la vista
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //Creo el bitmap y puedo pintar
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    public void NuevoDibujo(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();

    }

    public void Borrar(){
        drawPaint.setColor(Color.WHITE);
    }

    public void Pintar(){
        drawPaint.setColor(Color.BLACK);
    }
}
