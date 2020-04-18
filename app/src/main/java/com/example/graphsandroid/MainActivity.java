package com.example.graphsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GraphView graph;
    Canvas canvas;
    Paint paint;
    ArrayList<DataPoint> dataPoints;
    ArrayList<Point> canvasPoints;
    private static final String TAG = "MainActivity";
    int counter = 0;
    LineGraphSeries<DataPoint> series;
    float X_point = 0, Y_point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DemoView(this));

        /*canvas = new Canvas();
        dataPoints = new ArrayList<>();
        canvasPoints = new ArrayList<>();
        paint = new Paint();
        paint.setStrokeWidth(4f);
        //  paint.setColor(Color.BLACK);
        graph = findViewById(R.id.graphView);
        Viewport viewport = graph.getViewport();
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalable(true);
        viewport.setMaxX(150);
        viewport.setMinX(-150);
        viewport.setMaxY(150);
        viewport.setMinY(0);

        series = new LineGraphSeries<>();
        for (int x = -150; x <= 150; x++) {
            for (int y = 0; y <= 150; y++) {
                DataPoint dataPoint = new DataPoint(x, y);
                series.appendData(dataPoint, false, 100000);
            }
        }
        graph.addSeries(series);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(MainActivity.this, "Datapoints are " + dataPoint.getX()
                        + " " + dataPoint.getY(), Toast.LENGTH_LONG).show();

                DataPoint dataPoint1 = (DataPoint) dataPoint;
                int x = (int) dataPoint1.getX();
                int y = (int) dataPoint1.getY();
                dataPoints.add(dataPoint1);
                canvasPoints.add(new Point(x, y));
                Log.d(TAG, "onTap: " + dataPoints.size());

                for (int i = 0; i < canvasPoints.size(); i++) {
                    Point point = canvasPoints.get(i);
                    canvas.drawCircle(point.x, point.y, 10, paint);


                    // Draw line with next point (if it exists)
                    if (i + 1 < canvasPoints.size()) {
                        Point next = canvasPoints.get(i + 1);
                        canvas.drawLine(point.x, point.y, next.x, next.y, paint);
                    }
                }
            }
        });*/
    }

    private class DemoView extends View {
        public DemoView(Context context){
            super(context);
        }

        @Override protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);

            series = new LineGraphSeries<>();
            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                @Override
                public void onTap(Series series, DataPointInterface dataPoint) {
                    Toast.makeText(MainActivity.this,"Datapoints are "+dataPoint.getX()
                            +" "+dataPoint.getY(),Toast.LENGTH_LONG).show();

                    DataPoint dataPoint1 = (DataPoint) dataPoint;
                    int x = (int) dataPoint1.getX();
                    int y = (int) dataPoint1.getY();
                    dataPoints.add(dataPoint1);
                    canvasPoints.add(new Point(x,y));
                    Log.d(TAG, "onTap: "+dataPoints.size());

                    for(int i = 0;i < canvasPoints.size();i++){
                        final Canvas canvas1 = canvas;
                        Point point = canvasPoints.get(i);
                        canvas1.drawCircle(point.x,point.y,10,paint);


                        // Draw line with next point (if it exists)
                        if (i + 1 < canvasPoints.size()) {
                            Point next = canvasPoints.get(i + 1);
                            canvas.drawLine(point.x, point.y, next.x, next.y, paint);
                        }
                    }



                }
            });
        }

        @Override
        public boolean onTouchEvent(final MotionEvent event) {

            X_point = 0;
            Y_point = 0;

            Thread CoordinatesThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    float x = event.getX();
                    float y = event.getX();

                    Log.e("coordinates", x + ":" + y);

                    /*// if its the same point again don't display it.
                    if (x != X_point && y != Y_point) {
                        Log.e("coordinates", X_point + ":" + Y_point);

                        X_point = event.getX();
                        Y_point = event.getY();
                    }
*/
                    try {// take a gap for 5000 milisec before displaying new coordinates
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            CoordinatesThread.start();

            return true;
        }
    }
}
