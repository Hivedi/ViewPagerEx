package com.hivedi.viewpagerexexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.hivedi.widget.ViewPagerEx;

public class MainActivity extends AppCompatActivity {

    ViewPagerEx pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    default:
                    case 0: pager.setScrollInterpolator(new LinearInterpolator()); break;
                    case 1: pager.setScrollInterpolator(new AccelerateInterpolator()); break;
                    case 2: pager.setScrollInterpolator(new AccelerateDecelerateInterpolator()); break;
                    case 3: pager.setScrollInterpolator(new DecelerateInterpolator()); break;
                    case 4: pager.setScrollInterpolator(new BounceInterpolator()); break;
                    case 5: pager.setScrollInterpolator(new AnticipateInterpolator()); break;
                    case 6: pager.setScrollInterpolator(new AnticipateOvershootInterpolator()); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    default:
                    case 0: pager.setScrollAnimTime(250); break;
                    case 1: pager.setScrollAnimTime(500); break;
                    case 2: pager.setScrollAnimTime(1000); break;
                    case 3: pager.setScrollAnimTime(1500); break;
                    case 4: pager.setScrollAnimTime(2000); break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pager = (ViewPagerEx) findViewById(R.id.pager);
        //pager.setScrollAnimTime(500);
       // pager.setScrollInterpolator(new BounceInterpolator());
        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return Frag.newInstance(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    public static class Frag extends Fragment {

        public static Frag newInstance(int c) {
            return new Frag().setColor(c);
        }

        private int color;

        public Frag() {}

        public Frag setColor(int c) {
            switch(c) {
                default:
                case 0: color = 0xFFFF0000; break;
                case 1: color = 0xFFFFFF00; break;
                case 2: color = 0xFFFF00FF; break;
            }
            return this;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View res = inflater.inflate(R.layout.frag, container, false);

            res.setBackgroundColor(color);

            return res;
        }
    }
}
