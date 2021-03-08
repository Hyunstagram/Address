package com.kang.address;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView main_name;
    private TextView main_num;
    private TextView main_email;
    private TextView dlg_name;
    private TextView dlg_num;
    private TextView dlg_email;
    private EditText edit_name;
    private EditText edit_num;
    private EditText edit_email;
    private ImageView btn_add;
    private Dialog dlg_info;
    private Dialog dlg_add;
    ListViewAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_name = (TextView)findViewById(R.id.textView_name);
        main_num = (TextView)findViewById(R.id.textView_num);
        main_email = (TextView)findViewById(R.id.textView_email);
        btn_add = (ImageView)findViewById(R.id.imageView_add);

        dlg_info = new Dialog(MainActivity.this);
        dlg_info.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg_info.setContentView(R.layout.dialog_info);

        adapter = new ListViewAdapter();
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        adapter.addItem("김나현",
                "010-0147-0258", "hustar1@hustar.com") ;
        adapter.addItem("김도현",
                "010-0258-0369", "hustar2@hustar.com") ;
        adapter.addItem("이주은",
                "010-0369-0147", "hustar3@hustar.com") ;
        adapter.addItem("조은지",
                "010-0147-0258", "hustar4@hustar.com") ;
        adapter.addItem("진채은",
                "010-0258-0369", "hustar5@hustar.com") ;
        adapter.addItem("최은지",
                "010-0369-0147", "hustar6@hustar.com") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String nameStr = item.getName();
                String numStr = item.getNum();
                String emailStr = item.getEmail();

                dlg_name = (TextView)dlg_info.findViewById(R.id.dlg_name);
                dlg_num = (TextView)dlg_info.findViewById(R.id.dlg_num);
                dlg_email = (TextView)dlg_info.findViewById(R.id.dlg_email);

                dlg_name.setText(nameStr);
                dlg_num.setText(numStr);
                dlg_email.setText(emailStr);

                dlg_info.show();
                dlg_info.setCancelable(false);

                dlg_info.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dlg_info.dismiss();
                    }
                });
                // TODO : use item data.

                dlg_info.findViewById(R.id.imageView_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.deleteItem(position);
                        listview.clearChoices();
                        adapter.notifyDataSetChanged();
                        dlg_info.dismiss();
                    }
                }) ;
            }
        }) ;
    }

    public void onClick(View v) {
        ImageView btn_delete;

        dlg_name = (TextView)dlg_info.findViewById(R.id.dlg_name);
        dlg_num = (TextView)dlg_info.findViewById(R.id.dlg_num);
        dlg_email = (TextView)dlg_info.findViewById(R.id.dlg_email);

        dlg_name.setText(main_name.getText());
        dlg_num.setText(main_num.getText());
        dlg_email.setText(main_email.getText());

        btn_delete = (ImageView)dlg_info.findViewById(R.id.imageView_delete);
        btn_delete.setVisibility(View.INVISIBLE);

        dlg_info.show();
        dlg_info.setCancelable(false);

        dlg_info.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg_info.dismiss();
                btn_delete.setVisibility(View.VISIBLE);
            }
        });
    }

    public void onAdd(View v) {
        dlg_add = new Dialog(MainActivity.this);
        dlg_add.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg_add.setContentView(R.layout.dialog_add);

        edit_name = (EditText)dlg_add.findViewById(R.id.edit_name);
        edit_num = (EditText)dlg_add.findViewById(R.id.edit_num);
        edit_email = (EditText)dlg_add.findViewById(R.id.edit_email);

        dlg_add.show();

        dlg_add.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_name.getText().toString().getBytes().length <= 0 || edit_num.getText().toString().getBytes().length <= 0 || edit_email.getText().toString().getBytes().length <= 0){//빈값이 넘어올때의 처리
                    if(edit_name.getText().toString().getBytes().length <= 0)
                        edit_name.setHintTextColor(Color.rgb(228, 165, 165));
                    if(edit_num.getText().toString().getBytes().length <= 0)
                        edit_num.setHintTextColor(Color.rgb(228, 165, 165));
                    if(edit_email.getText().toString().getBytes().length <= 0)
                        edit_email.setHintTextColor(Color.rgb(228, 165, 165));
                } else {
                    adapter.addItem(String.valueOf(edit_name.getText()), String.valueOf(edit_num.getText()), String.valueOf(edit_email.getText()));
                    adapter.notifyDataSetChanged();
                    dlg_add.dismiss();
                }
            }
        });
    }
}