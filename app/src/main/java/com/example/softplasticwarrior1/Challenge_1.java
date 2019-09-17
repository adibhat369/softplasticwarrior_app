package com.example.softplasticwarrior1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Px;
import androidx.appcompat.app.AppCompatActivity;

public class Challenge_1 extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.challenge_1);
        ImageView itemimg = (ImageView) findViewById(R.id.item1);
        itemimg.setTag("Bin2");
        itemimg.setOnLongClickListener(this);

        ImageView bin2 = (ImageView)findViewById(R.id.bin2);
        bin2.setTag("Bin2");
        bin2.setOnDragListener(this);
        ImageView bin1 = (ImageView) findViewById(R.id.bin1);
        bin1.setTag("Bin1");
        bin1.setOnDragListener(this);

//        Button startbutton = (Button) findViewById(R.id.startbutton);
//        startbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                LayoutInflater inflater = (LayoutInflater)
//                        getSystemService(LAYOUT_INFLATER_SERVICE);
//                View popupView = inflater.inflate(R.layout.popup, null);
//
//                // create the popup window
//                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                boolean focusable = true; // lets taps outside the popup also dismiss it
//                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//                // show the popup window
//                // which view you pass in doesn't matter, it is only used for the window tolken
//                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//                // dismiss the popup window when touched
//                popupView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        popupWindow.dismiss();
//                        return true;
//                    }
//                });

    //        }
      //  });
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(Challenge_1.this);
        //builder1.setMessage("Write your message here.");
        builder1.setCancelable(true);
        final View customLayout = getLayoutInflater().inflate(R.layout.popup, null);
        TextView popuptextview = (TextView)customLayout.findViewById(R.id.textpopup);
        popuptextview.setText("Drag items on the table to their bins");



        builder1.setView(customLayout);

//        builder1.setPositiveButton(
//                "READY TO PLAY",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });

//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
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

                if ((droptarget_tag != null) && (droptarget_tag.equals (dropped_tag))) {
                    Toast.makeText(this, "Great Job!!", Toast.LENGTH_SHORT).show();
                    MediaPlayer ring= MediaPlayer.create(Challenge_1.this,R.raw.cartooncowbell);
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
                    MediaPlayer ring= MediaPlayer.create(Challenge_1.this,R.raw.cartoonboing);
                    ring.start();
                    Toast.makeText(this, "Invalid drop ", Toast.LENGTH_SHORT).show();


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
                if (!event.getResult())
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();
                // returns true; the value is ignored.
                return true;
            // An unknown action type was received.
            default:
               // Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;

    }

    @Override
    public boolean onLongClick(View v) {
        System.out.println("Came inside longclick");
        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        // Instantiates the drag shadow builder.
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
        // Starts the drag
        v.startDrag(data        // data to be dragged
                , dragshadow   // drag shadow builder
                , v           // local data about the drag and drop operation
                , 0          // flags (not currently used, set to 0)
        );
        return true;
    }
}

