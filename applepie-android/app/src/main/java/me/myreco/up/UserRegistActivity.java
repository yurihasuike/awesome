package me.myreco.up;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.myreco.up.UserInfoApi.UserInfo;
import me.myreco.up.UserInfoApi.UserInfoApi;
import me.myreco.up.UserRegistApi.UserRegist;
import me.myreco.up.UserRegistApi.UserRegistApi;
import me.myreco.up.UserRegistApi.UserRegistResult;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegistActivity extends Activity {

    private EditText nicknameEdit, mailEdit, usernameEdit, passEdit;
    private TextView privacyLink, termsLink, loginLink;
    private Button registButton;
    private JSONObject jsonObject;
    private CheckBox checkBox;
    private ImageButton icon;
    private Bitmap bitmap;
    private byte[] bytes;
    private Uri bitmapUri;
    private static final int REQUEST_CODE_CAMERA = 1; /* カメラを判定するコード */
    private static final int REQUEST_CODE_GALLERY = 2; /* ギャラリーを判定するコード */
    private static final long CLICK_DELAY = 1000;
    private static long OLD_CLICK_TIME;
    private RequestBody icon_img;
    private ProgressDialog progressDialog;
    private FirebaseAnalytics FirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_regist_activity);


        FirebaseAnalytics = FirebaseAnalytics.getInstance(getApplication());
        FirebaseAnalytics.logEvent("UserRegistActivity", null);

        nicknameEdit = (EditText) findViewById(R.id.nicknameEdit);
        mailEdit = (EditText) findViewById(R.id.mailEdit);
        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);
        privacyLink = (TextView) findViewById(R.id.privacy_link);
        termsLink = (TextView) findViewById(R.id.terms_link);
        loginLink = (TextView) findViewById(R.id.loginLink);
        checkBox = (CheckBox) findViewById(R.id.termsCheckBox);
        registButton = (Button) findViewById(R.id.registButton);
        icon = (ImageButton) findViewById(R.id.icon);


        //テキストエリアからタブにフォーカスが移るのを回避
        View.OnTouchListener focus_listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        };

        nicknameEdit.setOnTouchListener(focus_listener);
        mailEdit.setOnTouchListener(focus_listener);
        usernameEdit.setOnTouchListener(focus_listener);
        passEdit.setOnTouchListener(focus_listener);

        registButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isClickEvent()) return;

                if (checkBox.isChecked()) {

                    new AlertDialog.Builder(UserRegistActivity.this)
                            .setMessage("入力した内容で登録してもよろしいですか？")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    progressDialog = new ProgressDialog(UserRegistActivity.this);
                                    progressDialog.setIndeterminate(true);

                                    progressDialog.setMessage("登録中...");
                                    progressDialog.show();

                                    String nickname = String.valueOf(nicknameEdit.getText());
                                    String mail = String.valueOf(mailEdit.getText());
                                    final String username = String.valueOf(usernameEdit.getText());
                                    String password = String.valueOf(passEdit.getText());

                                    if (nickname.equals("")) {
                                        new AlertDialog.Builder(UserRegistActivity.this)
                                                .setTitle("ニックネーム")
                                                .setMessage("この項目は必須です")
                                                .setPositiveButton("OK", null)
                                                .show();
                                        if(progressDialog.isShowing()){
                                            progressDialog.dismiss();
                                        }
                                        return;
                                    }

                                    UserRegist userRegist = new UserRegist(username, password, mail, nickname);
                                    UserRegistApi userRegistApi = ServiceGenerator.createService(UserRegistApi.class);

                                    final Call<UserRegistResult> userRegistResultCall = userRegistApi.regist(userRegist);
                                    userRegistResultCall.enqueue(new Callback<UserRegistResult>() {
                                        @Override
                                        public void onResponse(Call<UserRegistResult> call, Response<UserRegistResult> response) {

                                            if (response.errorBody() != null) {
                                                try {
                                                    jsonObject = new JSONObject(response.errorBody().string());

                                                    if (jsonObject.has("password1")) {
                                                        CreateDialog("パスワード", "password1");
                                                    }

                                                    if (jsonObject.has("username")) {
                                                        CreateDialog("ユーザーID", "username");
                                                    }

                                                    if (jsonObject.has("email")) {
                                                        CreateDialog("メールアドレス", "email");
                                                    }

                                                    if(progressDialog.isShowing()){
                                                        progressDialog.dismiss();
                                                    }

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            } else {

                                                SavePreference savePreference = new SavePreference();
                                                String aToken = response.body().getKey();
                                                savePreference.save_aToken(UserRegistActivity.this, aToken);
                                                savePreference.save_username(UserRegistActivity.this, username);
                                                Integer tmp_uid = response.body().getUid();

                                                if (bitmap != null) {
                                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                    bitmap.compress(Bitmap.CompressFormat.PNG, 10, baos);
                                                    bytes = baos.toByteArray();
                                                    icon_img = RequestBody.create(MediaType.parse("image/png"), bytes);
                                                    RequestBody uid = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(tmp_uid));

                                                    UserInfoApi userInfoApi = ServiceGenerator.createService(UserInfoApi.class, aToken);
                                                    Call<UserInfo> userInfoCall = userInfoApi.iconPost(uid, icon_img);
                                                    userInfoCall.enqueue(new Callback<UserInfo>() {
                                                        @Override
                                                        public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                                                        }

                                                        @Override
                                                        public void onFailure(Call<UserInfo> call, Throwable t) {

                                                        }
                                                    });
                                                }

                                                setResult(RESULT_OK);
                                                if(progressDialog.isShowing()){
                                                    progressDialog.dismiss();
                                                }
                                                new AlertDialog.Builder(UserRegistActivity.this)
                                                        .setMessage("登録に成功しました")
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                finish();
                                                            }
                                                        })
                                                        .show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UserRegistResult> call, Throwable t) {
                                            if(progressDialog.isShowing()){
                                                progressDialog.dismiss();
                                            }
                                            Toast.makeText(UserRegistActivity.this, "登録に失敗しました", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(UserRegistActivity.this, "投稿をキャンセルしました", Toast.LENGTH_LONG).show();
                                }
                            })
                            .show();
                }else {
                    new AlertDialog.Builder(UserRegistActivity.this)
                            .setMessage("利用規約に同意してください")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });

        final String[] str_items = {"カメラで撮影", "ギャラリーの選択", "キャンセル"};
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UserRegistActivity.this)
                        .setTitle("写真をアップロード")
                        .setItems(str_items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        wakeupCamera(); // カメラ起動
                                        break;
                                    case 1:
                                        wakeupGallery(); // ギャラリー起動
                                        break;
                                    default:
                                        // キャンセルを選んだ場合
                                        break;
                                }
                            }
                        }).show();
            }
        });


        MovementMethod movementMethod = LinkMovementMethod.getInstance();
        privacyLink.setMovementMethod(movementMethod);
        termsLink.setMovementMethod(movementMethod);

        privacyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getApplicationContext(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"privacy/");
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("PrivacyLinkClicked", null);
            }
        });

        termsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getApplicationContext(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"terms/");
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("TermsLinkClicked", null);
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public AlertDialog CreateDialog(String title, String content){
        String result = "";
        try {
            String str = jsonObject.getString(content);
            String regex = "\\[\"(.+?)\"\\]";
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(str);
            result = matcher.replaceFirst("$1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new AlertDialog.Builder(UserRegistActivity.this)
                .setTitle(title)
                .setMessage(result)
                .setPositiveButton("OK", null)
                .show();

    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void wakeupCamera() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            // 許可されている時の処理
            File mediaStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES
                    ), "myreco_up"
            );
            if (!mediaStorageDir.exists() & !mediaStorageDir.mkdir()) {
                return;
            }
            String timeStamp = new SimpleDateFormat("yyyMMddHHmmss").format(new Date());
            File mediaFile;
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + timeStamp + ".JPG");
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            bitmapUri = Uri.fromFile(mediaFile);
            i.putExtra(MediaStore.EXTRA_OUTPUT, bitmapUri); // 画像をmediaUriに書き込み
            startActivityForResult(i, REQUEST_CODE_CAMERA);
        } else {
            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) && shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("パーミッションの説明")
                        .setMessage("写真を撮るにはパーミッションが必要です")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                            }
                        })
                        .show();
                return;
            }else{
                requestPermissions(new String[]{android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                return;
            }
        }
    }

    protected void wakeupGallery() {
        Intent i;
        if (Build.VERSION.SDK_INT < 19) {
            i  = new Intent(Intent.ACTION_GET_CONTENT);
            i.setType("image/*"); // 画像のみが表示されるようにフィルターをかける
        } else {
            i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*"); // 画像のみが表示されるようにフィルターをかける
        }
        startActivityForResult(i, REQUEST_CODE_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (bitmap != null)
                bitmap.recycle(); // 直前のBitmapが読み込まれていたら開放する

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4; // 元の1/4サイズでbitmap取得

            switch (requestCode) {
                case 1: // カメラの場合
                    bitmap = BitmapFactory.decodeFile(bitmapUri.getPath(), options);
                    // 撮影した画像をギャラリーのインデックスに追加されるようにスキャンする。
                    String[] paths = {bitmapUri.getPath()};
                    String[] mimeTypes = {"image/*"};
                    MediaScannerConnection.scanFile(getApplicationContext(), paths, mimeTypes, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
                    break;
                case 2: // ギャラリーの場合
                    try {
                        InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(data.getData());
                        bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
            bitmap = RadiusImage(bitmap);
            icon.setImageDrawable(null);
            icon.setImageBitmap(bitmap);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {

            if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("パーミッション取得エラー")
                        .setMessage("今後は許可しないが選択されています。アプリ設定＞権限からチェックしてください")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                openPermission();
                            }
                        })
                        .show();
            }
        }else if(grantResults[1] != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("パーミッション取得エラー")
                        .setMessage("今後は許可しないが選択されています。アプリ設定＞権限からチェックしてください")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                openPermission();
                            }
                        })
                        .show();
            }
        }else{
            File mediaStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES
                    ), "myreco_up"
            );
            if (!mediaStorageDir.exists() & !mediaStorageDir.mkdir()) {
                return;
            }
            String timeStamp = new SimpleDateFormat("yyyMMddHHmmss").format(new Date());
            File mediaFile;
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + timeStamp + ".JPG");
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            bitmapUri = Uri.fromFile(mediaFile);
            i.putExtra(MediaStore.EXTRA_OUTPUT, bitmapUri); // 画像をmediaUriに書き込み
            startActivityForResult(i, REQUEST_CODE_CAMERA);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //クリックイベントの実行可否
    public static boolean isClickEvent() {
        // 現在時間を取得する
        long time = System.currentTimeMillis();

        // 一定時間経過していなければクリックイベント実行不可
        if (time - OLD_CLICK_TIME < CLICK_DELAY) {
            return false;
        }

        // 一定時間経過したらクリックイベント実行可能
        OLD_CLICK_TIME = time;
        return true;
    }

    private void openPermission() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        //Fragmentの場合はgetContext().getPackageName()
        Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
        intent.setData(uri);
        getApplicationContext().startActivity(intent);
    }

    public Bitmap RadiusImage(Bitmap bm){
        int width  = bm.getWidth();
        int height = bm.getHeight();
        int size = Math.min(width, height);
        Bitmap clipArea = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(clipArea);
        c.drawRoundRect(new RectF(0, 0, size, size), size, size, new Paint(Paint.ANTI_ALIAS_FLAG));
        Bitmap newImage = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newImage);
        Paint paint = new Paint();
        canvas.drawBitmap(clipArea, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bm, new Rect(0, 0, size, size), new Rect(0, 0, size, size), paint);
        return newImage;
    }

}
