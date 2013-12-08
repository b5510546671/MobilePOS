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
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
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

public class LoginFallAnimationActivity extends Activity {

	private int[] LEAVES = { R.drawable.snow1, R.drawable.snow1,
			R.drawable.snow2, R.drawable.snow2, };

	private Rect mDisplaySize = new Rect();
	private SaleController saleController;
	private RelativeLayout mRootLayout;
	private ArrayList<View> mAllImageViews = new ArrayList<View>();

	private float mScale;

	private Button btLogin;
	private Button btSignUp;
	private Map<String, Cashier> cashierMap = new HashMap<String, Cashier>();

	private EditText txtUsername;
	private EditText txtPassword;

	private String username;
	private String password;

	private Timer t = new Timer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_fall);
		saleController = SaleController.getInstance();

		// TODO delete after cashier database finished
		Cashier c = new Cashier(1, "Sikarin", "1", "1");
		cashierMap.put(c.getUsername(), c);

		// TODO getCashier from the database
		//saleController.getAllCashier(getApplicationContext());

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

				username = txtUsername.getText().toString();
				password = txtPassword.getText().toString();

				
				Cashier cash = cashierMap.get(username);

				if (cash != null) {
					if (cash.getPassword().equals(password)) {
						Intent intent = new Intent(getApplicationContext(),MainActivity.class);
						intent.putExtra("cashier", cash);
						
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

	public void shake() {
		Animation shake = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.shake);
		findViewById(R.id.imgLoginFall).startAnimation(shake);
		btLogin.startAnimation(shake);
		txtPassword.startAnimation(shake);
		txtUsername.startAnimation(shake);
		btSignUp.startAnimation(shake);
	}

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

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int viewId = new Random().nextInt(LEAVES.length);
			Drawable d = getResources().getDrawable(LEAVES[viewId]);
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

	private class ExeTimerTask extends TimerTask {
		@Override
		public void run() {
			mHandler.sendEmptyMessage(Constants.EMPTY_MESSAGE_WHAT);
		}
	}
}