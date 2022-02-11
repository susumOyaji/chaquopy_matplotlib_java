package com.example.chaquopy_matplotlib_java;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.Python.Platform;
import com.chaquo.python.android.AndroidPlatform;
//import kotlin.Metadata;
//import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

// adb devices
@Metadata(
        mv = {1, 5, 1},
        k = 1,
        d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"},
        d2 = {"Lcom/chaquo/myapplication/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}
)
public final class MainActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        if (!Python.isStarted()) {
            Python.start((Platform)(new AndroidPlatform((Context)this)));
        }

        Python var10000 = Python.getInstance();
        Intrinsics.checkNotNullExpressionValue(var10000, "Python.getInstance()");
        Python py = var10000;

        PyObject var4 = py.getModule("plot");
        Intrinsics.checkNotNullExpressionValue(var4, "py.getModule(\"plot\")");
        final PyObject module = var4;
        
        ((Button)this.findViewById(R.id.button)).setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                try {
                    PyObject var10000 = module;
                    Object[] var10002 = new Object[2];



                    View var10005 = MainActivity.this.findViewById(R.id.etX);
                    Intrinsics.checkNotNullExpressionValue(var10005, "findViewById<EditText>(R.id.etX)");
                    var10002[0] = ((EditText)var10005).getText().toString();




                    var10005 = MainActivity.this.findViewById(R.id.etY);
                    Intrinsics.checkNotNullExpressionValue(var10005, "findViewById<EditText>(R.id.etY)");
                    var10002[1] = ((EditText)var10005).getText().toString();


                    Object var8 = var10000.callAttr("plot", var10002).toJava(byte[].class);
                    Intrinsics.checkNotNullExpressionValue(var8, "module.callAttr(\"plot\",\n…va(ByteArray::class.java)");

                    byte[] bytes = (byte[])var8;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    ((ImageView) MainActivity.this.findViewById(R.id.imageView)).setImageBitmap(bitmap);
                    View var9 = MainActivity.this.getCurrentFocus();



                    if (var9 != null) {
                        View var4 = var9;
                        //int var6 = false;

                        var8 = MainActivity.this.getSystemService("input_method");
                        if (var8 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                        }

                        InputMethodManager var10 = (InputMethodManager)var8;
                        Intrinsics.checkNotNullExpressionValue(var4, "it");
                        var10.hideSoftInputFromWindow(var4.getWindowToken(), 0);
                    }
                } catch (PyException var7) {
                    Toast.makeText((Context) MainActivity.this, (CharSequence)var7.getMessage(), 1).show();
                }

            }
        }));
    }
}
