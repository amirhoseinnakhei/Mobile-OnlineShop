package com.example.opalshop.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.opalshop.R;

public class PaymentActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "order_notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        EditText cardNumber = findViewById(R.id.card_number);
        EditText cardHolderName = findViewById(R.id.card_holder_name);
        EditText expiryDate = findViewById(R.id.expiry_date);
        EditText cvv = findViewById(R.id.cvv);
        Button submitButton = findViewById(R.id.submit_payment_button);

        submitButton.setOnClickListener(v -> {
            String cardNum = cardNumber.getText().toString();
            String holderName = cardHolderName.getText().toString();
            String expiry = expiryDate.getText().toString();
            String cvvCode = cvv.getText().toString();

            if (cardNum.isEmpty() || holderName.isEmpty() || expiry.isEmpty() || cvvCode.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // فرض می‌کنیم پرداخت موفق است
                sendNotification(); // ارسال نوتیفیکیشن
                Intent resultIntent = new Intent();
                resultIntent.putExtra("payment_success", true);
                setResult(RESULT_OK, resultIntent);
                Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
                finish(); // بازگشت به صفحه قبلی
            }
        });
    }

    private void sendNotification() {
        // ایجاد Notification Channel برای نسخه‌های بالاتر از Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Order Notifications";
            String description = "Notifications for order processing";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // ساخت و ارسال نوتیفیکیشن
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon1) // آیکون نوتیفیکیشن (یک آیکون مناسب در پوشه drawable اضافه کنید)
                .setContentTitle("Order Status")
                .setContentText("Your order is being processed.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(1, builder.build()); // ارسال نوتیفیکیشن با یک شناسه یکتا
        }
    }
}
