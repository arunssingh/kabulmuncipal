package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rams.kabulmuncipal.About_us;
import com.rams.kabulmuncipal.Application.MyUIApplication;
import com.rams.kabulmuncipal.Devlopment_Plan_dynamic;
import com.rams.kabulmuncipal.R;

import java.util.ArrayList;

import Model.InformationModel;
import Model.NewsFeed;
import Utils.Logger1;

public class Information_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context contaxt;
    ArrayList<InformationModel> list=new ArrayList<>();
    public Information_Adapter(Context context, ArrayList<InformationModel> list) {
        contaxt=context;
        this.list=list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_information,viewGroup,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        Information_Adapter.MyViewholder myViewholder=(Information_Adapter.MyViewholder)viewHolder;
            MyUIApplication.getInstance().LoadImage(R.mipmap.ic_launcher, 100, 100, contaxt, list.get(i).getIcon(), myViewholder.iv_icon, false);
            String []name= list.get(i).getName().split(" ");
            if(name.length==2) {
                myViewholder.tv1.setText("" + name[0]);
                myViewholder.tv2.setText("" + name[1]);
            }
            else if(name.length==3){
                myViewholder.tv1.setText("" + name[0]+" "+name[1]);
                myViewholder.tv2.setText("" + name[2]);
            }
            else if(name.length==4){
                myViewholder.tv1.setText("" + name[0]+" "+name[1]);
                myViewholder.tv2.setText("" + name[2]+" "+name[3]);
            }
            else
            {
                myViewholder.tv1.setText("" + list.get(i).getName());
                myViewholder.tv2.setText("" + "");
            }



        myViewholder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(contaxt, Devlopment_Plan_dynamic.class);
                    intent.putExtra("name", list.get(i).getName());
                    intent.putExtra("id", list.get(i).getId());
                    contaxt.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {

            return list.size();

    }

    public class MyViewholder extends RecyclerView.ViewHolder
    {
        ImageView iv_icon;
        TextView tv1,tv2;
        LinearLayout ll_main;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            iv_icon=itemView.findViewById(R.id.iv_icon);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            ll_main=itemView.findViewById(R.id.ll_main);

        }
    }
}
