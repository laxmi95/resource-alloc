package com.example.laxmi.assessmentproject;


        import android.support.v7.view.menu.MenuView;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

/**
 * Created by laxmi on 9/23/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    //  Assets a;
    TextView name, name1,name2;
    Button allocate;
    ImageView image;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        name1 = (TextView)itemView.findViewById(R.id.name1);
        name2 = (TextView)itemView.findViewById(R.id.allocTo1);
        image = (ImageView)itemView.findViewById(R.id.image);
        allocate = (Button)itemView.findViewById(R.id.alloc);
//        a = new Assets();

        //  int n=3;


//        if(a.getAllocatedTo().equals("NA")){
//            allocate.setText("Allocate");
//        }else{
//            allocate.setText("Deallocate");
//        }
    }
}


