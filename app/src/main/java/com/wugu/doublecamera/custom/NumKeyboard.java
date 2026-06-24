package com.wugu.doublecamera.custom;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.wugu.doublecamera.databinding.LayoutNumKeyboardBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.SoundHelper;

public class NumKeyboard extends ConstraintLayout {
    private int MAX_EMS;
    private LayoutNumKeyboardBinding binding;
    private AppCompatEditText editText;
    private IStringListener listener;

    public NumKeyboard(Context context) {
        super(context);
        this.MAX_EMS = 12;
        initView(context);
    }

    public NumKeyboard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.MAX_EMS = 12;
        initView(context);
    }

    private void initView(Context context) {
        LayoutNumKeyboardBinding layoutNumKeyboardBindingInflate = LayoutNumKeyboardBinding.inflate(LayoutInflater.from(context), this, true);
        this.binding = layoutNumKeyboardBindingInflate;
        ViewUtil.setUiLocate(layoutNumKeyboardBindingInflate.button0, UiPosIndexEnum.KEYBOARD_0);
        ViewUtil.setUiLocate(this.binding.button1, UiPosIndexEnum.KEYBOARD_1);
        ViewUtil.setUiLocate(this.binding.button2, UiPosIndexEnum.KEYBOARD_2);
        ViewUtil.setUiLocate(this.binding.button3, UiPosIndexEnum.KEYBOARD_3);
        ViewUtil.setUiLocate(this.binding.button4, UiPosIndexEnum.KEYBOARD_4);
        ViewUtil.setUiLocate(this.binding.button5, UiPosIndexEnum.KEYBOARD_5);
        ViewUtil.setUiLocate(this.binding.button6, UiPosIndexEnum.KEYBOARD_6);
        ViewUtil.setUiLocate(this.binding.button7, UiPosIndexEnum.KEYBOARD_7);
        ViewUtil.setUiLocate(this.binding.button8, UiPosIndexEnum.KEYBOARD_8);
        ViewUtil.setUiLocate(this.binding.button9, UiPosIndexEnum.KEYBOARD_9);
        ViewUtil.setUiLocate(this.binding.buttonDelete, UiPosIndexEnum.KEYBOARD_DEL);
        ViewUtil.setUiLocate(this.binding.buttonConfirm, UiPosIndexEnum.KEYBOARD_OK);
        initListener();
    }

    public void setEdittext(AppCompatEditText appCompatEditText, IStringListener iStringListener) {
        this.editText = appCompatEditText;
        this.listener = iStringListener;
        for (InputFilter inputFilter : appCompatEditText.getFilters()) {
            if (inputFilter instanceof InputFilter.LengthFilter) {
                this.MAX_EMS = ((InputFilter.LengthFilter) inputFilter).getMax();
                return;
            }
        }
    }

    private void initListener() {
        this.binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1585lambda$initListener$0$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1586lambda$initListener$1$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1587lambda$initListener$2$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1588lambda$initListener$3$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1589lambda$initListener$4$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1590lambda$initListener$5$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1591lambda$initListener$6$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1592lambda$initListener$7$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1593lambda$initListener$8$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1594lambda$initListener$9$comwugudoublecameracustomNumKeyboard(view);
            }
        });
        this.binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.deleteNum(view);
            }
        });
        this.binding.buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.confirm(view);
            }
        });
    }

    void m1585lambda$initListener$0$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 0);
    }

    void m1586lambda$initListener$1$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 1);
    }

    void m1587lambda$initListener$2$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 2);
    }

    void m1588lambda$initListener$3$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 3);
    }

    void m1589lambda$initListener$4$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 4);
    }

    void m1590lambda$initListener$5$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 5);
    }

    void m1591lambda$initListener$6$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 6);
    }

    void m1592lambda$initListener$7$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 7);
    }

    void m1593lambda$initListener$8$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 8);
    }

    void m1594lambda$initListener$9$comwugudoublecameracustomNumKeyboard(View view) {
        insertNum(view, 9);
    }

    private void insertNum(View view, int i) {
        SoundHelper.getInstance().playSoundEffect(3);
        AnimUtil.startBtnClickAnim(view);
        AppCompatEditText appCompatEditText = this.editText;
        if (appCompatEditText == null || appCompatEditText.length() == this.MAX_EMS) {
            return;
        }
        int selectionStart = this.editText.getSelectionStart();
        String string = this.editText.getText().toString();
        this.editText.setText(string.substring(0, selectionStart) + i + string.substring(selectionStart));
        this.editText.setSelection(selectionStart + 1);
    }

    public void deleteNum(View view) {
        int selectionStart;
        SoundHelper.getInstance().playSoundEffect(3);
        AnimUtil.startBtnClickAnim(view);
        AppCompatEditText appCompatEditText = this.editText;
        if (appCompatEditText != null && (selectionStart = appCompatEditText.getSelectionStart()) > 0) {
            this.editText.getText().delete(selectionStart - 1, selectionStart);
        }
    }

    public void confirm(View view) {
        AppCompatEditText appCompatEditText;
        SoundHelper.getInstance().playSoundEffect(3);
        AnimUtil.startBtnClickAnim(view);
        IStringListener iStringListener = this.listener;
        if (iStringListener == null || (appCompatEditText = this.editText) == null) {
            return;
        }
        iStringListener.setValue(appCompatEditText.getText().toString());
    }
}
