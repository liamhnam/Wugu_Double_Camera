package ZtlApi;

import com.p020hp.jipp.model.WhichPrinter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CpuInfo {
    public int cpuTemp;
    public int gpuFreq;
    public int gpuMaxFreq;
    public String gpuName;
    public int gpuTemp;
    public int gpu_utilisation;
    public int memFree;
    public int memTotal;
    public float usage;
    public List<SubCore> mCores = new ArrayList();
    DevType devType = DevType.dt_3288_5_1;
    BaseDev curDev = null;
    Eventhandler eventhandler = null;
    boolean bexit = false;

    enum DevType {
        dt_3288_5_1,
        dt_3288_7_1,
        dt_3399,
        dt_3328,
        dt_3128,
        dt_3368,
        dt_A33,
        dt_A64,
        dt_3568
    }

    public interface Eventhandler {
        void OnInitOK(CpuInfo cpuInfo);

        void OnUpdate();
    }

    public static class SubCore {
        public int cur_freq;
        public String governor;
        public int max_freq;
        DevType mdevType;
        public int min_freq;
        public String name;
        public Object tag;

        public String formatFreq() {
            int i = this.cur_freq / 1000;
            return (i == 0 && (this.mdevType == DevType.dt_A33 || this.mdevType == DevType.dt_A64)) ? "休眠" : String.format("%d/(%d-%d)Mhz", Integer.valueOf(i), Integer.valueOf(this.min_freq / 1000), Integer.valueOf(this.max_freq / 1000));
        }

        public void Init(DevType devType) {
            this.mdevType = devType;
            String onelinevalue = CpuInfo.getOnelinevalue(String.format("/sys/devices/system/cpu/%s/cpufreq/cpuinfo_max_freq", this.name));
            if (onelinevalue != null) {
                this.max_freq = Integer.valueOf(onelinevalue).intValue();
            } else {
                this.max_freq = 0;
            }
            String onelinevalue2 = CpuInfo.getOnelinevalue(String.format("/sys/devices/system/cpu/%s/cpufreq/cpuinfo_min_freq", this.name));
            if (onelinevalue2 != null) {
                this.min_freq = Integer.valueOf(onelinevalue2).intValue();
            } else {
                this.min_freq = 0;
            }
            if (devType == DevType.dt_A33 || devType == DevType.dt_A64) {
                ZtlManager.GetInstance().execRootCmdSilent("chmod 777 " + String.format("/sys/devices/system/cpu/%s/cpufreq/scaling_governor", this.name));
            }
            this.governor = CpuInfo.getOnelinevalue(String.format("/sys/devices/system/cpu/%s/cpufreq/scaling_governor", this.name));
        }

        public void Speedup() {
            ZtlManager.GetInstance().execRootCmdSilent(String.format("echo \"performance\" > /sys/devices/system/cpu/%s/cpufreq/scaling_governor", this.name));
        }

        public static String[] getFreq() {
            try {
                return CpuInfo.getOnelinevalue("/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies").split(" ");
            } catch (Exception e) {
                e.printStackTrace();
                return new String[0];
            }
        }

        public void SetFreq(String str) {
            ZtlManager.GetInstance().execRootCmdSilent(String.format("echo \"userspace\" > /sys/devices/system/cpu/%s/cpufreq/scaling_governor", this.name));
            ZtlManager.GetInstance().execRootCmdSilent(String.format("echo %s > /sys/devices/system/cpu/%s/cpufreq/scaling_setspeed", str, this.name));
        }

        public void Restore() {
            ZtlManager.GetInstance().execRootCmdSilent(String.format("echo \"interactive\" > /sys/devices/system/cpu/%s/cpufreq/scaling_governor", this.name));
        }

        public static boolean isExist(String str) {
            return new File(str).exists();
        }

        public void Update(boolean z) {
            String onelinevalue;
            if (isExist(String.format("/sys/devices/system/cpu/%s/cpufreq/scaling_cur_freq", this.name)) && (onelinevalue = CpuInfo.getOnelinevalue(String.format("/sys/devices/system/cpu/%s/cpufreq/scaling_cur_freq", this.name))) != null) {
                this.cur_freq = Integer.valueOf(onelinevalue).intValue();
            } else {
                this.cur_freq = 0;
            }
            if (z) {
                this.governor = CpuInfo.getOnelinevalue(String.format("/sys/devices/system/cpu/%s/cpufreq/scaling_governor", this.name));
            }
        }
    }

    abstract class BaseDev {
        public String gpu_base_path = "";
        public String temp_base_path = "";

        abstract int getCPUTemp();

        abstract int getGPUCurfreq();

        abstract int getGPUMaxFreq();

        abstract String getGPUName();

        abstract int getGPUTemp();

        abstract int getGPUUtilisation();

        abstract void speedUp();

        BaseDev() {
        }
    }

    class BD3288_51 extends BaseDev {
        int gpuFreq0;

        @Override
        void speedUp() {
        }

        BD3288_51() {
            super();
            this.gpuFreq0 = 0;
            this.gpu_base_path = "/sys/bus/platform/devices/ffa30000.gpu/";
            this.temp_base_path = "/sys/bus/platform/drivers/tsadc/ff280000.tsadc/";
        }

        @Override
        String getGPUName() {
            return CpuInfo.getOnelinevalue(this.gpu_base_path + "gpuinfo");
        }

        @Override
        int getGPUMaxFreq() {
            List<String> lines = CpuInfo.getLines(this.gpu_base_path + "clock", 2);
            if (lines != null && lines.size() >= 2) {
                String[] strArrSplit = lines.get(1).split(",");
                String str = strArrSplit[strArrSplit.length - 1];
                if (str.contains("KHz")) {
                    return Integer.valueOf(str.replace("possible_freqs :", "").trim().split(" ")[0]).intValue();
                }
            }
            return 0;
        }

        @Override
        int getCPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.temp_base_path + "temp1_input");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue();
            }
            return -1;
        }

        @Override
        int getGPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.temp_base_path + "temp2_input");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue();
            }
            return -1;
        }

        @Override
        int getGPUUtilisation() {
            List<String> lines = CpuInfo.getLines(this.gpu_base_path + "dvfs", 3);
            if (lines.size() >= 3) {
                String str = lines.get(1);
                iIntValue = str.contains("gpu_utilisation") ? Integer.valueOf(str.replace("gpu_utilisation :", "").trim()).intValue() : 0;
                String str2 = lines.get(2);
                if (str2.contains("current_gpu_clk_freq")) {
                    this.gpuFreq0 = Integer.valueOf(str2.replace("current_gpu_clk_freq : ", "").replace("MHz", "").trim()).intValue();
                }
            } else if (lines.size() >= 2) {
                String str3 = lines.get(1);
                if (str3.contains("current_gpu_clk_freq")) {
                    this.gpuFreq0 = Integer.valueOf(str3.replace("current_gpu_clk_freq : ", "").replace("MHz", "").trim()).intValue();
                }
            }
            return iIntValue;
        }

        @Override
        int getGPUCurfreq() {
            return this.gpuFreq0;
        }
    }

    class BD3288_71 extends BaseDev {
        int gpuFreq0;

        @Override
        void speedUp() {
        }

        BD3288_71() {
            super();
            this.gpuFreq0 = 0;
            this.gpu_base_path = "/sys/bus/platform/devices/ffa30000.gpu/";
            this.temp_base_path = "/sys/bus/platform/drivers/tsadc/ff280000.tsadc/";
        }

        @Override
        String getGPUName() {
            return CpuInfo.getOnelinevalue(this.gpu_base_path + "gpuinfo");
        }

        @Override
        int getGPUMaxFreq() {
            return (int) (Long.valueOf(CpuInfo.getOnelinevalue(this.gpu_base_path + "devfreq/ffa30000.gpu/available_frequencies").split(" ")[r0.length - 1]).longValue() / 1000);
        }

        @Override
        public int getCPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue("/sys/class/thermal/thermal_zone0/temp");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue() / 1000;
            }
            return -1;
        }

        @Override
        int getGPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue("/sys/class/thermal/thermal_zone1/temp");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue() / 1000;
            }
            return -1;
        }

        @Override
        int getGPUUtilisation() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.gpu_base_path + "utilisation");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue();
            }
            return -1;
        }

        @Override
        int getGPUCurfreq() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.gpu_base_path + "devfreq/ffa30000.gpu/cur_freq");
            if (onelinevalue != null) {
                return (int) (Long.valueOf(onelinevalue).longValue() / 1000000);
            }
            return -1;
        }
    }

    class BD3399 extends BaseDev {
        @Override
        int getCPUTemp() {
            return -1;
        }

        @Override
        int getGPUTemp() {
            return -1;
        }

        BD3399() {
            super();
            this.gpu_base_path = "/sys/bus/platform/devices/ff9a0000.gpu/";
            this.temp_base_path = "/sys/class/thermal/thermal_zone0/temp/";
        }

        @Override
        void speedUp() {
            ZtlManager.GetInstance().execRootCmdSilent(String.format("echo \"performance\" > /sys/bus/platform/devices/ff9a0000.gpu/devfreq/ff9a0000.gpu/governor", new Object[0]));
        }

        @Override
        String getGPUName() {
            return CpuInfo.getOnelinevalue(this.gpu_base_path + "gpuinfo");
        }

        @Override
        int getGPUMaxFreq() {
            return (int) (Long.valueOf(CpuInfo.getOnelinevalue(this.gpu_base_path + "devfreq/ff9a0000.gpu/available_frequencies").split(" ")[r0.length - 1]).longValue() / 1000);
        }

        @Override
        int getGPUUtilisation() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.gpu_base_path + "utilisation");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue();
            }
            return -1;
        }

        @Override
        int getGPUCurfreq() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.gpu_base_path + "devfreq/ff9a0000.gpu/cur_freq");
            if (onelinevalue != null) {
                return (int) (Long.valueOf(onelinevalue).longValue() / 1000000);
            }
            return -1;
        }
    }

    class BD3328 extends BD3399 {
        @Override
        String getGPUName() {
            return "";
        }

        @Override
        int getGPUTemp() {
            return -1;
        }

        @Override
        void speedUp() {
        }

        BD3328() {
            super();
            this.gpu_base_path = "/sys/bus/platform/devices/ff300000.gpu/";
            this.temp_base_path = "/sys/class/thermal/thermal_zone0/temp/";
        }

        @Override
        public int getCPUTemp() {
            return super.getCPUTemp() / 1000;
        }

        @Override
        int getGPUUtilisation() {
            return (super.getGPUUtilisation() / 256) * 100;
        }

        @Override
        int getGPUMaxFreq() {
            return (int) (Long.valueOf(CpuInfo.getLines(this.gpu_base_path + "available_frequencies", 10).get(r0.size() - 1)).longValue() / 1000);
        }

        @Override
        int getGPUCurfreq() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.gpu_base_path + "clock");
            if (onelinevalue != null) {
                return (int) (Long.valueOf(onelinevalue).longValue() / 1000000);
            }
            return -1;
        }
    }

    class BD3128 extends BD3328 {
        @Override
        public int getCPUTemp() {
            return -1;
        }

        @Override
        String getGPUName() {
            return "Mali-400MP2";
        }

        @Override
        int getGPUTemp() {
            return -1;
        }

        @Override
        void speedUp() {
        }

        BD3128() {
            super();
            this.gpu_base_path = "/sys/bus/platform/devices/10091000.gpu/";
        }

        @Override
        int getGPUMaxFreq() {
            ZtlManager.GetInstance().execRootCmdSilent("chmod 777 " + this.gpu_base_path + "available_frequencies");
            ZtlManager.GetInstance().execRootCmdSilent("chmod 777 " + this.gpu_base_path + "clock");
            return (int) (Long.valueOf(CpuInfo.getLines(this.gpu_base_path + "available_frequencies", 10).get(r0.size() - 1)).longValue() / 1000);
        }
    }

    class BD3368 extends BaseDev {
        @Override
        String getGPUName() {
            return "PowerVR G6110";
        }

        @Override
        void speedUp() {
        }

        BD3368() {
            super();
            this.gpu_base_path = "/sys/devices/platform/ffa30000.rogue-g6110/devfreq/ffa30000.rogue-g6110/";
        }

        @Override
        public int getCPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue("/sys/class/thermal/thermal_zone0/temp");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue() / 1000;
            }
            return -1;
        }

        @Override
        int getGPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue("/sys/class/thermal/thermal_zone1/temp");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue() / 1000;
            }
            return -1;
        }

        @Override
        int getGPUMaxFreq() {
            return (int) (Long.valueOf(CpuInfo.getOnelinevalue(this.gpu_base_path + "available_frequencies").split(" ")[r0.length - 1]).longValue() / 1000);
        }

        @Override
        int getGPUUtilisation() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.gpu_base_path + "load");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue.substring(0, onelinevalue.indexOf(64))).intValue();
            }
            return -1;
        }

        @Override
        int getGPUCurfreq() {
            String onelinevalue = CpuInfo.getOnelinevalue(this.gpu_base_path + "cur_freq");
            if (onelinevalue != null) {
                return (int) (Long.valueOf(onelinevalue).longValue() / 1000000);
            }
            return -1;
        }
    }

    class BDA33 extends BaseDev {
        @Override
        public int getCPUTemp() {
            return -1;
        }

        @Override
        int getGPUCurfreq() {
            return -1;
        }

        @Override
        String getGPUName() {
            return "Mali-400 MP2";
        }

        @Override
        int getGPUTemp() {
            return -1;
        }

        @Override
        int getGPUUtilisation() {
            return -1;
        }

        @Override
        void speedUp() {
        }

        BDA33() {
            super();
        }

        @Override
        int getGPUMaxFreq() {
            String onelinevalue = CpuInfo.getOnelinevalue("/sys/kernel/debug/clk/hosc/pll_gpu/gpu/clk_rate");
            if (onelinevalue != null) {
                return (int) (Long.valueOf(onelinevalue).longValue() / 1000);
            }
            return -1;
        }
    }

    class BDA64 extends BaseDev {
        @Override
        int getGPUCurfreq() {
            return -1;
        }

        @Override
        String getGPUName() {
            return "Mali-400 MP2";
        }

        @Override
        int getGPUUtilisation() {
            return -1;
        }

        @Override
        void speedUp() {
        }

        BDA64() {
            super();
        }

        @Override
        public int getCPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue("/sys/class/thermal/thermal_zone0/temp");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue();
            }
            return -1;
        }

        @Override
        int getGPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue("/sys/bus/platform/drivers/mali-utgard/1c40000.gpu/dvfs/tempctrl");
            String strTrim = onelinevalue.substring(onelinevalue.indexOf("temperature:")).replace("temperature: ", "").trim();
            if (strTrim != null) {
                return Integer.valueOf(strTrim).intValue();
            }
            return -1;
        }

        @Override
        int getGPUMaxFreq() {
            String strTrim = CpuInfo.getOnelinevalue("/sys/bus/platform/drivers/mali-utgard/1c40000.gpu/dvfs/android").replace("MHz", "").trim();
            if (strTrim != null) {
                return (int) (Long.valueOf(strTrim).longValue() * 1000);
            }
            return -1;
        }
    }

    class BD3568 extends BaseDev {
        @Override
        int getGPUCurfreq() {
            return -1;
        }

        @Override
        String getGPUName() {
            return "Mali-G52";
        }

        @Override
        int getGPUTemp() {
            return -1;
        }

        @Override
        int getGPUUtilisation() {
            return -1;
        }

        @Override
        void speedUp() {
        }

        BD3568() {
            super();
        }

        @Override
        public int getCPUTemp() {
            String onelinevalue = CpuInfo.getOnelinevalue("/sys/class/thermal/thermal_zone0/temp");
            if (onelinevalue != null) {
                return Integer.valueOf(onelinevalue).intValue();
            }
            return -1;
        }

        @Override
        int getGPUMaxFreq() {
            String strTrim = CpuInfo.getOnelinevalue("/sys/bus/platform/drivers/mali/fde60000.gpu/devfreq/fde60000.gpu/max_freq").replace("MHz", "").trim();
            if (strTrim != null) {
                return (int) (Long.valueOf(strTrim).longValue() * 1000);
            }
            return -1;
        }
    }

    public void setCPUFreq(String str) {
        for (int i = 0; i < this.mCores.size(); i++) {
            this.mCores.get(i).SetFreq(str);
        }
    }

    public void Init(Eventhandler eventhandler) {
        this.eventhandler = eventhandler;
        String[] cpuCount = getCpuCount();
        ZtlManager.GetInstance();
        String deviceVersion = ZtlManager.getDeviceVersion();
        if (deviceVersion.contains("3399")) {
            this.devType = DevType.dt_3399;
            this.curDev = new BD3399();
        } else if (deviceVersion.contains("3288")) {
            ZtlManager.GetInstance();
            String androidVersion = ZtlManager.getAndroidVersion();
            if (androidVersion.contains("5.1")) {
                this.devType = DevType.dt_3288_5_1;
                this.curDev = new BD3288_51();
            } else if (androidVersion.contains("7.1")) {
                this.devType = DevType.dt_3288_7_1;
                this.curDev = new BD3288_71();
            }
        } else if (deviceVersion.contains("3328")) {
            this.devType = DevType.dt_3328;
            this.curDev = new BD3328();
        } else if (deviceVersion.contains("3126c") || deviceVersion.contains("3128")) {
            this.devType = DevType.dt_3128;
            this.curDev = new BD3128();
        } else if (deviceVersion.contains("3368")) {
            this.devType = DevType.dt_3368;
            this.curDev = new BD3368();
        } else if (deviceVersion.contains("A33")) {
            this.devType = DevType.dt_A33;
            this.curDev = new BDA33();
        } else if (deviceVersion.contains("A64")) {
            this.devType = DevType.dt_A64;
            this.curDev = new BDA64();
        } else if (deviceVersion.contains("3568") || deviceVersion.contains("3566")) {
            this.devType = DevType.dt_3568;
            this.curDev = new BD3568();
        }
        if (this.curDev == null) {
            this.curDev = new BD3288_51();
        }
        for (int i = 0; i < cpuCount.length; i++) {
            SubCore subCore = new SubCore();
            subCore.name = cpuCount[i];
            subCore.Init(this.devType);
            if ((this.devType == DevType.dt_A33 || this.devType == DevType.dt_A64) && i >= 1) {
                subCore.min_freq = this.mCores.get(0).min_freq;
                subCore.max_freq = this.mCores.get(0).max_freq;
                subCore.governor = this.mCores.get(0).governor;
            }
            this.mCores.add(subCore);
        }
        this.gpuName = this.curDev.getGPUName();
        this.gpuMaxFreq = this.curDev.getGPUMaxFreq();
        String onelinevalue = getOnelinevalue("/proc/meminfo");
        if (onelinevalue.contains("MemTotal")) {
            this.memTotal = Integer.valueOf(onelinevalue.replace("MemTotal:", "").trim().split(" ")[0]).intValue();
        }
        Eventhandler eventhandler2 = this.eventhandler;
        if (eventhandler2 != null) {
            eventhandler2.OnInitOK(this);
        }
    }

    public void SpeedUp() {
        for (int i = 0; i < this.mCores.size(); i++) {
            this.mCores.get(i).Speedup();
        }
        BaseDev baseDev = this.curDev;
        if (baseDev != null) {
            baseDev.speedUp();
        }
    }

    public void Restore() {
        for (int i = 0; i < this.mCores.size(); i++) {
            this.mCores.get(i).Restore();
        }
    }

    public void close() {
        this.bexit = true;
    }

    float getRate() {
        Map<String, String> cPUUsageSnap = getCPUUsageSnap();
        long j = Long.parseLong(cPUUsageSnap.get("user")) + Long.parseLong(cPUUsageSnap.get("nice")) + Long.parseLong(cPUUsageSnap.get("system")) + Long.parseLong(cPUUsageSnap.get(WhichPrinter.idle)) + Long.parseLong(cPUUsageSnap.get("iowait")) + Long.parseLong(cPUUsageSnap.get("irq")) + Long.parseLong(cPUUsageSnap.get("softirq"));
        long j2 = Long.parseLong(cPUUsageSnap.get(WhichPrinter.idle));
        try {
            Thread.sleep(360L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> cPUUsageSnap2 = getCPUUsageSnap();
        long j3 = ((((((Long.parseLong(cPUUsageSnap2.get("user")) + Long.parseLong(cPUUsageSnap2.get("nice"))) + Long.parseLong(cPUUsageSnap2.get("system"))) + Long.parseLong(cPUUsageSnap2.get(WhichPrinter.idle))) + Long.parseLong(cPUUsageSnap2.get("iowait"))) + Long.parseLong(cPUUsageSnap2.get("irq"))) + Long.parseLong(cPUUsageSnap2.get("softirq"))) - j;
        return ((j3 - (Long.parseLong(cPUUsageSnap2.get(WhichPrinter.idle)) - j2)) * 100) / j3;
    }

    static Map<String, String> getCPUUsageSnap() {
        String[] strArrSplit;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")));
            String line = bufferedReader.readLine();
            bufferedReader.close();
            strArrSplit = line.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
            strArrSplit = null;
        }
        HashMap map = new HashMap();
        map.put("user", strArrSplit[2]);
        map.put("nice", strArrSplit[3]);
        map.put("system", strArrSplit[4]);
        map.put(WhichPrinter.idle, strArrSplit[5]);
        map.put("iowait", strArrSplit[6]);
        map.put("irq", strArrSplit[7]);
        map.put("softirq", strArrSplit[8]);
        return map;
    }

    public static String[] getCpuCount() {
        char cCharAt;
        ArrayList arrayList = new ArrayList();
        for (File file : new File("/sys/devices/system/cpu").listFiles()) {
            if (file.getName().contains("cpu") && (cCharAt = file.getName().charAt(3)) >= '0' && cCharAt <= '9') {
                arrayList.add(file.getName());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    static String getOnelinevalue(String str) {
        List<String> lines = getLines(str, 1);
        if (lines != null) {
            return lines.get(0);
        }
        return null;
    }

    static List<String> getLines(String str, int i) {
        ArrayList arrayList = new ArrayList();
        try {
            FileReader fileReader = new FileReader(str);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i2 = 0;
            while (i2 < i) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                i2++;
                arrayList.add(line);
            }
            fileReader.close();
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static String getSpecail(String str, String str2) {
        try {
            FileReader fileReader = new FileReader(str);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains(str2)) {
                    return line;
                }
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
            }
            fileReader.close();
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
