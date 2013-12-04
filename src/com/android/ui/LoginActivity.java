//package com.android.ui;
//
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import com.android.softspectproject.R;
//import com.database.CashierBookDataManager;
//import com.utils.Constants;
//import com.utils.FallAnimationActivity;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.animation.ValueAnimator;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.res.AssetManager;
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.util.DisplayMetrics;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//import android.widget.RelativeLayout.LayoutParams;
//import android.view.View.OnClickListener;
//import android.view.animation.AccelerateInterpolator;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.view.animation.LayoutAnimationController;
//public class LoginActivity extends Activity {
//	private Button btLogin;
//	private Button btSignUp;
//	private String FILENAME="cashier.txt";
//	private String content = "1,Sikarin Larnamwong,b5510546174,sikarin1993\n2,Krittayout Techasombooranakit,b5510545976,benzsk130";
//	private CashierBookDataManager fileDataManager;
//	private FileInputStream fileInputStream;
//	private FileOutputStream fileOutputStream;
//	
//	private EditText txtUsername;
//	private EditText txtPassword;
//	
//	private String username;
//	private String password;
//	
//	//Animation snow
//	private int[] LEAVES = { 
//			R.drawable.leaf_green, 
//			R.drawable.leaf_red,
//			R.drawable.leaf_yellow,
//			R.drawable.leaf_other,
//		};
//
//	private Rect mDisplaySize = new Rect();
//	
//	private RelativeLayout mRootLayout;
//	private ArrayList<View> mAllImageViews = new ArrayList<View>();
//	
//	private float mScale;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        
//        Display display = getWindowManager().getDefaultDisplay(); 
//		display.getRectSize(mDisplaySize);
//		
//		DisplayMetrics metrics = new DisplayMetrics();
//		display.getMetrics(metrics);
//		mScale = metrics.density;
//
//		mRootLayout = (RelativeLayout) findViewById(R.id.main_layout);
//
//		new Timer().schedule(new ExeTimerTask(), 0, 5000);
//        
//        
//        
//        btLogin = (Button)findViewById(R.id.btLogin);
//        fileDataManager = CashierBookDataManager.getInstance();
//        txtUsername = (EditText)findViewById(R.id.txtUsername);
//		txtPassword = (EditText)findViewById(R.id.txtLoginPassword);
//		btSignUp = (Button)findViewById(R.id.btLoginSignUp);
//        btLogin.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				
//				
//			username = txtUsername.getText().toString();
//			password = txtPassword.getText().toString();
//			
//			if(username.equals("1") && password.equals("1")){
//				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//				startActivity(intent);
//			}
//			else {
//				Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
//		        findViewById(R.id.imgLogin).startAnimation(shake);
//		        btLogin.startAnimation(shake);
//		        txtPassword.startAnimation(shake);
//		        txtUsername.startAnimation(shake);
//		        btSignUp.startAnimation(shake);
//		        Toast.makeText(getApplicationContext(), "Wrong Password",0).show();
//			}
//				
//				
//				
//				
//			}
//		});
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.login, menu);
//        return true;
//    }
//    
//    
//    public void startAnimation(final ImageView aniView) {
//
//		aniView.setPivotX(aniView.getWidth()/2);
//		aniView.setPivotY(aniView.getHeight()/2);
//
//		long delay = new Random().nextInt(Constants.MAX_DELAY);
//
//		final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
//		animator.setDuration(Constants.ANIM_DURATION);
//		animator.setInterpolator(new AccelerateInterpolator());
//		animator.setStartDelay(delay);
//
//		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//			
//			int angle = 50 + (int)(Math.random() * 101);
//			int movex = new Random().nextInt(mDisplaySize.right);
//			
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				float value = ((Float) (animation.getAnimatedValue())).floatValue();
//				
//				aniView.setRotation(angle*value);
//				aniView.setTranslationX((movex-40)*value);
//				aniView.setTranslationY((mDisplaySize.bottom + (150*mScale))*value);
//			}
//		});
//
//		animator.start();
//	}
//	
//	private Handler mHandler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			int viewId = new Random().nextInt(LEAVES.length);
//			Drawable d = getResources().getDrawable(LEAVES[viewId]);
//			LayoutInflater inflate = LayoutInflater.from(LoginActivity.this);
//			ImageView imageView = (ImageView) inflate.inflate(R.layout.ani_image_view, null);
//			imageView.setImageDrawable(d);
//			mRootLayout.addView(imageView);
//			
//			mAllImageViews.add(imageView);			
//			
//			LayoutParams animationLayout = (LayoutParams) imageView.getLayoutParams();
//			animationLayout.setMargins(0, (int)(-150*mScale), 0, 0);
//			animationLayout.width = (int) (60*mScale);
//			animationLayout.height = (int) (60*mScale);
//			
//			startAnimation(imageView);
//		}
//	};
//	
//	private class ExeTimerTask extends TimerTask {
//		@Override
//		public void run() {
//			// we don't really use the message 'what' but we have to specify something.
//			mHandler.sendEmptyMessage(Constants.EMPTY_MESSAGE_WHAT);
//		}
//	}
//    
//}
