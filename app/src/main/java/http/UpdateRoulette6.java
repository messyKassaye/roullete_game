package http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateRoulette6 extends AsyncTask<String, Integer, Boolean> {
    ProgressDialog bar;
    private Context context;

    public UpdateRoulette6(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.bar = new ProgressDialog(this.context);
        this.bar.setCancelable(false);
        this.bar.setMessage("Downloading...");
        this.bar.setIndeterminate(true);
        this.bar.setCanceledOnTouchOutside(false);
        this.bar.show();
    }

    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        this.bar.setIndeterminate(false);
        this.bar.setMax(100);
        this.bar.setProgress(progress[0].intValue());
        String msg = "";
        if (progress[0].intValue() > 99) {
            msg = "Finishing... ";
        } else {
            msg = "Downloading... " + progress[0] + "%";
        }
        this.bar.setMessage(msg);
    }

    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        this.bar.dismiss();
        if (result.booleanValue()) {
            Toast.makeText(this.context, "Update Done", 0).show();
        } else {
            Toast.makeText(this.context, "Error: Try Again", 0).show();
        }
    }

    protected Boolean doInBackground(String... arg0) {
        Boolean flag = Boolean.valueOf(false);
        try {
            HttpURLConnection c = (HttpURLConnection) new URL("http://roulette6.club/roulette.apk").openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            String PATH = Environment.getExternalStorageDirectory() + "/Download/";
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file, "roulette.apk");
            if (outputFile.exists()) {
                outputFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int downloaded = 0;
            while (true) {
                int len1 = is.read(buffer);
                if (len1 != -1) {
                    fos.write(buffer, 0, len1);
                    downloaded += len1;
                    int per = (downloaded * 100) / 1431692;
                    publishProgress(new Integer[]{Integer.valueOf(per)});
                } else {
                    fos.close();
                    is.close();
                    OpenNewVersion(PATH);
                    return Boolean.valueOf(true);
                }
            }
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }

    void OpenNewVersion(String location) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(location + "roulette.apk")), "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        this.context.startActivity(intent);
    }
}
