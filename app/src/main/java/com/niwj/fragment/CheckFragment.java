package com.niwj.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.niwj.neihandz.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;



import static android.app.Activity.RESULT_OK;


public class CheckFragment extends Fragment {
    EditText editText;
    Button button;
    ImageView image;
    ImageView showImage;
    String picturePath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_check,null);

        editText= (EditText) view.findViewById(R.id.et_upload_joke);
        button= (Button) view.findViewById(R.id.b_upload_commit);
        image= (ImageView) view.findViewById(R.id.iv_upload_image);
        showImage= (ImageView) view.findViewById(R.id.iv_upload_showimage);

        image.setOnClickListener(new MyImageListener());

        button.setOnClickListener(new MyImageListener());

        
        return view;
    }


    class MyImageListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_upload_image:
                    Intent i = new Intent(
                            Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, 2);
                    break;

                case R.id.b_upload_commit:
                    upLoadOnClick(picturePath);
                    break;
            }


        }



    }

    private void upLoadOnClick(String upUrl) {

//        String upUrl = "/mnt/sdcard/pic/test.jpg";//指定要上传的文件
        final ProgressDialog dia = new ProgressDialog(this.getContext());
        dia.setMessage("加载中....");
        dia.show();
        RequestParams params = new RequestParams("http://172.19.10.24:8080/NHDZ_Server/up");

        params.addBodyParameter("file", new File(upUrl));
        x.Ext.init(this.getActivity().getApplication());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //加载成功回调，返回获取到的数据
                System.out.println("hhhhhhhhhhhhhhhhhh");
            }

            @Override
            public void onFinished() {
                dia.dismiss();//加载完成
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("ooooooooooooooooooooo");
                System.out.println(ex.toString());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = this.getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
             picturePath = cursor.getString(columnIndex);
            cursor.close();


            showImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }
}
