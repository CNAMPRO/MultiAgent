package com.example.multiagentclient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int CANVAS_WIDTH = 820;
    private static final int CANVAS_HEIGHT = 480;

    ConstraintLayout relativeLayout;
    Paint paint;
    View view;
    Path path2;
    Bitmap bitmap;
    Canvas canvas;
    Button button;
    float taille = 2;
    int rouge = 0;
    int vert = 0;
    int bleu = 0;
    int txPhero = 2;
    int vitesse = 10;


    /**
     * Set les variables à utiliser par le drawer
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        relativeLayout = (ConstraintLayout) findViewById(R.id.layout_main);
        button = (Button)findViewById(R.id.button);
        view = new SketchSheetView(MainActivity.this);
        paint = new Paint();
        path2 = new Path();
        relativeLayout.addView(view, new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,                ConstraintLayout.LayoutParams.MATCH_PARENT));

        paint.setDither(true);
        paint.setColor(Color.parseColor("#000000"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(2);

        /** Clean du Canvas //TODO Fix need to click on canvas to clean it*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path2.reset();
            }
        });


        Client client = new Client();
        client.run();
    }


    @Override
    /**
     * Déclenche les changements et récupère les nouvelles valeurs des champs d'input
     */
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        EditText sizeText = drawer.findViewById(R.id.itemSize);
        EditText rougeText = drawer.findViewById(R.id.redColor);
        EditText vertText = drawer.findViewById(R.id.greenColor);
        EditText bleuText = drawer.findViewById(R.id.blueColor);
        EditText txPheroText = drawer.findViewById(R.id.tauxPheromones);
        EditText vitesseText = drawer.findViewById(R.id.itemsSpeed);
        taille = Float.parseFloat(sizeText.getText().toString());
        rouge = Integer.parseInt(rougeText.getText().toString());
        vert = Integer.parseInt(vertText.getText().toString());
        bleu = Integer.parseInt(bleuText.getText().toString());
        txPhero = Integer.parseInt(txPheroText.getText().toString());
        vitesse = Integer.parseInt(vitesseText.getText().toString());
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class SketchSheetView extends View {
        Context myContext;
        public SketchSheetView(Context context) {

            super(context);
            myContext = context;
            bitmap = Bitmap.createBitmap(CANVAS_WIDTH, CANVAS_HEIGHT, Bitmap.Config.ARGB_4444);
            canvas = new Canvas(bitmap);
            this.setBackgroundColor(Color.WHITE);

        }

        private ArrayList<DrawingClass> DrawingClassArrayList = new ArrayList<DrawingClass>();

        public float xPos;
        public float yPos;
        public boolean nidPose = false;

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            if(!nidPose){
                xPos = event.getX();
                yPos = event.getY();
                nidPose = true;
            }
            DrawingClass pathWithPaint = new DrawingClass();
            canvas.drawCircle(event.getX(), event.getY(), 60, paint);
            canvas.drawPath(path2, paint);

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                path2.moveTo(event.getX(), event.getY());
                path2.lineTo(event.getX(), event.getY());
            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                path2.lineTo(event.getX(), event.getY());
                pathWithPaint.setPath(path2);
                pathWithPaint.setPaint(paint);
                DrawingClassArrayList.add(pathWithPaint);
            }
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int color = Color.argb(255,rouge, vert, bleu);
            paint.setColor(color);
            paint.setStrokeWidth(taille);


            if (DrawingClassArrayList.size() > 0) {

                canvas.drawPath(
                        DrawingClassArrayList.get(DrawingClassArrayList.size() - 1).getPath(),

                        DrawingClassArrayList.get(DrawingClassArrayList.size() - 1).getPaint());
            }
            if(nidPose){

                canvas.drawCircle(xPos, yPos, 60, paint);
            }

        }
    }

}


