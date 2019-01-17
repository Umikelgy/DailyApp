package com.example.a10068921.myapplication.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import cn.droidlover.xrichtext.XRichText;
import com.example.a10068921.myapplication.R;
import ezy.ui.view.NoticeView;

import static com.example.a10068921.myapplication.common.Utils.getList;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/17 10:18
 **/
public class TextDialog extends Dialog {
    private String massages;
    private XRichText xRichText;
    private NoticeView vNoticeView;
    public TextDialog(@NonNull Context context,String massages) {
        super(context);
        this.massages=massages;
    }

    public TextDialog(@NonNull Context context, int themeResId, String massages) {
        super(context, themeResId);
        this.massages = massages;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);

        xRichText=findViewById(R.id.richText);
        xRichText.text(massages);

        vNoticeView =findViewById(R.id.notice);
        vNoticeView.start(getList(massages));
    }
}
