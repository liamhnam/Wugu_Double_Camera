package com.wugu.doublecamera.main;

import android.R;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.databinding.FragmentInitBoardBinding;
import com.wugu.doublecamera.main.p025vm.AdminVModel;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.ToastHelper;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class InitBoardFragment extends ABaseFragment<FragmentInitBoardBinding, AdminVModel> {
    private String cpSn;
    private String downPath;
    private boolean isUsaServer;

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((FragmentInitBoardBinding) this.mViewBinding).tvTitle.setText(getString(C1910R.string.init_res_title));
        this.isUsaServer = true;
        ((FragmentInitBoardBinding) this.mViewBinding).tvServerName.setText("美国服务器 \n" + AppUtil.getCPUSerial());
        initHeadSysData();
        initPosterEasyData();
        initPosterData();
        initListener();
        HttpManager.getInstance().init();
    }

    private void initHeadSysData() {
        List listAsList;
        String[] strArr;
        if (this.isUsaServer) {
            listAsList = Arrays.asList("繁英双语-5555555555555555", "英语-3333333333333333", "越南-4444444444444444", "马来西亚-7777777777777777", "其它");
            strArr = new String[]{"繁体-1111111111111111", "俄语-7777777777777778", "西班牙-7777777777777779", "波兰-3333333333333334"};
        } else {
            listAsList = Arrays.asList("繁英双语-5555555555555555", "英语-3333333333333333", "简中-0000000000000000", "香港-6666666666666666", "其它");
            strArr = new String[]{"繁体-1111111111111111", "繁英双语粉色UI-5555555555555556", "繁英双语蓝色UI-5555555555555557", "繁英双语红色UI-5555555555555558"};
        }
        for (int i = 0; i < listAsList.size(); i++) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setId(i);
            radioButton.setText((CharSequence) listAsList.get(i));
            radioButton.setTextSize(32.0f);
            radioButton.setPadding(0, 8, 0, 8);
            ((FragmentInitBoardBinding) this.mViewBinding).rgHeadSys.addView(radioButton);
        }
        ((FragmentInitBoardBinding) this.mViewBinding).rgHeadSys.check(0);
        setHeadSysBtnText((String) listAsList.get(0));
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.simple_spinner_item, strArr);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        ((FragmentInitBoardBinding) this.mViewBinding).spinnerHeadSys.setAdapter((SpinnerAdapter) arrayAdapter);
    }

    private void initPosterEasyData() {
        List listAsList;
        if (this.isUsaServer) {
            listAsList = Arrays.asList("繁体-2222222222222222", "英语-2222222222222223");
        } else {
            listAsList = Arrays.asList("简中-2222222222222222");
        }
        for (int i = 0; i < listAsList.size(); i++) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setId(i);
            radioButton.setText((CharSequence) listAsList.get(i));
            radioButton.setTextSize(32.0f);
            radioButton.setPadding(0, 8, 0, 8);
            ((FragmentInitBoardBinding) this.mViewBinding).rgPeSys.addView(radioButton);
        }
        ((FragmentInitBoardBinding) this.mViewBinding).rgPeSys.check(0);
        setPeSysBtnText((String) listAsList.get(0));
    }

    private void initPosterData() {
        List listAsList;
        if (this.isUsaServer) {
            listAsList = Arrays.asList("英文-8888888888888888");
        } else {
            listAsList = Arrays.asList("简中-8888888888888888");
        }
        for (int i = 0; i < listAsList.size(); i++) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setId(i);
            radioButton.setText((CharSequence) listAsList.get(i));
            radioButton.setTextSize(32.0f);
            radioButton.setPadding(0, 8, 0, 8);
            ((FragmentInitBoardBinding) this.mViewBinding).rgPoSys.addView(radioButton);
        }
        ((FragmentInitBoardBinding) this.mViewBinding).rgPoSys.check(0);
        setPoSysBtnText((String) listAsList.get(0));
    }

    private void initListener() {
        ((FragmentInitBoardBinding) this.mViewBinding).rgHeadSys.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.m941xca09db8b(radioGroup, i);
            }
        });
        ((FragmentInitBoardBinding) this.mViewBinding).rgPeSys.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.m942x8381692a(radioGroup, i);
            }
        });
        ((FragmentInitBoardBinding) this.mViewBinding).rgPoSys.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.m943x3cf8f6c9(radioGroup, i);
            }
        });
        ((FragmentInitBoardBinding) this.mViewBinding).spinnerHeadSys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (((FragmentInitBoardBinding) InitBoardFragment.this.mViewBinding).spinnerHeadSys.getVisibility() == 0) {
                    InitBoardFragment.this.setHeadSysBtnText(adapterView.getItemAtPosition(i).toString());
                }
            }
        });
        ((FragmentInitBoardBinding) this.mViewBinding).spinnerPeSys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (((FragmentInitBoardBinding) InitBoardFragment.this.mViewBinding).spinnerPeSys.getVisibility() == 0) {
                    InitBoardFragment.this.setPeSysBtnText(adapterView.getItemAtPosition(i).toString());
                }
            }
        });
        ((FragmentInitBoardBinding) this.mViewBinding).spinnerPoSys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (((FragmentInitBoardBinding) InitBoardFragment.this.mViewBinding).spinnerPoSys.getVisibility() == 0) {
                    InitBoardFragment.this.setPoSysBtnText(adapterView.getItemAtPosition(i).toString());
                }
            }
        });
        ((FragmentInitBoardBinding) this.mViewBinding).btnHeadSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m944xf6708468(view);
            }
        });
        ((FragmentInitBoardBinding) this.mViewBinding).btnPeSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m945xafe81207(view);
            }
        });
        ((FragmentInitBoardBinding) this.mViewBinding).btnPoSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m946x695f9fa6(view);
            }
        });
    }

    void m941xca09db8b(RadioGroup radioGroup, int i) {
        String string = ((RadioButton) ((FragmentInitBoardBinding) this.mViewBinding).getRoot().findViewById(i)).getText().toString();
        if (string.equals("其它")) {
            if (((FragmentInitBoardBinding) this.mViewBinding).spinnerHeadSys.getCount() > 0) {
                ((FragmentInitBoardBinding) this.mViewBinding).spinnerHeadSys.setSelection(0);
                ((FragmentInitBoardBinding) this.mViewBinding).spinnerHeadSys.setVisibility(0);
                setHeadSysBtnText(((FragmentInitBoardBinding) this.mViewBinding).spinnerHeadSys.getAdapter().getItem(0).toString());
                return;
            }
            return;
        }
        setHeadSysBtnText(string);
        ((FragmentInitBoardBinding) this.mViewBinding).spinnerHeadSys.setVisibility(8);
    }

    void m942x8381692a(RadioGroup radioGroup, int i) {
        String string = ((RadioButton) ((FragmentInitBoardBinding) this.mViewBinding).getRoot().findViewById(i)).getText().toString();
        if (string.equals("其它")) {
            if (((FragmentInitBoardBinding) this.mViewBinding).spinnerPeSys.getCount() > 0) {
                ((FragmentInitBoardBinding) this.mViewBinding).spinnerPeSys.setVisibility(0);
                ((FragmentInitBoardBinding) this.mViewBinding).spinnerPeSys.setSelection(0);
                setPeSysBtnText(((FragmentInitBoardBinding) this.mViewBinding).spinnerPeSys.getAdapter().getItem(0).toString());
                return;
            }
            return;
        }
        setPeSysBtnText(string);
        ((FragmentInitBoardBinding) this.mViewBinding).spinnerPeSys.setVisibility(8);
    }

    void m943x3cf8f6c9(RadioGroup radioGroup, int i) {
        String string = ((RadioButton) ((FragmentInitBoardBinding) this.mViewBinding).getRoot().findViewById(i)).getText().toString();
        if (string.equals("其它")) {
            if (((FragmentInitBoardBinding) this.mViewBinding).spinnerPoSys.getCount() > 0) {
                ((FragmentInitBoardBinding) this.mViewBinding).spinnerPoSys.setVisibility(0);
                ((FragmentInitBoardBinding) this.mViewBinding).spinnerPoSys.setSelection(0);
                setPoSysBtnText(((FragmentInitBoardBinding) this.mViewBinding).spinnerPoSys.getAdapter().getItem(0).toString());
                return;
            }
            return;
        }
        setPoSysBtnText(string);
        ((FragmentInitBoardBinding) this.mViewBinding).spinnerPoSys.setVisibility(8);
    }

    void m944xf6708468(View view) {
        startCopySys(((FragmentInitBoardBinding) this.mViewBinding).btnHeadSys.getText().toString());
    }

    void m945xafe81207(View view) {
        startCopySys(((FragmentInitBoardBinding) this.mViewBinding).btnPeSys.getText().toString());
    }

    void m946x695f9fa6(View view) {
        startCopySys(((FragmentInitBoardBinding) this.mViewBinding).btnPoSys.getText().toString());
    }

    public void setHeadSysBtnText(String str) {
        ((FragmentInitBoardBinding) this.mViewBinding).btnHeadSys.setText("开始复制 \n" + str);
    }

    public void setPeSysBtnText(String str) {
        ((FragmentInitBoardBinding) this.mViewBinding).btnPeSys.setText("开始复制 \n" + str);
    }

    public void setPoSysBtnText(String str) {
        ((FragmentInitBoardBinding) this.mViewBinding).btnPoSys.setText("开始复制 \n" + str);
    }

    private void startCopySys(String str) {
        String[] strArrSplit = str.split("-");
        if (strArrSplit.length != 2) {
            ToastHelper.getInstance().showToast("参数错误");
        }
        String strTrim = strArrSplit[1].trim();
        this.cpSn = strTrim;
        if (strTrim.isEmpty()) {
            ToastHelper.getInstance().showToast("参数错误，序列号为空");
            return;
        }
        if (this.isUsaServer) {
            String str2 = this.cpSn;
            str2.hashCode();
            switch (str2) {
                case "2222222222222222":
                case "2222222222222223":
                    startDownloadRes("res/device/3/Camera_Def_poster_e_tc.zip");
                    break;
                case "7777777777777777":
                case "7777777777777778":
                case "7777777777777779":
                case "3333333333333333":
                case "3333333333333334":
                    startDownloadRes("res/device/2/Camera_Def_en.zip");
                    break;
                case "5555555555555555":
                    startDownloadRes("res/device/1/Camera_Def_tce.zip");
                    break;
                case "4444444444444444":
                    startDownloadRes("res/device/4/Camera_Def_vi.zip");
                    break;
                case "8888888888888888":
                    startDownloadRes("res/device/3/Camera_Def_poster_en.zip");
                    break;
            }
        }
        String str3 = this.cpSn;
        str3.hashCode();
        switch (str3.hashCode()) {
            case -2039451136:
                if (!str3.equals("2222222222222222")) {
                }
                break;
            case -1225568000:
                if (!str3.equals("1111111111111111")) {
                }
                break;
            case -1000016384:
                if (!str3.equals("6666666666666666")) {
                }
                break;
            case -411684864:
                if (!str3.equals("0000000000000000")) {
                }
                break;
            case -186133248:
                if (!str3.equals("5555555555555555")) {
                }
                break;
            case -186133247:
                if (!str3.equals("5555555555555556")) {
                }
                break;
            case -186133246:
                if (!str3.equals("5555555555555557")) {
                }
                break;
            case -186133245:
                if (!str3.equals("5555555555555558")) {
                }
                break;
            case 1441633024:
                if (!str3.equals("3333333333333333")) {
                }
                break;
            case 1667184640:
                if (!str3.equals("8888888888888888")) {
                }
                break;
            default:
                break;
        }
        /*  JADX ERROR: Method code generation error
            java.lang.NullPointerException: Switch insn not found in header
            	at java.base/java.util.Objects.requireNonNull(Objects.java:246)
            	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:246)
            	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:88)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
            	at jadx.core.ProcessClass.process(ProcessClass.java:88)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:126)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
            */
        /*
            Method dump skipped, instruction units count: 530
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.main.InitBoardFragment.startCopySys(java.lang.String):void");
    }

    private void startDownloadRes(String str) {
        ((FragmentInitBoardBinding) this.mViewBinding).btnHeadSys.setEnabled(false);
        ((FragmentInitBoardBinding) this.mViewBinding).btnPeSys.setEnabled(false);
        ((FragmentInitBoardBinding) this.mViewBinding).btnPoSys.setEnabled(false);
        ((FragmentInitBoardBinding) this.mViewBinding).tvTitle.setText("注册机子");
        HttpManager.getInstance().mHttpApi.autoRegisterDevice(AppUtil.getCPUSerial(), this.cpSn, Base64.encodeToString(AppConfig.AUTO_KEY.getBytes(), 0)).subscribeOn(Schedulers.m1287io()).subscribe(new C19504(AppConfig.HTTP_HOST + str));
    }

    class C19504 extends ABaseHttpObserver<BaseDto<Object>> {
        final String val$url;

        C19504(String str) {
            this.val$url = str;
        }

        void m1647lambda$onNext$0$comwugudoublecameramainInitBoardFragment$4() {
            ((FragmentInitBoardBinding) InitBoardFragment.this.mViewBinding).tvTitle.setText("注册成功，开始拷贝 " + InitBoardFragment.this.cpSn);
        }

        @Override
        public void onNext(BaseDto<Object> baseDto) {
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1647lambda$onNext$0$comwugudoublecameramainInitBoardFragment$4();
                }
            });
            InitBoardFragment.this.getResZip(this.val$url);
        }
    }

    public void getResZip(String str) {
        final String fileNameFromPath = StringUtil.getFileNameFromPath(str);
        String usbPath = AppUtil.getUsbPath();
        this.downPath = AppUtil.getInternalSDCardPath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileNameFromPath;
        if (usbPath == null || usbPath.isEmpty()) {
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1643lambda$getResZip$6$comwugudoublecameramainInitBoardFragment();
                }
            });
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1644lambda$getResZip$7$comwugudoublecameramainInitBoardFragment();
                }
            });
            return;
        }
        File file = new File(usbPath, fileNameFromPath);
        if (file.exists() && file.isFile()) {
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1645lambda$getResZip$8$comwugudoublecameramainInitBoardFragment(fileNameFromPath);
                }
            });
            FileUtil.copyFile(file.getAbsolutePath(), AppUtil.getInternalSDCardPath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileNameFromPath);
            copyApk();
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1646lambda$getResZip$9$comwugudoublecameramainInitBoardFragment();
                }
            });
            startUnzip();
            return;
        }
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1642lambda$getResZip$10$comwugudoublecameramainInitBoardFragment(fileNameFromPath);
            }
        });
    }

    void m1643lambda$getResZip$6$comwugudoublecameramainInitBoardFragment() {
        ((FragmentInitBoardBinding) this.mViewBinding).tvTitle.append("\n\n错误！！未发现U盘，无法初始化资源");
    }

    void m1644lambda$getResZip$7$comwugudoublecameramainInitBoardFragment() {
        Toast.makeText(requireContext(), "错误！！未发现U盘，无法初始化资源", 1).show();
    }

    void m1645lambda$getResZip$8$comwugudoublecameramainInitBoardFragment(String str) {
        ((FragmentInitBoardBinding) this.mViewBinding).tvTitle.append("\n\n正在从U盘复制文件 " + str);
    }

    void m1646lambda$getResZip$9$comwugudoublecameramainInitBoardFragment() {
        ((FragmentInitBoardBinding) this.mViewBinding).tvTitle.append("\n\n复制完成，开始解压");
    }

    void m1642lambda$getResZip$10$comwugudoublecameramainInitBoardFragment(String str) {
        ((FragmentInitBoardBinding) this.mViewBinding).tvTitle.append("\n\n错误！！U盘无初始化文件 " + str);
    }

    private void startUnzip() {
        FileUtil.unzipFile(this.downPath, AppUtil.getInternalSDCardPath() + "/WgDCamera");
        FileUtil.deleteFileFolder(this.downPath);
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m947x9f45b937();
            }
        });
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m948x58bd46d6();
            }
        }, 1000L);
    }

    void m947x9f45b937() {
        ((FragmentInitBoardBinding) this.mViewBinding).tvTitle.append("\n\n解压完成，跳转主页面");
    }

    void m948x58bd46d6() {
        ((MainActivity) requireActivity()).initBoardOk();
    }

    private void copyApk() {
        String str = this.isUsaServer ? "DCamera_usa_v130.apk" : "DCamera_china_v130.apk";
        final File file = new File(AppUtil.getUsbPath(), str);
        if (file.exists() && file.isFile()) {
            FileUtil.createFolder(AppConfig.APK_DIR);
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1641lambda$copyApk$13$comwugudoublecameramainInitBoardFragment(file);
                }
            });
            FileUtil.copyFile(file.getAbsolutePath(), AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + str);
        }
    }

    void m1641lambda$copyApk$13$comwugudoublecameramainInitBoardFragment(File file) {
        ((FragmentInitBoardBinding) this.mViewBinding).tvTitle.append("\n\n正在从U盘复制apk " + file.getName());
    }
}
