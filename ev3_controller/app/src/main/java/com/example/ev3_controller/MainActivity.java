package com.example.ev3_controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Configuration;
import android.os.Bundle;
import com.example.ev3_controller.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EV3Service mref_ev3;

    public EV3Service getEV3Service() {
        return mref_ev3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mref_ev3 = new EV3Service(MainActivity.this);

        // Set up the ViewPager2 and the adapter
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // Set up the BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_connection:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_move:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_sound:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_sensor:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        });

        // Sync the ViewPager2 with the BottomNavigationView
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Handle the configuration changes here if needed
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ConnectionFragment();
                case 1:
                    return new MoveFragment();
                case 2:
                    return new SoundFragment();
                case 3:
                    return new SensorFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}