package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rams.kabulmuncipal.Application.MyUIApplication;
import com.rams.kabulmuncipal.R;

import java.util.ArrayList;

import Model.NewsFeed;

public class News_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context contaxt;
    ArrayList<NewsFeed> list=new ArrayList<>();
    public News_Adapter(Context context, ArrayList<NewsFeed> list) {
        contaxt=context;
        this.list=list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_base,viewGroup,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        News_Adapter.MyViewholder myViewholder=(News_Adapter.MyViewholder)viewHolder;
        myViewholder.tv_user_name.setText(""+list.get(i).getName());
        myViewholder.tv_title.setText(""+list.get(i).getTitle());
        myViewholder.tv_detail.setText(""+list.get(i).getMessage());

        MyUIApplication.getInstance().LoadImage(R.mipmap.ic_launcher, 100, 100, contaxt, list.get(i).getPic(), myViewholder.iv_profile, false);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder
    {
        ImageView iv_profile;
        TextView tv_user_name,tv_title,tv_detail;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            iv_profile=itemView.findViewById(R.id.iv_profile);
            tv_user_name=itemView.findViewById(R.id.tv_user_name);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_detail=itemView.findViewById(R.id.tv_detail);
        }
    }
}
