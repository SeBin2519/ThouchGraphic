package kr.hs.emirim.sebin2519.touchgraphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.display.VirtualDisplay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    static final int LINE=1, RECT=2, CIRCLE=3;
    int chooseShape=CIRCLE;
    int r;
    DrawShape ds;//DrawShape을 저장하는, 참조하는 변수
    int startX, startY, stopX, stopY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//setContentView(new DrawShape(getApplicationContext()));
        ds=new DrawShape(getApplicationContext());
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear_view);//LinearLayout을 참조하는 참조변수
        linear.addView(ds);
    }

    public void drawShape(View v){ //핸들러 메소드
        switch (v.getId()){
            case R.id.but_line:
                chooseShape=LINE;
                break;
            case R.id.but_rect:
                chooseShape=RECT;
                break;
            case R.id.but_cir:
                chooseShape=CIRCLE;
                break;
        }
        ds.invalidate(); //ondraw() 다시 호출
    }

    class DrawShape extends View{//내부클래스
        DrawShape(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);//그림 그리기 메서드
            float cx = getWidth() / 2.0f; //점의 정중앙 좌표
            float cy = getWidth() / 2.0f; //점의 정중앙 좌표
            Paint paint = new Paint();
            paint.setStrokeWidth(3);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);

            switch (chooseShape) {
                case LINE:
                    canvas.drawLine(startX, startY,stopX ,stopY, paint);
                    break; //선분좌표
                case RECT:
                    paint.setColor(Color.MAGENTA);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(startX, startY,stopX ,stopY,paint); //1.사각형의 영역지정, 좌표를 가지고 설정(x,y,가로,세로,그리기 객체)
                    break;
                case CIRCLE:
                    r=(int)Math.sqrt(Math.pow((stopY-startY),2) + Math.pow((stopX-startX),2));//반지름 구하기
                    canvas.drawCircle(startX, startY, r, paint); //중심점, 반지름=> 피타고라스 정리
                    break;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            switch(event.getAction()){ //정수를 반한
                case MotionEvent.ACTION_DOWN:
                    startX=(int)event.getX(); //눌렀을때 x좌표 반환
                    startY=(int)event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP: //해제
                    stopX=(int)event.getX();//해제할때 x좌표 반환
                    stopY=(int)event.getY();
                    break;
            }
            invalidate();
            return true;// return super.onTouchEvent(event);
        }
    }
}
