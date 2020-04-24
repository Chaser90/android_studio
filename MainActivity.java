package com.example.calculator;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    final String CLEAR_INPUT_TEXT = "0";

    boolean isFirstInput = true; // 입력값이 첫 입력인가를 확인
    int resultNumber = 0; // 계산된 결과 값 저장하는 변수
    char operator = '+';  // 입력된 연산자 저장하는 변수

    TextView resultText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.result_text);
    }
    // AC, CE, BS, .이 클릭했을 때 실행하는 메소드
    public void buttonClick(View view) {
       switch (view.getId()) {
            case R.id.all_clear_button:
                resultNumber = 0;
                operator = '+';
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.clear_entry_button:
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.back_space_button:
                if (resultText.getText().toString().length() > 1){
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0,getResultText.length() - 1);
                    resultText.setText(subString);
                } else {
                    isFirstInput = true;
                    resultText.setTextColor(0xFF666666);
                    resultText.setText(CLEAR_INPUT_TEXT);

                }
                break;

            case R.id.decimal_button:
                Log.e("buttonClick","decimal 버튼이 클릭되었습니다.");
                break;

            }
    }


    // 입력된 숫자를 클리어 시켜주는 메소드
    public void setClearText(String clearText) {
        isFirstInput = true;
        resultText.setTextColor(0xFF666666);
        resultText.setText(clearText);
    }


    // 0~9 버튼이 클릭되었을 때 실행되는 메소드
    public void numButtonClick(View view) {
        Button getButton = findViewById(view.getId());
        if (isFirstInput) {
            resultText.setTextColor(0xFF000000);
            resultText.setText(getButton.getText().toString());
            isFirstInput = false;
        } else {
            if(resultText.getText().toString().equals("0")) {
                Toast.makeText(getApplicationContext(), "0으로 시작하는 정수는 없습니다.", Toast.LENGTH_SHORT).show();
                setClearText(CLEAR_INPUT_TEXT);
            }
            resultText.append(getButton.getText().toString());
        }
    }

    //연산자가 클릭되면 실행되는 메소드
    public void operatorClick (View view) {
        Button getButton = findViewById(view.getId());

        if(view.getId() == R.id.result_button){
            if(isFirstInput == true) {
                resultNumber = 0;
                operator = '+';
                setClearText("0");
                // Todo: 2020-04-11 다음에 실수형 계산기 만들 때 윈도우 계산기 처럼 = 을 두번 이상 누를 때 실행 방법과 같이 구현할 것/
            } else {
                resultNumber = intCal(resultNumber, Integer.parseInt(resultText.getText().toString()), operator);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
            }
        } else {
            if(isFirstInput) {
                operator = getButton.getText().toString().charAt(0);
            } else {
                int lastNum = Integer.parseInt(resultText.getText().toString());
                resultNumber = intCal(resultNumber, lastNum, operator);
                operator = getButton.getText().toString().charAt(0);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
            }

        }
    }

    // 사칙연산을 해서 값을 변환하는 메소드
    public int intCal(int result, int lastNum, char operator) {
        if (operator == '+') {
            result = resultNumber + lastNum;
        } else if (operator == '-') {
            result = resultNumber - lastNum;
        } else if (operator == '/') {
            result = resultNumber / lastNum;
        } else if (operator == '*') {
            result = resultNumber * lastNum;
        }
        return result;
    }
}


/*
                case R.id.num_0_button:
                case R.id.num_1_button:
                case R.id.num_2_button:
                case R.id.num_3_button:
                case R.id.num_4_button:
                case R.id.num_5_button:
                case R.id.num_6_button:
                case R.id.num_7_button:
                case R.id.num_8_button:
                case R.id.num_9_button:
                    if (isFirstInput) {
                        resultText.setTextColor(0xFF000000);
                        resultText.setText(getButton.getText().toString());
                        isFirstInput = false;
                   } else {
                        resultText.append(getButton.getText().toString());
                    }
                    break;

        if(view.getId() == R.id.num_0_button
                || view.getId() == R.id.num_1_button
                || view.getId() == R.id.num_2_button
                || view.getId() == R.id.num_3_button
                || view.getId() == R.id.num_4_button
                || view.getId() == R.id.num_5_button
                || view.getId() == R.id.num_6_button
                || view.getId() == R.id.num_7_button
                || view.getId() == R.id.num_8_button
                || view.getId() == R.id.num_9_button) {
            if(isFirstInput) {
                resultText.setTextColor(0xFF000000);
                resultText.setText(getButton.getText().toString());
                isFirstInput = false;
            } else {
                resultText.append(getButton.getText().toString());
            }
        }
*/
