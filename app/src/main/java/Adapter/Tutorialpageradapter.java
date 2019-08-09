package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.rams.kabulmuncipal.Language_Acitivity;
import com.rams.kabulmuncipal.MainActivity;
import com.rams.kabulmuncipal.R;

import java.util.ArrayList;

import Utils.StaticDataHelper;


public class Tutorialpageradapter extends PagerAdapter {


	// Declare Variables
	Activity context;

	ViewPager pager;
	LayoutInflater inflater;
	boolean is_first;
	ArrayList<Integer> list=new ArrayList<Integer>();
	//int pos=0;
	//	Timer timer;
	public Tutorialpageradapter(Activity context, ArrayList<Integer> list1, ViewPager pager, boolean as) {
		this.context = context;
		this.pager=pager;
		//	timer=new Timer();
		list=list1;
		is_first=as;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((RelativeLayout) arg1);
	}
	@Override
	public Object instantiateItem(final ViewGroup container, final int position) {
		// TODO Auto-generated method stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View itemView = inflater.inflate(R.layout.tutorial_list_item, container,
				false);
		final RelativeLayout rl=(RelativeLayout) itemView.findViewById(R.id.rl_dynmic);
		final RelativeLayout rls=(RelativeLayout) itemView.findViewById(R.id.rl_dynmic_s);
		final ImageView imageView1=(ImageView) itemView.findViewById(R.id.imageView1);
		final Button btn_skip=(Button) itemView.findViewById(R.id.btn_skip);
		final Button btn_skip_s=(Button) itemView.findViewById(R.id.btn_skip_s);
		final Button btn_next=(Button) itemView.findViewById(R.id.btn_next);

		final TextView tv_name=(TextView) itemView.findViewById(R.id.tv_name);
		final RelativeLayout layout=(RelativeLayout) itemView.findViewById(R.id.layout_s);



        	
        	if (position==0) {
    			btn_skip_s.setVisibility(View.GONE);

    			//tv_name.setText("Enjoy amazing dialogue by us in yedub library");
    		}
    		else if (position==1) {
    			btn_skip_s.setVisibility(View.GONE);

    			//tv_name.setText("Customize your message in celeb voice.");
    		}
    		else
    		{
				btn_next.setVisibility(View.GONE);
    			btn_skip_s.setVisibility(View.VISIBLE);
    			//tv_name.setText("Share & chat your selfie video messages.");
    		}


		//fonts.setfont_regular(layout, context);
		//	pos=position;
		imageView1.setImageResource(list.get(position));
	/*	if (position==2) {
			btn_skip.setVisibility(View.VISIBLE);
		}
		else {
			btn_skip.setVisibility(View.GONE);
		}
*/
	
		context.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				container.addView(itemView);
			}
		});


		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int a=position+1;
				pager.setCurrentItem(a);

			}
		});

		btn_skip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StaticDataHelper.setBooleanInPreferences(context,StaticDataHelper.isUserLogin,true);
				context.startActivity(new Intent(context, MainActivity.class));
				context.finish();


				/*	if(timer!=null)
				{
					timer.cancel();
				}*/
			}
		});
		
		btn_skip_s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StaticDataHelper.setBooleanInPreferences(context,StaticDataHelper.isUserLogin,true);
				context.startActivity(new Intent(context, MainActivity.class));
				context.finish();
			}
		});

		/*	timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				pager.setCurrentItem(pos++);
			}
		}, 0, 1500);*/

		return itemView;
	}

	@Override
	public void destroyItem(final ViewGroup container, int position, final Object object) {
		context.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				container.removeView((RelativeLayout) object);
			}
		});


	}
}
