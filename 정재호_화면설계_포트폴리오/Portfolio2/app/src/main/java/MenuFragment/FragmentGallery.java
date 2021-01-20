package MenuFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.os.strictmode.CleartextNetworkViolation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jaeho.portfolio2.R;


public class FragmentGallery extends Fragment {

    EditText editText;
    TextView textView;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        textView = view.findViewById(R.id.textView);
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 0);
        });

        clearState();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        saveState();
    }

    @Override
    public void onResume() {
        super.onResume();

        getState();
    }

    // 데이터 저장 메서드
    public void saveState() {
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("data", editText.getText().toString());
        editor.commit();
    }

    // 데이터 가져오기
    public void getState() {
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if ((pref != null) && (pref.contains("data"))) {
            String name = pref.getString("data", "");
            textView.setText(name);
        } else {
            editText.setText(null);
        }
    }

    // 데이터 초기화
    public void clearState() {
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}