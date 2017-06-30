package com.demo.android.mydragger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by XiongRun on 2017/5/20.
 */

/**
 * 用Module提供Person实例
 */
@Module
public class PersonModule {

	//一个默认的
	@Provides
	Person providePerson(){
		return new Person();
	}

	//采用@Qualifier注解，表示我可以提供这种标识符的Person
	@Provides
	@Named("male")
	Person providePersonMale(){
		return new Person("male");
	}

	@Provides
	@Named("female")
	Person providePersonFemale(){
		return new Person("妹子");
	}

	@Provides
	@PersonQualifier
	Person providePersonByQualifier() {
		return new Person("qualifier sex");
	}
}
