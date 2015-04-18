package com.aurora.gears;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;

public class GameMain extends MainActivity {

    public void turn90(View startBtn){
        RotateAnimation rotate = new RotateAnimation(0 , 90, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(0);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        startBtn.startAnimation(rotate);
        switch(startBtn.getId()){
            case R.id.Gear1:
                DegreesGear1 = 90;
                break;
            case R.id.Gear2:
                DegreesGear2 = 90;
                break;
            case R.id.Gear3:
                DegreesGear3 = 90;
                break;
            case R.id.Gear4:
                DegreesGear4 = 90;
                break;
        }

    }

    public void turn180(View startBtn){
        RotateAnimation rotate = new RotateAnimation(0 , 180, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(0);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        startBtn.startAnimation(rotate);
        switch(startBtn.getId()){
            case R.id.Gear1:
                DegreesGear1 = 180;
                break;
            case R.id.Gear2:
                DegreesGear2 = 180;
                break;
            case R.id.Gear3:
                DegreesGear3 = 180;
                break;
            case R.id.Gear4:
                DegreesGear4 = 180;
                break;
        }

    }

    public void turn270(View startBtn){
        RotateAnimation rotate = new RotateAnimation(0 , 270, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(0);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        startBtn.startAnimation(rotate);
        switch(startBtn.getId()){
            case R.id.Gear1:
                DegreesGear1 = 270;
                break;
            case R.id.Gear2:
                DegreesGear2 = 270;
                break;
            case R.id.Gear3:
                DegreesGear3 = 270;
                break;
            case R.id.Gear4:
                DegreesGear4 = 270;
                break;
        }

    }

    public void turnLast(View v,int gearCount){
        final int GearCount = gearCount;
        final View getGear = v;
        getGear.setEnabled(false);
        if (DegreesGear1 == 360) {
            DegreesGear1 = 0;
        } else if (DegreesGear2 == 360) {
            DegreesGear2 = 0;
        } else if (DegreesGear3 == 360) {
            DegreesGear3 = 0;
        } else if (DegreesGear4 == 360) {
            DegreesGear4 = 0;
        }
        switch(getGear.getId()){
            case R.id.Gear1:
                DegreesGear = DegreesGear1;
                DegreesGear1 = DegreesGear1 +90;
                break;
            case R.id.Gear2:
                DegreesGear = DegreesGear2;
                DegreesGear2 = DegreesGear2 +90;
                break;
            case R.id.Gear3:
                DegreesGear = DegreesGear3;
                DegreesGear3 = DegreesGear3 +90;
                break;
            case R.id.Gear4:
                DegreesGear = DegreesGear4;
                DegreesGear4 = DegreesGear4 + 90;
                break;
        }
        RotateAnimation rotate = new RotateAnimation(DegreesGear , DegreesGear + 90, Animation.RELATIVE_TO_SELF, 0.5f ,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        rotate.setDuration(400);
        v.startAnimation(rotate);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch(GearCount){
                    case 1:
                        if (DegreesGear1 == 180) {
                            //I want to call this once
                            levelClearOneGear();
                        } else {
                            getGear.setEnabled(true);
                        }
                        break;
                    case 2:
                        if (DegreesGear1 == 180 && DegreesGear1 == DegreesGear2) {
                            //I want to call this once
                            levelClearTwoGear();
                        } else {
                            getGear.setEnabled(true);
                        }
                        break;
                    case 3:
                        if (DegreesGear1 == 180 && DegreesGear1 == DegreesGear2 && DegreesGear1 == DegreesGear3) {
                            //I want to call this once
                            levelClearThreeGear();
                        } else {
                            getGear.setEnabled(true);
                        }
                        break;
                    case 4:
                        if (DegreesGear1 == 180 && DegreesGear1 == DegreesGear2 && DegreesGear1 == DegreesGear3 && DegreesGear1 == DegreesGear4) {
                            //I want to call this once
                            levelClearFourGear();
                        } else {
                            getGear.setEnabled(true);
                        }
                        break;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void levelClearOneGear() {
        ImageButton Gear1 = (ImageButton) findViewById(R.id.Gear1);

        AnimationSet endRotate = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.endrotate);

        Gear1.startAnimation(endRotate);
        for (Animation a : endRotate.getAnimations())
            a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (currentLevel % 3 == 0) {
                    createAd();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (currentLevel % 3 == 0) {
                    if (interstitialAd.isLoaded()) {
                        Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                        startActivity(EndScreen);
                        finish();
                    } else {
                        Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                        startActivity(EndScreen);
                        finish();
                    }
                } else {
                    Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                    startActivity(EndScreen);
                    finish();
                }
            }
        });
    }

    public void levelClearTwoGear() {
        ImageButton Gear1 = (ImageButton) findViewById(R.id.Gear1);
        ImageButton Gear2 = (ImageButton) findViewById(R.id.Gear2);

        AnimationSet endRotate = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.endrotate);

        Gear1.startAnimation(endRotate);
        Gear2.startAnimation(endRotate);
        for (Animation a : endRotate.getAnimations())
            a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (currentLevel % 3 == 0) {
                    createAd();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (currentLevel % 3 == 0) {
                    if (interstitialAd.isLoaded()) {
                        Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                        startActivity(EndScreen);
                        finish();
                    } else {
                        Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                        startActivity(EndScreen);
                        finish();
                    }
                } else {
                    Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                    startActivity(EndScreen);
                    finish();
                }
            }
        });
    }

