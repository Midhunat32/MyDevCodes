package com.wisilica.androdev.ui.activity.JavaTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.wisilica.androdev.R;

public class Animal extends AppCompatActivity {

    int ANIM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_test);


        Animal animal = new Dog();
        animal.sound();
        Dog dog = new Dog();
        dog.sound();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public  void sound(){
        System.out.println("SOUND>>>>>>");
    }
}
