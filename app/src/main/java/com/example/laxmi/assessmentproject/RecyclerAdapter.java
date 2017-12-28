package com.example.laxmi.assessmentproject;


        import android.content.ContentValues;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.Toast;

        import java.nio.BufferUnderflowException;
        import java.util.ArrayList;
        import java.util.Calendar;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    ArrayList<String> data1;
    ArrayList<Integer> data2;
    ArrayList<String> data3;
    ArrayList<Button> data4;
    ArrayList<String> data5;
    //String name[] = {"Ritu","Prajwala"};
    Context context;
    LayoutInflater lf;
    DBHelper assetHelper;
    SQLiteDatabase sd;


    public RecyclerAdapter(ArrayList<String> data1, ArrayList<Integer> data2,ArrayList<String> data3,ArrayList<String> data5,Context context) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3=data3;
        this.data4 = data4;
        this.data5 = data5;
        this.context = context;
        lf = LayoutInflater.from(context);

    }

    public RecyclerAdapter(Context context) {

        this.context = context;
        lf = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = lf.inflate(R.layout.layout_card_view,parent,false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(v);

        return rvh;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        holder.name.setText(data1.get(position));
        holder.name1.setText(data3.get(position));
        holder.name2.setText(data5.get(position));
        // holder.allocate.setText(data4.get(position));

        if(holder.name2.getText().equals("")){
            holder.allocate.setText("DE-ALLOCATED");
        }else{
            holder.allocate.setText("ALLOCATED");
            //Calendar c = Calendar.getInstance();
            //c.add(Calendar.MONTH,6);
        }

        holder.image.setImageResource(data2.get(position));
        // holder.name.setText(name[0]);

        holder.image.setOnClickListener(clickListener);
        holder.image.setTag(holder);




        holder.allocate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final int fID= Integer.parseInt(holder.name1.getText().toString());
                if (holder.allocate.getText().equals("DE-ALLOCATED")){
                    final DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    Intent i = new Intent(context,DisplayEmpList.class);
                                    i.putExtra("key",fID);
                                    context.startActivity(i);
                                    // Toast.makeText(context,"Allocate asset",Toast.LENGTH_SHORT).show();
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog.cancel();
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setMessage("Do you wish to allocate?").setPositiveButton("Yes",dialogListener).setNegativeButton("No",dialogListener).show();

                }else{
                    final DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final int sID= Integer.parseInt(holder.name1.getText().toString());
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:

                                    assetHelper= new DBHelper(context);
                                    sd = assetHelper.getWritableDatabase();

                                    ContentValues cvl1 = new ContentValues();

                                    cvl1.put(assetHelper.ASSET_ALLOC,"");

                                    long d =  sd.update(assetHelper.TABLE_ASSET, cvl1, assetHelper._ID+"=" + sID,null);

                                    Intent intent = new Intent(context,DashBoard.class);
                                    context.startActivity(intent);

                                    //write delete logic
                                    Toast.makeText(context,"Deallocate asset",Toast.LENGTH_SHORT).show();
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog.cancel();
                                    // Toast.makeText(context,"Allocate Asset",Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setMessage("Do you want to de-allocate asset?").setPositiveButton("Yes",dialogListener).setNegativeButton("No",dialogListener).show();

                }



            }
        });
    }
    View.OnClickListener clickListener = new View.OnClickListener(){
        public void onClick(View v){
            RecyclerViewHolder viewHolder = (RecyclerViewHolder)v.getTag();
            int position = viewHolder.getPosition();
            Toast.makeText(context,"This is position"+position,Toast.LENGTH_SHORT).show();
            Toast.makeText(context,"Allocated to:"+viewHolder.name2.getText(),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public int getItemCount() {
        return data1.size();
    }
}
