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
import com.rams.kabulmuncipal.Kabulcity_Acitivity;
import com.rams.kabulmuncipal.R;

import java.util.ArrayList;
import java.util.List;

import Model.Develop_Model.Childs;
import Model.NewsFeed;

public class Develop_Adapter_Child extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context contaxt;
    List<Childs> list=new ArrayList<>();
    public Develop_Adapter_Child(Context context, List<Childs> list) {
        contaxt=context;
        this.list=list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_childplan,viewGroup,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        Develop_Adapter_Child.MyViewholder myViewholder=(Develop_Adapter_Child.MyViewholder)viewHolder;
        myViewholder.tvname.setText(""+list.get(i).getPlanName());
        myViewholder.ll_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(contaxt, About_us.class);
                is.putExtra("name",list.get(i).getPlanName());
                is.putExtra("desc",list.get(i).getDescription());
                is.putExtra("file",list.get(i).getFile());
                contaxt.startActivity(is);
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder
    {
        ImageView iv_profile;
        TextView tvname,tv_title,tv_detail;
        LinearLayout ll_linear;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            tvname=itemView.findViewById(R.id.tvname);
            ll_linear=itemView.findViewById(R.id.ll_linear);

        }
    }
}
