# ViewPagerEx
ViewPager with some extra features:
* enable/disable swipe gesture
* animation interpolator
* animation time
* fixed WRAP_CONTENT of height

###Repo
```
repositories {
	maven {
		url "https://jitpack.io"
	}
}
```

###Dependences
```
dependencies {
	compile 'com.github.Hivedi:QueryBuilder:1.0.0'
}
```

###Sample code

In layout:
```xml
<com.hivedi.widget.ViewPagerEx
	android:id="@+id/pager"
	android:layout_width="match_parent"
	android:layout_height="match_parent"/>
```

In activity:
```java
pager = (ViewPagerEx) findViewById(R.id.pager);
pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
	@Override
	public Fragment getItem(int position) {
		return MyFragment.newInstance(position);
	}
	@Override
	public int getCount() {
		return 3;
	}
});

// ...
// animation time
pager.setScrollAnimTime(500);
// interpolator
pager.setScrollInterpolator(new BounceInterpolator());
// disable gests
pager.deactivate();
// enable
pager.activate();
```