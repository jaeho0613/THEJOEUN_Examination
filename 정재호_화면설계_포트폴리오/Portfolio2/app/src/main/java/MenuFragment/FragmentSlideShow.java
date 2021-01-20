package MenuFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jaeho.portfolio2.MenuActivity;
import org.jaeho.portfolio2.R;

import static android.app.Activity.RESULT_OK;

public class FragmentSlideShow extends Fragment {

    EditText editTextName;
    EditText editTextAge;
    TextView textViewOutPut;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("Debug", "onCreateView 12");
        View view = inflater.inflate(R.layout.fragment_silde_show, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextAge = view.findViewById(R.id.editTextAge);
        textViewOutPut = view.findViewById(R.id.textViewOutPut);
        button = view.findViewById(R.id.buttonSend);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), MenuActivity.class);
            intent.putExtra("name", editTextName.getText().toString());
            intent.putExtra("age", editTextAge.getText().toString());
            startActivityForResult(intent, 101);
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        editTextAge.setText(null);
        editTextName.setText(null);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Log.d("Debug", "onActivityResult 발동");
                String name = data.getStringExtra("name");
                String age = data.getStringExtra("age");

                editTextName.setText(name);
                editTextAge.setText(age);

                textViewOutPut.setText("이름 : " + name + " , 나이 : " + age);
            }
        }
    }
}