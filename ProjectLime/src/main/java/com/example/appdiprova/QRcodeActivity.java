package com.example.appdiprova;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QRcodeActivity extends AppCompatActivity {

    private final static String NEEDED_PERMISSION = Manifest.permission.CAMERA;

    private static final int REQUEST_ID = 444;
    private BarcodeDetector detector;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        message = findViewById(R.id.barcode_text);

        // chiediamo di individuare QR code e EAN 13
        detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE | Barcode.EAN_13 | Barcode.EAN_8)
                .build();

        // verifichiamo che BarcodeDetector sia operativo
        if (!detector.isOperational()) {
            exit("Detector di codici a barre non attivabile");
            return;
        }

        // istanziamo un oggetto CameraSource collegata al detector
        cameraSource = new CameraSource
                .Builder(this, detector)
                .setAutoFocusEnabled(true)
                .build();

        // gestione delle fasi di vita della SurfaceView
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                activateCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> items = detections.getDetectedItems();
                final Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


                if (items.size() != 0) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            String barcode =/*"Rilevato: "+*/items.valueAt(0).displayValue;
                            message.setText(barcode);
                            assert vib != null; //test
                            vib.vibrate(100);
                        }
                    });
                }

            }
        });
    }

    private void exit(String msg) {
        new AlertDialog.Builder(this)
                .setTitle("Impossibile proseguire")
                .setMessage(msg)
                .setPositiveButton("chiudi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }

    public void cleanTextView(View v) {
        message.setText("");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    activateCamera();

                } else {

                    exit("Negata la Permission di accesso alla fotocamera");
                }
                // test-remove return;
            }
        }
    }



    private void activateCamera() {

        // verifichiamo che sia stata concessa la permission CAMERA

        if (ActivityCompat.checkSelfPermission(this, NEEDED_PERMISSION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, NEEDED_PERMISSION)) {
                new AlertDialog.Builder(this)
                        .setMessage("Questa app utilizza la fotocamera per rilevare codici a barre. Concedere la permission " +
                                "relativa è assolutamente indispensabile")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(QRcodeActivity.this,
                                        new String[]{NEEDED_PERMISSION},
                                        REQUEST_ID);
                            }
                        })
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{NEEDED_PERMISSION}, REQUEST_ID);

            }
        } else {
            try {
                cameraSource.start(surfaceView.getHolder());
            } catch (IOException e) {
                exit("Errore nell'avvio della fotocamera");
            }
        }

    }
}
