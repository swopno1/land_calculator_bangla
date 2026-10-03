# জমির হিসাব (Land Calculator BD)

**স্মার্ট, দ্রুত ও ১০০% অফলাইন বাংলাদেশি জমির পরিমাপ ও রূপান্তর ক্যালকুলেটর**  
**Developer:** [ViveScript Solutions](https://www.vivescriptsolutions.com/)  
**Application ID:** `com.vivescriptsolutions.jomirhisab`  
**Version:** 1.0.0 (Version Code: 1)

---

## 📌 পরিচিতি (Introduction)

**"জমির হিসাব"** হলো বাংলাদেশের জমি ক্রেতা, বিক্রেতা, সার্ভেয়ার (আমিন), কৃষক ও সাধারণ নাগরিকদের জন্য বিশেষভাবে তৈরি একটি হালকা, নির্ভুল এবং একক-স্ক্রিনবিশিষ্ট অফলাইন ক্যালকুলেটর। 

অ্যাপটির মাধ্যমে যেকোনো এককে (যেমন: শতাংশ, কাঠা, বিঘা, একর, বর্গফুট ইত্যাদি) জমির পরিমাণ লিখে মুহূর্তেই বাকি সকল প্রচলিত এককে লাইভ রূপান্তর দেখা যায়।

### 🌟 প্রধান বৈশিষ্ট্যসমূহ (Key Features)
1. **তাত্ক্ষণিক হিসাব (Live Calculation):** কোনো "হিসাব করুন" বোতাম ছাড়াই টাইপ করার সাথে সাথে রিয়েল-টাইমে ফলাফল প্রদর্শিত হয়।
2. **১০০% অফলাইন (Airplane-Mode Friendly):** কোনো ইন্টারনেট বা সার্ভার সংযোগের প্রয়োজন নেই।
3. **বাংলা ও ইংরেজি সমর্থন:** বাংলা সংখ্যা (১, ২, ৩) এবং ইংরেজি ডিজিট (1, 2, 3) উভয়ই অনায়াসে ইনপুট ও আউটপুট দেওয়া যায়।
4. **এক-ট্যাপে কপি ও শেয়ার:** এক ক্লিকে একক ফলাফল বা সম্পূর্ণ হিসাবের সারাংশ ক্লিপবোর্ডে কপি বা মেসেজিং অ্যাপে শেয়ার করা যায়।
5. **প্রমিত সরকারি পরিমাপ মানদণ্ড:** বাংলাদেশ সরকারের ভূমি জরিপ ও ভূমি সংস্কারে সর্বাধিক স্বীকৃত প্রমিত হিসাব অনুসারী।
6. **কোনো লগইন বা বিজ্ঞাপন নেই:** কোনো অ্যাকাউন্ট খোলার ঝামেলা নেই, শতভাগ বিজ্ঞাপনমুক্ত ও পরিচ্ছন্ন।

---

## 📐 সমর্থিত একক ও রূপান্তর মানদণ্ড (Supported Land Units)

অ্যাপের সকল হিসাব কেন্দ্রীয় রূপান্তর ইঞ্জিন (`LandConversionEngine`) দ্বারা পরিচালিত এবং এর ভিত্তিমূল হলো **বর্গফুট (Square Feet)**:

* **১ শতাংশ / শতক / ডেসিমেল** = ৪৩৫.৬০ বর্গফুট = ৪৮.৪০ বর্গগজ = ৪০.৪৭ বর্গমিটার
* **১ কাঠা** = ৭২০.০০ বর্গফুট = ৮০ বর্গগজ = ১৬ ছটাক ≈ ১.৬৫২৮৯ শতাংশ
* **১ বিঘা (প্রমিত ৩৩ শতক)** = ২০ কাঠা = ১৪,৪০০.০০ বর্গফুট ≈ ৩৩.০৬ শতাংশ
* **১ একর** = ১০০ শতাংশ = ৩.০২৫ বিঘা = ৬০.৫ কাঠা = ৪৩,৫৬০.০০ বর্গফুট
* **১ হেক্টর** = ১০,০০০ বর্গমিটার = ২.৪৭১ একর = ২৪৭.১০ শতাংশ
* **১ ছটাক** = ৪৫.০০ বর্গফুট (১/১৬ কাঠা) = ২০ গণ্ডা
* **১ গণ্ডা** = ২.২৫ বর্গফুট (১/২০ ছটাক) = ৪ কড়া
* **১ বর্গমিটার** = ১০.৭৬৪ বর্গফুট
* **১ বর্গগজ** = ৯.০০ বর্গফুট

*বিস্তারিত জানার জন্য দেখুন:* [`docs/LAND-MEASUREMENT-STANDARD.md`](docs/LAND-MEASUREMENT-STANDARD.md)

---

## 🏗 প্রযুক্তি ও আর্কিটেকচার (Tech Stack)

* **Language:** 100% Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Architecture:** Clean Single-Screen Architecture with Domain-Driven Conversion Engine
* **Testing:** JUnit4 Unit Tests for all conversion vectors
* **Zero Runtime Permissions:** `android.permission.INTERNET` অনুপস্থিত, শতভাগ নিরাপদ ও প্রাইভেসি-বান্ধব।

---

## 🚀 যেভাবে চালাবেন ও বিল্ড করবেন (Build & Run Instructions)

### ১. ইউনিট টেস্ট রান করা:
```bash
gradle :app:testDebugUnitTest
```

### ২. Debug APK তৈরি করা:
```bash
gradle :app:assembleDebug
```
ফাইল অবস্থান: `builds/app-debug.apk`

### ৩. Release APK তৈরি করা:
```bash
gradle :app:assembleRelease
```
ফাইল অবস্থান: `builds/app-release.apk`

### ৪. Google Play Release AAB (Android App Bundle) তৈরি করা:
```bash
gradle :app:bundleRelease
```
ফাইল অবস্থান: `builds/app-release.aab`

---

## 📦 প্রজেক্ট ও ডকুমেন্টেশন স্ট্রাকচার

```
├── app/                              # Android অ্যাপ্লিকেশন সোর্স কোড
│   ├── src/main/java/com/vivescriptsolutions/jomirhisab/
│   │   ├── MainActivity.kt
│   │   ├── engine/LandConversionEngine.kt
│   │   ├── model/LandUnit.kt
│   │   └── ui/                       # Compose UI ও থিম
│   └── src/test/java/                # স্বয়ংক্রিয় ইউনিট টেস্ট
├── builds/                           # প্রস্তুতকৃত APK ও AAB ফাইল
├── docs/                             # পূর্ণাঙ্গ প্রজেক্ট ডকুমেন্টেশন
│   ├── ASO-RESEARCH.md               # গুগল প্লে এসইও ও মেটাডাটা
│   ├── LAND-MEASUREMENT-STANDARD.md  # পরিমাপের গাণিতিক ও সরকারি মানদণ্ড
│   ├── PLAY-STORE-PUBLISHING.md      # প্লে স্টোর পাবলিশিং গাইড
│   └── PRIVACY-DATA-SAFETY.md        # ডেটা সুরক্ষা ও প্রাইভেসি পলিসি
└── store-assets/                     # গুগল প্লে স্টোর গ্রাফিক্স এসেট
    ├── feature-graphic/              # ১০২৪×৫০০ ফিচার গ্রাফিক
    ├── icon/                         # ৫১২×৫১২ হাই-রেজুলেশন আইকন
    └── screenshots/                  # ১০৮০×১৯২০ প্রফেশনাল স্ক্রিনশটসমূহ
```

---

## 🛡️ লিগ্যাল ডিসক্লেইমার (Legal Disclaimer)

এই অ্যাপ্লিকেশনটি সাধারণ হিসাব ও পারস্পরিক রূপান্তরের জন্য তৈরি। জমি ক্রয়-বিক্রয়, রেজিস্ট্রি, নকশা বা আইনগত কাজে ব্যবহারের পূর্বে অবশ্যই সংশ্লিষ্ট সরকারি ভূমি অফিস, মৌজা নকশা বা রেজিস্টার্ড সার্ভেয়ার থেকে পরিমাপ যাচাই করুন।

---

## 🏢 যোগাযোগ ও সাপোর্ট

**ViveScript Solutions**  
ওয়েবসাইট: [https://www.vivescriptsolutions.com/](https://www.vivescriptsolutions.com/)  
ইমেইল: support@vivescriptsolutions.com
