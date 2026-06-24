package com.wugu.doublecamera.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentAdminBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.AdminVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.service.MainService;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.ToastHelper;

public class AdminFragment extends ABaseFragment<FragmentAdminBinding, AdminVModel> {
    private static final int CHECK_FRAME_COUNTS = 5;
    private static final long COUNT_DURATION = 6000;
    private MainVModel mainVModel;
    private boolean isExist = false;
    private boolean isChangePwd = false;
    private long[] countHits = new long[5];

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initListener();
        initObserver();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AdminVModel) this.mViewModel).cancelCountdown();
        this.mainVModel = null;
        if (this.isExist) {
            AppUtil.setSystemBar(true);
            AppUtil.unKeepActivity();
        } else {
            AppUtil.setSystemBar(false);
            AppUtil.keepActivity(requireActivity().getPackageName());
        }
    }

    private void initView() {
        ViewUtil.setViewGroupBg(((FragmentAdminBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.ADMIN_PWD_BG);
        ViewUtil.setImageDrawable(((FragmentAdminBinding) this.mViewBinding).btnBack, UiPosIndexEnum.ADMIN_BTN_BACK);
        ViewUtil.setImageDrawable(((FragmentAdminBinding) this.mViewBinding).ivExit, UiPosIndexEnum.ADMIN_BTN_EXIT);
        ViewUtil.setImageDrawable(((FragmentAdminBinding) this.mViewBinding).ivPowerOff, UiPosIndexEnum.ADMIN_BTN_OFF);
        ViewUtil.setImageDrawable(((FragmentAdminBinding) this.mViewBinding).ivReboot, UiPosIndexEnum.ADMIN_BTN_REBOOT);
        ViewUtil.setImageDrawable(((FragmentAdminBinding) this.mViewBinding).ivChangePwd, UiPosIndexEnum.ADMIN_BTN_C_PWD);
        ((AdminVModel) this.mViewModel).startCountdown(SpManager.getInstance().getAdminTime());
        ((FragmentAdminBinding) this.mViewBinding).tvVersion.setText("130   1.14.1\n" + App.mCPUSerial + " ");
        if (App.mIsDownApk) {
            ((FragmentAdminBinding) this.mViewBinding).tvVersion.append(getString(C1910R.string.downing_apk));
        }
        ((FragmentAdminBinding) this.mViewBinding).cbLockScreen.setChecked(SpManager.getInstance().getIsLockScreen());
        ((FragmentAdminBinding) this.mViewBinding).cbPeShowDate.setChecked(SpManager.getInstance().getIsPeShowDate());
        ((FragmentAdminBinding) this.mViewBinding).barBlur.setProgress(App.mTestCertifyBlur);
        ((FragmentAdminBinding) this.mViewBinding).barWhite.setProgress(App.mTestCertifyWhite);
    }

    private void initListener() {
        ((FragmentAdminBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1611lambda$initListener$0$comwugudoublecameramainAdminFragment(view);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).vCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1612lambda$initListener$1$comwugudoublecameramainAdminFragment(view);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).vCheck.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public final boolean onLongClick(View view) {
                return this.f$0.m1613lambda$initListener$2$comwugudoublecameramainAdminFragment(view);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).rgServer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.m1614lambda$initListener$3$comwugudoublecameramainAdminFragment(radioGroup, i);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).layoutNumKeyboard.setEdittext(((FragmentAdminBinding) this.mViewBinding).etInputCode, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m1615lambda$initListener$4$comwugudoublecameramainAdminFragment(str);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1616lambda$initListener$6$comwugudoublecameramainAdminFragment(view);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).ivPowerOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1617lambda$initListener$7$comwugudoublecameramainAdminFragment(view);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).ivReboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1618lambda$initListener$8$comwugudoublecameramainAdminFragment(view);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).ivChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1619lambda$initListener$9$comwugudoublecameramainAdminFragment(view);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).cbLockScreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SpManager.getInstance().setIsLockScreen(z);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).cbPeShowDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SpManager.getInstance().setIsPeShowDate(z);
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).barBlur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                App.mTestCertifyBlur = seekBar.getProgress();
            }
        });
        ((FragmentAdminBinding) this.mViewBinding).barWhite.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                App.mTestCertifyWhite = seekBar.getProgress();
            }
        });
    }

    void m1611lambda$initListener$0$comwugudoublecameramainAdminFragment(View view) {
        requireActivity().onBackPressed();
    }

    void m1612lambda$initListener$1$comwugudoublecameramainAdminFragment(View view) {
        long[] jArr = this.countHits;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.countHits;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        if (this.countHits[0] >= SystemClock.uptimeMillis() - COUNT_DURATION) {
            this.countHits = new long[5];
            ((AdminVModel) this.mViewModel).cancelCountdown();
            this.mainVModel.fragmentLD.postValue(7);
        }
    }

    boolean m1613lambda$initListener$2$comwugudoublecameramainAdminFragment(View view) {
        String usbPath = AppUtil.getUsbPath();
        if (usbPath == null || usbPath.isEmpty()) {
            Toast.makeText(requireContext(), "未检测到U盘", 0).show();
        } else {
            ((AdminVModel) this.mViewModel).cancelCountdown();
            this.mainVModel.fragmentLD.postValue(13);
        }
        return false;
    }

    void m1614lambda$initListener$3$comwugudoublecameramainAdminFragment(RadioGroup radioGroup, int i) {
        if (i == ((FragmentAdminBinding) this.mViewBinding).rbtnNone.getId()) {
            SpManager.getInstance().setTestServerSelect(0);
            return;
        }
        if (i == ((FragmentAdminBinding) this.mViewBinding).rbtnTest.getId()) {
            SpManager.getInstance().setTestServerSelect(1);
        } else if (i == ((FragmentAdminBinding) this.mViewBinding).rbtnLocal.getId()) {
            SpManager.getInstance().setTestServerSelect(2);
        } else if (i == ((FragmentAdminBinding) this.mViewBinding).rbtnForeign.getId()) {
            SpManager.getInstance().setTestServerSelect(3);
        }
    }

    void m1615lambda$initListener$4$comwugudoublecameramainAdminFragment(String str) {
        if (this.isChangePwd) {
            changePwd(str);
        } else {
            ((AdminVModel) this.mViewModel).checkPwd(str);
        }
    }

    void m1616lambda$initListener$6$comwugudoublecameramainAdminFragment(View view) {
        this.isExist = true;
        AppUtil.setSystemBar(true);
        AppUtil.unKeepActivity();
        requireActivity().stopService(new Intent(requireContext(), (Class<?>) MainService.class));
        ToastHelper.getInstance().showToast(getString(C1910R.string.exiting));
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                System.exit(0);
            }
        }, 500L);
    }

    void m1617lambda$initListener$7$comwugudoublecameramainAdminFragment(View view) {
        ((AdminVModel) this.mViewModel).powerOff();
    }

    void m1618lambda$initListener$8$comwugudoublecameramainAdminFragment(View view) {
        ((AdminVModel) this.mViewModel).reboot();
    }

    void m1619lambda$initListener$9$comwugudoublecameramainAdminFragment(View view) {
        ((FragmentAdminBinding) this.mViewBinding).groupItem.setVisibility(8);
        ((FragmentAdminBinding) this.mViewBinding).groupInput.setVisibility(0);
        ((FragmentAdminBinding) this.mViewBinding).etInputCode.setHint(C1910R.string.input_new_pwd);
        this.isChangePwd = true;
    }

    private void initObserver() {
        ((AdminVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1620lambda$initObserver$12$comwugudoublecameramainAdminFragment((Integer) obj);
            }
        });
        ((AdminVModel) this.mViewModel).getPwdVerifyLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1621lambda$initObserver$13$comwugudoublecameramainAdminFragment((Integer) obj);
            }
        });
    }

    void m1620lambda$initObserver$12$comwugudoublecameramainAdminFragment(Integer num) {
        ((FragmentAdminBinding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        if (num.intValue() == 0) {
            requireActivity().onBackPressed();
        }
    }

    void m1621lambda$initObserver$13$comwugudoublecameramainAdminFragment(Integer num) {
        if (num.intValue() == 1) {
            ViewUtil.setViewGroupBg(((FragmentAdminBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.ADMIN_BG);
            ((FragmentAdminBinding) this.mViewBinding).etInputCode.setText((CharSequence) null);
            ((FragmentAdminBinding) this.mViewBinding).groupInput.setVisibility(8);
            ((FragmentAdminBinding) this.mViewBinding).groupItem.setVisibility(0);
            ((FragmentAdminBinding) this.mViewBinding).cbLockScreen.setVisibility(0);
            if (App.mSystemMode == 3) {
                ((FragmentAdminBinding) this.mViewBinding).cbPeShowDate.setVisibility(0);
            }
            ((FragmentAdminBinding) this.mViewBinding).vCheck.setVisibility(0);
            return;
        }
        ToastHelper.getInstance().showToast(getString(C1910R.string.pwd_error));
    }

    private void changePwd(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 4) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.pwn_format_error));
            return;
        }
        SpManager.getInstance().setAdminPwd(StringUtil.getSha256(str, AppConfig.PWD_SALT));
        ToastHelper.getInstance().showToast(getString(C1910R.string.change_pwd_ok));
        ((FragmentAdminBinding) this.mViewBinding).etInputCode.setText((CharSequence) null);
        ((FragmentAdminBinding) this.mViewBinding).groupInput.setVisibility(8);
        ((FragmentAdminBinding) this.mViewBinding).groupItem.setVisibility(0);
    }
}
