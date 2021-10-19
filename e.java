package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.b;

public class e extends b {
    @Override // androidx.fragment.app.b
    public Dialog onCreateDialog(Bundle bundle) {
        return new d(getContext(), getTheme());
    }

    @Override // androidx.fragment.app.b
    public void setupDialog(Dialog dialog, int i) {
        if (dialog instanceof d) {
            d dVar = (d) dialog;
            switch (i) {
                case 1:
                case 2:
                    break;
                default:
                    return;
                case 3:
                    dialog.getWindow().addFlags(24);
                    break;
            }
            dVar.supportRequestWindowFeature(1);
            return;
        }
        super.setupDialog(dialog, i);
    }
}
