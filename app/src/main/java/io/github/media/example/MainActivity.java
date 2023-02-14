package io.github.media.example;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import io.github.wong1988.media.MediaCenter;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
    }

    public void click(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            StringBuilder builder = new StringBuilder();
            String fileAbsolutePath = TestCase.getFileAbsolutePath(this, uri);
            MediaCenter.MediaFileType mediaFileType = MediaCenter.getMediaFileType(fileAbsolutePath);
            builder.append(fileAbsolutePath);
            builder.append("\n");
            builder.append("mimeType：");
            builder.append(mediaFileType.mimeType);
            builder.append("\n");
            builder.append("fileType：");
            builder.append(mediaFileType.fileType);
            builder.append("\n");
            builder.append("是否是图片格式：");
            builder.append(MediaCenter.isImageFileType(mediaFileType));
            builder.append("\n");
            builder.append("所有图片类型文件：");
            for (int i = 0; i < MediaCenter.IMAGE_EXTENSIONS.length; i++) {
                builder.append(MediaCenter.IMAGE_EXTENSIONS[i]);
            }
            builder.append("\n");
            builder.append("\n");
            builder.append("点击打开文件");
            tv.setText(builder.toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void open(View view) {
        int end = tv.getText().toString().indexOf("\n");
        String substring = tv.getText().toString().substring(0, end);
        Toast.makeText(this, substring, Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        MediaCenter.openFile(this, substring);
    }
}