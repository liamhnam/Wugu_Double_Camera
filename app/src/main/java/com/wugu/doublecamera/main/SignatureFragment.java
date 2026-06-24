package com.wugu.doublecamera.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.SignCommand;
import com.wugu.doublecamera.custom.CircleView;
import com.wugu.doublecamera.custom.ColorPickerView;
import com.wugu.doublecamera.databinding.FragmentSignatureBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.SignatureVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.ViewUtil;

public class SignatureFragment extends ABaseFragment<FragmentSignatureBinding, SignatureVModel> {
    private int currentSize;
    private int customColor;
    private MainVModel mainVModel;
    private View selectedColorView;
    private final SignCommand signCommand = new SignCommand();

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initListener();
        initObserver();
    }

    @Override
    public void onDestroyView() {
        this.mainVModel = null;
        super.onDestroyView();
    }

    private void initView() {
        ViewUtil.setImageDrawable(((FragmentSignatureBinding) this.mViewBinding).ivBg, UiPosIndexEnum.PRINT_SIGN_BG);
        if (AppConfig.isBelongHeadSys()) {
            ViewUtil.setImageViewGray(((FragmentSignatureBinding) this.mViewBinding).ivCustomColor, true, 0.0f);
        } else if (App.mSystemMode == 2) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentSignatureBinding) this.mViewBinding).layoutContent.getLayoutParams();
            layoutParams.topMargin = 0;
            ((FragmentSignatureBinding) this.mViewBinding).layoutContent.setLayoutParams(layoutParams);
        }
        ViewUtil.setImageDrawable(((FragmentSignatureBinding) this.mViewBinding).btnUndo, UiPosIndexEnum.PRINT_SIGN_UNDO);
        ViewUtil.setImageDrawable(((FragmentSignatureBinding) this.mViewBinding).btnRedo, UiPosIndexEnum.PRINT_SIGN_REDO);
        ViewUtil.setImageDrawable(((FragmentSignatureBinding) this.mViewBinding).btnClear, UiPosIndexEnum.PRINT_SIGN_CLEAR);
        ViewUtil.setImageDrawable(((FragmentSignatureBinding) this.mViewBinding).ivCustomColor, UiPosIndexEnum.PRINT_SIGN_CUSTOM);
        ViewUtil.setImageDrawable(((FragmentSignatureBinding) this.mViewBinding).ivSize, UiPosIndexEnum.PRINT_SIGN_SIZE);
        this.currentSize = 1;
        CircleView circleView = ((FragmentSignatureBinding) this.mViewBinding).viewBlack;
        this.selectedColorView = circleView;
        changeDrawColor(circleView);
        changePaintSize(1);
    }

    private void initListener() {
        ((FragmentSignatureBinding) this.mViewBinding).btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1026xc8620e8d(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1027x81d99c2c(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1028x3b5129cb(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).ivSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1029xf4c8b76a(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewSize1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1030xae404509(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewSize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1031x67b7d2a8(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewSize3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1032x212f6047(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewSize4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1033xdaa6ede6(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).viewPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.changeDrawColor(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).ivCustomColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1034x941e7b85(view);
            }
        });
        ((FragmentSignatureBinding) this.mViewBinding).layoutColorPicker.setOnColorPickerChangerListener(new ColorPickerView.OnColorPickerChangerListener() {
            @Override
            public final void onColorPickerChanger(int i, int i2, int i3, int i4) {
                this.f$0.m1035x4d960924(i, i2, i3, i4);
            }
        });
    }

    void m1026xc8620e8d(View view) {
        AnimUtil.startBtnClickAnim(view);
        this.signCommand.operate = 1;
        this.mainVModel.signCommand.postValue(this.signCommand);
    }

    void m1027x81d99c2c(View view) {
        AnimUtil.startBtnClickAnim(view);
        this.signCommand.operate = 2;
        this.mainVModel.signCommand.postValue(this.signCommand);
    }

    void m1028x3b5129cb(View view) {
        AnimUtil.startBtnClickAnim(view);
        this.signCommand.operate = 5;
        this.mainVModel.signCommand.postValue(this.signCommand);
    }

    void m1029xf4c8b76a(View view) {
        AnimUtil.startBtnClickAnim(view);
        int i = this.currentSize + 1;
        this.currentSize = i;
        if (i > 4) {
            this.currentSize = 1;
        }
        changePaintSize(this.currentSize);
    }

    void m1030xae404509(View view) {
        clickSizePoint(1);
    }

    void m1031x67b7d2a8(View view) {
        clickSizePoint(2);
    }

    void m1032x212f6047(View view) {
        clickSizePoint(3);
    }

    void m1033xdaa6ede6(View view) {
        clickSizePoint(4);
    }

    void m1034x941e7b85(View view) {
        AnimUtil.startBtnClickAnim(view);
        View view2 = this.selectedColorView;
        if (view2 != null) {
            view2.setBackgroundColor(Color.parseColor("#16333333"));
        }
        ((FragmentSignatureBinding) this.mViewBinding).layoutColorPicker.setEnabled(true);
        this.selectedColorView = null;
        if (AppConfig.isBelongHeadSys()) {
            ViewUtil.setImageViewGray(((FragmentSignatureBinding) this.mViewBinding).ivCustomColor, false, 1.0f);
        }
        postDrawColor(this.customColor);
    }

    void m1035x4d960924(int i, int i2, int i3, int i4) {
        this.customColor = i;
        if (this.selectedColorView == null) {
            postDrawColor(i);
        }
    }

    private void initObserver() {
        this.mainVModel.signPaintUndoRedo.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1036x53bb10ba((Integer) obj);
            }
        });
    }

    void m1036x53bb10ba(Integer num) {
        if (num.intValue() == 0) {
            ((FragmentSignatureBinding) this.mViewBinding).btnRedo.setEnabled(false);
            ((FragmentSignatureBinding) this.mViewBinding).btnUndo.setEnabled(false);
            return;
        }
        if (num.intValue() == 1) {
            ((FragmentSignatureBinding) this.mViewBinding).btnRedo.setEnabled(false);
            ((FragmentSignatureBinding) this.mViewBinding).btnUndo.setEnabled(true);
        } else if (num.intValue() == 2) {
            ((FragmentSignatureBinding) this.mViewBinding).btnRedo.setEnabled(true);
            ((FragmentSignatureBinding) this.mViewBinding).btnUndo.setEnabled(false);
        } else if (num.intValue() == 3) {
            ((FragmentSignatureBinding) this.mViewBinding).btnRedo.setEnabled(true);
            ((FragmentSignatureBinding) this.mViewBinding).btnUndo.setEnabled(true);
        }
    }

    private void clickSizePoint(int i) {
        this.currentSize = i;
        changePaintSize(i);
    }

    private void changePaintSize(int i) {
        int i2;
        ((FragmentSignatureBinding) this.mViewBinding).viewSize1.setBackgroundColor(Color.parseColor("#F2F2F2"));
        ((FragmentSignatureBinding) this.mViewBinding).viewSize2.setBackgroundColor(Color.parseColor("#F2F2F2"));
        ((FragmentSignatureBinding) this.mViewBinding).viewSize3.setBackgroundColor(Color.parseColor("#F2F2F2"));
        ((FragmentSignatureBinding) this.mViewBinding).viewSize4.setBackgroundColor(Color.parseColor("#F2F2F2"));
        if (i != 1) {
            i2 = 2;
            if (i == 2) {
                ((FragmentSignatureBinding) this.mViewBinding).viewSize2.setBackgroundColor(Color.parseColor("#C1C1C1"));
                i2 = 6;
            } else if (i == 3) {
                ((FragmentSignatureBinding) this.mViewBinding).viewSize3.setBackgroundColor(Color.parseColor("#C1C1C1"));
                i2 = 4;
            } else if (i != 4) {
                i2 = 20;
            } else {
                ((FragmentSignatureBinding) this.mViewBinding).viewSize4.setBackgroundColor(Color.parseColor("#C1C1C1"));
            }
        } else {
            ((FragmentSignatureBinding) this.mViewBinding).viewSize1.setBackgroundColor(Color.parseColor("#C1C1C1"));
            i2 = 8;
        }
        this.signCommand.paintSize = i2;
        this.signCommand.operate = 3;
        this.mainVModel.signCommand.postValue(this.signCommand);
    }

    public void changeDrawColor(View view) {
        ((FragmentSignatureBinding) this.mViewBinding).layoutColorPicker.setEnabled(false);
        View view2 = this.selectedColorView;
        if (view2 == null) {
            ViewUtil.setImageViewGray(((FragmentSignatureBinding) this.mViewBinding).ivCustomColor, true, 0.0f);
        } else {
            view2.setBackgroundColor(Color.parseColor("#F2F2F2"));
        }
        this.selectedColorView = view;
        AppCompatImageView appCompatImageView = ((FragmentSignatureBinding) this.mViewBinding).ivCustomColor;
        View view3 = this.selectedColorView;
        if (appCompatImageView == view3) {
            ViewUtil.setImageViewGray(((FragmentSignatureBinding) this.mViewBinding).ivCustomColor, false, 1.0f);
        } else {
            view3.setBackgroundColor(Color.parseColor("#C1C1C1"));
        }
        postDrawColor(((CircleView) view).getColor());
    }

    private void postDrawColor(int i) {
        this.signCommand.paintColor = i;
        this.signCommand.operate = 3;
        this.mainVModel.signCommand.postValue(this.signCommand);
    }
}
