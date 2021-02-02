package com.example.Heed_Real_Estate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteLoader  {

    private WebView webView;
    private Context context;

    public WebsiteLoader(WebView webView, Context context){

        this.webView=webView;
        this.context=context;
        WebSettings settings = webView.getSettings();
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.setWebViewClient(new WebViewClient());
        settings.setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36" +
                " (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36");
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);

    }
    private boolean CheckNetwork(){

        assert context != null;
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        if((manager.getActiveNetworkInfo())==null)
            return false;
        else{
            return (manager.getActiveNetworkInfo()).isConnected();
        }

    }

    public void loadWebsite(final String url){

        if(CheckNetwork()) {
            webView.loadUrl(url);
        }
        else{
            new AlertDialog.Builder(context).setTitle("ERROR").setMessage("Check Your Network Connection").
                    setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loadWebsite(url);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setIcon(R.drawable.ic_network_error).show();
        }

    }


}
