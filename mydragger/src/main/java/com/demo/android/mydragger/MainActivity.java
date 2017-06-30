package com.demo.android.mydragger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity {

	/*@Inject
	User mUser;
*/
	@Inject
	Person person;

	@Inject
	@Named("male")
	Person male;

	@Inject
	@Named("female")
	Person female;

	@Inject
	@PersonQualifier
	Person mPersonQualifier;
	private Button btn1,btn2,btn3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);

		//DaggerMainActivityComponent.create().inject(this);
		DaggerPersonComponent.builder().personModule(new PersonModule()).build().inject(this);
		Log.i("TAG",person.getSex());
		Log.i("TAG",mPersonQualifier.getSex());
		btn1.setText(person.getSex());
		btn2.setText(male.getSex());
		btn3.setText(female.getSex());




	}
}
