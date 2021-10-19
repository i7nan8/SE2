package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertController;

public class a extends d implements DialogInterface {
    final AlertController a = new AlertController(getContext(), this, getWindow());

    protected a(Context context, int i) {
        super(context, a(context, i));
    }

    static int a(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // androidx.appcompat.app.d, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.a.a(charSequence);
    }

    public void a(View view) {
        this.a.c(view);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.d
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a.a();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.a.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.a.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    /* renamed from: androidx.appcompat.app.a$a  reason: collision with other inner class name */
    public static class C0015a {
        private final AlertController.a a;
        private final int b;

        public C0015a(Context context) {
            this(context, a.a(context, 0));
        }

        public C0015a(Context context, int i) {
            this.a = new AlertController.a(new ContextThemeWrapper(context, a.a(context, i)));
            this.b = i;
        }

        public Context a() {
            return this.a.a;
        }

        public C0015a a(int i) {
            AlertController.a aVar = this.a;
            aVar.f = aVar.a.getText(i);
            return this;
        }

        public C0015a a(CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }

        public C0015a a(View view) {
            this.a.g = view;
            return this;
        }

        public C0015a b(int i) {
            AlertController.a aVar = this.a;
            aVar.h = aVar.a.getText(i);
            return this;
        }

        public C0015a b(CharSequence charSequence) {
            this.a.h = charSequence;
            return this;
        }

        public C0015a c(int i) {
            this.a.c = i;
            return this;
        }

        public C0015a a(Drawable drawable) {
            this.a.d = drawable;
            return this;
        }

        public C0015a a(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.i = aVar.a.getText(i);
            this.a.k = onClickListener;
            return this;
        }

        public C0015a a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.i = charSequence;
            aVar.k = onClickListener;
            return this;
        }

        public C0015a b(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.l = aVar.a.getText(i);
            this.a.n = onClickListener;
            return this;
        }

        public C0015a b(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.l = charSequence;
            aVar.n = onClickListener;
            return this;
        }

        public C0015a a(DialogInterface.OnKeyListener onKeyListener) {
            this.a.u = onKeyListener;
            return this;
        }

        public C0015a a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.a aVar = this.a;
            aVar.w = listAdapter;
            aVar.x = onClickListener;
            return this;
        }

        public C0015a b(View view) {
            AlertController.a aVar = this.a;
            aVar.z = view;
            aVar.y = 0;
            aVar.E = false;
            return this;
        }

        public a b() {
            a aVar = new a(this.a.a, this.b);
            this.a.a(aVar.a);
            aVar.setCancelable(this.a.r);
            if (this.a.r) {
                aVar.setCanceledOnTouchOutside(true);
            }
            aVar.setOnCancelListener(this.a.s);
            aVar.setOnDismissListener(this.a.t);
            if (this.a.u != null) {
                aVar.setOnKeyListener(this.a.u);
            }
            return aVar;
        }
    }
}
