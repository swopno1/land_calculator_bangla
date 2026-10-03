# Google Play Store Publishing Guide

**App Name:** জমির হিসাব (Land Calculator BD)  
**Package Name:** `com.vivescriptsolutions.jomirhisab`  
**Developer / Publisher:** ViveScript Solutions (https://www.vivescriptsolutions.com/)  
**Target Category:** **Tools** (টুলস)

---

## ১. Google Play Console মেটাডাটা

### App Title (সর্বোচ্চ ৩০ অক্ষর):
`জমির হিসাব – Land Calculator`  
*(২৯ অক্ষর - গুগল প্লে নীতি অনুযায়ী সম্পূর্ণ নিরাপদ ও অনুমোদিত)*

### Short Description (সর্বোচ্চ ৮০ অক্ষর):
`শতাংশ, কাঠা, বিঘা ও একরের সহজ ও নির্ভুল হিসাব। ১০০% অফলাইন ও ফ্রি।`  
*(৬৬ অক্ষর)*

### English Short Description:
`Land measurement calculator for BD: Decimal, Katha, Bigha & Acre. Offline.`  
*(৭৩ অক্ষর)*

### Primary Category (ক্যাটেগরি):
- **Category:** Tools (টুলস)  
- **Reasoning:** এটি একটি গাণিতিক ও পরিমাপ রূপান্তর ইউটিলিটি টুল। Productivity-এর চেয়ে Tools ক্যাটাগরি ব্যবহারকারীদের কাছে বেশি প্রাসঙ্গিক ও বিশ্বস্ত।

### Tags:
- Calculator
- Tools
- Utilities
- Education

---

## ২. গ্রাফিক এসেট চেকলিস্ট (Store Assets Checklist)

সবগুলো প্রস্তুতকৃত গ্রাফিক্স প্রজেক্টের `store-assets/` ফোল্ডারে সংরক্ষিত রয়েছে:

| এসেট | বিবরণ ও মাপ | ফাইলের অবস্থান |
| :--- | :--- | :--- |
| **App Icon** | 512 × 512 px, 32-bit PNG | `store-assets/icon/play_store_icon_512.png` |
| **Feature Graphic** | 1024 × 500 px, PNG | `store-assets/feature-graphic/feature_graphic_1024x500.png` |
| **Screenshot 1** | 1080 × 1920 px, মূল ক্যালকুলেটর | `store-assets/screenshots/screenshot_1_calculator.png` |
| **Screenshot 2** | 1080 × 1920 px, রূপান্তর তালিকা | `store-assets/screenshots/screenshot_2_conversion_results.png` |
| **Screenshot 3** | 1080 × 1920 px, অফলাইন সুবিধা | `store-assets/screenshots/screenshot_3_offline_first.png` |
| **Screenshot 4** | 1080 × 1920 px, ভাষা ও সংখ্যা বিকল্প | `store-assets/screenshots/screenshot_4_language_numerals.png` |
| **Screenshot 5** | 1080 × 1920 px, মাপের নিয়ম ও তথ্য | `store-assets/screenshots/screenshot_5_standards_info.png` |

---

## ৩. অ্যাপ রিলিজ বিল্ড (Release Artifacts)

### বিল্ড তৈরি করার কমান্ড:
```bash
# Debug APK তৈরি
gradle :app:assembleDebug

# Release APK তৈরি
gradle :app:assembleRelease

# Production Release Android App Bundle (AAB) তৈরি
gradle :app:bundleRelease
```

### প্রস্তুতকৃত ফাইলের অবস্থান:
- **Production AAB:** `app/build/outputs/bundle/release/app-release.aab`
- **Release APK:** `app/build/outputs/apk/release/app-release-unsigned.apk` (বা signed)
- **Debug APK:** `app/build/outputs/apk/debug/app-debug.apk`

---

## ৪. প্রডাকশন সাইনিং কী (Release Keystore) সেটআপ

Google Play Store-এ আপলোড করার জন্য একটি নিরাপদ আপলোড কি-স্টোর প্রয়োজন:

1. **কী তৈরি করুন (Generate Upload Key):**
   ```bash
   keytool -genkeypair -v -keystore my-upload-key.jks -alias upload -keyalg RSA -keysize 2048 -validity 10000
   ```
2. **এনভায়রনমেন্ট ভেরিয়েবল সেট করুন (বা CI/CD-তে যুক্ত করুন):**
   ```bash
   export KEYSTORE_PATH="/path/to/my-upload-key.jks"
   export STORE_PASSWORD="your-strong-keystore-password"
   export KEY_ALIAS="upload"
   export KEY_PASSWORD="your-strong-key-password"
   ```
   > ⚠️ **নিরাপত্তা সতর্কতা:** কখনোই কি-স্টোর পাসওয়ার্ড বা প্রাইভেট কী পাবলিক গিট রিপোজিটরিতে কমিট করবেন না।

---

## ৫. Play Console-এ ম্যানুয়াল চেকলিস্ট (Human Confirmation Required)

ডেভেলপারকে গুগল প্লে কনসোলে লগইন করে ম্যানুয়ালি নিচের ধাপগুলো নিশ্চিত করতে হবে:
1. **Google Play App Signing:** Play App Signing চালু রাখুন (Google Play সাইনিং কী পরিচালনা করবে)।
2. **App Content ফর্মগুলো পূরণ করুন:**
   - Privacy Policy: `https://www.vivescriptsolutions.com/privacy-jomirhisab`
   - Ads: "No"
   - App Access: "All features are available without restrictions"
   - Content Ratings: Questionnaire পূরণ করুন (Rating: Everyone)
   - Target Audience: 18+ (বা 13+)
   - Data Safety: "Does not collect or share user data"
   - Government Apps: "No"
3. **Internal Test / Production Track:**
   - প্রথমে **Internal testing** ট্র্যাকে `app-release.aab` আপলোড করে রিলিজ টেস্ট করুন।
   - সন্তোষজনক হলে **Production** ট্র্যাকে রোলআউট করুন।
