package com.enduo.ndonline.appupdate;




import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.enduo.ndonline.R;

/**
 * 自定义版本更新Dialog弹出框
 * 
 * @author Administrator
 * 
 */
@SuppressLint("WrongViewCast") public class UpDataProgressDialog extends Dialog {

	public UpDataProgressDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	public UpDataProgressDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	public static class Builder {
		private Context context;
		private ProgressBar pb;
		private View cancle_updata;
		CancleProess l ;
		private TextView jingdu;
		
		public Builder(Context context) {
			this.context = context;
		}

		
		public void setCancleProessListiser(CancleProess l){
			this.l=l;
		}
		

		
		

		
		@SuppressLint("WrongViewCast") @SuppressWarnings("deprecation")
		public UpDataProgressDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final UpDataProgressDialog dialog = new UpDataProgressDialog(context, R.style.update_ialog);
			View layout = inflater.inflate(R.layout.updata_progerss, null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
			jingdu = (TextView) layout.findViewById(R.id.jingdu);
			pb = (ProgressBar) layout.findViewById(R.id.updata_pb);
			cancle_updata = layout.findViewById(R.id.cancle_updata);
			cancle_updata.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (l!=null) {
						l.cancle(dialog);
					}
					
				}
			});
				
				
			
			dialog.setContentView(layout);
			dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
			return dialog;
		}
		
		
		
		public void setProgressBar(int p){
			pb.setProgress(p);
		}
		
		public void setProgressText(int p){
			jingdu.setText(p+"%");
		}

	}
	
	
	
	public interface CancleProess {
		void cancle(Dialog dialog);
	}

}
