package com.ledebour.androidpermissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * POC check permission life cycle
 */
public class MainActivity extends AppCompatActivity {

	private static final int REQUEST_CODE = 10;


	private boolean hasPermission() {
		return ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.SIGNATURE_FIRST_NOT_SIGNED
				|| ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (hasPermission()) {
			final String[] permissions = new String[]{Manifest.permission.READ_SMS};
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("To add automatically data the application need to read your Sms.");
				builder.setTitle("READ SMS");
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CODE);
					}
				});

				builder.show();
			} else {
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},
						REQUEST_CODE);
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       @NonNull String permissions[], @NonNull int[] grantResults) {
		switch (requestCode) {
			case REQUEST_CODE: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(this, "Permission ok", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "Permission NOT_OK", Toast.LENGTH_SHORT).show();

					Intent intent = new Intent();
					intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
					Uri uri = Uri.fromParts("package", this.getPackageName(), null);
					intent.setData(uri);
					this.startActivity(intent);
				}
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		TextView t = (TextView) findViewById(R.id.textView);
		t.setText("Have read SMS permission: " + (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED));
	}
}
