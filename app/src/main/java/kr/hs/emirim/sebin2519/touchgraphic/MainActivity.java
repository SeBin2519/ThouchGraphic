package kr.hs.emirim.sebin2519.touchgraphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.display.VirtualDisplay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawShape(getApplicationContext()));
    }

    class DrawShape extends View{//내부클래스
        DrawShape(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);//그림 그리기 메서드
            float cx = getWidth() / 2.0f; //점의 정중앙 좌표
            float cy = getWidth() / 2.0f; //점의 정중앙 좌표
            Paint paint=new Paint();
            canvas.drawCircle(cx,cy,50,paint);
        }
    }
}
