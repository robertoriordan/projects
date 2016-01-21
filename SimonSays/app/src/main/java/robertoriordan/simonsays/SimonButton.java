package robertoriordan.simonsays;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robert on 22/12/15.
 */
public class SimonButton extends View {

    private String colour = "RED";
    public SimonButton(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        if (colour.equals("RED")) {
            paint.setColor(Color.rgb(225, 0, 0));
        }
        else if (colour.equals("GREEN")) {
            paint.setColor(Color.rgb(0, 225, 0));
        }
        else if (colour.equals("BLUE")) {
            paint.setColor(Color.rgb(0, 0, 225));
        }
        else if (colour.equals("YELLOW")) {
            paint.setColor(Color.rgb(237, 234, 0));
        }

        canvas.drawPaint(paint);
    }

    public void setColour(String c) {
        colour = c;
    }



}
