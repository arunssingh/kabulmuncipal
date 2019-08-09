package Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rams.kabulmuncipal.Application.MyUIApplication;
import com.rams.kabulmuncipal.Complaintdetail_Acitivity;
import com.rams.kabulmuncipal.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import Model.Model_Complaint.ModelComplaint;
import Model.NewsFeed;

public class Complaint_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context contaxt;
    ArrayList<ModelComplaint> list=new ArrayList<>();
    public Complaint_Adapter(Context context, ArrayList<ModelComplaint> list) {
        contaxt=context;
        this.list=list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.issue_report,viewGroup,false);
        return new MyViewholder(view);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        Complaint_Adapter.MyViewholder myViewholder=(Complaint_Adapter.MyViewholder)viewHolder;
        myViewholder.tv_value.setText(""+list.get(i).getIds());



        String string1 = list.get(i).getCreatedAt().replace("Z","");

        LocalDateTime localDateTime = LocalDateTime.parse(string1);
        myViewholder.tv_date.setText(""+localDateTime.getDayOfMonth()+"/"+localDateTime.getMonthValue()+"/"+localDateTime.getYear());
        myViewholder.rlmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent as = new Intent(contaxt, Complaintdetail_Acitivity.class);
                as.putExtra("obj",list.get(i));
                contaxt.startActivity(as);

            }
        });
       // myViewholder.tv_title.setText(""+list.get(i).getTitle());
        //myViewholder.tv_detail.setText(""+list.get(i).getMessage());

       // MyUIApplication.getInstance().LoadImage(R.mipmap.ic_launcher, 100, 100, contaxt, list.get(i).getPic(), myViewholder.iv_profile, false);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder
    {
        ImageView iv_profile;
        TextView tv_value,tv_title,tv_detail,tv_date;
        RelativeLayout rlmain;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            //iv_profile=itemView.findViewById(R.id.iv_profile);
            tv_value=itemView.findViewById(R.id.tv_value);
            rlmain=itemView.findViewById(R.id.rlmain);
            tv_date=itemView.findViewById(R.id.tv_date);
           // tv_title=itemView.findViewById(R.id.tv_title);
           // tv_detail=itemView.findViewById(R.id.tv_detail);
        }
    }
}
