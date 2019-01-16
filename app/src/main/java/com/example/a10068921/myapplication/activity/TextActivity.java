package com.example.a10068921.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.droidlover.xrichtext.ImageLoader;
import cn.droidlover.xrichtext.XRichText;
import com.example.a10068921.myapplication.R;
import com.zzhoujay.richtext.RichText;

import java.io.IOException;
import java.util.List;


/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/15 16:52
 **/
public class TextActivity extends AppCompatActivity {

    String TEXT="时间改变着一切，一切改变着我们。原先看不惯的，如今习惯了；曾经很想要的，现在不需要了；开始很执着的，后来很洒脱了。失去产生了痛苦，也铸就了坚强；经历付出了代价，也锤炼了成长。没流泪，不代表没眼泪；无所谓，不代表无所累。当你知道什么是欲哭无泪，欲诉无语，欲笑无声的时候，你成熟了。累了没人疼，你要学会休息；哭了没人哄，你要知道自立；痛了没人懂，你要扛起压力抱怨的话不要说。有些委屈，是说不出来的。即使有人问，也不知从何说起；就算有人疼，也代替不了自己。嘴里有话却说不出，沉默代表了一切；心中有疼却表不明，泪水倾诉着所有。一些经历，只有自己感受；一些心情，只有自己懂得。说不出的委屈，才最委屈；心里的疼痛，才最疼痛！总是为别人着想，却要独自去疗伤；一直在嘴上逞强，心却没那么坚强。";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);


        TextView textView=findViewById(R.id.text2);
        textView.setText(R.string.text);

        XRichText richText=findViewById(R.id.richText);
        richText.callback(new XRichText.BaseClickCallback() {
            @Override
            public boolean onLinkClick(String url) {
                showMsg(url);
                return true;
            }
            @Override
            public void onImageClick(List<String> urlList, int position) {
                super.onImageClick(urlList, position);
                showMsg("图片:" + position);
            }
            @Override
            public void onFix(XRichText.ImageHolder holder) {
                super.onFix(holder);
                if (holder.getPosition() % 3 == 0) {
                    holder.setStyle(XRichText.Style.LEFT);
                } else if (holder.getPosition() % 3 == 1) {
                    holder.setStyle(XRichText.Style.CENTER);
                } else {
                    holder.setStyle(XRichText.Style.RIGHT);
                }

                //设置宽高
                holder.setWidth(550);
                holder.setHeight(400);
            }
        })
                //如果不设置，有默认的下载器
                .imageDownloader(new ImageLoader() {
                    @Override
                    public Bitmap getBitmap(String url) throws IOException {
                        return null;
                    }
                })
                .text(TEXT);




    }

    private void showMsg(String url) {
        Toast.makeText(TextActivity.this, url, Toast.LENGTH_SHORT).show();
    }

}
