package com.android.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.android.softspectproject.R;
import com.controller.SaleController;
import com.core.Cashier;
import com.utils.Constants;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

/**
 * LoginFallAnimationActivity is the Animation snow fall animation login Activity.
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class LoginFallAnimationActivity extends Activity {

	/**
	 * snow is the snow picture.
	 */
	private int[] snow = { R.drawable.snow1, R.drawable.snow1,
			R.drawable.snow2, R.drawable.snow2, };
	/**
	 * mDisplaySize is the Rect to display the animation.
	 */
	private Rect mDisplaySize = new Rect();
	/**
	 * saleController is the instance of the SaleController. 
	 */
	private SaleController saleController;
	/**
	 * mRootLayout is the animation root RelativeLayout.
	 */
	private RelativeLayout mRootLayout;
	/**
	 * mAllImageViews is the ArrayList of the ImageView.
	 */
	private ArrayList<View> mAllImageViews = new ArrayList<View>();
	/**
	 * mScale is the animation scale.
	 */
	private float mScale;
	/**
	 * btLogin is the login Button.
	 */
	private Button btLogin;
	/**
	 * btSignUp is the sign up Button.
	 */
	private Button btSignUp;
	/**
	 * cashierMap is the HashMap of Cashier.
	 */
	private Map<String, Cashier> cashierMap = new HashMap<String, Cashier>();
	/**
	 * txtUsername is the username EditText.
	 */
	private EditText txtUsername;
	/**
	 * txtPassword is the password EditText.
	 */
	private EditText txtPassword;
	/**
	 * username is the Cashier username.
	 */
	private String username;
	/**
	 * password is the Cashier password.
	 */
	private String password;
	/**
	 * t is the animation Timer.
	 */
	private Timer t = new Timer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_fall);
		saleController = SaleController.getInstance();

		

		Display display = getWindowManager().getDefaultDisplay();
		display.getRectSize(mDisplaySize);

		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		mScale = metrics.density;

		mRootLayout = (RelativeLayout) findViewById(R.id.main_layout);
		ImageView imgButton = (ImageView) findViewById(R.id.imgLoginFall);
		imgButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				t.schedule(new ExeTimerTask(), 0, 1500);

			}
		});

		btLogin = (Button) findViewById(R.id.btLoginFall);
		txtUsername = (EditText) findViewById(R.id.txtLoginFallName);
		txtPassword = (EditText) findViewById(R.id.txtLoginFallPassword);
		btSignUp = (Button) findViewById(R.id.btLoginFallSignUp);

		btSignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						CashierSignUpActivity.class);
				startActivity(intent);

			}
		});

		btLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				cashierMap.clear();
				for(Cashier c : saleController.getAllCashierFromCashierBook(getApplicationContext())){
					cashierMap.put(c.getUsername(), c);
				}

				username = txtUsername.getText().toString();
				password = txtPassword.getText().toString();

				
				Cashier cash = cashierMap.get(username);

				if (cash != null) {
					if (cash.getPassword().equals(password)) {
						Intent intent = new Intent(getApplicationContext(),MainActivity.class);
						intent.putExtra("cashier", cash);
						finish();
						
						startActivity(intent);
					} else {
						shake();
						Toast.makeText(getApplicationContext(),
								"Invalid username or password!", 0).show();
					}
				} else {
					shake();
					Toast.makeText(getApplicationContext(),
							"Invalid username or password!", 0).show();
				}

			}
		});
	}

	/**
	 * shake is the method to shake the element in the layout.
	 */
	public void shake() {
		Animation shake = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.shake);
		findViewById(R.id.imgLoginFall).startAnimation(shake);
		btLogin.startAnimation(shake);
		txtPassword.startAnimation(shake);
		txtUsername.startAnimation(shake);
		btSignUp.startAnimation(shake);
	}

	/**
	 * startAnimation is the method to start animation.
	 * @param aniView is animation view.
	 */
	public void startAnimation(final ImageView aniView) {

		aniView.setPivotX(aniView.getWidth() / 2);
		aniView.setPivotY(aniView.getHeight() / 2);

		long delay = new Random().nextInt(Constants.MAX_DELAY);

		final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
		animator.setDuration(Constants.ANIM_DURATION);
		animator.setInterpolator(new AccelerateInterpolator());
		animator.setStartDelay(delay);

		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			int angle = 50 + (int) (Math.random() * 101);
			int movex = new Random().nextInt(mDisplaySize.right);

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = ((Float) (animation.getAnimatedValue()))
						.floatValue();

				aniView.setRotation(angle * value);
				aniView.setTranslationX((movex - 40) * value);
				aniView.setTranslationY((mDisplaySize.bottom + (150 * mScale))
						* value);
			}
		});

		animator.start();
	}

	/**
	 * mHandler is the animation Handler.
	 */
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int viewId = new Random().nextInt(snow.length);
			Drawable d = getResources().getDrawable(snow[viewId]);
			LayoutInflater inflate = LayoutInflater
					.from(LoginFallAnimationActivity.this);
			final ImageView imageView = (ImageView) inflate.inflate(
					R.layout.ani_image_view, null);
			imageView.setImageDrawable(d);
			mRootLayout.addView(imageView);

			mAllImageViews.add(imageView);

			LayoutParams animationLayout = (LayoutParams) imageView
					.getLayoutParams();
			Display display = getWindowManager().getDefaultDisplay();
			int width = display.getWidth(); // deprecated
			int height = display.getHeight(); // deprecated
			animationLayout.setMargins((int) (Math.random() * width),
					(int) (-150 * mScale), 0, 0);

			animationLayout.width = (int) (60 * mScale);
			animationLayout.height = (int) (60 * mScale);

			startAnimation(imageView);
		}
	};

	/**
	 * ExeTimerTask is the customTimerTask
	 * @author thigirakaipon
	 *
	 */
	private class ExeTimerTask extends TimerTask {
		/**
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			mHandler.sendEmptyMessage(Constants.EMPTY_MESSAGE_WHAT);
		}
	}
	
	/**
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Quit Confirmation");
			alert.setMessage("Are you sure wnt to quit?");

			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  finish();
			  }
			});

			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			   
			  }
			});

			alert.show();
	    }
	    
		return super.onKeyDown(keyCode, event);
	}
}