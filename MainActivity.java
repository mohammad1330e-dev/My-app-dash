package com.rubina.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    LinearLayout root, content, nav;
    boolean dark = false;
    int purple = Color.rgb(108,74,182);
    GradientDrawable bg(int color, float radius){ GradientDrawable g=new GradientDrawable(); g.setColor(color); g.setCornerRadius(radius); return g; }
    TextView tv(String s,float size,boolean bold){ TextView v=new TextView(this); v.setText(s); v.setTextSize(size); v.setTextColor(dark?Color.WHITE:Color.DKGRAY); v.setGravity(Gravity.CENTER_VERTICAL); if(bold)v.setTypeface(Typeface.DEFAULT,Typeface.BOLD); v.setPadding(18,12,18,12); return v; }
    Button button(String s){ Button b=new Button(this); b.setText(s); b.setTextSize(12); b.setAllCaps(false); return b; }
    @Override public void onCreate(Bundle b){ super.onCreate(b); showHome(); }
    void base(String title){
        root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(dark?Color.rgb(28,26,34):Color.rgb(250,249,252));
        content=new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL); content.setPadding(12,8,12,8);
        root.addView(content,new LinearLayout.LayoutParams(-1,0,1));
        nav=new LinearLayout(this); nav.setGravity(Gravity.CENTER); nav.setBackgroundColor(dark?Color.rgb(38,35,46):Color.WHITE);
        String[] names={"خانه","پیام‌ها","تماس","روبینا AI","پروفایل"};
        for(int i=0;i<names.length;i++){ final int n=i; Button x=button(names[i]); x.setOnClickListener(v->{if(n==0)showHome();else if(n==1)showMessages();else if(n==2)showCalls();else if(n==3)showAI();else showProfile();}); nav.addView(x,new LinearLayout.LayoutParams(0,62,1)); }
        root.addView(nav,new LinearLayout.LayoutParams(-1,62)); setContentView(root); content.addView(tv(title,25,true),new LinearLayout.LayoutParams(-1,66));
    }
    TextView card(String s){ TextView v=tv(s,16,true); v.setBackground(bg(dark?Color.rgb(52,48,62):Color.rgb(241,238,247),28)); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,74);p.setMargins(4,6,4,6);v.setLayoutParams(p);return v; }
    void showHome(){ base("روبینا"); EditText s=new EditText(this); s.setHint("جست‌وجو در روبینا"); s.setSingleLine(); s.setBackground(bg(dark?Color.rgb(52,48,62):Color.rgb(242,240,247),55)); content.addView(s,new LinearLayout.LayoutParams(-1,56)); content.addView(tv("کانال‌های پیشنهادی",19,true)); for(String c:new String[]{"روبینا نیوز","دنیای فناوری","ورزش امروز"}) content.addView(card(c+"\nپست جدید منتشر شده است")); }
    void showMessages(){ base("پیام‌ها"); content.addView(tv("گفت‌وگوهای اخیر",19,true)); for(String n:new String[]{"علی","سارا","گروه دوستان","پشتیبانی روبینا"}){ TextView v=card(n+"                                      ۱۲:۳۰\nآخرین پیام نمونه"); content.addView(v); } }
    void showCalls(){ base("تماس‌ها"); content.addView(tv("تماس‌های اخیر",20,true)); content.addView(tv("یک مخاطب را انتخاب کنید و نوع تماس را مشخص کنید.",16,false)); LinearLayout row=new LinearLayout(this); Button a=button("📞 تماس صوتی"), b=button("📹 تماس تصویری"); a.setOnClickListener(v->toast("تماس صوتی آماده است")); b.setOnClickListener(v->toast("تماس تصویری آماده است")); row.addView(a,new LinearLayout.LayoutParams(0,62,1));row.addView(b,new LinearLayout.LayoutParams(0,62,1));content.addView(row); }
    void showAI(){ base("روبینا AI"); TextView m=card("سلام! من روبینا AI هستم.\nپیامت را بنویس تا پاسخ نمونه دریافت کنی."); content.addView(m); content.addView(new Space(this),new LinearLayout.LayoutParams(1,0,1)); LinearLayout row=new LinearLayout(this); EditText in=new EditText(this); in.setHint("از روبینا بپرس..."); in.setSingleLine(); Button send=button("ارسال"); send.setOnClickListener(v->{ if(in.getText().length()>0){ Toast.makeText(this,"پاسخ روبینا AI: پیام شما دریافت شد.",Toast.LENGTH_LONG).show(); in.setText(""); }}); row.addView(in,new LinearLayout.LayoutParams(0,62,1));row.addView(send,new LinearLayout.LayoutParams(92,62));content.addView(row); }
    void showProfile(){ base("پروفایل"); content.addView(card("👤  کاربر روبینا\n@rubina_user")); Switch d=new Switch(this); d.setText("حالت تاریک"); d.setTextSize(17); d.setTextColor(dark?Color.WHITE:Color.DKGRAY); d.setChecked(dark); d.setOnCheckedChangeListener((v,c)->{dark=c;showProfile();});content.addView(d); content.addView(card("⚙  تنظیمات حساب")); content.addView(card("🔒 امنیت و حریم خصوصی")); content.addView(card("ℹ  درباره روبینا")); }
    void toast(String s){ Toast.makeText(this,s,Toast.LENGTH_SHORT).show(); }
}
