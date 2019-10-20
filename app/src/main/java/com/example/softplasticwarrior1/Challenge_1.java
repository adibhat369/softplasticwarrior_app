package com.example.softplasticwarrior1;


import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

public class Challenge_1 extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {

    private static int totalObjects = 0;
    Button donebutton;
    TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        //Initialise the view objects and set on touch listener for them

        setContentView(R.layout.challenge_1);
        totalObjects = 0;
        final ImageView itemimg = (ImageView) findViewById(R.id.item1);
        itemimg.setTag("Bin2");
        itemimg.setOnTouchListener(this);

        final ImageView item2 = (ImageView) findViewById(R.id.item2);
        item2.setTag("Bin1");
        item2.setOnTouchListener(this);

        final ImageView item3 = (ImageView) findViewById(R.id.item3);
        item3.setTag("Bin2");
        item3.setOnTouchListener(this);

        final ImageView item4 = (ImageView) findViewById(R.id.item4);
        item4.setTag("Bin2");
        item4.setOnTouchListener(this);

        final ImageView item5 = (ImageView) findViewById(R.id.item5);
        item5.setTag("Bin1");
        item5.setOnTouchListener(this);

        final ImageView item6 = (ImageView) findViewById(R.id.item6);
        item6.setTag("Bin2");
        item6.setOnTouchListener(this);

        final ImageView item7 = (ImageView) findViewById(R.id.item7);
        item7.setTag("Bin2");
        item7.setOnTouchListener(this);

        final ImageView item8 = (ImageView) findViewById(R.id.item8);
        item8.setTag("Bin1");
        item8.setOnTouchListener(this);

        final ImageView item9 = (ImageView) findViewById(R.id.item9);
        item9.setTag("Bin2");
        item9.setOnTouchListener(this);

        final ImageView item10 = (ImageView) findViewById(R.id.item10);
        item10.setTag("Bin1");
        item10.setOnTouchListener(this);

        errorMsg = (TextView) findViewById(R.id.errormsg);


        ImageView bin2 = (ImageView) findViewById(R.id.bin2);
        bin2.setTag("Bin2");
        bin2.setOnDragListener(this);
        ImageView bin1 = (ImageView) findViewById(R.id.bin1);
        bin1.setTag("Bin1");
        bin1.setOnDragListener(this);

        // Information popup
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(Challenge_1.this);
        //builder1.setMessage("Write your message here.");
        builder1.setCancelable(true);
        final View customLayout = getLayoutInflater().inflate(R.layout.popup, null);
        TextView popuptextview = (TextView) customLayout.findViewById(R.id.textpopup);
        popuptextview.setText("Drag the items to the correct bins");


        builder1.setView(customLayout);

        // Done popup and on challenge completed
        final AlertDialog alert11 = builder1.create();
        alert11.show();
        Button buttonGotit = (Button) customLayout.findViewById(R.id.button_gotit);
        buttonGotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert11.dismiss();
            }
        });
        donebutton = (Button) findViewById(R.id.donebutton);
        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("challenge1", true);
                editor.commit();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        System.out.println("Came inside drag");
        int action = event.getAction();
        // Handles each of the expected events
        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept data.
                    // v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    // Invalidate the view to force a redraw in the new tint
                    //  v.invalidate();
                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                // Applies a GRAY or any color tint to the View. Return true; the return value is ignored.
                //v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                // Re-sets the color tint to blue. Returns true; the return value is ignored.
                // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                //It will clear a color filter .
                // v.getBackground().clearColorFilter();
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                System.out.println("Came inside drop");

                ImageView droptarget = (ImageView) v;
                String droptarget_tag = (String) droptarget.getTag();

                ImageView dropped = (ImageView) event.getLocalState();
                String dropped_tag = (String) dropped.getTag();

                if ((droptarget_tag != null) && (droptarget_tag.equals(dropped_tag))) {
                    totalObjects = totalObjects + 1;
                    if (totalObjects == 10) {
                        donebutton.setVisibility(View.VISIBLE);
                    }
                    //Toast.makeText(this, "Great Job!!", Toast.LENGTH_SHORT).show();

                    errorMsg.setText("GREAT JOB!");
                    errorMsg.setTextColor(Color.GREEN);
                    MediaPlayer ring = MediaPlayer.create(Challenge_1.this, R.raw.cartooncowbell);
                    ring.start();

                    ClipData.Item item = event.getClipData().getItemAt(0);
                    // Gets the text data from the item.
                    String dragData = item.getText().toString();
                    // Displays a message containing the dragged data.
                    //Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
                    // Turns off any color tints
                    //v.getBackground().clearColorFilter();
                    // Invalidates the view to force a redraw
                    v.invalidate();

                    View vw = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) vw.getParent();
                    owner.removeView(vw); //remove the dragged view

                } else {
                    MediaPlayer ring = MediaPlayer.create(Challenge_1.this, R.raw.cartoonboing);
                    ring.start();
                    //Toast.makeText(this, "Wrong Bin!! ", Toast.LENGTH_LONG).show();

                    errorMsg.setText("WRONG BIN!");
                    errorMsg.setTextColor(Color.RED);


                }
                // Gets the item containing the dragged data
                //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
//                LinearLayout container = (LinearLayout) v;
//                container.addView(vw);//Add the dragged view
//                vw.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE
                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                // Turns off any color tinting
                //v.getBackground().clearColorFilter();
                // Invalidates the view to force a redraw
                v.invalidate();
                // Does a getResult(), and displays what happened.
                if (!event.getResult()) {
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();

                }

                // returns true; the value is ignored.
                return true;
            // An unknown action type was received.
            default:
                // Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;

    }

    // Override this method and handle the on touch events
    @Override
    @SuppressWarnings("deprecation")
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            ClipData data = ClipData.newPlainText("id", view.getResources().getResourceEntryName(view.getId()));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(data, shadowBuilder, view, 0);
            } else {
                view.startDrag(data, shadowBuilder, view, 0);
            }

            // view.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }
}

