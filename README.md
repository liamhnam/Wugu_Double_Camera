# DoubleCamera - Kiosk Photo Booth Ecosystem

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Room](https://img.shields.io/badge/Room-Database-blue?style=for-the-badge)
![Retrofit](https://img.shields.io/badge/Retrofit-Network-green?style=for-the-badge)
![MQTT](https://img.shields.io/badge/MQTT-IoT-orange?style=for-the-badge)

**DoubleCamera** là một giải pháp phần mềm toàn diện (End-to-End) dành cho thiết bị **Kiosk Chụp ảnh tự động (Photo Booth / Purikura)** chạy hệ điều hành Android. Dự án không chỉ là một ứng dụng Android thông thường, mà còn là một hệ thống Kiosk chuyên dụng kết nối trực tiếp với nhiều loại thiết bị phần cứng công nghiệp thông qua cổng USB và Serial (UART), kết hợp với hệ thống IoT để giám sát và điều khiển từ xa.

---

## 🌟 Chức năng cốt lõi (Core Features)

### 1. Tương tác & Trải nghiệm Người dùng (UI/UX Workflow)
Toàn bộ quy trình từ lúc khách hàng bước vào đến khi nhận được ảnh in được tối ưu hóa thành các bước (`Fragment`) mượt mà:
- **Màn hình chờ (Idle/Home):** Phát video hoặc hình ảnh quảng cáo khi không có khách sử dụng.
- **Thanh toán đa luồng:** Chấp nhận thanh toán bằng tiền xu, tiền mặt, quét mã QR nội bộ và quẹt thẻ từ.
- **Chụp ảnh đếm ngược:** Trải nghiệm chụp nhiều lần, cho phép người dùng chọn lại những tấm ảnh ưng ý nhất.
- **Cá nhân hóa ảnh (Photo Editing Studio):**
  - Cắt ghép ảnh vào các Layout/Khung (Frames) cấu hình trước.
  - Chọn và dán nhãn dán (Stickers) lên ảnh.
  - Vẽ và ký tên trực tiếp lên ảnh bằng màn hình cảm ứng (Signature).
- **In ảnh tự động:** Đưa ảnh qua quá trình xử lý (Render) và đẩy luồng in trực tiếp xuống máy in công nghiệp.

### 2. Xử lý Ảnh bằng Trí tuệ Nhân tạo (AI & Image Processing)
- **Face Beauty:** Tích hợp bộ SDK làm đẹp khuôn mặt (Faceunity), cho phép làm mịn da, chỉnh tông màu.
- **AI Background Replacement:** Tự động tách nền và cho phép khách hàng thay phông nền đằng sau theo các chủ đề có sẵn (Sử dụng AI Models được tải động từ máy chủ).
- **Bộ lọc (Filters):** Áp dụng các bộ lọc màu thời gian thực trên màn hình preview và ảnh đầu ra.
- **FFmpeg Processing:** Xử lý các luồng media, đóng gói hoặc ghép âm thanh, khung ảnh một cách linh hoạt dưới nền.

### 3. Tích hợp Phần cứng chuyên sâu (Hardware Integrations)
Ứng dụng giao tiếp sâu vào tầng thấp của hệ điều hành Android (Linux Kernel) để điều khiển thiết bị:
- **Máy in ảnh chuyên dụng (Dye-Sublimation Printers):** Tích hợp SDK gốc (Native SDK) và Driver USB của nhiều hãng lớn nhằm đảm bảo chất lượng và tốc độ in cao nhất:
  - Máy in **HP** (Gói `com.p020hp...`)
  - Máy in **Epson** (Gói `com.epson.isv...`)
  - Máy in **Brother** (Gói `com.brother...`)
  - Máy in **Hiti** (Gói `com.hiti...`)
- **Thiết bị giao dịch (Coin & Bill Acceptors / Card Readers):**
  - Đọc/Ghi dữ liệu qua các cổng Serial Port: `/dev/ttyXRUSB0` đến `/dev/ttyXRUSB3`.
  - Nhận diện máy quét mã vạch (Barcode Scanner).
- **Board mạch Kiosk:** Hỗ trợ điều khiển GPIO trên các Board Android công nghiệp thông qua `ZtlApi` (Ví dụ: Board RK3399, RK3568).

### 4. Quản trị IoT & Giám sát Hệ thống (Remote Management)
- **MQTT Telemetry:** Kết nối qua giao thức MQTT liên tục 24/7 tới server để:
  - Báo cáo nhịp tim (Heartbeat) của Kiosk.
  - Báo cáo lỗi phần cứng: kẹt giấy, hết mực in, mất kết nối thiết bị thanh toán, lỗi máy ảnh.
  - Nhận lệnh điều khiển từ xa: Reboot máy, Update phiên bản mới.
- **Over-The-Air (OTA) Updates:** Tự động tải xuống file APK phiên bản mới (`OkDownload`) và tiến hành nâng cấp ngầm. Cập nhật động các tài nguyên như Khung ảnh, Nhạc nền, Stickers mới theo cấu hình từ CMS.

---

## 🏗 Cấu trúc Mã nguồn (Project Architecture)

Dự án được viết chủ yếu bằng **Java** và một phần **Kotlin**, được tổ chức theo kiến trúc module chặt chẽ:

```text
app/src/main/java/com/wugu/doublecamera/
│
├── main/              # (View/Controller) UI cốt lõi của Photo Booth. Chứa các Activity/Fragment xử lý quy trình.
│   ├── MainActivity.java        # Container chính chứa các Fragment.
│   ├── HomeFragment.java        # Màn hình chờ.
│   ├── PaymentFragment.java     # Xử lý quy trình nạp tiền.
│   ├── PhotoFragment.java       # Chụp ảnh (Standard mode).
│   ├── CertifyPhotoFragment.java# Chụp ảnh thẻ.
│   ├── ChooseFrameFragment.java # Xử lý chọn khung viền.
│   ├── ReplaceBgFragment.java   # Xử lý thay nền AI.
│   └── PrintingFragment.java    # Luồng in ấn và trạng thái tiến độ in.
│
├── device/            # (Hardware Abstraction) Tương tác phần cứng.
│   ├── CashHelper.java          # Nhận diện/Đếm tiền giấy qua Serial.
│   ├── CoinHelper.java          # Nhận diện/Đếm tiền xu qua Serial.
│   ├── ScannerHelper.java       # Điều khiển đầu đọc mã QR/Barcode.
│   └── PrinterHelper.java       # Factory gọi đến các SDK Máy in (HP, Epson, Brother, Hiti).
│
├── network/           # (Networking & IoT) Liên lạc với Máy chủ trung tâm.
│   ├── HttpManager.java         # Quản lý request RESTful API.
│   ├── IHttpApi.java            # Khai báo các endpoints (Upload ảnh, check thanh toán).
│   └── MqttHelper.java          # Quản lý vòng đời kết nối MQTT, nhận gửi tín hiệu (Pub/Sub).
│
├── service/           # (Background Tasks) Các tiến trình ngầm (Workers).
│   ├── MainService.java         # Service duy trì sống còn cùng Kiosk.
│   ├── AiModelDownUtil.java     # Trình tải mô hình AI động.
│   └── PictureIcDownUtil.java   # Tải các nội dung đa phương tiện.
│
├── database/          # (Local Storage) Cơ sở dữ liệu và Preferences.
│   ├── DbHelper.java            # Setup Room Database (lịch sử giao dịch).
│   └── SpManager.java           # Trình quản lý cấu hình cục bộ Kiosk.
│
├── bean/              # (Models) Chứa các Data Classes / POJO.
├── adapter/           # (UI Adapters) Chứa các Adapter cho RecyclerView.
├── utils/             # (Utilities) Các hàm hỗ trợ dùng chung (Files, Bitmaps, Time).
└── AppConfig.java     # Cấu hình hằng số (File paths, MQTT Host, API base URL).
```

---

## 🛠 Công nghệ & Thư viện sử dụng (Tech Stack)

### Môi trường phát triển:
- **Android SDK:** Hỗ trợ từ API Level 25 (Android 7.1) đến API 33 (Android 13).
- **Biên dịch:** Gradle.

### Thư viện mã nguồn mở:
- **Networking:** 
  - `Retrofit2` & `OkHttp3` cho HTTP/HTTPS REST APIs.
  - `Eclipse Paho` cho kết nối MQTT.
- **Database:** `AndroidX Room Database` cho việc lưu trữ an toàn ngay trên thiết bị mà không cần mạng.
- **Download Management:** `com.liulishuo.okdownload` để quản lý các tiến trình tải file lớn có tính năng resume (mô hình AI, video, APK).
- **Hardware/Serial Port:** Thư viện `proembed` và các thư viện điều khiển cổng Serial Linux.
- **Xử lý Đa phương tiện:**
  - `FFmpeg` xử lý video overlay và audio.
  - SDK Máy in của bên thứ ba (Vendor-specific libs).

---

## 🚀 Hướng dẫn Cài đặt & Build (Setup Instructions)

Vì đây là ứng dụng Kiosk chạy trên phần cứng đặc thù, vui lòng đọc kỹ yêu cầu trước khi build.

### 1. Yêu cầu Thiết bị (Hardware Requirements)
- **Board Android:** Ưu tiên sử dụng các thiết bị board công nghiệp (Industrial Board) sử dụng chip Rockchip (như RK3399, RK3568, v.v.).
- **OS:** Firmware Android hỗ trợ **Root access** (Su) hoặc được cấp phép mở cổng Serial (`/dev/tty*`).
- **Ngoại vi:** Kết nối màn hình cảm ứng qua cáp eDP/HDMI, cắm Webcam/Camera chuẩn USB (UVC), kết nối máy in ảnh qua cáp USB Type-A.

### 2. Môi trường Build (Development Environment)
- Tải và cài đặt [Android Studio](https://developer.android.com/studio).
- Clone dự án bằng lệnh:
  ```bash
  git clone <repository_url>
  ```
- Mở thư mục gốc dự án trên Android Studio.
- Đợi Gradle Sync tất cả các thư viện cần thiết.

### 3. Cấu hình Máy chủ (Server Configuration)
- Mở file `app/src/main/java/com/wugu/doublecamera/AppConfig.java`.
- Tùy chỉnh các thông số server của bạn:
  ```java
  public static String HTTP_HOST = "http://api.yourdomain.com/"; // API URL
  public static String MQTT_HOST = "tcp://mqtt.yourdomain.com:1883"; // Cấu hình broker
  ```

### 4. Build và Chạy (Run & Deploy)
- Cắm dây USB Debug (hoặc ADB over Wi-Fi) kết nối máy tính với Board Android Kiosk.
- Cấp quyền truy cập Serial Port (nếu cần thiết qua ADB root terminal):
  ```bash
  adb shell
  su
  chmod 777 /dev/ttyXRUSB*
  ```
- Nhấn **Run (Shift + F10)** trên Android Studio. Ứng dụng sẽ biên dịch và cài đặt lên thiết bị đích. Kiosk sẽ tự động khởi động đầy đủ giao diện.

---

## 🔒 Giấy phép & Điều khoản (License)
Dự án này là tài sản phần mềm nhúng thương mại thuộc quyền sở hữu của nhóm phát triển (Wugu).
Mọi hành vi sử dụng, sao chép, chỉnh sửa hoặc phân phối lại vì mục đích thương mại mà không có sự cho phép bằng văn bản đều bị nghiêm cấm.
