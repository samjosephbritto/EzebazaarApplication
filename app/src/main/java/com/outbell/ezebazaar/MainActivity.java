package com.outbell.ezebazaar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webview_id);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WBrowser());
        webView.loadUrl(EzebazaarURL.mainpage);

    }

    public class WBrowser extends WebViewClient {

        boolean loadingFinished = true;
        boolean redirect = false;
        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!loadingFinished) {
                redirect = true;
            }

            loadingFinished = false;
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            loadingFinished = false;
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            if(progressDialog.isShowing()) progressDialog.dismiss();
           // view.loadUrl("javascript:callFromActivity(\"18091996\")");
            if(!redirect){
                loadingFinished = true;
            }

            if(loadingFinished && !redirect){
                view.loadUrl("javascript:callFromActivity(\"18091996\")");
            } else{
                redirect = false;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
