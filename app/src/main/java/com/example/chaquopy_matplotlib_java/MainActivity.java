package com.example.chaquopy_matplotlib_java;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.Python.Platform;
import com.chaquo.python.android.AndroidPlatform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

//import org.jetbrains.annotations.Nullable;

//import kotlin.Metadata;
//import kotlin.jvm.internal.Intrinsics;
//import android.renderscript.ScriptIntrinsic;
// adb devices
//adb devices Emulator Reflesh


public final class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        if (!Python.isStarted()) {
            Python.start((Platform)(new AndroidPlatform((Context)this)));
        }

        Python py = Python.getInstance();
        //Intrinsics.checkNotNullExpressionValue(var10000, "Python.getInstance()");
        //Python py = var10000;

        PyObject module = py.getModule("plot");
        //Intrinsics.checkNotNullExpressionValue(var4, "py.getModule(\"plot\")");
        //final PyObject module = var4;
        
        ((Button)this.findViewById(R.id.button)).setOnClickListener((OnClickListener)(new OnClickListener() {
            //@android.support.annotation.RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            public final void onClick(View it) {
                try {
                    //PyObject var10000 = module;
                    Object[] Coordinates = new Object[2];


                    EditText X_coordinates = (EditText)findViewById(R.id.etX);
                    //View var10005 = MainActivity.this.findViewById(R.id.etX);
                    //Intrinsics.checkNotNullExpressionValue(var10005, "findViewById<EditText>(R.id.etX)");
                    Coordinates[0] = ((EditText)X_coordinates).getText().toString();



                    EditText Y_coordinates = (EditText)findViewById(R.id.etY);
                    //var10005 = MainActivity.this.findViewById(R.id.etY);
                    //Intrinsics.checkNotNullExpressionValue(var10005, "findViewById<EditText>(R.id.etY)");
                    Coordinates[1] = ((EditText)Y_coordinates).getText().toString();


                    //public PyObject callAttr(@NotNull String key, Object... args)
                    Object var8 = module.callAttr("plot",Coordinates).toJava(byte[].class);
                    //Object var8 = var10000.callAttr("plot", var10002).toJava(byte[].class);
                    //Intrinsics.checkNotNullExpressionValue(var8, "module.callAttr(\"plot\",\n…va(ByteArray::class.java)");

                    byte[] bytes = (byte[])var8;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);
                    View var9 = getCurrentFocus();



                    if (var9 != null) {
                        View var4 = var9;
                        Boolean var6 = false;


                        //var8 = MainActivity.this.getSystemService("input_method");
                        var8 = MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (var8 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                        }

                        //InputMethodManager var10 = (InputMethodManager)var8;
                        //Intrinsics.checkNotNullExpressionValue(var4, "it");
                        //var10.hideSoftInputFromWindow(var4.getWindowToken(), 0);
                    }
                } catch (PyException var7) {
                    Toast.makeText((Context) MainActivity.this, (CharSequence)var7.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }));
    }
}