    public void levelClearThreeGear() {
        ImageButton Gear1 = (ImageButton) findViewById(R.id.Gear1);
        ImageButton Gear2 = (ImageButton) findViewById(R.id.Gear2);
        ImageButton Gear3 = (ImageButton) findViewById(R.id.Gear3);

        AnimationSet endRotate = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.endrotate);

        Gear1.startAnimation(endRotate);
        Gear2.startAnimation(endRotate);
        Gear3.startAnimation(endRotate);
        for (Animation a : endRotate.getAnimations())
            a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (currentLevel % 3 == 0) {
                    createAd();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (currentLevel % 3 == 0) {
                    if (interstitialAd.isLoaded()) {
                        Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                        startActivity(EndScreen);
                        finish();
                    } else {
                        Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                        startActivity(EndScreen);
                        finish();
                    }
                } else {
                    Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                    startActivity(EndScreen);
                    finish();
                }
            }
        });
    }

    public void levelClearFourGear() {
        ImageButton Gear1 = (ImageButton) findViewById(R.id.Gear1);
        ImageButton Gear2 = (ImageButton) findViewById(R.id.Gear2);
        ImageButton Gear3 = (ImageButton) findViewById(R.id.Gear3);
        ImageButton Gear4 = (ImageButton) findViewById(R.id.Gear4);

        AnimationSet endRotate = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.endrotate);

        Gear1.startAnimation(endRotate);
        Gear2.startAnimation(endRotate);
        Gear3.startAnimation(endRotate);
        Gear4.startAnimation(endRotate);
        for (Animation a : endRotate.getAnimations())
            a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (currentLevel % 3 == 0) {
                    createAd();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (currentLevel % 3 == 0) {
                    if (interstitialAd.isLoaded()) {
                        Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                        startActivity(EndScreen);
                        finish();
                    } else {
                        Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                        startActivity(EndScreen);
                        finish();
                    }
                } else {
                    Intent EndScreen = new Intent (GameMain.this, EndScreen.class);
                    startActivity(EndScreen);
                    finish();
                }
            }
        });
    }

    public void turn(View gear) {
        final View getGear = gear;
        gear.setEnabled(false);
        if (DegreesGear1 == 360) {
            DegreesGear1 = 0;
        } else if (DegreesGear2 == 360) {
            DegreesGear2 = 0;
        } else if (DegreesGear3 == 360) {
            DegreesGear3 = 0;
        } else if (DegreesGear4 == 360) {
            DegreesGear4 = 0;
        }
        switch (gear.getId()) {
            case R.id.Gear1:
                DegreesGear = DegreesGear1;
                DegreesGear1 = DegreesGear1 + 90;
                break;
            case R.id.Gear2:
                DegreesGear = DegreesGear2;
                DegreesGear2 = DegreesGear2 + 90;
                break;
            case R.id.Gear3:
                DegreesGear = DegreesGear3;
                DegreesGear3 = DegreesGear3 + 90;
                break;
            case R.id.Gear4:
                DegreesGear = DegreesGear4;
                DegreesGear4 = DegreesGear4 + 90;
                break;
        }
        RotateAnimation rotate = new RotateAnimation(DegreesGear, DegreesGear + 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(400);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        gear.startAnimation(rotate);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                getGear.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
