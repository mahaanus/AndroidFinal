package com.apackage.yvyor.androidfinal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class PlayActivity extends AppCompatActivity {
    DatabaseHelper sqlBase;
    //grid
    TextView tb1, tb2, tb3, tb4, tb5, tb6, tb7, tb8;
    TextView tb9, tb10, tb11, tb12, tb13, tb14, tb15, tb16;

    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8;
    ImageView iv9, iv10, iv11, iv12, iv13, iv14, iv15, iv16;
    //everything else
    TextView timerView;
    TextView scoreView;
    TextView numberView;
    ImageView stateImage;
    //values
    int[] boxValues = new int[16];
    ArrayList<Integer> numberList = new ArrayList<Integer>();

    String username;
    int nextNumber = 1;
    int difficulty = 3;
    int difficultyTracker = 0;
    int playerScore = 0;
    long startTime = 0;
    boolean hard=true;
    boolean stopClock=true;
    boolean stopOver = true;
    int startClock;
    int backClock = difficulty+2;

    protected void getUsername()
    {
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username").toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initializeUI();
        generateGrid(difficulty);
        showGrid(true);
        InitiateClock();
        getUsername();
        Toast.makeText(PlayActivity.this,("Hello " + username), Toast.LENGTH_SHORT).show();
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            //miliseconds into seconds
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            //visual output
            timerView.setText(String.format("%d:%02d", minutes, seconds));

            if(seconds>(difficulty+2) && stopClock==true)
            {
                timerView.setTextColor(Color.GREEN);
                InitiateClock();
                showGrid(false);
                stopClock=false;

            }
            if(stopClock==false)
            {
                interactiveGrid();
            }

            if(seconds>(difficulty+5) && stopClock==false)
            {
                gameOver();
            }
            //tView2.setText(Integer.toString(gTime-seconds));
            //if(seconds>gTime) gTime+=12;
            timerHandler.postDelayed(this, 500);
        }
    };

    protected void InitiateClock()
    {
        timerView = findViewById(R.id.timer_TextView);
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    //if nextNumber>difficulty start a new level
    protected void checkStatus()
    {
        if(difficultyTracker+1>=difficulty)
        {
            playerScore+=difficulty;
            difficulty++;
            //nextNumber=1;

            if(difficulty>16)
            {
                gameOver();
            }
            generateGrid(difficulty);
            showGrid(true);
            difficultyTracker=0;
            nextNumber = numberList.get(difficultyTracker);
            numberView.setText(Integer.toString(nextNumber));
            scoreView.setText(Integer.toString(playerScore));
        }
        else
        {
            difficultyTracker++;
            nextNumber = numberList.get(difficultyTracker);
            numberView.setText(Integer.toString(nextNumber));
        };
    }

    protected void initializeUI()
    {
        scoreView = findViewById(R.id.score_TextView);
        scoreView.setText("0");
        numberView = findViewById(R.id.nextNumber_TextView);
        numberView.setText(Integer.toString(nextNumber));
    }

    protected void showGrid(boolean show)
    {
        if(show==true) {
            tb1.setText(Integer.toString(boxValues[0]));
            tb2.setText(Integer.toString(boxValues[1]));
            tb3.setText(Integer.toString(boxValues[2]));
            tb4.setText(Integer.toString(boxValues[3]));
            tb5.setText(Integer.toString(boxValues[4]));
            tb6.setText(Integer.toString(boxValues[5]));
            tb7.setText(Integer.toString(boxValues[6]));
            tb8.setText(Integer.toString(boxValues[7]));
            tb9.setText(Integer.toString(boxValues[8]));
            tb10.setText(Integer.toString(boxValues[9]));
            tb11.setText(Integer.toString(boxValues[10]));
            tb12.setText(Integer.toString(boxValues[11]));
            tb13.setText(Integer.toString(boxValues[12]));
            tb14.setText(Integer.toString(boxValues[13]));
            tb15.setText(Integer.toString(boxValues[14]));
            tb16.setText(Integer.toString(boxValues[15]));
        }
        else if(show == false)
        {
            tb1.setText("?");
            tb2.setText("?");
            tb3.setText("?");
            tb4.setText("?");
            tb5.setText("?");
            tb6.setText("?");
            tb7.setText("?");
            tb8.setText("?");
            tb9.setText("?");
            tb10.setText("?");
            tb11.setText("?");
            tb12.setText("?");
            tb13.setText("?");
            tb14.setText("?");
            tb15.setText("?");
            tb16.setText("?");
        }
    }

    //essentually create a new level
    public void generateGrid(int gridNumbers)
    {
        //everything goes to zero
        tb1 = findViewById(R.id.textButton1);
        tb2 = findViewById(R.id.textButton2);
        tb3 = findViewById(R.id.textButton3);
        tb4 = findViewById(R.id.textButton4);
        tb5 = findViewById(R.id.textButton5);
        tb6 = findViewById(R.id.textButton6);
        tb7 = findViewById(R.id.textButton7);
        tb8 = findViewById(R.id.textButton8);
        tb9 = findViewById(R.id.textButton9);
        tb10 = findViewById(R.id.textButton10);
        tb11 = findViewById(R.id.textButton11);
        tb12 = findViewById(R.id.textButton12);
        tb13 = findViewById(R.id.textButton13);
        tb14 = findViewById(R.id.textButton14);
        tb15 = findViewById(R.id.textButton15);
        tb16 = findViewById(R.id.textButton16);

        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);
        iv7 = findViewById(R.id.imageView7);
        iv8 = findViewById(R.id.imageView8);
        iv9 = findViewById(R.id.imageView9);
        iv10 = findViewById(R.id.imageView10);
        iv11 = findViewById(R.id.imageView11);
        iv12 = findViewById(R.id.imageView12);
        iv13 = findViewById(R.id.imageView13);
        iv14 = findViewById(R.id.imageView14);
        iv15 = findViewById(R.id.imageView15);
        iv16 = findViewById(R.id.imageView16);

        stateImage = findViewById(R.id.stateImage);
        Random rb = new Random();
        int indexB = rb.nextInt(4);
        int b;
        switch(indexB)
        {
            case 0: b = R.drawable.image_0;
            break;
            case 1: b = R.drawable.image_1;
                break;
            case 2: b = R.drawable.image_2;
                break;
            case 3: b = R.drawable.image_3;
            break;
            default: b = R.drawable.image_0;
            break;
        }
        iv1.setImageResource(b);
        iv2.setImageResource(b);
        iv3.setImageResource(b);
        iv4.setImageResource(b);
        iv5.setImageResource(b);
        iv6.setImageResource(b);
        iv7.setImageResource(b);
        iv8.setImageResource(b);
        iv9.setImageResource(b);
        iv10.setImageResource(b);
        iv11.setImageResource(b);
        iv12.setImageResource(b);
        iv13.setImageResource(b);
        iv14.setImageResource(b);
        iv15.setImageResource(b);
        iv16.setImageResource(b);


        InitiateClock();
        timerView = findViewById(R.id.timer_TextView);
        timerView.setTextColor(Color.RED);
        stopClock=true;
        numberList.clear();
        for(int i = 0; i<boxValues.length-1;i++)
        {
            boxValues[i]=0;
        }
        //put the non-zeroes
        for(int i = 0; i<gridNumbers; i++)
        {
            boxValues[i]=i+1;
            int x = i+1;
            numberList.add(x);
        }
        //shufle the array

        Random r = new Random();
        for (int i = boxValues.length - 1; i > 0; i--)
        {
            int index = r.nextInt(i + 1);
            // Simple swap
            int a = boxValues[index];
            boxValues[index] = boxValues[i];
            boxValues[i] = a;
        }
        if(hard==true)
        {
            Random ra = new Random();
            int index = ra.nextInt(3);
            if(index==0) stateImage.setImageResource(R.drawable.up);
            else if(index==1)
            {
                Collections.shuffle(numberList);
                stateImage.setImageResource(R.drawable.dice_64);
            }
            else if(index==2)
            {
                Collections.reverse(numberList);
                stateImage.setImageResource(R.drawable.down);
            }
        }
    }

    protected void interactiveGrid()
    {
            tb1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[0] == nextNumber) {
                            tb1.setText(Integer.toString(boxValues[0]));
                            checkStatus();
                        } else if (boxValues[0] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[1] == nextNumber) {
                            tb2.setText(Integer.toString(boxValues[1]));
                            checkStatus();
                        } else if (boxValues[1] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[2] == nextNumber) {
                            tb3.setText(Integer.toString(boxValues[2]));
                            checkStatus();
                        } else if (boxValues[2] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[3] == nextNumber) {
                            tb4.setText(Integer.toString(boxValues[3]));
                            checkStatus();
                        } else if (boxValues[3] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb5.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[4] == nextNumber) {
                            tb5.setText(Integer.toString(boxValues[4]));
                            checkStatus();
                        } else if (boxValues[4] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb6.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[5] == nextNumber) {
                            tb6.setText(Integer.toString(boxValues[5]));
                            checkStatus();
                        } else if (boxValues[5] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb7.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[6] == nextNumber) {
                            tb7.setText(Integer.toString(boxValues[6]));
                            checkStatus();
                        } else if (boxValues[6] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb8.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[7] == nextNumber) {
                            tb8.setText(Integer.toString(boxValues[7]));
                            checkStatus();
                        } else if (boxValues[7] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb9.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[8] == nextNumber) {
                            tb9.setText(Integer.toString(boxValues[8]));
                            checkStatus();
                        } else if (boxValues[8] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb10.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[9] == nextNumber) {
                            tb10.setText(Integer.toString(boxValues[9]));
                            checkStatus();
                        } else if (boxValues[9] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb11.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[10] == nextNumber) {
                            tb11.setText(Integer.toString(boxValues[10]));
                            checkStatus();
                        } else if (boxValues[10] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb12.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[11] == nextNumber) {
                            tb12.setText(Integer.toString(boxValues[11]));
                            checkStatus();
                        } else if (boxValues[11] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb13.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[12] == nextNumber) {
                            tb13.setText(Integer.toString(boxValues[12]));
                            checkStatus();
                        } else if (boxValues[12] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb14.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[13] == nextNumber) {
                            tb14.setText(Integer.toString(boxValues[13]));
                            checkStatus();
                        } else if (boxValues[13] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb15.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[14] == nextNumber) {
                            tb15.setText(Integer.toString(boxValues[14]));
                            checkStatus();
                        } else if (boxValues[14] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

            tb16.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(stopClock==false) {
                        if (boxValues[15] == nextNumber) {
                            tb16.setText(Integer.toString(boxValues[15]));
                            checkStatus();
                        } else if (boxValues[15] != nextNumber) {
                            gameOver();
                        }
                    }
                }
            });

    }

    public void gameOver()
    {
        timerHandler.removeCallbacks(timerRunnable);
        if(stopOver==true) {
            stopOver=false;
            ApiHelper helper = new ApiHelper();
            String[] params = {"sendData", username, Integer.toString(playerScore) };
            try {
                String result = helper.execute(params).get();
                System.out.println("Api result: " + result);
                if(result != ""){
                    Intent nInt = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(nInt);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
