package com.demo.android.myrxjavaretrofit.acticty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.demo.android.myrxjavaretrofit.R;
import com.demo.android.myrxjavaretrofit.entity.MovieEntity;
import com.demo.android.myrxjavaretrofit.http.HttpMethod;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.click_me_BN)
	Button clickMeBN;
	@BindView(R.id.result_TV)
	TextView resultTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		//getMovie();
		new Thread(new Runnable() {
			@Override
			public void run() {
				testTime();
				testTime1();
			}
		}).start();


	}

	private void testTime1() {
		long start = System.currentTimeMillis();
		int sum = 0;
		int n =10000000;
		sum= (1+n)*n/2;
		long end = System.currentTimeMillis();
		System.out.println("time"+start);
		System.out.println("time"+end );
		System.out.println("花费了"+(end - start));
	}

	private void testTime() {
		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 1; i< 10000000;i++){
			sum = sum+i;
		}
		long end = System.currentTimeMillis();

		System.out.println("time"+start);
		System.out.println("time"+end );
		System.out.println("花费了"+(end - start));

	}


	private void getM(){
		HttpMethod.getInstance().getM(new Subscriber<MovieEntity>() {
			@Override
			public void onSubscribe(Subscription s) {

			}

			@Override
			public void onNext(MovieEntity movieEntity) {

			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onComplete() {

			}
		},20,8);
	}

	private void getMovie() {
		HttpMethod.getInstance().getTopMovie(new Observer<MovieEntity>() {
			Disposable d;
			@Override
			public void onSubscribe(Disposable d) {
				this.d = d;
			}

			@Override
			public void onNext(MovieEntity value) {
				System.out.println("value"+value.getSubjects().size());
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onComplete() {

			}
		},0,10);

	}


}
