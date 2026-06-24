package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import androidx.core.view.ViewCompat;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.BtnBackCountdown;
import com.wugu.doublecamera.custom.CircleView;
import com.wugu.doublecamera.databinding.DialogAddTextBinding;

public class AddTextDialog extends Dialog {
    private static final int MIN_SIZE = 20;
    private final DialogAddTextBinding binding;
    private int color;
    private int size;

    public interface ITextInfoListener {
        void setValue(String str, int i, int i2);
    }

    public AddTextDialog(Context context, final ITextInfoListener iTextInfoListener) {
        super(context, C1910R.style.publicDialog);
        this.color = ViewCompat.MEASURED_STATE_MASK;
        DialogAddTextBinding dialogAddTextBindingInflate = DialogAddTextBinding.inflate(LayoutInflater.from(context));
        this.binding = dialogAddTextBindingInflate;
        setContentView(dialogAddTextBindingInflate.getRoot());
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
        }
        dialogAddTextBindingInflate.layoutBtnCountdown.setListener(new BtnBackCountdown.ICountdownEndListener() {
            @Override
            public final void countdownEnd() {
                this.f$0.dismiss();
            }
        });
        dialogAddTextBindingInflate.layoutBtnCountdown.startCountdown(60);
        this.size = (dialogAddTextBindingInflate.seekbarFontSize.getProgress() * 2) + 20;
        dialogAddTextBindingInflate.etInput.setTextSize(this.size);
        dialogAddTextBindingInflate.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1602lambda$new$0$comwugudoublecameradialogAddTextDialog(iTextInfoListener, view);
            }
        });
        dialogAddTextBindingInflate.viewBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        dialogAddTextBindingInflate.viewWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        dialogAddTextBindingInflate.viewRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        dialogAddTextBindingInflate.viewBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        dialogAddTextBindingInflate.viewYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        dialogAddTextBindingInflate.viewGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        dialogAddTextBindingInflate.viewPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        dialogAddTextBindingInflate.seekbarFontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                AddTextDialog.this.size = (i * 2) + 20;
                AddTextDialog.this.binding.etInput.setTextSize(AddTextDialog.this.size);
            }
        });
    }

    void m1602lambda$new$0$comwugudoublecameradialogAddTextDialog(ITextInfoListener iTextInfoListener, View view) {
        iTextInfoListener.setValue(this.binding.etInput.getText().toString(), this.size + 30, this.color);
        dismiss();
    }

    public void changeDrawColor(View view) {
        this.color = ((CircleView) view).getColor();
        this.binding.etInput.setTextColor(this.color);
    }
}
